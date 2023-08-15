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
    private final String CONFIRM_ORDER = "INSERT INTO `live_table`(`TableNumber`, `ItemID`, `ItemQuantity`, `ItemOrderStatus`) " +
            "VALUES (?,?,?,?)";
    private final String LIVE_ORDER = "SELECT * FROM `live_table` WHERE `TableNumber` = '%s'";

    @Getter
    private final String SELECT_MENU = "SELECT * FROM menu";

    public String REGISTER_CUSTOMER() {
        return REGISTER_CUSTOMER;
    }

    public String UPDATE_CUSTOMER_STATUS() {
        return UPDATE_CUSTOMER_STATUS;
    }

    public String CONFIRM_ORDER() { return CONFIRM_ORDER; }

    public String getLIVE_ORDER(int tableID) {
        return String.format(LIVE_ORDER, tableID);
    }
}
