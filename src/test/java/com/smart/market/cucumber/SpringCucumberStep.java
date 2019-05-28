package com.smart.market.cucumber;


import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import com.smart.market.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Common Cucumber step file that handle common steps as
 * initialize JDBC template,
 * execute database operations,
 * clean database content or cache content.
 * Will be extended by all step classes that need these common operations
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {Application.class},
        webEnvironment = RANDOM_PORT
)
@ContextConfiguration
@ActiveProfiles("cucumber")
public abstract class SpringCucumberStep {

    @Value("${database.schemas.referential}")
    protected String schema;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static List<Operation> dbOperations = new ArrayList<>();

    public void executeDbOperations() {
        new DbSetup(new DataSourceDestination(dataSource), sequenceOf(dbOperations)).launch();
        dbOperations = new ArrayList<>();
    }

    public void executeDbOperations(List<Insert> operations){
        new DbSetup(new DataSourceDestination(dataSource), sequenceOf(operations)).launch();
    }

    public void executeDbOperations(Operation operation){
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }

    public JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    public void setup() {
    }

    public void cleanAll() {
        cleanDB();
    }

    public void cleanDB() {
        dbOperations.add(deleteAllFrom(
                schema + ".TEST_TABLE"
                )
        );
        executeDbOperations();
    }

}
