Feature: Manage Product Comments

  Scenario Outline: Guest adds a comment to a product
    #user on MacBook
    Given guest user is on the product page
    #check in Admin that allow comments for guests is checked
    And comments for guests are enabled
    And the inputs: "<comment>", "<name>", are valid
    #check later
    When the guest submits a "<comment>", their "<name>", and their "<rating>"
    #Selenium should detect this message
    Then message should be displayed: 'Thank you for your review. It has been submitted to the webmaster for approval.'


  Examples:
    | name            | comment                                           | rating |
    | keren@gmail.com | wow.wow.wow.WOW! what a great product :^))))))))  |  5     |
    | maaaa           | lollllllllllllllllllllllllllllllllllllllllllll    | 3      |



  Scenario: Admin disables adding comments by guests
    Given the admin is logged in to the admin panel
    And the admin navigates to the settings for product comments
    And the comments for guests are enabled
    When the admin disables the "Allow guest comments" option
    And saves the changes
    Then message should be displayed: 'Success: You have modified settings!'



