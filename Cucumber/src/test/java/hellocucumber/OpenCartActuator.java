package hellocucumber;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class OpenCartActuator {
    private static WebDriver driver;
    private static WebDriverWait wait;


    public void initSessionAsUser(String webDriver, String path) {
        // webDriver = "webdriver.chrome.driver"
        System.setProperty(webDriver, path);

        // new chrome driver object
        driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/opencart/index.php?route=product/product&language=en-gb&product_id=43");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().setPosition(new Point(700, 5));



        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }


    //////////////////////////////////////////////////////////////////////////////
    public void submit_review(String review, String name, String rating) {
        try {
            // Scroll to the reviews button
            WebElement reviewsButton = driver.findElement(By.xpath("//div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reviewsButton);
            Thread.sleep(500); // Ensure smooth scrolling (optional)

            // Click the reviews button
            reviewsButton.click();

            // Fill out the "Your Name" field
            WebElement usernameField = driver.findElement(By.xpath("//div[2]/input[1]"));
            usernameField.sendKeys(name);

            // Fill out the "Your Review" field
            driver.findElement(By.xpath("//div[3]/textarea[1]")).sendKeys(review);

            System.out.println(rating);
            String xpathString = "//input["+rating+"]";
            System.out.println(xpathString);
            driver.findElement(By.xpath(xpathString)).click();
            // Submit the review
            driver.findElement(By.xpath("//*[@id='button-review']")).click();

        } catch (Exception e) {
            System.err.println("Error during review submission: " + e.getMessage());
        }
    }

    public void verifyConfirmationMessage(String expectedMessage) {
        try {
            // Wait for the confirmation message to be visible
            WebElement confirmationMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));

            // Get the actual message text
            String actualMessage = confirmationMessage.getText();

            // Assert the message matches the expected message
            if (actualMessage.contains(expectedMessage)) {
                System.out.println("Confirmation message is correct: " + actualMessage);
            } else {
                System.err.println("Confirmation message mismatch! Expected: " + expectedMessage + ", but got: " + actualMessage);
            }
        } catch (TimeoutException e) {
            System.err.println("Confirmation message did not appear within the timeout period.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    public void validate_input(String comment, String name) {
        // Validate name length
        if (name.length() < 3 || name.length() > 25) {
            throw new IllegalArgumentException("Name must be between 3 and 25 characters. Provided: " + name);
        }

        // Validate comment length
        if (comment.length() < 25 || comment.length() > 1000) {
            throw new IllegalArgumentException("Comment must be between 25 and 1000 characters. Provided: " + comment);
        }

        System.out.println("Input is valid: Name: " + name + ", Comment: " + comment);
    }

    public void check_comments_allowed() {
        try {
            // Attempt to locate the username field
            WebElement usernameField = driver.findElement(By.xpath("//div[2]/input[1]"));
            System.out.println("Username field found: " + usernameField.isDisplayed());
        } catch (NoSuchElementException e) {
            // Throw a custom exception or print the error message if the element is not found
            throw new NoSuchElementException("Username field not found. Ensure the comments section is accessible.", e);
        }
    }

}