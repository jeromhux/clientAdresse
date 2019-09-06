package fr.canal.plus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Client {

    private long id;
    private Identity identity;
    private Address address;
    private List<Contract> contracts;

    public boolean isInFrance() {
        return address.isInFrance();
    }
}
