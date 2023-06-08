package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Challenge4Page;
import pages.HomePage;

import java.text.ParseException;

public class CommonStepDefinitions {
    public static WebDriver driver;
    public static HomePage homePage;
    public static Challenge4Page challenge4Page;

    @Before
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(co);
        homePage = new HomePage(driver);
        challenge4Page = new Challenge4Page(driver);
    }

    @Given("I am on the Home page")
    public void goHomePage() {
        driver.get("http://testingchallenges.thetestingmap.org/index.php");
        driver.manage().window().maximize();
    }

    @And("Go challenge4 page")
    public void goChallengePage() {
        homePage.clickButtonChallengeBtn();
    }

    @Then("Find CNP Numbers")
    public void inputCNPNumber() throws ParseException {
        challenge4Page.findCNPNumber();
    }
}
