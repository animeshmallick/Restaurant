package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

/**
 * MenuWrapper Entity
 */
@AllArgsConstructor
@Getter
public class MenuWrapper {
    private ArrayList<Product> menu;

    /**
     * Get Product from Menu
     * @return Product
     */
    public Product getProductFromMenu(int productID) {
        for(Product product : menu) {
            if(product.getProductID() == productID)
                return product;
        }
        return null;
    }
}
