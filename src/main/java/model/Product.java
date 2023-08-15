package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Product Entity
 */
@Getter
@AllArgsConstructor
public class Product {
    private int ProductID;
    private String ProductName;
    private String ProductDescription;
    private String ProductType;
    private String ProductCategory;
    private double ProductPrice;
    private double ProductRating;
}
