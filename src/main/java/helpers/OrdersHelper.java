package helpers;

import data.SQLQueries;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.Order;

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
    private final String orderCookieNameIdentifier = "order";
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
    public void placeOrder() {
        ArrayList<Order> orders = getCookieValue(request, orderCookieNameIdentifier);
        int tableID = getTableID(request);
        try {
            for(Order order : orders) {
                log.info("Inserting Orders.");
                PreparedStatement statement = connection.prepareStatement(SQLQueries.CONFIRM_ORDER());
                statement.setString(1, Integer.toString(tableID));
                statement.setString(2, Integer.toString(order.getProductID()));
                statement.setString(3, Integer.toString(order.getQuantity()));
                statement.setString(4, order.getStatus());
                log.info("Executing SQL : " + statement);
                statement.executeUpdate();
            }
            request.setAttribute("menu", getMenu(connection, request, response));
            request.setAttribute("liveOrder", getLiveOrder(connection, request, response, tableID));

            log.info("Redirecting to Orders.jsp");
            response.addCookie(new Cookie(orderCookieNameIdentifier,""));
            response.addCookie(new Cookie(cartCookieNameIdentifier, ""));
            redirectTo(request, response, "orders");
        }catch(SQLException ex){
            redirectToErrorPage(request, response, ex);
        }
    }
}