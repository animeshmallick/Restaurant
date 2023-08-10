package data;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLQueries {
    private final String REGISTER_CUSTOMER = "INSERT INTO `bookings`" +
            "(`CustomerID`, `Name`, `Comments`, `Guests`, `Status`,`TableID`) " +
            "VALUES (?,?,?,?,?,?)";

    @Getter
    private final String SELECT_TABLE_ID = "SELECT `TableID` FROM `bookings` where `Status`='booked'";

    @Getter
    private final String SELECT_CUSTOMER_ID = "SELECT `CustomerID` FROM `bookings`";

    private final String UPDATE_CUSTOMER_STATUS = "UPDATE `bookings` SET `Status`='active' " +
            "WHERE `TableID`='%s'";

    @Getter
    private final String SELECT_MENU = "SELECT * FROM menu";

    public String REGISTER_CUSTOMER() {
        return REGISTER_CUSTOMER;
    }

    public String UPDATE_CUSTOMER_STATUS() {
        return UPDATE_CUSTOMER_STATUS;
    }
}
