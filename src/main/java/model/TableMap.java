package model;

import helpers.BaseHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class TableMap {
    private Map<String, Table> tableMap;

    public TableMap() {
        tableMap = new HashMap<>();
    }

    public void addOrder(String tableNumber, Order order, String tableID, MenuWrapper menuWrapper) {
        if(tableMap.containsKey(tableNumber)) {
            Table table = tableMap.get(tableNumber);
            table.getOrders().add(order);
            tableMap.put(tableNumber, table);
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(order);
            Table table = new Table(Integer.parseInt(tableID),
                   Integer.parseInt(tableNumber),
                   orders,
                   menuWrapper,
                   "Order Placed");
            tableMap.put(tableNumber, table);
        }
    }

    public void addTable(String tableNumber, Table table) { tableMap.put(tableNumber, table); }
}
