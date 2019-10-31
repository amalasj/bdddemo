Feature: Employee feature

  Scenario: Get employee with id
   Given I have employee details in table
   When I make a call to get employee with id 1
   Then the HTTP response status code should be 200
   And employee first name should be 'Lokesh' and last name should be 'Gupta'

   Scenario: Get all employees and check ids
       Given I have employee details in table
       When I make a call to get all employees
       Then the HTTP response status code should be 200
       And employee ids should include the following:
        | 1 |
        | 2 |
        | 3 |


     Scenario: Get all employees and check all details
         Given I have employee details in table
         When I make a call to get all employees
         Then the HTTP response status code should be 200
         And employee details should include the following:
         | id  | firstName | lastName     |                 email          |
         |    1| Lokesh    | Gupta        |        howtodoinjava@gmail.com |
         |    2| Alex      | Kolenchiskey |             abc@gmail.com      |
         |    3| Joseph    | Xavier       |         titanic@gmail.com      |


     Scenario Outline: Get employee with id - multiple scenarios
        When I make a call to get employee with id <id>
        Then the HTTP response status code should be <statusCode>
        And employee first name should be '<firstName>' and last name should be '<lastName>'

       Examples:
        | id  | firstName | lastName     | statusCode |
        |    1| Lokesh    | Gupta        |        200 |
        |    2| Alex      | Kolenchiskey |        200 |


     Scenario: Add employee
        When I make a call to add employee with below details:
         | firstName | lastName     |                 email          |
         | John      | Joseph       |        howtodoinjava@gmail.com |
        Then the HTTP response status code should be 200

