package model;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
/**
 * Customer Entity
 */
public class Customer {
    private int BOOKING_ID;
    private long ID;
    private Date BOOKING_TIME;
    private String NAME;
    private long PHONE_NUMBER;
    private String COMMENT;
    private int GUESTS;
    private int TABLE_ID;
    private String STATUS;
}
