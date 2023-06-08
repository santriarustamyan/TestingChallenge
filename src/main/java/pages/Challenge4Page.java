package pages;

import classes.CreatorRandomCNP;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.Duration;

public class Challenge4Page {
    WebDriver driver;
    WebDriverWait wait;
    private final By inputBoxCNPPath = By.className("inputbox");
    private final By checkValidityBtnPath = By.className("button");
    private final By countRightNumberPath = By.cssSelector("body > div.grid-container.t15.main > div > div > div.row.group.t20 > div > p > span");

    public Challenge4Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void inputCNP(String numberCNP) {
        wait.until(ExpectedConditions.elementToBeClickable(inputBoxCNPPath)).sendKeys(numberCNP);
    }

    public void clickCheckValidityBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(checkValidityBtnPath)).click();
    }

    public void clickAlertBtn() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {
        }
    }

    public void findCNPNumber() throws ParseException {
        CreatorRandomCNP creatorRandomCNP = new CreatorRandomCNP();
        do {
            inputCNP(creatorRandomCNP.RandomCNP());
            clickCheckValidityBtn();
            clickAlertBtn();
            clickAlertBtn();
        } while (driver.findElement(countRightNumberPath).getText() == "" || Integer.parseInt(driver.findElement(countRightNumberPath).getText()) < 5);
    }
}
