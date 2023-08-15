package servlets;

import controller.DatabaseConnection;
import helpers.OrdersHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/Orders")
public class Orders extends HttpServlet {
    private Connection connection;
    /**
     * doGet method for Orders servlet
     * @param request Servlet Request
     * @param response Servlet Response
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request,
                         @NonNull HttpServletResponse response) {
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);

        new OrdersHelper(connection, request, response)
                .placeOrder();
    }
}
