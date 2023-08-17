package helpers;

import controller.DatabaseConnection;
import data.SQLQueries;
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
    }
    public T getAllTables() {
        this.tableMap = getLiveTables();
        return (T) this;
    }
    public T formatTableStatus() {
        TableMap tempTableMap = this.tableMap;
        for(Map.Entry<String, Table> tableEntry : tempTableMap.getTableMap().entrySet()){
            String tableNumber = tableEntry.getKey();
            Table table = tableEntry.getValue();
            ArrayList<Order> orders = table.getOrders();
            String status = "No Action Required";
            for(Order order : orders) {
                if(order.getStatus().equals("placed")) {
                    status = "Action Required";
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

    private Map<String, String> getTableMap() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_TABLES());
            Map<String, String> tableMap = new HashMap<>();
            while(resultSet.next()) {
                log.info("table found");
                String tableNumber = resultSet.getString(1);
                String tableID = resultSet.getString(2);
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
            Map<String, String> tableIDMap = getTableMap();
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_ORDERS());
            TableMap tableMap = new TableMap();

            while(resultSet.next()) {
                log.info("order found");
                String orderID = resultSet.getString(1);
                String tableID = resultSet.getString(2);
                String itemID = resultSet.getString(3);
                String itemQuantity = resultSet.getString(4);
                String itemOrderStatus = resultSet.getString(5);
                if (tableIDMap != null && tableIDMap.containsKey(tableID)) {
                    String tableNumber = tableIDMap.get(tableID);
                    Order order = new Order(Integer.parseInt(itemID),
                            Integer.parseInt(itemQuantity),
                            itemOrderStatus,
                            Integer.parseInt(orderID));
                    tableMap.addOrder(tableNumber, order, tableID, menuWrapper);
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
