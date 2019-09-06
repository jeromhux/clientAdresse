package fr.canal.plus.infrastructure;

import fr.canal.plus.domain.Client;
import fr.canal.plus.domain.History;
import fr.canal.plus.domain.Movement;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private History history;

    public ClientService() {
        this.history = new History();
    }

    public Client updateInformationWithoutEffectiveDate(Client client, String canal) {
        Movement movement = new Movement(canal, "UpdateInformation", client.getId());
        history.add(movement);
        return client;
    }

    public History historyOf() {
        return history;
    }

}
