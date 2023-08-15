package helpers;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@Log4j
public class CartHelper<T extends CartHelper> extends BaseHelper {
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CartHelper(@NonNull Connection connection,
                      @NonNull HttpServletRequest request,
                      @NonNull HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response = response;
        log.info("CartHelper Object Generated.");
    }

    /**
     * Add menu and orders attribute and redirect to cart.jsp
     */
    public void displayCart() {
        log.info("Reading Menu from CartHelper.");
        request.setAttribute("menu", getMenu(connection, request, response));

        log.info("Reading Order from Cookies.");
        request.setAttribute("orders", getCookieValue(request, "cart"));

        log.info("Generating Cart.");
        redirectTo(request, response, "cart");
    }
}
