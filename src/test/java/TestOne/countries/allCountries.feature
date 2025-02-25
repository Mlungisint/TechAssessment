Feature: Back-End Validation

  Background:
    * url 'https://restcountries.com'
    * header Accept = 'application/json'
#    * header Content-Type = 'application/json'

#    BEGIN THE TEST

  ######################################################################################

  Scenario: Ensure Correct API Endpoint Schema Configuration
    Given path '/v3.1/all/'
    When method get
    Then status 200
   * def info = "########## Validate the response ##########";
   * def originalSchema = read("./all.json/")
    And print originalSchema
    And match response ==
  """
  '#[] ##(originalSchema)'
  """


  ########################################################################################

  Scenario: Validate that there are 195 countries for map building
    Given path '/v3.1/all/'
    And method get
    When status 200
#    ######using population as a unique property to count country objects #####
    * def population = $..population
#    * def expectedCount = 195
    * def expectedCount = 250
#    Then print population
    * assert population.length == expectedCount
    * def actualCount = population.length
    And print actualCount
    And print 'actualCount :',actualCount
    



   ########################################################################################
  Scenario: Validate that Sign Language (SASL) is included in the list of South Africa's official languages.
    Given path '/v3.1/all/'
    When method get
    Then status 200
    * def saslSchema = {SASL:'#present'}
    And print response.[21].languages
    And match response.[21].languages contains any saslSchema



