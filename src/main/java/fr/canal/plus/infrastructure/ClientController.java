package fr.canal.plus.infrastructure;

import fr.canal.plus.domain.Client;
import fr.canal.plus.domain.History;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

@RestController
public class ClientController {
    private ClientService clientService ;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/updateClient")
    public Client updateClient(HttpResponse response, String canal) throws IOException {
        if(response.getStatusLine().getStatusCode() == 200) {
            return clientService.updateInformationWithoutEffectiveDate(ClientMapper.httpResponseToClient(response), canal);
        }
        throw new NotImplementedException();
    }
}
