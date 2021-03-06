package fr.canal.plus.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {

    private Street street;
    private City city;
    private Country country;

    boolean isInFrance() {
        return "France".equals(country.getValue());
    }
}
