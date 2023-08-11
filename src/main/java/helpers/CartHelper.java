package helpers;

import data.SQLQueries;
import lombok.extern.log4j.Log4j;
import model.MenuWrapper;
import model.Order;
import model.Product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class CartHelper<T extends CartHelper> extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String orderCookieNameIdentifier = "order";
    private final String menuProductIDIdentifier = "ProductID";
    private final String menuProductNameIdentifier = "ProductName";
    private final String menuProductDescriptionIdentifier = "ProductDesc";
    private final String menuProductTypeIdentifier = "ProductType";
    private final String menuProductCategoryIdentifier = "ProductCategory";
    private final String menuProductPriceIdentifier = "ProductPrice";
    private final String menuProductRatingIdentifier = "ProductRating";

    public CartHelper(Connection connection, HttpServletRequest request, HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;
        log.info("CartHelper Object Generated.");
    }
    public T displayCart() {
        log.info("Reading Menu from CartHelper.");
        request.setAttribute("menu", getMenu());

        log.info("Reading Order from Cookies.");
        request.setAttribute("orders", getOrderList());

        log.info("Generating Cart.");
        redirectTo(request, response, "cart");

        return (T) this;
    }

    private String getOrderStringFromCookie() {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(orderCookieNameIdentifier)) {
                log.info(cookie.getValue());
                return cookie.getValue();
            }
        }
        return null;
    }
    public ArrayList<Order> getOrderList() {
        ArrayList<Order> orderList = new ArrayList<>();
        String str= getOrderStringFromCookie();
        if(str != null) {
            String[] orderArrayString = getOrderStringFromCookie().split("AND");
            ArrayList<String> orderListString = new ArrayList<>();
            for (String order : orderArrayString) {
                if (order != null && !order.isEmpty())
                    orderListString.add(order);
            }
            for (String order : orderListString) {
                String[] temp = order.trim().split("Q");
                int productID = Integer.parseInt(temp[0]);
                int quantity = Integer.parseInt(temp[1]);
                orderList.add(new Order(productID, quantity));
            }
        }
        return orderList;
    }

    private MenuWrapper getMenu() {
        ArrayList<Product> menu = new ArrayList<>();
        try {
            log.info("Executing SQL Query : " + SQLQueries.getSELECT_MENU());
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.getSELECT_MENU());

            while (resultSet.next()) {
                Product product = new Product(
                        Integer.parseInt(resultSet.getString(menuProductIDIdentifier)),
                        resultSet.getString(menuProductNameIdentifier),
                        resultSet.getString(menuProductDescriptionIdentifier),
                        resultSet.getString(menuProductTypeIdentifier),
                        resultSet.getString(menuProductCategoryIdentifier),
                        Double.parseDouble(resultSet.getString(menuProductPriceIdentifier)),
                        Integer.parseInt(resultSet.getString(menuProductRatingIdentifier)));
                log.info("Fetching Product ID : " + product.getProductID());
                menu.add(product);
            }
            request.setAttribute("menu", menu);
            redirectTo(request, response, "menu");
        } catch (SQLException ex) {
            log.info("Failed to fetch MENU from the menu table in DB.");
            redirectToErrorPage(request, response, ex);
        }
        return new MenuWrapper(menu);
    }
}
