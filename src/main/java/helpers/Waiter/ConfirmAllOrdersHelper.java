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
public class ConfirmAllOrdersHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ConfirmAllOrdersHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Confirm All Order Object is created.");
    }
    public void confirmAllOrders() {
        String tableNumber = request.getParameter("tableNumber");
        String tableID = request.getParameter("tableID");
        log.info("Confirming all orders for Table ID : " + tableID);
        try {
            connection.createStatement().executeUpdate(SQLQueries.CONFIRM_ALL_ORDERS(tableID));
            log.info("Redirecting to Table servlet with table ID " + tableID);
            response.sendRedirect("/RestaurantServer/Waiter/Table?tableNumber=" + tableNumber);
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
