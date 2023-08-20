package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Table {
    private int tableID;
    private int tableNumber;
    private ArrayList<Order> cart;
    private ArrayList<Order> orders;
    private MenuWrapper menuWrapper;
    @Setter
    private String status;
}
