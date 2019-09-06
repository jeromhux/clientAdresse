package fr.canal.plus;

import fr.canal.plus.domain.*;
import fr.canal.plus.infrastructure.ClientService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ClientServiceTest {

    private Address address;

    @Before
    public void setUp() {
        address = aAddress(new City("75000", "Paris"), new Street(20, "rue de Rivoli"));
    }

    @Test
    public void verify_update_informations_change_contracts_address() {
        ClientService clientService = new ClientService();
        Identity identity = new Identity("Martin", "Durand");
        Client newClientInformation = newClientInformation(address, identity);

        Client updatedClient = clientService.updateInformationWithoutEffectiveDate(newClientInformation, "canal");

        updatedClient.getContracts().stream().forEach(x -> assertThat(x.getAddress()).isEqualTo(newClientInformation.getAddress()));
    }


    private Client newClientInformation(Address address, Identity identity) {
        return new Client(1, identity, address, contracts());
    }

    private Address aAddress(City city, Street street) {
        return new Address(street, city, new Country("France"));
    }

    private List<Contract> contracts() {
        List<Contract> contracts = new ArrayList<>();
        Contract contract = new Contract(1, address);
        Contract otherContract = new Contract(2, address);
        contracts.add(contract);
        contracts.add(otherContract);

        return contracts;
    }
}
