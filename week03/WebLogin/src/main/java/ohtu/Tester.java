package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();

    // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana

    // driver.get("http://localhost:4567");
    // sleep(2);

    // WebElement element = driver.findElement(By.linkText("login"));
    // element.click();

    // sleep(2);

    // element = driver.findElement(By.name("username"));
    // element.sendKeys("pekka");
    // element = driver.findElement(By.name("password"));
    // element.sendKeys("akkep");
    // element = driver.findElement(By.name("login"));

    // sleep(2);

    // element.submit();

    // sleep(3);

    // uuden käyttäjätunnuksen luominen

    // driver.get("http://localhost:4567/user");
    // sleep(2);

    // WebElement element = driver.findElement(By.name("username"));
    // element.sendKeys("admin");
    // element = driver.findElement(By.name("password"));
    // element.sendKeys("password123");
    // element = driver.findElement(By.name("passwordConfirmation"));
    // element.sendKeys("password123");

    // sleep(2);

    // element = driver.findElement(By.name("signup"));

    // sleep(2);

    // element.click();
    // sleep(2);

    // driver.quit();

    // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta

    driver.get("http://localhost:4567/user");
    sleep(2);

    WebElement element = driver.findElement(By.name("username"));
    element.sendKeys("admin3");
    element = driver.findElement(By.name("password"));
    element.sendKeys("password123");
    element = driver.findElement(By.name("passwordConfirmation"));
    element.sendKeys("password123");

    sleep(2);

    element = driver.findElement(By.name("signup"));

    sleep(2);

    element.click();
    sleep(2);

    element =
      driver.findElement(By.linkText("continue to application mainpage"));
    element.click();

    sleep(2);

    element = driver.findElement(By.linkText("logout"));
    element.click();
    sleep(2);

    driver.quit();
  }

  private static void sleep(int n) {
    try {
      Thread.sleep(n * 1000);
    } catch (Exception e) {}
  }
}
