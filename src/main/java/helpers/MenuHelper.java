package helpers;

import data.SQLQueries;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.MenuWrapper;
import model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private int tableID;

    public MenuHelper(@NonNull Connection connection,
                      @NonNull HttpServletRequest request,
                      @NonNull HttpServletResponse response) {
        this.connection = connection;
        this.request = request;
        this.response =  response;
        log.info("Menu Helper Object Created.");
        this.tableID = getTableID(request);
        if(tableID == -1)
            log.info("Menu requested from a unknown table.");
        else
            log.info("Menu requested from TableID : " + tableID);
    }

    /**
     *Read Menu table from DB and redirect to menu.jsp
     */
    public void showMenu() {
        if(tableID != -1) {
            request.setAttribute("menu", getMenu(connection, request, response));
            redirectTo(request, response, "menu");
        }else {
            try {
                response.sendRedirect("/RestaurantServer/customerLogin.jsp");
            }catch(IOException ex) { redirectToErrorPage(request, response,
                    "Table ID not verified and still not able to redirect to login page.");}
        }
    }
}
