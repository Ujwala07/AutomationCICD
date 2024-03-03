
@tag
Feature: Purchase the order from the Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page.
  

  @regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page.

    Examples: 
      | name              |     password | productName     |
      | saavi07@gmail.com |     Saavi#07 | ADIDAS ORIGINAL |
     
