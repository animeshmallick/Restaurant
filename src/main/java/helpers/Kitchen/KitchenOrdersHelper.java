package helpers.Kitchen;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

@Log4j
public class KitchenOrdersHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public KitchenOrdersHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
    }

    public void viewAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_ORDERS_FOR_KITCHEN());
            while(resultSet.next()) {
                orders.add(new Order(
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(1))
                ));
            }
            sortOrderList(orders);
            request.setAttribute("orders", orders);
            request.setAttribute("menu", getMenu(connection, request, response));
            redirectTo(request, response, "Kitchen/KitchenOrders");
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
    private void sortOrderList(ArrayList<Order> orders) {
        orders.sort(Comparator.comparingInt(Order::getProductID));
    }
}
