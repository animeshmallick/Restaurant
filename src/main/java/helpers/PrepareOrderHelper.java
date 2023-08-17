package helpers;

import controller.DatabaseConnection;
import data.SQLQueries;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PrepareOrderHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public PrepareOrderHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
    }

    public void prepareOrder() {
        String orderID = request.getParameter("orderID");
        try {
            connection.createStatement().executeUpdate(SQLQueries.PREPARE_ORDER(orderID));
            response.sendRedirect("/RestaurantServer/KitchenOrders");
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
