package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.PIMPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

import static utils.Utils.readJSONData;


public class PIMTestRunner extends Setup {

    PIMPage pimPage;
    LoginPage loginPage;

    @BeforeTest
    public void doLogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }



    @Test(priority = 1, description = "Admin cannot create new user with empty firstname")
    public void addNewEmployeeWithEmptyFirstname() {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = " ";
        String lastname = faker.name().lastName();
        String username = faker.name().username();
        String password = faker.letterify("??").toUpperCase() + faker.letterify("??").toLowerCase() + faker.regexify("[!@#$%^&*()_+]{2}") + faker.regexify("[0-9]{2,4}");

        pimPage.createNewEmployee(firstName, lastname, username, password);

        String errorMsgActual = driver.findElements(By.className("oxd-input-group__message")).get(0).getText();
        System.out.println(errorMsgActual);
        Assert.assertEquals(errorMsgActual, "Required");
    }

    @Test(priority = 2, description = "Admin cannot create new user with empty username")
    public void addNewEmployeeWithEmptyusername() {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = " ";
        String password = faker.letterify("??").toUpperCase() + faker.letterify("??").toLowerCase() + faker.regexify("[!@#$%^&*()_+]{2}") + faker.regexify("[0-9]{2,4}");

        pimPage.createNewEmployee(firstName, lastname, username, password);

        String errorMsgActual = driver.findElements(By.className("oxd-input-group__message")).get(0).getText();
        System.out.println(errorMsgActual);
        Assert.assertEquals(errorMsgActual, "Required");
    }

    @Test(priority = 3, description = "Admin cannot create new user with invalid username")
    public void addNewEmployeeWithInvalidusername() {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = "tama";
        String password = faker.letterify("??").toUpperCase() + faker.letterify("??").toLowerCase() + faker.regexify("[!@#$%^&*()_+]{2}") + faker.regexify("[0-9]{2,4}");

        pimPage.createNewEmployee(firstName, lastname, username, password);


    }


    @Test(priority = 4, description = "Admin cannot create new user with invalid password")
    public void addNewEmployeeWithInvalidPassword() {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = faker.name().username();
        String password = "12345678";
        pimPage.createNewEmployee(firstName, lastname, username, password);

        String errorMsgActual = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        System.out.println(errorMsgActual);
        Assert.assertEquals(errorMsgActual, "Your password must contain minimum 1 lower-case letter");
    }


    @Test(priority = 5, description = "Admin can create new user with valid creds")
    public void addNewEmployee() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String employeeid = pimPage.textElem.get(4).getAttribute("value");
        String username = faker.name().username();
        String password = faker.letterify("??").toUpperCase() + faker.letterify("??").toLowerCase() + faker.regexify("[!@#$%^&*()_+]{2}") + faker.regexify("[0-9]{2,4}");

        pimPage.createNewEmployee(firstName, lastname, username, password);
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement headerTitleElem = driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]"));
        System.out.printf(headerTitleElem.getText());
        wait.until(ExpectedConditions.visibilityOf(headerTitleElem));
        String textTile = headerTitleElem.getText();
        Assert.assertTrue(textTile.contains("Personal Details"));

        Utils.saveUsers(firstName, lastname, employeeid, username, password);

    }


    @Test(priority = 6, description = "Admin cannot search employee details with invalid ID")
    public void searchEmployeeWithInvalidID() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.searchbar.get(1).sendKeys("hjh");
        Thread.sleep(2000);
        pimPage.searchbutton.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-text--span")));

        String msgActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        System.out.println(msgActual);
        String msgExpected = "No Records Found";
        Assert.assertTrue(msgActual.contains(msgExpected));
    }

    @Test(priority = 7, description = "Admin can search employee details with valid ID")
    public void searchEmployeeWithValidID() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();

        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String empId = jsonObject.get("empId").toString();

        pimPage.searchbar.get(1).sendKeys(empId);
        Thread.sleep(1000);
        pimPage.searchbutton.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-text--span")));

        String msgActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        System.out.println(msgActual);
        String msgExpected = "Record Found";
        Assert.assertTrue(msgActual.contains(msgExpected));


    }

    @Test(priority = 8, description = "Admin cannot search employee details with invalid name")
    public void searchEmployeeWithInvalidName() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(8).click();

        driver.findElements(By.tagName("input")).get(1).sendKeys("1234");
        Thread.sleep(2000);
        pimPage.searchbutton.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-text--span")));

        String msgActual = driver.findElement(By.className("oxd-input-group__message")).getText();
        System.out.println(msgActual);
        String msgExpected = "Invalid";
        Assert.assertTrue(msgActual.contains(msgExpected));

    }

    @Test(priority = 9, description = "Admin can search employee details with valid name and logout")
    public void searchEmployeeWithValidName() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenubar.get(8).click();
        JSONArray empArray = readJSONData();
        JSONObject jsonObject = (JSONObject) empArray.get(empArray.size() - 1);
        String firstName = jsonObject.get("firstName").toString();
//        String lastName = jsonObject.get("lastName").toString();
//        String fullname =firstName+" "+lastName;

        // driver.findElements(By.tagName("input")).get(1).sendKeys(fullname);
        driver.findElements(By.tagName("input")).get(1).sendKeys(firstName);
        Thread.sleep(4000);
        driver.findElement(By.className("oxd-autocomplete-option")).click();
        Thread.sleep(2000);
        pimPage.searchbutton.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-text--span")));

        String msgActual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        System.out.println(msgActual);
        String msgExpected = "Record Found";
        Assert.assertTrue(msgActual.contains(msgExpected));

        loginPage = new LoginPage((driver));
        loginPage.doLogout();
        WebElement forgotPasswordLink = driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPasswordLink.isDisplayed());


    }
}
