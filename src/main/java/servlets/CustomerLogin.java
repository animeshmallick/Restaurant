package servlets;

import controller.DatabaseConnection;
import helpers.CustomerLoginHelper;
import lombok.NonNull;
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
    private final String tableIdIdentifier = "tableID";

    @Override
    /**
     * doPost method for CustomerLogin servlet
     * @param request Servlet Request
     * @param response Servlet Response
     */
    protected void doPost(@NonNull HttpServletRequest request,
                          @NonNull HttpServletResponse response) {
        connection = new DatabaseConnection()
                .initialiseDatabase(request, response);
        new CustomerLoginHelper(connection, request, response)
                .validateTableID()
                .addCookie()
                .updateCustomerEntry()
                .goToMenu();
    }
    /**
     * doGet method for CustomerLogin servlet
     * @param request Servlet Request
     * @param response Servlet Response
     */
    @Override
    protected void doGet(@NonNull HttpServletRequest request,
                         @NonNull HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            int tableID = -1;
            if (cookies == null) {
                log.info("Cookies not found.\nRedirecting to Login Page.");
                response.sendRedirect("/RestaurantServer/customerLogin.jsp");
            } else {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals(tableIdIdentifier)) {
                        tableID = Integer.parseInt(cookie.getValue());
                    }
                }
            }

            if(tableID == -1) {
                log.info("TableID Cookie not found.\nRedirecting to Login Page.");
                response.sendRedirect("/RestaurantServer/customerLogin.jsp");
            }else {
                log.info("TableID cookie detected.\nTable ID : " + tableID);
                new CustomerLoginHelper(connection, request, response)
                        .goToMenu();
            }
        }catch(Exception ex) {
            log.info("Something went wrong.\nRedirecting to Login Page.");
        }
    }
}
