package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Order Entity
 */
@AllArgsConstructor
@Getter
public class Order {
    private int productID;
    private int quantity;
    private String status;
}
