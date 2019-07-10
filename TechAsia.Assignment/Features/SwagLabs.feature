Feature: Assignments For swaglabs
Scenario: Validate the login page with valid and invalid user
Given Open the browser
Given Open the URL
|https://www.saucedemo.com/ |
Then validate user details by enter valid and invalid data
	|Username|Password|
	|standard_user|secret_sauce|
	|problem_user|secret_sauce|
	|abcdefgh|xxxxxxxx|


Scenario: Add a product and go to checkout
Given Open the browser
Given Open the URL
|https://www.saucedemo.com/ |
Then User enters crediential to login
|standard_user|secret_sauce|
And Click the login button
Then Add a product from homepage
And go to cart page and validate the product
Then Click on checkout
Then Enter delivery details
And Finish the order

Scenario: Verify the filter the inventory page
Given Open the browser
Given Open the URL
|https://www.saucedemo.com/ |
Then User enters crediential to login
|standard_user|secret_sauce|
And Click the login button
Then Check the name sort filter
Then check the price low to high filter
Then check the price high to low filter