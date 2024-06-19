Feature: User Login and Logout

  Scenario Outline: Successful login and logout
    Given I navigate to the login page
    When I click on the sign in button
    And I enter "<username>" and "<password>"
    And I click on the login button
    Then I should be logged in successfully
    When I click on the logout link
    Then I should be logged out successfully

    Examples:
      | username | password  |
      | wipro    | wipro123  |
      | imvsunil | imvsunil123|
  Scenario Outline: Unsuccessful login with invalid credentials
    Given I navigate to the login page
    When I click on the sign in button
    And I enter "<username>" and "<password>"
    And I click on the login button
    Then I should see an error message

    Examples:
      | username | password    |
      | imvsunil | invalidpass |
      | imvsunil | InvalidPass |       
    