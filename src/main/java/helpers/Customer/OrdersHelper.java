package helpers.Customer;

import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.Order;
import model.Table;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j
public class OrdersHelper <T extends OrdersHelper> extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final String cartCookieNameIdentifier = "cart";

    public OrdersHelper(@NonNull Connection connection,
                      @NonNull HttpServletRequest request,
                      @NonNull HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;
        log.info("CartHelper Object Generated.");
    }

    /**
     * Insert Orders into live_table table in DB
     */
    public T placeOrder() {
        ArrayList<Order> orders = getCartFromCookie(request);
        int tableID = getTableIDfromCookie(request);
        log.info("Placing order for TableID : " + tableID);
        try {
            for(Order order : orders) {
                log.info("Inserting Orders.");
                PreparedStatement statement = connection.prepareStatement(SQLQueries.PLACE_ORDER());
                statement.setString(1, Integer.toString(tableID));
                statement.setString(2, Integer.toString(order.getProductID()));
                statement.setString(3, Integer.toString(order.getQuantity()));
                statement.setString(4, order.getStatus());
                log.info("Executing SQL : " + statement);
                statement.executeUpdate();
            }
            response.addCookie(new Cookie(cartCookieNameIdentifier, ""));
        }catch(SQLException ex){
            redirectToErrorPage(request, response, ex);
        }
        return (T) this;
    }
    public void displayOrder() {
        request.setAttribute("table", getTable(connection, request, response, "Orders"));
        redirectTo(request, response, "Customer/webpage/orders");
    }
}