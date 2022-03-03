
Feature: Contacts page

  Scenario: Default page number
    Given the user is on the login page
    And the user enters the driver information
    When the user navigates to "Customers" "Contacts"
    Then default page number should be 1

  Scenario: login as a given user
    Given the user is on the login page
    When the user loggs in using following credentials
      | username  | user10      |
      | password  | UserUser123 |
      | firstname | John        |
      | lastname  | Doe         |
    @wip @db
  Scenario: Contacts test with email
    Given the user logged in as "sales manager"
    And the user navigates to "Customers" "Contacts"
    When the user clicks the "mbrackstone9@example.com" from contacts
    Then the information should be same with database

