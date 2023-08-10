package servlets;

import controller.DatabaseConnection;
import helpers.CustomerBookingHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/CustomerBooking")
public class CustomerBooking extends HttpServlet {
    private Connection connection = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        connection = DatabaseConnection.initialiseDatabase(request, response);
        new CustomerBookingHelper(connection, request, response)
                .confirmBooking();
    }
}
