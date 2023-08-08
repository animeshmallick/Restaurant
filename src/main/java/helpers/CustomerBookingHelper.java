package helpers;

import data.SQLQueries;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomerBookingHelper {
    Connection connection;
    public String generateUniqueOTP() throws SQLException {
        ResultSet resultSet = connection.createStatement()
                .executeQuery(SQLQueries.getGET_EXISTING_OTPs());

        List<String> existingOtpList = new ArrayList<String>();
        String newOtp = String.valueOf((int) (100000 + 100000 * Math.random()));

        while(resultSet.next())
            existingOtpList.add(resultSet.getString(1));

        while(existingOtpList.contains(newOtp)) {
            newOtp = String.valueOf((int) (100000 * Math.random()));
        }
        return newOtp;
    }
}
