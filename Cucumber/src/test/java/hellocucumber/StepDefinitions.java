package hellocucumber;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.BeforeEach;

public class StepDefinitions {
    private static OpenCartActuator opencartUser;
    private static OpenCartActuatorAdmin opencartManager;
    private final String webDriver = "webdriver.chrome.driver";
    private final String path = "C:\\Users\\מתן איסר\\Desktop\\איכות תוכנה\\assignment 4\\2025-mbt-n-main\\Selenium\\chromedriver.exe";

    public void OpenCartInitGuest() {
        System.out.println("--------------- INITIALIZING MODULE TEST - OPENING WEBPAGE ---------------");
        opencartUser = new OpenCartActuator();
        opencartUser.initSessionAsUser(webDriver, path);
    }
    public void OpenCartInitAdmin() {
        System.out.println("--------------- INITIALIZING MODULE TEST - OPENING WEBPAGE ---------------");
        opencartManager = new OpenCartActuatorAdmin();
        opencartManager.initSessionAsAdmin(webDriver, path);
    }

    //------------------scenario 1------------------
    // Initialize the guest user and navigate to the product page
    @Given("guest user is on the product page")
    public void guestUserIsOnProductPage() {
        OpenCartInitGuest();
    }
    // Verifies that comments for guests are enabled
    @And("comments for guests are enabled")
    public void commentsForGuestsAreEnabled() {
        opencartUser.check_comments_allowed();
    }
    // Checking that the input is valid in the review section of the product
    @And("the inputs: {string}, {string}, are valid")
    public void theInputsAreValid(String comment, String name) {
        opencartUser.validate_input(comment, name);
    }

    // Fill and submit the review form
    @When("the guest submits a {string}, their {string}, and their {string}")
    public void guestSubmitsAReview(String comment, String name, String rating) {
        opencartUser.submit_review(comment, name, rating);
    }
    // Checks whether review submission success message was displayed
    @Then("message should be displayed: 'Thank you for your review. It has been submitted to the webmaster for approval.'")
    public void confirmationMessageIsDisplayed() {
        opencartUser.verifyConfirmationMessage("Thank you for your review. It has been submitted to the webmaster for approval."); // Verify the confirmation message
    }


    //------------------scenario 2------------------
    //Initialize the Admin
    @Given("the admin is logged in to the admin panel")
    public void AdminLogin() {
        OpenCartInitAdmin(); // Initialize admin session
        opencartManager.loginAsAdmin("matan", "123matan"); // Log in as admin
    }
    // Navigate to the right section in the settings
    @And("the admin navigates to the settings for product comments")
    public void adminNavigatesToProductComments() {
        opencartManager.navigateToReviewsButton();
    }
    // Verifies that comments were enabled to begin with
    @And("the comments for guests are enabled")
    public void theCommentsForGuestsAreEnabled() {
        opencartManager.check_guest_comments_enabled();
    }
    // Disabled the option for guests to comment
    @When("the admin disables the \"Allow guest comments\" option")
    public void adminDisablesGuestComments() {
        opencartManager.disable_comments_guests();
    }
    // Saves the applied changes
    @And("saves the changes")
    public void saveChanges() {
        opencartManager.saveChanges();
    }
    // Checks whether confirmation message was displayed
    @Then("message should be displayed: 'Success: You have modified settings!'")
    public void messageIsDisplayed() {
        opencartManager.messageIsDisplayed();
    }




}




