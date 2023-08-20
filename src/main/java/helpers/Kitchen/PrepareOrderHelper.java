package helpers.Kitchen;

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
public class PrepareOrderHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public PrepareOrderHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Order Prepare Helper object is generated.");
    }

    public void prepareOrder() {
        String orderID = request.getParameter("orderID");
        try {
            log.info("Food Preparation Started for Order ID : " + orderID);
            connection.createStatement().executeUpdate(SQLQueries.PREPARE_ORDER(orderID));
            log.info("Redirecting to KitchenOrders servlet.");
            response.sendRedirect("/RestaurantServer/Kitchen/KitchenOrders");
        } catch (SQLException | IOException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
}
