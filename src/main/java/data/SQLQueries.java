package data;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SQLQueries {
    @Getter
    private final String REGISTER_CUSTOMER = "INSERT INTO `bookings`" +
            "(`CustomerID`, `Name`, `Comments`, `Guests`, `Status`,`TableID`) " +
            "VALUES (?,?,?,?,?,?)";

    @Getter
    private final String GET_EXISTING_OTPs = "SELECT `TableID` FROM `bookings`";
}
