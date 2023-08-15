package servlets;

import controller.DatabaseConnection;
import helpers.CartHelper;
import lombok.NonNull;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private Connection connection;

    /**
     * doGet method for Cart servlet
     * @param request Servlet Request
     * @param response Servlet Response
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request,
                         @NonNull HttpServletResponse response) {
        connection = new DatabaseConnection().initialiseDatabase(request, response);
        new CartHelper(connection, request, response)
                .displayCart();
    }
}
