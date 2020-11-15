package ohtu;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

  //WebDriver driver = new ChromeDriver();
  WebDriver driver = new HtmlUnitDriver();
  String baseUrl = "http://localhost:4567";

  @Given("login is selected")
  public void loginIsSelected() {
    driver.get(baseUrl);
    WebElement element = driver.findElement(By.linkText("login"));
    element.click();
  }

  @When("correct username {string} and password {string} are given")
  public void correctUsernameAndPasswordAreGiven(
    String username,
    String password
  ) {
    logInWith(username, password);
  }

  @Then("user is logged in")
  public void userIsLoggedIn() {
    pageHasContent("Ohtu Application main page");
  }

  @When("correct username {string} and incorrect password {string} are given")
  public void correctUsernameAndIncorrectPasswordAreGiven(
    String username,
    String password
  ) {
    logInWith(username, password);
  }

  @Then("user is not logged in and error message is given")
  public void userIsNotLoggedInAndErrorMessageIsGiven() {
    pageHasContent("invalid username or password");
    pageHasContent("Give your credentials to login");
  }

  @When("username {string} and password {string} are given")
  public void usernameAndPasswordAreGiven(String username, String password)
    throws Throwable {
    logInWith(username, password);
  }

  /* my stuff */

  @When("nonexistent username {string} is given")
  public void unsavedUsernameGiven(String username) {
    logInWith(username, "password");
  }

  @Then("system will respond {string}")
  public void systemWillRespond(String pageContent) throws Throwable {
    assertTrue(driver.getPageSource().contains(pageContent));
  }

  @Given("command new user is selected")
  public void newUserCommandSelected() throws Throwable {
    driver.get(baseUrl);
    WebElement element = driver.findElement(By.linkText("register new user"));
    element.click();
  }

  @When(
    "a valid username {string} and password {string} and matching password confirmation are entered"
  )
  public void validUsernameAndPasswordAreGiven(
    String username,
    String password
  )
    throws Throwable {
    registerWith(username, password, password);
  }

  @Then("a new user is created")
  public void newUserIsCreated() throws Throwable {
    pageHasContent("Welcome to Ohtu Application!");
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  /* helper methods */

  private void pageHasContent(String content) {
    assertTrue(driver.getPageSource().contains(content));
  }

  private void logInWith(String username, String password) {
    assertTrue(
      driver.getPageSource().contains("Give your credentials to login")
    );
    WebElement element = driver.findElement(By.name("username"));
    element.sendKeys(username);
    element = driver.findElement(By.name("password"));
    element.sendKeys(password);
    element = driver.findElement(By.name("login"));
    element.submit();
  }

  private void registerWith(
    String username,
    String password,
    String passwordConfirm
  ) {
    assertTrue(
      driver.getPageSource().contains("Create username and give password")
    );
    WebElement element = driver.findElement(By.name("username"));
    element.sendKeys(username);
    element = driver.findElement(By.name("password"));
    element.sendKeys(password);
    element = driver.findElement(By.name("passwordConfirmation"));
    element.sendKeys(password);
    element = driver.findElement(By.name("signup"));
    element.submit();
  }
}
