package com.smart.market.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Cucumber basic configuration file which specifies features files location
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/",
        plugin = {"pretty", "html:target/Destination"}
)
public class CucumberIntegrationTest {
}
