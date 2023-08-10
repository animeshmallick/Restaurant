package helpers;

import data.SQLQueries;
import lombok.extern.log4j.Log4j;
import model.Product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class MenuHelper <T extends MenuHelper> extends BaseHelper {
    private final Connection connection;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final String menuProductIDIdentifier = "ProductID";
    private final String menuProductNameIdentifier = "ProductName";
    private final String menuProductDescriptionIdentifier = "ProductDesc";
    private final String menuProductTypeIdentifier = "ProductType";
    private final String menuProductCategoryIdentifier = "ProductCategory";
    private final String menuProductPriceIdentifier = "ProductPrice";
    private final String menuProductRatingIdentifier = "ProductRating";
    private String tableIdIdentifier = "tableID";

    public MenuHelper(Connection connection, HttpServletRequest request, HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response =  response;
        log.info("Menu Helper Object Created.");
    }

    public T showMenu() {
        if(isValidTable()) {
            try {
                log.info("Executing SQL Query : " + SQLQueries.getSELECT_MENU());
                ResultSet resultSet = connection.createStatement()
                        .executeQuery(SQLQueries.getSELECT_MENU());
                List<Product> productList = new ArrayList<Product>();

                while (resultSet.next()) {
                    Product product = new Product(
                            Integer.parseInt(resultSet.getString(menuProductIDIdentifier)),
                            resultSet.getString(menuProductNameIdentifier),
                            resultSet.getString(menuProductDescriptionIdentifier),
                            resultSet.getString(menuProductTypeIdentifier),
                            resultSet.getString(menuProductCategoryIdentifier),
                            Double.parseDouble(resultSet.getString(menuProductPriceIdentifier)),
                            Integer.parseInt(resultSet.getString(menuProductRatingIdentifier)));
                    log.info("Product ID : " + product.getProductID() + " fetched.");
                    productList.add(product);
                }
                request.setAttribute("menu", productList);
                redirectTo(request, response, "menu");
            } catch (SQLException ex) {
                log.info("Failed to fetch MENU from the menu table in DB.");
                redirectToErrorPage(request, response, ex);
            }
        }
        return (T) this;
    }

    private boolean isValidTable() {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(tableIdIdentifier))
                return true;
        }
        return false;
    }
}
