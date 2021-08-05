Feature: Address Book

  Scenario: Verify that user is able to add address in the application.
    Given Login to the application
    And Click on Address Book
    When Click on Add Address button
    And Enter Details and Click Add button
    Then Address book should be created

  Scenario: Verify that user is able to delete the added address from the application.
    Given Login to the application
    And Click on Address Book
    When Click on Delete button against newly added address
    Then Address book should be deleted
