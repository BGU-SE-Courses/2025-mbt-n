# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [OpenCart](https://www.opencart.com/).

OpenCart is a widely-used, open-source eCommerce platform designed to help businesses create and manage online stores efficiently. 
It offers a user-friendly interface, a robust set of features, and a flexible architecture that supports customization through extensions and themes.

## Installation
To conduct testing on OpenCart, we installed the software locally and set up the necessary environment. Below are the step-by-step instructions to install OpenCart and configure the testing tools.
1. We used XAMPP, a popular cross-platform web server solution, to run OpenCart locally by activating Apache and MySQL services.
2. For automated testing with Provengo, we used a given Selenium Server and run it for the first time with a compatible ChromeDriver we found online.
3. Provengo was used for scenario-based testing, and Graphviz was required to visualize test flow graphs.

To summarize: 
For cucumber testing: xampp, chromedriver
For Provengo testing: xampp, chromedriver, Graphiz, Provengo

## What we tested
In this assignment, we tested the product review module of the OpenCart system, which allows guest users to submit product reviews and enables administrators to manage the review settings. We focused on the following user stories that cover both guest and admin functionalities related to product reviews.

User Story 1: Guest adds a comment to a product
Description:
As a guest user, I want to submit a review for a product so that I can share my opinion without registering an account.

Preconditions:

The guest user is on the product page.
The "Allow guest comments" option is enabled in the admin panel.
The review form inputs (comment, name) are valid.

Steps:

Given the guest user is on the product page.
And comments for guests are enabled in the system.
And the inputs for <comment> and <name> are valid.
When the guest submits their <comment>, <name>, and <rating>.
Then a confirmation message should be displayed:
'Thank you for your review. It has been submitted to the webmaster for approval.'


Expected Outcome:

The guest's review submission should be successful, and a confirmation message should appear.



****************
User Story 2: Admin disables guest comments


Description:
As an admin, I want to disable guest comments on product reviews to prevent spam and unauthorized feedback.

Preconditions:

The admin is logged into the OpenCart admin panel.
The guest comments feature is currently enabled.

Steps:

Given the admin is logged into the admin panel.
And the admin navigates to the settings for product comments.
And the comments for guests are enabled.
When the admin disables the "Allow guest comments" option.
And saves the changes.

Then a confirmation message should be displayed:
'Success: You have modified settings!'

Expected Outcome:

The admin should successfully disable guest comments, and a success message should confirm the update.

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

