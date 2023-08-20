package helpers.Waiter;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.Order;
import model.Table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j
public class TableHelper extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Table table;

    public TableHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Table Helper object is created.");
        this.table = getTable();
    }
    private Table getTable() {
        log.info("Creating/Generating Table model object");
        String tableNumber = request.getParameter("tableNumber");
        int tableID = getTableIDFromTableNumber(connection, request, response, Integer.parseInt(tableNumber));
        ArrayList<Order> tableOrder = getTableOrder(tableID);
        String tableStatus = getTableStatus(tableOrder);
        return new Table(tableID,
                Integer.parseInt(tableNumber),
                null,
                tableOrder,
                getMenu(connection, request, response),
                tableStatus);
    }

    public void displayTable() {
        request.setAttribute("table", this.table);
        redirectTo(request, response, "Waiter/displayTable");
    }

    private String getTableStatus(ArrayList<Order> tableOrders) {
        log.info("Finding table Status from orderList from table.");
        for(Order order : tableOrders) {
            if(order.getStatus().equalsIgnoreCase("placed"))
                return "Waiting to Confirm";
            if(order.getStatus().equalsIgnoreCase("prepared"))
                return "Waiting to Deliver";
        }
        return "No Action Required";
    }

    private ArrayList<Order> getTableOrder(int tableID) {
        log.info("Finding table orders from live table.");
        ArrayList<Order> tableOrders = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.GET_ORDERS_FROM_LIVE_TABLE(Integer.toString(tableID)));
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
