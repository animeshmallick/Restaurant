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
public class CustomerBookingHelper <T extends CustomerBookingHelper> {
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
        String phone = request.getParameter(phoneNumberIdentifier);
        String guest = request.getParameter(guestsIdentifier);

        customer = new Customer(name, phone, guest);
        log.info("Customer Booking Helper Object is Generated");
    }

    public T confirmBooking() {
        if(!isDuplicateBooking()) {
            log.info("Customer details are valid for Booking.");
            try {
                PreparedStatement statement = connection.prepareStatement(SQLQueries.getREGISTER_CUSTOMER());
                statement.setString(1, customer.getPHONE_NUMBER());
                statement.setString(2, customer.getNAME());
                statement.setString(3, "NA");
                statement.setString(4, customer.getGUESTS());
                statement.setString(5, "booked");
                statement.setString(6, generateUniqueOTP());
                log.debug("Executing : " + statement);
                statement.executeUpdate();
                log.info("Booking Details are saved in database.");
                statement.close();
                connection.close();
                CommonHelper.redirectTo(request, response,"bookingConfirmation");
            } catch (SQLException e) {
                log.info("SQL Exception while saving customer data to database, e");
                CommonHelper.redirectToErrorPage(request, response, e);
            }
        } else {
            CommonHelper.redirectToStaticErrorPage(request, response);
        }
        return (T) this;
    }

    private String generateUniqueOTP() {
        String newOtp = "";
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.getGET_EXISTING_OTPs());

            List<String> existingOtpList = new ArrayList<String>();
            newOtp = String.valueOf((int) (100000 + 100000 * Math.random()));

            while (resultSet.next())
                existingOtpList.add(resultSet.getString(1));

            while (existingOtpList.contains(newOtp)) {
                newOtp = String.valueOf((int) (100000 * Math.random()));
            }
            log.info("Customer Booking OTP generated successfully. OTP : " + newOtp);
        } catch(SQLException ex) {
            log.debug("SQL Exception found while generating new OTP on Customer Booking", ex);
            CommonHelper.redirectToErrorPage(request, response, ex);
        }
        return newOtp;
    }

    private boolean isDuplicateBooking() {
        log.info("Validating Duplicate Booking.");
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.getGET_EXISTING_BOOKING());

            List<String> existingCustomerIdList = new ArrayList<String>();
            while(resultSet.next())
                existingCustomerIdList.add(resultSet.getString(1));

            if(existingCustomerIdList.contains(customer.getPHONE_NUMBER())) {
                log.debug("Duplicate Booking found in database.");
                CommonHelper.redirectToErrorPage(request, response, "Duplicate Booking Found");
                return true;
            }
            log.info("No existing booking found with the given phone number.");
            return false;
        } catch(Exception ex){
            log.debug("Exception occurred while validating duplicate booking.", ex);
            CommonHelper.redirectToErrorPage(request, response, ex);
            return true;
        }
    }
}
