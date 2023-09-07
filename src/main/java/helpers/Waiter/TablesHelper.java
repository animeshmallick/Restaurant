package helpers.Waiter;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.MenuWrapper;
import model.Order;
import model.Table;
import model.TableMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Log4j
public class TablesHelper <T extends TablesHelper> extends BaseHelper {
    Connection connection;
    HttpServletRequest request;
    HttpServletResponse response;
    TableMap tableMap;
    MenuWrapper menuWrapper;

    public TablesHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        this.menuWrapper = getMenu(connection, request, response);
        log.info("Tables Helper object is created.");
    }
    public T getAllTables() {
        log.info("Getting Live table for all active table.");
        this.tableMap = getLiveTables();
        return (T) this;
    }
    public T formatTableStatus() {
        log.info("Formatting / Generating table status for all active tables.");
        TableMap tempTableMap = this.tableMap;
        for(Map.Entry<Integer, Table> tableEntry : tempTableMap.getTableMap().entrySet()){
            int tableNumber = tableEntry.getKey();
            Table table = tableEntry.getValue();
            ArrayList<Order> orders = table.getOrders();
            String status = "No Action Required";
            for(Order order : orders) {
                if (order.getStatus().equals("placed")) {
                    status = "Waiting to Confirm";
                    break;
                }
                if(order.getStatus().equalsIgnoreCase("prepared")) {
                    status = "Waiting to Deliver";
                    break;
                }
            }
            table.setStatus(status);
            this.tableMap.addTable(tableNumber, table);
        }
        return (T) this;
    }

    public void displayAllTables() {
        request.setAttribute("tables", this.tableMap);
        redirectTo(request, response, "Waiter/tables");
    }

    private Map<Integer, Integer> getTableMap() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_TABLES());
            Map<Integer, Integer> tableMap = new HashMap<>();
            while(resultSet.next()) {
                log.info("table found");
                int tableNumber = resultSet.getInt(1);
                int tableID = resultSet.getInt(2);
                tableMap.put(tableID, tableNumber);
            }
            return tableMap;
        } catch(SQLException ex) {
            log.info(ex.getMessage());
            redirectToErrorPage(request, response, ex);
            return null;
        }
    }

    private TableMap getLiveTables() {
        try {
            Map<Integer, Integer> tableIDMap = getTableMap();
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_ORDERS());
            TableMap tableMap = new TableMap();

            while(resultSet.next()) {
                log.info("order found");
                String orderID = resultSet.getString(1);
                int tableID = resultSet.getInt(2);
                String itemID = resultSet.getString(3);
                String itemQuantity = resultSet.getString(4);
                String itemOrderStatus = resultSet.getString(5);
                if (tableIDMap != null && tableIDMap.containsKey(tableID)) {
                    int tableNumber = tableIDMap.get(tableID);
                    Order order = new Order(Integer.parseInt(itemID),
                            Integer.parseInt(itemQuantity),
                            itemOrderStatus,
                            Integer.parseInt(orderID));
                    tableMap.addOrder(tableNumber, order, tableID, menuWrapper,
                            getCustomer(connection, request, response, tableID));
                }
            }
            return tableMap;
        } catch(SQLException ex) {
            log.info(ex.getMessage());
            redirectToErrorPage(request, response, ex);
            return null;
        }
    }
}
