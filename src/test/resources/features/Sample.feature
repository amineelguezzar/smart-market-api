Feature: H2 table test
  Sample to test cucumber tests are well configured and able to insert in H2 tables and use H2 views

  Scenario: Insert and read from H2 table

    When I insert the message 'Hello H2 database' into HTwo test table

    Then we should be able to retrieve the message 'Hello H2 database' using the view pointing towards that table
