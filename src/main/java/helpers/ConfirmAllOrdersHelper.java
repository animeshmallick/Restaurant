package helpers;

import controller.DatabaseConnection;
import data.SQLQueries;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConfirmAllOrdersHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ConfirmAllOrdersHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
    }
    public void confirmAllOrders() {
        String tableNumber = request.getParameter("tableNumber");
        String tableID = request.getParameter("tableID");
        try {
            connection.createStatement().executeUpdate(SQLQueries.CONFIRM_ALL_ORDERS(tableID));
            response.sendRedirect("/RestaurantServer/Table?tableNumber=" + tableNumber);
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
