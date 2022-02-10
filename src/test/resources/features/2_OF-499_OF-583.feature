@REQ_OF-499
Feature: Nocibe_Création de compte

  Background:
    #@PRECOND_OF-583
    Given User is on homepage
    When User clicks on profile icon
    And User click on the button 'Je crée un compte'
    Then User must be redirected to the account creation page

  #An user has correctly filled out the *account creation form* and clicked on *create my account* so the user must see his *personal space displayed*
  @TEST_OF-507
  Scenario Outline: Successful completion of account creation first step
    Given User is on new account page view one
    When User choose civility "<civility>"
    And User fills out fields "<firstname>" and "<surname>"
    And User fills out credentials fields "<email>" and "<password>"
    And On field nocibe loyalty card user click on "<choice>"
    And User click on button 'Continuer'
    Then The second view of account creation is shown
    Examples:
      | civility | firstname | surname | email               | password   | choice |
      | Monsieur | Richnel   | Ndo     | ndorichne@gmail.com | Nd@r1chnel | Non    |

  #An user who does not fill in the *required fields* of the *account creation form* should not have the opportunity to click on the button *create my account*
  @TEST_OF-517
  Scenario: Filling in the required fields of the form
    Given User is on new account page view one
    When User scrolls to bottom of the page without filling any fields
    Then User should see button 'Continuer' disabled

 #A user enters an *email* with an *invalid format* then the user should see an *error message* on the email field
  @TEST_OF-528
  Scenario Outline: Check the incorrect email field
    Given User is on new account page view one
    When User fills email field "<email>"
    Then User should see error message for email field
    Examples:
      | email       |
      | m@.2020     |
      | mgmail@2020 |

  #A user enters a password with an *invalid format* then the user should see an *error message* in the password field
  @TEST_OF-532
  Scenario Outline: Check the incorrect password field
    Given User is on new account page view one
    When User fills password field "<password>"
    Then User should see error message for password field
    Examples:
      | password    |
      | D@r2        |
      | dodo2020    |

  #Un utilisateur lors de l’ouverture d’un compte doit pouvoir renseigner sa carte de fidélité
  @TEST_OF-537
  Scenario: Availability of the loyalty card
    Given User is on new account page view one
    When User scrolls to the bottom of the page
    And User select "Oui" On the field 'Avez-vous une carte de fidélité Nocibé ?'
    Then User should be able to enter fidelity card number

  #An user should be able to modify password visibility
  @TEST_OF-539
  Scenario Outline: Check visibility of password field
    Given User is on new account page view one
    When User fills password field "<password>"
    And User click on discover password
    Then Password must be visible
    Examples:
      |password   |
      | Ben@10Ten |