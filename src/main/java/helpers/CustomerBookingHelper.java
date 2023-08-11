package helpers;

import data.SQLQueries;
import lombok.extern.log4j.Log4j;
import model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j
public class CustomerBookingHelper <T extends CustomerBookingHelper> extends BaseHelper {
    Connection connection;
    HttpServletRequest request;
    HttpServletResponse response;
    Customer customer;
    private final String nameIdentifier = "name";
    private final String phoneNumberIdentifier = "phone";
    private final String guestsIdentifier = "guest";
    private final int MAX_GUEST_ALLOWED = 6;
    private final String defaultValueForBookingStatus = "booked";
    private final String defaultValueForBookingComment = "NA";

    public CustomerBookingHelper(Connection connection, HttpServletRequest request, HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;

        String name = request.getParameter(nameIdentifier);
        long phone = Long.parseLong(request.getParameter(phoneNumberIdentifier));
        int guest = Integer.parseInt(request.getParameter(guestsIdentifier));

        customer = new Customer(name, phone, guest);
        log.info("Customer Booking Helper Object is Generated.");
    }

    public T confirmBooking() {
        if(!isDuplicateBooking()) {
            log.info("Initiating addition of Customer Details to database.");
            try {
                PreparedStatement statement = connection.prepareStatement(SQLQueries.REGISTER_CUSTOMER());
                statement.setString(1, Long.toString(customer.getPHONE_NUMBER()));
                statement.setString(2, customer.getNAME());
                statement.setString(3, defaultValueForBookingComment);
                statement.setString(4, Integer.toString(customer.getGUESTS()));
                statement.setString(5, defaultValueForBookingStatus);
                statement.setString(6, generateUniqueTableID());
                log.debug("Executing : " + statement);
                statement.executeUpdate();
                log.info("Customer Details are saved in database.");
                statement.close();
                connection.close();
                redirectTo(request, response,"bookingConfirmation");
            } catch (SQLException e) {
                log.info("SQL Exception while saving customer data to database, e");
                redirectToErrorPage(request, response, e);
            }
        } else {
            log.debug("Something went wrong. !!\nRedirecting to Static Error Page.");
            redirectToStaticErrorPage(request, response);
        }
        return (T) this;
    }

    private String generateUniqueTableID() {
        String newTableID = "";
        try {
            log.info("Executing SQL Query : " + SQLQueries.getSELECT_TABLE_ID());
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.getSELECT_TABLE_ID());

            List<String> existingTableIDList = new ArrayList<>();
            newTableID = String.format("%06d", new Random().nextInt(999999));

            while (resultSet.next())
                existingTableIDList.add(resultSet.getString(1));

            while (existingTableIDList.contains(newTableID))
                newTableID = String.format("%06d", new Random().nextInt(999999));

            log.info("Customer Booking Table ID generated successfully. TableID : " + newTableID);
        } catch(SQLException ex) {
            log.debug("SQL Exception found while generating new Table ID on Customer Booking", ex);
            redirectToErrorPage(request, response, ex);
        }
        return newTableID;
    }

    private boolean isDuplicateBooking() {
        log.info("Validating Duplicate Booking.");
        try {
            log.info("Executing SQL Query : " + SQLQueries.getSELECT_CUSTOMER_ID());
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.getSELECT_CUSTOMER_ID());

            List<Long> existingCustomerIdList = new ArrayList<>();
            while(resultSet.next())
                existingCustomerIdList.add(Long.parseLong(resultSet.getString(1)));

            if(existingCustomerIdList.contains(customer.getPHONE_NUMBER())) {
                log.debug("Duplicate Booking found in database.");
                redirectToErrorPage(request, response, "Duplicate Booking Found");
                return true;
            }
            log.info("No existing booking found with the given phone number.");
            return false;
        } catch(Exception ex){
            log.debug("Exception occurred while validating duplicate booking.", ex);
            redirectToErrorPage(request, response, ex);
            return true;
        }
    }
}
