package servlets;

import controller.DatabaseConnection;
import helpers.CustomerBookingHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@Log4j
@WebServlet("/CustomerBooking")
public class CustomerBooking extends HttpServlet {
    private Connection connection = null;

    @Override
    protected void doGet(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        //Add method to check if the get call is made without params
        connection = new DatabaseConnection()
                .initialiseDatabase(request, response);
        new CustomerBookingHelper(connection, request, response)
                .confirmBooking();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("POST request made to CustomerBooking Servlet.");
        doGet(request, response);
    }
}
