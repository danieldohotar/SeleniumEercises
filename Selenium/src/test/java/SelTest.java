import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelTest {


    @Test
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        driver.getTitle();
        System.out.println(driver.getTitle());
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        driver.get("http://qa3.dev.evozon.com/men/tees-knits-and-polos.html");
        driver.findElement(By.cssSelector(".logo")).click();
        driver.navigate().to("http://qa3.dev.evozon.com/accessories/eyewear.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();
    }

    @Test
    public void account()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        driver.findElement(By.cssSelector(".account-cart-wrapper>a[href*='customer/account']")).click();
        driver.findElement(By.cssSelector("#header-account .first")).click();
        driver.quit();
    }

    @Test
    public void languages(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        Select se = new Select(driver.findElement(By.cssSelector("#select-language")));
        List<WebElement> langList = se.getOptions();
        langList.size();
        System.out.println(langList.size());
        se.selectByVisibleText("French");
        driver.quit();
    }
    @Test
    public void search(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        WebElement searchbar = driver.findElement(By.cssSelector("#search"));
        searchbar.clear();
        searchbar.sendKeys("woman");
        driver.findElement(By.cssSelector(".search-button")).click();
        driver.quit();
    }
    @Test
    public void newProductList(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        List<WebElement> list = driver.findElements(By.cssSelector(".item.last"));
        list.size();
        Iterator<WebElement> it = list.iterator() ;

        while(it.hasNext()){
            System.out.println(it.next().getText());
        }
    }
    @Test
    public void navigation(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        List<WebElement> list = driver.findElements(By.cssSelector(".level0.parent"));
        Iterator<WebElement> it = list.iterator();
        WebElement sale = driver.findElement(By.cssSelector(".level0.nav-5"));
        sale.click();
        driver.quit();
    }
}
