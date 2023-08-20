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
public class DeliverOrderHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public DeliverOrderHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Deliver Order Helper is created.");
    }

    public void deliverOrder() {
        String orderID = request.getParameter("orderID");
        try {
            connection.createStatement().executeUpdate(SQLQueries.DELIVER_ORDER(orderID));
            log.info("Delivered order for OrderID " + orderID + " and redirected to Table Servlet");
            response.sendRedirect("/RestaurantServer/Waiter/Table?tableNumber="+request.getParameter("tableNumber"));
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
