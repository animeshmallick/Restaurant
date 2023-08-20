package helpers.Waiter;

import controller.DatabaseConnection;
import data.SQLQueries;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j
public class NewEntryHelper <T extends NewEntryHelper> extends BaseHelper {
    private final Connection connection;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public NewEntryHelper(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.connection = new DatabaseConnection().initialiseDatabase(request, response);
        log.info("New Entry helper object is created/");
    }
    public void validateEntry() {
        try {
            long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
            log.info("Searching for customer details in DB from NewEntry Servlet");
            ResultSet resultSet = connection.createStatement()
                    .executeQuery(SQLQueries.SELECT_CUSTOMER(phoneNumber));
            log.info("SQL QUERY : " + SQLQueries.SELECT_CUSTOMER(phoneNumber));
            while (resultSet.next()) {
                request.setAttribute("id", resultSet.getString(2));
                request.setAttribute("name", resultSet.getString(4));
                request.setAttribute("guest", resultSet.getString(6));
                request.setAttribute("tableID", resultSet.getString(7));
                request.setAttribute("time", resultSet.getString(3));
                request.setAttribute("comment", resultSet.getString(5));
                log.info("Details found for Name : " + resultSet.getString(4));
                redirectTo(request, response, "Waiter/customerDetails");
                break;
            }
        } catch (SQLException ex) {
            log.info(ex.getMessage());
            //Todo:
        }
    }
}
