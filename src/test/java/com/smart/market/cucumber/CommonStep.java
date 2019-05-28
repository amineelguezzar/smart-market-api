package com.smart.market.cucumber;

import cucumber.api.java.Before;

public class CommonStep extends SpringCucumberStep {

    @Before
    public void init() {
        cleanDB();
    }
}
