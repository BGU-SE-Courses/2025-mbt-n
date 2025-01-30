package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenCartActuatorAdmin {

    protected WebDriver driver;
    private WebDriverWait wait;

    public void initSessionAsAdmin(String webDriver, String path) {
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // launch website -> localhost
        driver.get("http://localhost/opencart/matan");

        // maximize the window - some web apps look different in different sizes
        //driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public void loginAsAdmin(String username, String password) {
        try {
            // Enter the username
            WebElement usernameField = driver.findElement(By.xpath("//div[1]/div[1]/input[1]"));
            usernameField.sendKeys(username);

            // Enter the password
            WebElement passwordField = driver.findElement(By.xpath("//div[2]/div[1]/input[1]"));
            passwordField.sendKeys(password);

            // Click the login button
            WebElement loginButton = driver.findElement(By.xpath("//button[1]"));
            loginButton.click();

            System.out.println("Admin login successful.");
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate an element during admin login: " + e.getMessage());
        }
    }

    public void navigateToReviewsButton(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 2: Click the system button
            WebElement systemButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/nav[1]/ul[1]/li[9]/a[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", systemButton);
            systemButton.click();

            // Step 3: Click the settings button
            WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/nav[1]/ul[1]/li[9]/ul[1]/li[1]/a[1]")));
            settingsButton.click();

            // Step 4: Click the edit pen
            WebElement editPen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/table[1]/tbody[1]/tr[1]/td[4]/a[1]/i[1]")));
            editPen.click();

            // Step 5: Click the option button
            WebElement optionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/form[1]/ul[1]/li[4]/a[1]")));
            optionButton.click();

            // Step 6: Click the reviews button
            WebElement reviewsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div[2]/h2[1]/button[1]")));
            reviewsButton.click();

            System.out.println("Navigation to product comments settings is complete.");
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate an element during navigation: " + e.getMessage());
            throw e; // Re-throw to fail the test
        } catch (TimeoutException e) {
            System.err.println("Element was not clickable in the given time: " + e.getMessage());
            throw e;
        }
    }

    public void disable_comments_guests(){
        try {
            // Locate the slide button
            WebElement slideButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/input[2]")));
            slideButton.click();
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate the slide button: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }

    public void saveChanges(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div[1]/div[1]/div[1]/button[1]/i[1]")));
            saveButton.click();

            System.out.println("Save changes button clicked successfully.");
        } catch (TimeoutException e) {
            System.err.println("Save changes button was not clickable in the given time: " + e.getMessage());
            throw e; // Re-throw to fail the test
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate the save changes button: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }

    public void messageIsDisplayed(){
        try {
            // Wait for the success message to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'alert-success')]")
            ));

            // Get the text of the message
            String actualMessage = successMessage.getText().trim();

            // Verify the message
            String expectedMessage = "Success: You have modified settings!";
            if (actualMessage.contains(expectedMessage)) {
                System.out.println("Success message is displayed correctly: " + actualMessage);
            } else {
                System.err.println("Success message mismatch! Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'");
            }
        } catch (TimeoutException e) {
            System.err.println("Success message was not displayed within the timeout period: " + e.getMessage());
            throw e; // Re-throw to fail the test
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate the success message: " + e.getMessage());
            throw e; // Re-throw to fail the test
        }
    }
    public void check_guest_comments_enabled(){
        try {
            // Locate the slide button
            WebElement slideButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/input[2]")));

            // Check the current state
            if (!slideButton.isSelected()) {
                // If it's enabled, click to disable
                throw new IllegalArgumentException("Reviews for guests are already off");
            } else {
                System.out.println("comments were enabled");
            }
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate the slide button: " + e.getMessage());
            throw e; // Re-throw the exception to fail the test
        }
    }

}
