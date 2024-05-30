package StepDefinations;

import PageObjects.LoginPage;
import UtilityClasses.ExcelUtility;
import UtilityClasses.SeleniumHighlighterUtility;
import UtilityClasses.WebDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.time.Duration;
import java.util.Scanner;

import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = WebDriverManager.getChromeDriver();

    @Given("I am on the Amazon login page")
    public void i_am_on_the_Amazon_login_page() throws Exception {
        //driver = WebDriverManager.getChromeDriver();
        driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=900&openid." +
                "return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp" +
                "%252Fyourstore%252Fhome%26useRedirectOnSuccess%3D1%26signIn%3D1%26action%3Dsign-out%" +
                "26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid." +
                "ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
    }

    @When("I enter my email address {string} on the login page")
    public void i_enter_my_email_address(String email) throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
    }

    @When("I click on the Continue button on the login page")
    public void i_click_on_the_Continue_button() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickContinue();
    }

    @When("I enter my password {string} on the login page")
    public void i_enter_my_password(String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword(password);
    }

    @When("I click on the Sign-In button on the login page")
    public void i_click_on_the_Sign_In_button() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInSubmit();
//        Scanner scanner = new Scanner (System.in);
//        System.out.print("Enter your name");
//        String name = scanner.next(); // Get what the user types.

    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        LoginPage loginPage = new LoginPage(driver);
        String welcomeMessageText = loginPage.isWelcomeMessageDisplayed();
        assertTrue(welcomeMessageText.contains("Hello,"));
        ExcelUtility excelUtility = new ExcelUtility("D:/ESoft_Solutions/AutomationPractice/Amazon/src/test/resources/TestData/AmazonData.xlsx");
        excelUtility.setSheet("LoginPageData");
        String[] welcomeMessage = {
                welcomeMessageText
        };
        excelUtility.writeData(1, welcomeMessage, "Green");
    }

    @Then("I should see an error message on the login page")
    public void i_should_see_an_error_message() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isErrorMessageDisplayed());
        Thread.sleep(2000);
    }

    @Then("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        assertTrue(driver.getCurrentUrl().contains("https://www.amazon.in/ap/signin"));
        driver.quit();
    }
}
