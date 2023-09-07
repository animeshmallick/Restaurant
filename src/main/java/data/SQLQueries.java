package data;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

@Log4j
@UtilityClass
public class SQLQueries {
    private final String REGISTER_CUSTOMER = "INSERT INTO `bookings`" +
            "(`CustomerID`, `Name`, `Comments`, `Guests`, `Status`,`TableID`) " +
            "VALUES (?,?,?,?,?,?)";

    private final String SELECT_TABLE_ID = "SELECT `TableID` FROM `bookings` where `Status`='booked'";

    private final String SELECT_CUSTOMER_ID = "SELECT `CustomerID` FROM `bookings`";

    private final String UPDATE_CUSTOMER_STATUS = "UPDATE `bookings` SET `Status`='active' " +
            "WHERE `TableID`='%s'";
    private final String PLACE_ORDER = "INSERT INTO `live_table`(`TableNumber`, `ItemID`, `ItemQuantity`, `ItemOrderStatus`) " +
            "VALUES (?,?,?,?)";
    private final String LIVE_ORDER = "SELECT * FROM `live_table` WHERE `TableNumber` = '%s'";

    private final String SELECT_MENU = "SELECT * FROM menu";

    private final String SELECT_CUSTOMER = "SELECT * FROM `bookings` WHERE `CustomerID` = '%s' AND `Status` = 'booked'";

    private final String UPDATE_TABLE = "UPDATE `otp_table` SET `Table_Otp`='%s' WHERE `Table_Number` = '%s'";

    private final String SELECT_LIVE_TABLES = "SELECT * FROM otp_table";

    private final String SELECT_LIVE_ORDERS = "SELECT * FROM live_table";

    private final String GET_TABLE_ID = "SELECT Table_Otp FROM `otp_table` WHERE `Table_Number`='%s' LIMIT 1";

    private final String GET_TABLE_NUMBER ="SELECT Table_Number FROM `otp_table` WHERE `Table_Otp`='%s' LIMIT 1";

    private final String GET_ORDERS_FROM_LIVE_TABLE = "SELECT * FROM `live_table` WHERE `TableNumber`='%s'";

    private final String CONFIRM_ORDER = "UPDATE `live_table` SET `ItemOrderStatus`='confirmed' WHERE `OrderID`='%s'";

    private final String DELETE_ORDER = "UPDATE `live_table` SET `ItemOrderStatus`='deleted' WHERE `OrderID`='%s'";

    private final String CONFIRM_ALL_ORDERS = "UPDATE `live_table` SET `ItemOrderStatus`='confirmed' WHERE `TableNumber`='%s' AND `ItemOrderStatus`='placed'";

    private final String SELECT_LIVE_ORDERS_FOR_KITCHEN = "SELECT * FROM live_table WHERE `ItemOrderStatus` IN ('confirmed','preparing','prepared')";

    private final String ORDER_PREPARED = "UPDATE `live_table` SET `ItemOrderStatus`='prepared' WHERE `OrderID`='%s'";

    private final String DELIVER_ORDER = "UPDATE `live_table` SET `ItemOrderStatus`='delivered' WHERE `OrderID`='%s'";

    private final String PREPARE_ORDER = "UPDATE `live_table` SET `ItemOrderStatus`='preparing' WHERE `OrderID`='%s'";

    private final String GET_CUSTOMER = "SELECT * FROM `bookings` WHERE `TableID`='%s' LIMIT 1";

    public String SELECT_TABLE_ID() {
        log.info("Executing SQL Query : " + SELECT_TABLE_ID);
        return SELECT_TABLE_ID;
    }

    public String SELECT_CUSTOMER_ID() {
        log.info("Executing SQL Query : " + SELECT_CUSTOMER_ID);
        return SELECT_TABLE_ID;
    }

    public String SELECT_MENU() {
        log.info("Executing SQL Query : " + SELECT_MENU);
        return SELECT_MENU;
    }

    public String REGISTER_CUSTOMER() {
        log.info("Executing SQL Query : " + REGISTER_CUSTOMER);
        return REGISTER_CUSTOMER;
    }

