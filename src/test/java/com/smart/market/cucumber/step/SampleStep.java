
package com.smart.market.cucumber.step;

import com.ninja_squad.dbsetup.operation.Insert;
import com.smart.market.cucumber.SpringCucumberStep;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Sample step file which implement Sample.feature steps.
 */
public class SampleStep extends SpringCucumberStep {

    @When("^I insert the message '(.+)' into HTwo test table$")
    public void i_insert_data_into_H_test_table(String message) {
        Insert query = insertInto(schema + ".TEST_TABLE").columns("MESSAGE").values(message).build();
        executeDbOperations(query);
    }

    @When("^I put the message 'Hello H(\\d+) database' into HTwo test table$")
    public void i_put_the_message_Hello_H_database_into_HTwo_test_table(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^we should be able to retrieve the message '(.+)' using the view pointing towards that table$")
    public void we_should_be_able_to_retrieve_the_data_from_the_same_table(String expectedMessage) {
        String actualMessage = jdbcTemplate().queryForList("SELECT MESSAGE FROM " + schema + ".TEST_TABLE_VIEW", String.class).get(0);
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
