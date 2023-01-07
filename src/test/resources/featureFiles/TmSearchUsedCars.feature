Feature: Search for used cars on TradeMe
    Search Trademe for an existing used car listing and verify the results

  @webUI
  Scenario: Find and verify details of an existing user car listing on Trademe
    Given I am on the TradeMe Motors search page
    When I search for used cars based on the criteria
      | Keywords          | Make   | Model | Body style    |
      | 3897609660 NEC128 | Toyota | C-HR  | RV/SUV, Coupe |
    Then on viewing the search result I can see the following info
      | Number plate | Kilometers | Body style | Seats |
      | NEC128       |     38,952 | RV/SUV     |     5 |
