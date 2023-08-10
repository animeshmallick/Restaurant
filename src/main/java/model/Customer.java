package model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Customer {
    @Getter
    private String NAME;
    @Getter
    private int PHONE_NUMBER;
    @Getter
    private int GUESTS;
}
