package model;

import helpers.BaseHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class TableMap extends BaseHelper{
    private Map<Integer, Table> tableMap;

    public TableMap() {
        tableMap = new HashMap<>();
    }

    public void addOrder(int tableNumber, Order order, int tableID, MenuWrapper menuWrapper, Customer customer) {
        if(tableMap.containsKey(tableNumber)) {
            Table table = tableMap.get(tableNumber);
            table.getOrders().add(order);
            tableMap.put(tableNumber, table);
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(order);
            Table table = new Table(
                    tableID,
                   tableNumber,
                   customer,
                   null,
                    orders,
                   menuWrapper,
                   "Order Placed");
            tableMap.put(tableNumber, table);
        }
    }

    public void addTable(int tableNumber, Table table) { tableMap.put(tableNumber, table); }
}
