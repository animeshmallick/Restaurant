package helpers;

import data.SQLQueries;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

@Log4j
public class BaseHelper {
    private String path = "/%s.jsp";
    private final String menuProductIDIdentifier = "ProductID";
    private final String menuProductNameIdentifier = "ProductName";
    private final String menuProductDescriptionIdentifier = "ProductDesc";
    private final String menuProductTypeIdentifier = "ProductType";
    private final String menuProductCategoryIdentifier = "ProductCategory";
    private final String menuProductPriceIdentifier = "ProductPrice";
    private final String menuProductRatingIdentifier = "ProductRating";
    private final String tableIdIdentifier = "tableID";
    private final String itemIdIdentifier = "ItemID";
    private final String quantityIdentifier = "ItemQuantity";
    private final String statusIdentifier = "ItemOrderStatus";
    private final String cartCookieName = "cart";

    /**
     * Redirect To error.jsp and display the exception
     *
     * @param request
     * @param response
     * @param ex Exception thrown for error.jsp and change the URL
     */
    protected void redirectToErrorPage(@NonNull HttpServletRequest request,
                                       @NonNull HttpServletResponse response,
                                       @NonNull Exception ex) {
        try {
            String relativePath = String.format(path, "error");
            log.info("Setting Attribute Key:'error' and Value:" + ex);
            request.setAttribute("error", ex);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    /**
     * Redirect to error.jsp and display the exception errorMessage
     * @param request
     * @param response
     * @param errorMessage Error message thrown for error.jsp and change the URL
     */
    protected void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            String relativePath = String.format(path, "error");
            log.info("Setting Attribute Key:'error' and Value:" + errorMessage);
            request.setAttribute("error", errorMessage);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch(Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param pageType Redirect to pageType.jsp without changing the URL
     */
    protected void redirectTo(HttpServletRequest request, HttpServletResponse response, String pageType) {
        try {
            String relativePath = String.format(path, pageType);
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    /**
     * Redirect to staticError.jsp
     * @param request
     * @param response
     */
    protected void redirectToStaticErrorPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String relativePath = String.format(path, "staticError");
            log.info("Redirecting to : " + relativePath);
            request.getRequestDispatcher(relativePath).forward(request,response);
        } catch (Exception exception) {
            //Do nothing as the URL generated will never throw any error
        }
    }

    /**
     * Create Product object Product{,,,}
     * @return List of Product objects
     */
    protected MenuWrapper getMenu(@NonNull Connection connection,
                                  @NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response) {
        log.info("Reading Menu from DB.");
        ArrayList<Product> menu = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.SELECT_MENU());

            while (resultSet.next()) {
                Product product = new Product(
                        Integer.parseInt(resultSet.getString(menuProductIDIdentifier)),
                        resultSet.getString(menuProductNameIdentifier),
                        resultSet.getString(menuProductDescriptionIdentifier),
                        resultSet.getString(menuProductTypeIdentifier),
                        resultSet.getString(menuProductCategoryIdentifier),
                        Double.parseDouble(resultSet.getString(menuProductPriceIdentifier)),
                        Integer.parseInt(resultSet.getString(menuProductRatingIdentifier)));
                menu.add(product);
            }
            return new MenuWrapper(menu);
        } catch (SQLException ex) {
            log.info("Failed to fetch MENU from the menu table in DB.");
            redirectToErrorPage(request, response, ex);
        }
        return new MenuWrapper(menu);
    }

    /**
     * Create Order object (Order{ProductID, Quantity})
     * @return return list of Order objects.
     */
    public ArrayList<Order> getCartFromCookie(@NonNull HttpServletRequest request) {
        log.info("Reading Cookie : " + cartCookieName + " -> and creating orders from cart.");
        ArrayList<Order> cartList = new ArrayList<>();
        String cookieValue= getCookieValueAsString(request, cartCookieName);
        if(cookieValue != null) {
            //Split order line items
            String[] orderArrayString = Objects.requireNonNull(cookieValue.split("AND"));
            ArrayList<String> orderListString = new ArrayList<>(); //List to store <ProductID>Q<Quantity> as String
            for (String order : orderArrayString) {
                if (order != null && !order.isEmpty())
                    orderListString.add(order);
            }
            for (String order : orderListString) {
                String[] temp = order.trim().split("Q");
                int productID = Integer.parseInt(temp[0]);
                int quantity = Integer.parseInt(temp[1]);
                int index = findExistingProductInCart(cartList, productID);
                if(index == -1)
                    cartList.add(new Order(productID, quantity, "placed", 0));
                else {
                    cartList.remove(index);
                    cartList.add(new Order(productID, quantity, "placed", 0));
                }
            }
            return cartList;
        } else { log.info("Order cookies not found. Empty Cart."); }
        return cartList;
    }

    private int findExistingProductInCart(ArrayList<Order> cartList, int productID) {
        for(int i=0;i<cartList.size();i++)
            if(cartList.get(i).getProductID() == productID)
                return i;
        return -1;
    }

    /**
     * Read orders cookie from the request.
     * @return CartHelper instance
     */
    private String getCookieValueAsString(@NonNull HttpServletRequest request,
                                          @NonNull String cookieName) {
        log.info("Reading cookie : " + cookieName);
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                log.info(cookie.getValue());
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * Read tableID cookie from request
     * @return tableID cookie value
     */
    public int getTableIDfromCookie(@NonNull HttpServletRequest request) {
        log.info("Reading tableID cookie.");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(tableIdIdentifier))
                    return Integer.parseInt(cookie.getValue());
            }
        }
        return -1;
    }

    public int getTableIDFromTableNumber(@NonNull Connection connection,
                              @NonNull HttpServletRequest request,
                              @NonNull HttpServletResponse response,
                              int tableNumber) {
        try {
            log.info("Finding table ID from tableNumber.");
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.GET_TABLE_ID(Integer.toString(tableNumber)));
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
        return -1;
    }

    public int getTableNumberFromTableID(@NonNull Connection connection,
                                            @NonNull HttpServletRequest request,
                                            @NonNull HttpServletResponse response,
                                            int tableID) {
        try {
            log.info("Finding tableNumber from tableID");
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.GET_TABLE_NUMBER(Integer.toString(tableID)));
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
        return -1;
    }

    public ArrayList<Order> getLiveOrder(@NonNull Connection connection,
                                         @NonNull HttpServletRequest request,
                                         @NonNull HttpServletResponse response,
                                         int tableID) {
        log.info("Fetching live orders from tableID : " + tableID);
        try {
            ArrayList<Order> liveOrder = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery(SQLQueries.getLIVE_ORDER(tableID));
            while (resultSet.next()) {
                liveOrder.add(new Order(
                        Integer.parseInt(resultSet.getString(itemIdIdentifier)),
                        Integer.parseInt(resultSet.getString(quantityIdentifier)),
                        resultSet.getString(statusIdentifier),0));
                log.info(resultSet.getString(itemIdIdentifier) + " + " + resultSet.getString(quantityIdentifier));
            }
            return liveOrder;
        } catch (SQLException exception) {
            redirectToErrorPage(request, response, exception);
            return null;
        }
    }

    public Table getTable(@NonNull Connection connection,
                          @NonNull HttpServletRequest request,
                          @NonNull HttpServletResponse response,
                          String tableStatus) {
        int tableID = getTableIDfromCookie(request);
        return new Table(tableID,
                getTableIDFromTableNumber(connection, request, response, tableID),
                getCustomer(connection, request, response, tableID),
                getCartFromCookie(request),
                getLiveOrder(connection, request, response, tableID),
                getMenu(connection, request, response),
                tableStatus);
    }

    public Customer getCustomer(@NonNull Connection connection,
                                @NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                int tableID) {
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.GET_CUSTOMER(Integer.toString(tableID)));
            if(resultSet.next())
                return new Customer(resultSet.getInt(1),
                        resultSet.getLong(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getLong(2),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getString(8));
        } catch (SQLException ex) {
            redirectToErrorPage(request, response, ex);
        }
        return null;
    }
}
