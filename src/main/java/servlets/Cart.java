package servlets;

import controller.DatabaseConnection;
import helpers.CartHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private Connection connection;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        connection = new DatabaseConnection().initialiseDatabase(request, response);
        new CartHelper(connection, request, response)
                .displayCart();
    }
}
