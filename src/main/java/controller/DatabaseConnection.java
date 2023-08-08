package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import data.Database;

public class DatabaseConnection {

    public static Connection initialiseDatabase() throws SQLException, ClassNotFoundException {
        Class.forName(Database.getDB_DRIVER());
        return DriverManager.getConnection(Database.getDatabaseURL(),
                                           Database.getDB_USERNAME(),
                                           Database.getDB_PASSWORD());
    }
}
