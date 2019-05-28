package com.smart.market;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;

/**
 * AddDbTablesAndViewsListener class ensure views creation on application start
 */
@RequiredArgsConstructor
@Slf4j
@Component
@Profile("!h2")
public class AddDbTablesAndViewsListener implements ApplicationListener<ContextRefreshedEvent> {

    private final SqlQueriesProvider sqlQueriesProvider;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        sqlQueriesProvider.createOrReplaceViews();
    }
}
