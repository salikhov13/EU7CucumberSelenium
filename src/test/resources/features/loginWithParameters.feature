
Feature: Login as different users
  Scenario: Login as a driver
    Given the user is on the login page
    When the user logs in using "user10" and "UserUser123"
    And the title contains "Dashboard"

  Scenario: Menu Options
    Given the user logged in as "driver"
    Then the user should see following options
      | Fleets      |
      | Customers  |
      | Activities |
      | System     |
