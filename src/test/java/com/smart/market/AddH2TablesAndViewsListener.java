package com.smart.market;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
@Profile("h2")
public class AddH2TablesAndViewsListener implements ApplicationListener<ContextRefreshedEvent> {

    private final H2SqlQueriesProvider h2SqlQueriesProvider;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        h2SqlQueriesProvider.dropViews();
        h2SqlQueriesProvider.updateTables();
        h2SqlQueriesProvider.updateH2Tables();
        h2SqlQueriesProvider.createViews();
    }

}
