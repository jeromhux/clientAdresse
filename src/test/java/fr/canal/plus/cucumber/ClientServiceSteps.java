package fr.canal.plus.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.canal.plus.domain.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.*;

public class ClientServiceSteps {

    private static final String UPDATE = "/updateClient";
    private ClientService clientService = new ClientService();
    private Client client;
    private HttpResponse httpResponse;
    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
    private final String jsonString = new Scanner(Objects.requireNonNull(jsonInputStream), "UTF-8").useDelimiter("\\Z").next();
    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private ObjectMapper mapper = new ObjectMapper();

    @Given("^a client with a main address active in France$")
    public void a_client_with_a_main_address_active_in_France() throws Throwable {
        long id = 1;
        client = new Client(id, aContactInformation());
    }


    @When("^the advisor connected to \"([^\"]*)\" changes the client address without an effective date$")
    public void the_advisor_connected_to_canal_changes_the_client_address_without_an_effective_date(String canal) throws Throwable {

        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo(UPDATE)).withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withStatus(200).withBody(jsonString)));

        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/updateClient");
        request.addHeader("accept", APPLICATION_JSON);
        httpResponse = httpClient.execute(request);


        Client updatedClient = clientService.updateInformation(convertResponseToClient(httpResponse), canal);

        assertNotEquals(client, updatedClient);
        verify(getRequestedFor(urlEqualTo(UPDATE)).withHeader("accept", equalTo(APPLICATION_JSON)));

        wireMockServer.stop();
    }

    @Then("^the modified client address is recorded on all subscriber contracts$")
    public void the_modified_client_address_is_recorded_on_all_subscriber_contracts() throws Throwable {
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());

    }

    @And("^an address change movement is created$")
    public void an_address_change_movement_is_created() {
        Movement movement = clientService.lastMovement();
        assertTrue(movement.isOperation("UpdateInformation"));
    }

    private ContactInformation aContactInformation() {
        return new ContactInformation(new Identity("Martin", "Durand"), aAddress(new City("75000", "Paris"), new Street(20, "rue de Rivoli")));
    }

    private Address aAddress(City city, Street street) {
        return new Address(street, city, new Country("France"));
    }

    private Client convertResponseToClient(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return mapper.readValue(responseString, Client.class);
    }

}
