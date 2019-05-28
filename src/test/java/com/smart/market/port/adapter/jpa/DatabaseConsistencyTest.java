package com.smart.market.port.adapter.jpa;

import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.vavr.collection.HashSet.empty;
import static io.vavr.collection.List.of;
import static io.vavr.collection.List.rangeClosed;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;
import static org.junit.Assert.fail;

//@RunWith(SpringRunner.class)
public class DatabaseConsistencyTest {

    //TODO Write the new database consistency tests

//    @Autowired
//    private ResourcePatternResolver resourcePatternResolver;
//
//    private static final String VIEWS_PATH = "classpath:sql/views/*.sql";
//    private static final String H2_TABLES_PATH = "classpath:sql/h2/schema/*.sql";
//    private static final Pattern viewPattern = compile("(?:[\\w\\d]+\\.(\\w*)\\s*[,\\n])|(?:as\\s*(\\w*)\\s*[,\\n])", CASE_INSENSITIVE);
//    private static final Pattern tablePattern = compile("([\\w\\d_]+)\\s+(?:\\w+\\s*\\(?\\s*\\d*\\s*,?\\s*\\d*\\)?(?:\\s+NOT\\s+NULL\\s*)?[,)])", CASE_INSENSITIVE);
//    private static final String MISSING_COLUMN_ERROR = "The following columns are missing in %s \n\t %s";
//    private static final String MISSING_FILE_ERROR = "There is no H2 counterpart file for %s \n";
//
//    private List<String> errors = List.empty();
//
//    @Test
//    public void views_and_H2_tables_have_the_same_columns() throws Throwable {
//        for (Resource viewResource : resourcePatternResolver.getResources(VIEWS_PATH)) {
//            URL viewUrl = viewResource.getURL();
//            Option<Resource> maybeTableResource = of(resourcePatternResolver.getResources(H2_TABLES_PATH))
//                    .find(tableFile -> tableFile.getFilename().equalsIgnoreCase(viewResource.getFilename()));
//
//            if (maybeTableResource.isEmpty()) {
//                errors = errors.append(String.format(MISSING_FILE_ERROR, viewUrl.getPath()));
//            } else {
//                Resource tableResource = maybeTableResource.get();
//                Set<String> viewColumns = findColumnNames(viewPattern, viewResource);
//                Set<String> tableColumns = findColumnNames(tablePattern, tableResource);
//
//                addErrors(tableResource.getURL().getPath(), viewColumns.partition(tableColumns::contains)._2);
//                addErrors(viewResource.getURL().getPath(), tableColumns.partition(viewColumns::contains)._2);
//            }
//        }
//
//        if (!errors.isEmpty()) {
//            fail(errors.mkString("\n"));
//        }
//
//    }
//
//    private void addErrors(String path, Set<String> columnNames) {
//        if (!columnNames.isEmpty()) {
//            errors = errors.append(String.format(MISSING_COLUMN_ERROR, path, columnNames.mkString(",")));
//        }
//    }
//
//    private Set<String> findColumnNames(Pattern pattern, Resource resource) throws IOException {
//        Matcher matcher = pattern.matcher(asString(resource).toUpperCase().split("FROM")[0]);
//        Set<String> columnNames = empty();
//        while (matcher.find()) {
//            columnNames = columnNames.addAll(rangeClosed(1, matcher.groupCount())
//                    .map(matcher::group)
//                    .filter(Objects::nonNull)
//            );
//        }
//        return columnNames;
//    }
//
//    private String asString(Resource resource) throws IOException {
//        return new Scanner(resource.getInputStream(), "UTF-8").useDelimiter("\\A").next();
//    }

}