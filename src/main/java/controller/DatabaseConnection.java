package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import data.Database;
import helpers.BaseHelper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
public class DatabaseConnection extends BaseHelper {

    public Connection initialiseDatabase(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
        try {
            Class.forName(Database.getDB_DRIVER());
            log.info("JDBC Class Driver Found.");
            log.info("Connecting to Database.");
            Connection connection = DriverManager.getConnection(Database.getDatabaseURL(),
                    Database.getDB_USERNAME(),
                    Database.getDB_PASSWORD());
            log.info("Connected to Database.");
            return connection;
        } catch(ClassNotFoundException e) {
            log.info("Failed to Connect to Database.");
            redirectToErrorPage(request, response, e);
        } catch(SQLException e) {
            log.info("Failed to Connect to Database.");
            redirectToErrorPage(request, response, e);
        }
        return null;
    }
}
