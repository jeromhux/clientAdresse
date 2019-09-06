package fr.canal.plus.infrastructure.client;

import fr.canal.plus.domain.Address;
import fr.canal.plus.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDto {

    private Client client;
    private Address newAddress;

}
