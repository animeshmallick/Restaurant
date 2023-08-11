package model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer {
    private String NAME;
    private long PHONE_NUMBER;
    private int GUESTS;
}
