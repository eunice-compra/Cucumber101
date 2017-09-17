Feature: As a Gmail user, I can log in to my account.

  Scenario: Log in to my account with correct username and password
    Given I navigate to the login page of Gmail
    And I enter the username as "androyd.test17"
    And I click the user Next button
    And I enter the password as "nimbuscloud"
    And I click the password Next button
    Then I should see my mailbox
