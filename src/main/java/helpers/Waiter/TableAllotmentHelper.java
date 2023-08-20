package helpers.Waiter;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j
public class TableAllotmentHelper extends BaseHelper {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final Connection connection;

    public TableAllotmentHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("Table Allotment Helper object is created/+.");
    }

    public void allotTable() {
        try {
            String tableID = request.getParameter("tableID");
            String tableNumber = request.getParameter("tableNumber");
            connection.createStatement()
                    .executeUpdate(SQLQueries.UPDATE_TABLE(tableID, tableNumber));
            log.info("updated table entry");
            redirectTo(request, response, "Waiter/successfulEntry");
        } catch (SQLException ex) {
            log.info(ex.getMessage());
            redirectToErrorPage(request, response, ex);
        }
    }
}
