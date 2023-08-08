package model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Customer {
    @Getter
    private String NAME;
    @Getter
    private String PHONE_NUMBER;
    @Getter
    private String GUESTS;
}
