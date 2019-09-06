package fr.canal.plus.infrastructure.client;

import fr.canal.plus.domain.Address;
import fr.canal.plus.domain.Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/updateClient")
    public Client updateClient(Client client, String canal, Address newAddress) {
            return clientService.updateInformationWithoutEffectiveDate(client, canal, newAddress);
    }
}
