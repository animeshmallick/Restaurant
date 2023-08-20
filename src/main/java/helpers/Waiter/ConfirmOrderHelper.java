package helpers.Waiter;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j
public class ConfirmOrderHelper <T extends ConfirmOrderHelper> extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ConfirmOrderHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Confirm Order Helper object is created.");
    }
    public void confirmOrder() {
        String orderID = request.getParameter("orderID");
        try {
            connection.createStatement().executeUpdate(SQLQueries.CONFIRM_ORDER(orderID));
            log.info("Confirmed order for OrderID " + orderID + " and redirected to Table Servlet");
            response.sendRedirect("/RestaurantServer/Waiter/Table?tableNumber=" + request.getParameter("tableNumber"));
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
