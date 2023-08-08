package data;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Database {
    @Getter
    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_HOSTNAME = "localhost";
    private final String DB_PORT = "3306";
    private final String DB_NAME = "restaurant";
    @Getter
    private final String DB_USERNAME = "server";
    @Getter
    private final String DB_PASSWORD = "12345";
    @Getter
    private final String Bookings_Table_Name = "bookings";

    private final String databaseURLFormat = "jdbc:mysql://%s:%s/%s";

    public String getDatabaseURL() {
        return String.format(databaseURLFormat, DB_HOSTNAME, DB_PORT, DB_NAME);
    }
}
