package servlets;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.CustomerBookingHelper;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/CustomerBooking")
public class CustomerBooking extends HttpServlet {

    private final String nameIdentifier = "name";
    private final String phoneNumberIdentifier = "phoneNumber";
    private final String guestsIdentifier = "guests";
    private Connection connection = null;
    private PreparedStatement statement = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(nameIdentifier);
        String phoneNumber = request.getParameter(phoneNumberIdentifier);
        String guests = request.getParameter(guestsIdentifier);
        Customer customer = new Customer(name, phoneNumber, guests);

        try {
            connection = DatabaseConnection.initialiseDatabase();
            CustomerBookingHelper helper = new CustomerBookingHelper(connection);

            statement = connection.prepareStatement(SQLQueries.getREGISTER_CUSTOMER());
            statement.setString(1, customer.getPHONE_NUMBER());
            statement.setString(2, customer.getNAME());
            statement.setString(3, "NA");
            statement.setString(4, customer.getGUESTS());
            statement.setString(5, "booked");
            statement.setString(6, helper.generateUniqueOTP());
            statement.executeUpdate();
            statement.close();

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html");
        try {
            response.getWriter().println("<h1>Booking Confirmed. !!</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
