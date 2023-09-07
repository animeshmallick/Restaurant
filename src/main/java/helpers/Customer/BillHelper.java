package helpers.Customer;

import controller.DatabaseConnection;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import model.Order;
import model.Table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;

@Log4j
public class BillHelper <T extends BillHelper> extends BaseHelper {
    private final Connection connection;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private Table table;

    public BillHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Bill Helper object is created.");
        int tableID = Integer.parseInt(request.getParameter("tableID"));
        this.table = new Table(
                tableID,
                getTableNumberFromTableID(connection, request, response, tableID),
                getCustomer(connection, request, response, tableID),
                null,
                getLiveOrder(connection, request, response, tableID),
                getMenu(connection, request, response),
                ""
        );
    }

    private boolean validateNoPendingOrder() {
        for(Order order : this.table.getOrders())
            if(!order.getStatus().equals("delivered") && !order.getStatus().equals("deleted"))
                return false;
        return true;
    }

    public T generateBill() {
        if(validateNoPendingOrder()) {

        }
        return (T) this;
    }

    public void displayBill() {

    }
}
