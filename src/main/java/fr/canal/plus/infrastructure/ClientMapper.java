package fr.canal.plus.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.canal.plus.domain.Client;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ClientMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static Client httpResponseToClient(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return mapper.readValue(responseString, Client.class);
    }

}
