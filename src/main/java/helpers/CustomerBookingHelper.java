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

    public CustomerBookingHelper(Connection connection, HttpServletRequest request, HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;

        String name = request.getParameter(nameIdentifier);
        int phone = Integer.parseInt(request.getParameter(phoneNumberIdentifier));
        int guest = Integer.parseInt(request.getParameter(guestsIdentifier));

        customer = new Customer(name, phone, guest);
        log.info("Customer Booking Helper Object is Generated");
    }

    public T confirmBooking() {
        if(!isDuplicateBooking()) {
            log.info("Customer details are valid for Booking.");
            try {
                PreparedStatement statement = connection.prepareStatement(SQLQueries.REGISTER_CUSTOMER());
                statement.setString(1, Integer.toString(customer.getPHONE_NUMBER()));
                statement.setString(2, customer.getNAME());
                statement.setString(3, "NA");
                statement.setString(4, Integer.toString(customer.getGUESTS()));
                statement.setString(5, "booked");
                statement.setString(6, generateUniqueTableID());
                log.debug("Executing : " + statement);
                statement.executeUpdate();
                log.info("Booking Details are saved in database.");
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

            List<String> existingTableIDList = new ArrayList<String>();
            newTableID = String.valueOf((int) (100000 + 100000 * Math.random()));

            while (resultSet.next())
                existingTableIDList.add(resultSet.getString(1));

            while (existingTableIDList.contains(newTableID)) {
                newTableID = String.valueOf((int) (100000 * Math.random()));
            }
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

            List<String> existingCustomerIdList = new ArrayList<String>();
            while(resultSet.next())
                existingCustomerIdList.add(resultSet.getString(1));

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
