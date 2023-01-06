Feature: Is it Friday yet?
  Everybody wants to know when it's Friday
  Based on the 10 minute tutorial https://cucumber.io/docs/guides/10-minute-tutorial/?lang=java

  @example
  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"