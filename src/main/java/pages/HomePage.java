package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    private final By challenge4BtnPath = By.cssSelector("[href='challenge4.php']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickButtonChallengeBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(challenge4BtnPath)).click();
    }

}
