package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class MenuWrapper {
    private ArrayList<Product> menu;
    public Product getProductFromMenu(int productID) {
        for(Product product : menu) {
            if(product.getProductID() == productID)
                return product;
        }
        return null;
    }
}
