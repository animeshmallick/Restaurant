package helpers;

import controller.DatabaseConnection;
import data.SQLQueries;
import lombok.NonNull;
import model.Order;
import model.Table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Table table;

    public TableHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        this.table = getTable();
    }
    private Table getTable() {
        String tableNumber = request.getParameter("tableNumber");
        String tableID = getTableID(tableNumber);
        ArrayList<Order> tableOrder = getTableOrder(tableID);
        String tableStatus = getTableStatus(tableOrder);
        return new Table(Integer.parseInt(tableID),
                Integer.parseInt(tableNumber),
                tableOrder,
                getMenu(connection, request, response),
                tableStatus);
    }

    public void displayTable() {
        request.setAttribute("table", this.table);
        redirectTo(request, response, "Waiter/displayTable");
    }

    private String getTableID(String tableNumber) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.GET_TABLE_ID(tableNumber));
            if(resultSet.next())
                return resultSet.getString(1);
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
        return null;
    }

    private String getTableStatus(ArrayList<Order> tableOrders) {
        for(Order order : tableOrders) {
            if(order.getStatus().equalsIgnoreCase("placed"))
                return "Waiting to Confirm";
            if(order.getStatus().equalsIgnoreCase("prepared"))
                return "Waiting to Deliver";
        }
        return "No Action Required";
    }

    private ArrayList<Order> getTableOrder(String tableID) {
        ArrayList<Order> tableOrders = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.GET_ORDERS_FROM_LIVE_TABLE(tableID));
            while(resultSet.next()) {
                tableOrders.add(new Order(
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4)),
                        resultSet.getString(5),
                        Integer.parseInt(resultSet.getString(1))
                ));
            }
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
        return tableOrders;
    }
}
