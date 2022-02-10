@REQ_OF-499
Feature: Nocibe_Création de compte

  Background:
    #@PRECOND_OF-583
    Given User is on homepage
    When User clicks on profile icon
    And User click on the button 'Je crée un compte'
    Then User must be redirected to the account creation page
    #@PRECOND_OF-507
    Given User is on new account page view one
    When User fills out the form
      | civility | firstname | surname | email                  | password   | choice |
      | Monsieur | Richnel   | Ndo     | ndorichnelle@gmail.com | Nd@r1chnel | Non    |
    And User click on button 'Continuer'
    Then The second view of account creation is shown

  #An user has correctly filled out the *account creation form* and clicked on *create my account* so the user must see his *personal space displayed*
  @TEST_OF-507
  Scenario Outline: Display of a personal space for the user without loyalty card
    Given User is on new account page view two
    When User fills in his contact details "<address>" "<country>" <zipcode>
    And User fills in mobile number field "<phone>"
    And User select his date of birth "<year>" "<month>" "<day>"
    And User click on button 'Créer un compte'
    Then User must be redirected to his personal account space
    Examples:
      | address               | country | zipcode | phone      | year | month  | day |
      | 10 rue Gaston Dourdin | France  | 93200   | 0606060606 | 1983 | August | 29  |
