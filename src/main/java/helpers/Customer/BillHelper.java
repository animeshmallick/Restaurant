package helpers.Customer;

import controller.DatabaseConnection;
import helpers.BaseHelper;
import lombok.NonNull;
import model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;

public class BillHelper extends BaseHelper {
    private final Connection connection;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public BillHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
    }

    public void displayBill() {
        ArrayList<Order> allOrders = getLiveOrder(connection, request, response, getTableIDfromCookie(request));

    }
}
