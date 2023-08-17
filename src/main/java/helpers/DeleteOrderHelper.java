package helpers;

import controller.DatabaseConnection;
import data.SQLQueries;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteOrderHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public DeleteOrderHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
    }
    public void deleteOrder() {
        String orderID = request.getParameter("orderID");
        try {
            connection.createStatement().executeUpdate(SQLQueries.DELETE_ORDER(orderID));
            response.sendRedirect("/RestaurantServer/Table?tableNumber=" + request.getParameter("tableNumber"));
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
