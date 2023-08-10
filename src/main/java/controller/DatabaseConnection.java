package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import data.Database;
import helpers.CommonHelper;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
public class DatabaseConnection {

    public static Connection initialiseDatabase(HttpServletRequest request, HttpServletResponse response) {
        try {
            Class.forName(Database.getDB_DRIVER());
            log.info("JDBC Class Driver Found.");
            return DriverManager.getConnection(Database.getDatabaseURL(),
                    Database.getDB_USERNAME(),
                    Database.getDB_PASSWORD());
        } catch(ClassNotFoundException e) { CommonHelper.redirectToErrorPage(request, response, e);
        } catch(SQLException e) { CommonHelper.redirectToErrorPage(request, response, e); }
        return null;
    }
}
