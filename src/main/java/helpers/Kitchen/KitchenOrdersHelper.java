package helpers.Kitchen;

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
        log.info("Kitchen Order Helper object is generated.");
    }

    public void viewAllOrders() {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            log.info("Finding live orders for Kitchen.");
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.SELECT_LIVE_ORDERS_FOR_KITCHEN());
            while(resultSet.next()) {
                int tableID = resultSet.getInt(2);

                int index = findExistingOrderFromTables(tables,tableID);
                if(index == -1) {
                    ArrayList<Order> orders = new ArrayList<>();
                    orders.add(
                            new Order(
                                    resultSet.getInt(3),
                                    resultSet.getInt(4),
                                    resultSet.getString(5),
                                    resultSet.getInt(1)
                            ));
                    tables.add(new Table(
                            tableID,
                            getTableNumberFromTableID(connection, request, response, tableID),
                            null,
                            null,
                            orders,
                            getMenu(connection, request, response),
                            "kitchen"
                            ));
                } else {
                    Table table = tables.get(index);
                    table.getOrders().add(new Order(
                            resultSet.getInt(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getInt(1)
                    ));
                    tables.remove(index);
                    tables.add(table);
                }
            }
            sortOrderList(tables);
            request.setAttribute("tables", tables);
            redirectTo(request, response, "Kitchen/KitchenOrders");
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
    }
    private void sortOrderList(ArrayList<Table> tables) {
        tables.sort(Comparator.comparingInt(Table::getTableNumber));
    }

    private int findExistingOrderFromTables(ArrayList<Table> tables, int tableID) {
        for(int i=0;i<tables.size();i++)
            if(tables.get(i).getTableID() == tableID)
                return i;
        return -1;
    }
}
