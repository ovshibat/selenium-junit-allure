import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class Main {
    public static void main(String[] args) {

        // 1. Start the session
        WebDriver driver = new ChromeDriver();

        // 2. Navigate to the webpage
        driver.get("https://www.saucedemo.com/");

        // 3. Request browser information
        String title = driver.getTitle();
        System.out.println("The title of the webpage is " + title);
        System.out.println("Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        try {
            Thread.sleep(9000); // wait 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 4. Establish Waiting Strategy (Explicit Wait)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 5. Find an element (username input field)
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );

        // 6. Take action on element (enter username)
        usernameField.sendKeys("standard_user");

        // 7. Request element information
        System.out.println("Username Field - Tag Name: " + usernameField.getTagName());
        System.out.println("Username Field - Placeholder: " + usernameField.getAttribute("placeholder"));

        // 8. End the session
        driver.quit();
    }
}
