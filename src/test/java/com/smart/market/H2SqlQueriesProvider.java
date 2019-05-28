package com.smart.market;

import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2SqlQueriesProvider extends SqlQueriesProvider {

    private final String H2_VIEWS_DROP = "sql/h2/views/drop/";
    private final String H2_TABLES = "sql/h2/tables/";

    private final String H2_VIEWS_CREATION= "sql/h2/views/create/";

    public void updateH2Tables() {
        runSqlFilesFromFolder().accept(H2_TABLES);
    }

    public void dropViews() {
        runSqlFilesFromFolder().accept(H2_VIEWS_DROP);
    }

    public void createViews(){
        super.createOrReplaceViews();
        runSqlFilesFromFolder().accept(H2_VIEWS_CREATION);
    }

    public H2SqlQueriesProvider(Environment environment, ResourcePatternResolver resourcePatternResolver, JdbcTemplate jdbcTemplate) {
        super(environment, resourcePatternResolver, jdbcTemplate);
    }
}
