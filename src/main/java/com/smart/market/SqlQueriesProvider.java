package com.smart.market;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
@Component
public class SqlQueriesProvider {

    private final Environment environment;
    private final ResourcePatternResolver resourcePatternResolver;
    private final JdbcTemplate jdbcTemplate;

    private static final String TABLES = "sql/tables/";
    private static final String VIEWS_CREATION = "sql/views/create/";

    public void updateTables() {
        runSqlFilesFromFolder().accept(TABLES);
    }

    public void createOrReplaceViews() {
        runSqlFilesFromFolder().accept(VIEWS_CREATION);
    }

    Consumer<String> runSqlFilesFromFolder() {
        return folderPath -> loadSqlFilesFromFolder().apply(folderPath).forEach(runSqlQuery());
    }

    private Function<String, Set<Resource>> loadSqlFilesFromFolder() {
        return folderPath -> HashSet.of(getResources(folderPath));
    }

    private Resource[] getResources(String folderPath) {
        try {
            return resourcePatternResolver.getResources("classpath:" + folderPath + "*.sql");
        } catch (IOException e) {
            log.info("IOException : " + e.getMessage(), e);
        }
        return new Resource[0];
    }

    private Consumer<Resource> runSqlQuery() {
        return resource -> {
            try {
                long startTime = System.currentTimeMillis();
                log.info("Executing SQL from URL[" + resource.getURL().getPath() + "]");
                String query = environment.resolvePlaceholders(asString(resource));
                jdbcTemplate.execute(query);
                long elapsedTime = System.currentTimeMillis() - startTime;
                log.info("Executed SQL from URL[" + resource.getURL().getPath() + "] in " + elapsedTime + " ms.");
            } catch (IOException | DataAccessException e) {
                log.error("Error while executing SQL script : " + e);
                throw new SqlQueriesProvider.DbInitializationException(e);
            }
        };
    }

    private class DbInitializationException extends RuntimeException {
        DbInitializationException(Throwable throwable) {
            super(throwable);
        }
    }

    private String asString(Resource resource) throws IOException {
        return new Scanner(resource.getInputStream(), "UTF-8").useDelimiter("\\A").next();
    }


}
