package helpers.Customer;

import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@Log4j
public class MenuHelper <T extends MenuHelper> extends BaseHelper {
    private final Connection connection;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
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
            redirectTo(request, response, "Customer/menu");
        }else {
            try {
                response.sendRedirect("/RestaurantServer/customerLogin.jsp");
            }catch(IOException ex) { redirectToErrorPage(request, response,
                    "Table ID not verified and still not able to redirect to login page.");}
        }
    }
}
