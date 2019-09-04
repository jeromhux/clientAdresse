package fr.canal.plus.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ClientServiceSteps {

    Client client;

    @Given("^a client with a main address active in France$")
    public void a_client_with_a_main_address_active_in_France() throws Throwable {
        long id = 1;
        Identity identity = new Identity("Martin", "Durand");
        City city = new City("75000", "Paris");
        Country country = new Country("France");
        Address address = new Address(new Street(25, "rue du Louvre"),city,country);
        ContactInformation contactInformation = new ContactInformation(identity,address);
        client = new Client(id,contactInformation);
    }

    @When("^the advisor connected to canal changes the client address without an effective date$")
    public void the_advisor_connected_to_canal_changes_the_client_address_without_an_effective_date() throws Throwable {
        Assert.assertFalse(true);
    }

    @Then("^the modified client address is recorded on all subscriber contracts$")
    public void the_modified_client_address_is_recorded_on_all_subscriber_contracts() throws Throwable {
        Assert.assertFalse(true);

    }

    @And("^an address change movement is created$")
    public void an_address_change_movement_is_created() {
        Assert.assertFalse(true);
    }

}
