package servlets;

import controller.DatabaseConnection;
import helpers.MenuHelper;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@Log4j
@WebServlet("/menu")
public class Menu extends HttpServlet {
    Connection connection;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        connection = new DatabaseConnection().initialiseDatabase(request, response);
        new MenuHelper(connection, request, response)
                .showMenu();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
