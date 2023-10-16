# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As a developer I want to test, GitHub Search API Endpoints in my application

  Scenario: Is the returning 200 status
    Given url microserviceUrl
    And path '/latestHotRepositories'
    And param count = 4
    And param page = 0
    When method GET
    Then status 200
    And match header Content-Type contains 'application/vnd.github+json'

  Scenario: Does query param use default values
    Given url microserviceUrl
    And path '/latestHotRepositories'
    When method GET
    Then status 200
    And assert response.total_count != 5
    And assert response.items.length == 5

  Scenario: Does wrong page number return empty list
    Given url microserviceUrl
    And path '/latestHotRepositories'
    And param count = 4
    And param page = 30
    When method GET
    Then status 200
    And assert response.items.length == 0

  Scenario: Does wrong path fail the request
    Given url microserviceUrl
    And path '/latestHotrepositories'
    When method GET
    Then status 404

  Scenario: Does wrong value in query param throw bad request exception
     Given url microserviceUrl
     And path '/latestHotRepositories'
     And param count = 'five'
     When method GET
     Then status 400
     And assert response.error == "Bad Request"


