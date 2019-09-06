package fr.canal.plus;

import fr.canal.plus.domain.*;
import fr.canal.plus.infrastructure.client.ClientService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceTest {

    private Address address;
    private Address addressUpdated;
    private Address addressNotInFrance;
    ClientService clientService;
    Identity identity;

    @Before
    public void setUp() {
        address = addressInFrance(new City("75000", "Paris"), new Street(20, "rue de Rivoli"));
        addressUpdated = addressInFrance(new City("75000", "Paris"), new Street(10, "rue due Louvre"));
        addressNotInFrance = addressNotInFrance(new City("57876", "Denver"), new Street(20, "Street"));
        clientService = new ClientService();
        identity = new Identity("Martin", "Durand");
    }

    @Test
    public void verify_update_informations_change_contracts_address() {
        Client newClientInformation = newClientInformation(address, identity);

        Client updatedClient = clientService.updateInformationWithoutEffectiveDate(newClientInformation, "canal",addressUpdated);

        updatedClient.getContracts().stream().forEach(x -> assertThat(x.getAddress()).isEqualTo(newClientInformation.getAddress()));
    }

    @Test
    public void no_change_if_client_live_not_in_france() {
        Client newClientInformation = newClientInformation(addressNotInFrance, identity);

        Client updatedClient = clientService.updateInformationWithoutEffectiveDate(newClientInformation, "canal",addressUpdated);

        assertThat(updatedClient).isNull();
    }


    private Client newClientInformation(Address address, Identity identity) {
        return new Client(1, identity, address, contracts());
    }

    private Address addressInFrance(City city, Street street) {
        return new Address(street, city, new Country("France"));
    }

    private Address addressNotInFrance(City city, Street street) {
        return new Address(street, city, new Country("USA"));
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
