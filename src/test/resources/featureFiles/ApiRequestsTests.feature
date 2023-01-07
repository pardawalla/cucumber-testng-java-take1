Feature: Is it Friday yet?
  Everybody wants to know when it's Friday
  Based on the 10 minute tutorial https://cucumber.io/docs/guides/10-minute-tutorial/?lang=java

  @apiTests
  Scenario: St John is in the list of charities in Trandme Sandbox environment
    Given the API endpoint "https://api.tmsandbox.co.nz/v1/Charities.json"
    When I make a GET request
    Then the response contains "St John"

  @apiTests
  Scenario: I can make a POST Request
    Given the API endpoint "https://httpbin.org/anything"
    When I make a POST request using the JSON data "{\"ExistingListingId\":123,\"Category\":\"ABC\",\"Title\":\"ABC\",\"Subtitle\":\"ABC\",\"Description\":[\"ABC\",\"ABC\"],\"StartPrice\":123.0,\"ReservePrice\":123.0,\"BuyNowPrice\":123.0,\"Duration\":0,\"EndDateTime\":\"\\/Date(1514764800)\\/\",\"Pickup\":0,\"PickupSuburbId\":123,\"IsBrandNew\":false,\"AuthenticatedMembersOnly\":false,\"IsClassified\":false,\"OpenHomes\":[{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"},{\"Start\":\"\\/Date(1514764800)\\/\",\"End\":\"\\/Date(1514764800)\\/\"}]}"
    Then the POST request returns with the status code "200"
