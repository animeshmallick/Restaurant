package servlets;

import controller.DatabaseConnection;
import helpers.MenuHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@Log4j
@WebServlet("/Menu")
public class Menu extends HttpServlet {
    Connection connection;
    /**
     * doGet method for Menu servlet
     * @param request Servlet Request
     * @param response Servlet Response
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request,
                         @NonNull HttpServletResponse response) {
        connection = new DatabaseConnection().initialiseDatabase(request, response);
        new MenuHelper(connection, request, response)
                .showMenu();
    }
    @Override
    protected void doPost(@NonNull HttpServletRequest request,
                          @NonNull HttpServletResponse response) {
        //TODO: doPost method for Menu servlet
        doGet(request, response);
    }
}