    public String UPDATE_CUSTOMER_STATUS(String tableID) {
        log.info("Executing SQL Query : " + String.format(UPDATE_CUSTOMER_STATUS,tableID));
        return String.format(UPDATE_CUSTOMER_STATUS,tableID);
    }

    public String PLACE_ORDER() {
        log.info("Executing SQL Query : " + PLACE_ORDER);
        return PLACE_ORDER;
    }

    public String getLIVE_ORDER(int tableID) {
        log.info("Executing SQL Query : " + String.format(LIVE_ORDER, tableID));
        return String.format(LIVE_ORDER, tableID);
    }

    public String SELECT_CUSTOMER(long phoneNumber) {
        log.info("Executing SQL Query : " + String.format(SELECT_CUSTOMER, phoneNumber));
        return String.format(SELECT_CUSTOMER, phoneNumber);
    }

    public String UPDATE_TABLE(String tableID, String tableNumber) {
        log.info("Executing SQL Query : " + String.format(UPDATE_TABLE, tableID, tableNumber));
        return String.format(UPDATE_TABLE, tableID, tableNumber);
    }

    public String SELECT_LIVE_TABLES() {
        log.info("Executing SQL Query : " + SELECT_LIVE_TABLES);
        return  SELECT_LIVE_TABLES;
    }

    public String SELECT_LIVE_ORDERS() {
        log.info("Executing SQL Query : " + SELECT_LIVE_ORDERS);
        return SELECT_LIVE_ORDERS;
    }

    public String GET_TABLE_ID(String tableNumber) {
        log.info("Executing SQL Query : " + String.format(GET_TABLE_ID, tableNumber));
        return String.format(GET_TABLE_ID, tableNumber);
    }

    public String GET_TABLE_NUMBER(String tableID) {
        log.info("Executing SQL Query : " + String.format(GET_TABLE_NUMBER, tableID));
        return String.format(GET_TABLE_NUMBER, tableID);
    }

    public String GET_ORDERS_FROM_LIVE_TABLE(String tableID) {
        log.info("Executing SQL Query : " + String.format(GET_ORDERS_FROM_LIVE_TABLE, tableID));
        return String.format(GET_ORDERS_FROM_LIVE_TABLE, tableID);
    }

    public String CONFIRM_ORDER(String orderID) {
        log.info("Executing SQL Query : " + String.format(CONFIRM_ORDER, orderID));
        return String.format(CONFIRM_ORDER, orderID);
    }

    public String DELETE_ORDER(String orderID) {
        log.info("Executing SQL Query : " + String.format(DELETE_ORDER, orderID));
        return String.format(DELETE_ORDER, orderID);
    }

    public String CONFIRM_ALL_ORDERS(String tableID) {
        log.info("Executing SQL Query : " + String.format(CONFIRM_ALL_ORDERS, tableID));
        return String.format(CONFIRM_ALL_ORDERS, tableID);
    }

    public String SELECT_LIVE_ORDERS_FOR_KITCHEN() {
        log.info("Executing SQL Query : " + SELECT_LIVE_ORDERS_FOR_KITCHEN);
        return SELECT_LIVE_ORDERS_FOR_KITCHEN;
    }

    public String ORDER_PREPARED(String orderID) {
        log.info("Executing SQL Query : " + String.format(ORDER_PREPARED, orderID));
        return String.format(ORDER_PREPARED, orderID);
    }

    public String DELIVER_ORDER(String orderID) {
        log.info("Executing SQL Query : " + String.format(DELIVER_ORDER, orderID));
        return String.format(DELIVER_ORDER, orderID);
    }

    public String PREPARE_ORDER(String orderID) {
        log.info("Executing SQL Query : " + String.format(PREPARE_ORDER, orderID));
        return String.format(PREPARE_ORDER, orderID);
    }

    public String GET_CUSTOMER(String tableID) {
        log.info("Executing SQL Query : " + String.format(GET_CUSTOMER, tableID));
        return String.format(GET_CUSTOMER, tableID);
    }
}
