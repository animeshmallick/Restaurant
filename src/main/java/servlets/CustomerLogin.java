package servlets;

import controller.DatabaseConnection;
import helpers.CustomerLoginHelper;
import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;


@Log4j
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
    private Connection connection = null;
    private String tableIdIdentifier = "tableID";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        connection = new DatabaseConnection()
                .initialiseDatabase(request, response);
        new CustomerLoginHelper(connection, request, response)
                .validateTableID()
                .addCookie()
                .updateCustomerEntry()
                .displayMenu();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            int tableID = -1;
            if (cookies == null) {
                log.info("Cookies not found.\nRedirecting to Login Page.");
                request.getRequestDispatcher("/customerLogin.jsp").forward(request, response);
            } else {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals(tableIdIdentifier)) {
                        tableID = Integer.parseInt(cookie.getValue());
                    }
                }
            }

            if(tableID == -1) {
                log.info("TableID Cookie not found.\nRedirecting to Login Page.");
                request.getRequestDispatcher("/customerLogin.jsp").forward(request, response);
            }else {
                log.info("TableID cookie detected.\nTable ID : " + tableID);
                new CustomerLoginHelper(connection, request, response)
                        .displayMenu();
            }
        }catch(Exception ex) {
            log.info("Something went wrong.\nRedirecting to Login Page.");
        }
    }
}
