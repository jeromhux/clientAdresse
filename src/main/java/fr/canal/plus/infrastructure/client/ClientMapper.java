package fr.canal.plus.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ClientMapper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static ClientUpdateDto httpResponseToClient(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return mapper.readValue(responseString, ClientUpdateDto.class);
    }

}
