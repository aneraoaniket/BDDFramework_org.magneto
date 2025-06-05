Feature: Create an account and sign in on Software Testing Board

	Background: 
    Given User Launch Browser
    When User opens URL

  #Scenario: Verify user able to create an account entering all data
    #When user click on the Create Account button
    #And user fill in the registration form with valid details:
      #| Field            | Value               |
      #| First Name       | shubham             |
      #| Last Name        | Doe                 |
      #| Email Address    | shubham@example.com |
      #| Password         | Password123         |
      #| Confirm Password | Password123         |
    #And click on create an account button
    #Then user should see a confirmation message "Thank you for registering with Main Website Store."
    #And user should be able to sign out
    #And close browser

  Scenario: Verify user able to login with valid credentials
    Given user navigate to the Sign In page
    When user have an existing account with the following credentials:
      | Email Address | shubham@example.com |
      | Password      | Password123         |
    When user click on the Sign In button
    Then user should see the "Welcome, shubham Doe!" message
    And user should be able to sign out
    And close browser

  #Scenario: Verify user unable to create an account with an existing email
    #When user click on the Create Account button
    #And user fill in the registration form with the following details
      #| Field            | Value               |
      #| First Name       | shubham             |
      #| Last Name        | Doe                 |
      #| Email Address    | shubham@example.com |
      #| Password         | Password123         |
      #| Confirm Password | Password123         |
    #And click on create an account button
    #Then user should see an error message "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."
    #And close browser
#
  #Scenario: Verify user should not able to sign in with invalid credentials
    #Given user navigate to the Sign In page
    #When user enter the following invalid credentials:
      #| Email Address | shubhamb@example.com |
      #| Password      | WrongPassword123     |
    #When user click on the Sign In button
    #Then user should see an error message for invalid credentials "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."
    #And close browser
#
  #Scenario: Verify user should not able to create account with mismatched passwords
    #When user click on the Create Account button
    #And user fill in the registration form with the following details
      #| Field            | Value            |
      #| First Name       | shubham          |
      #| Last Name        | Doe              |
      #| Email Address    | abhi@example.com |
      #| Password         | Password123      |
      #| Confirm Password | Password456      |
    #And click on create an account button
    #Then user should see an error message for same value as "Please enter the same value again."
    #And close browser
