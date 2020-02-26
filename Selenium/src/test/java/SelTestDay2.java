import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


public class SelTestDay2 {

    WebDriver driver;

    @Ignore
    @Test
    public void testAssertions() {
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = null;
        String str4 = "abc";
        String str5 = "abc";

        int val1 = 5;
        int val2 = 6;

        String[] expextedArray = {"one", "two", "three"};
        String[] resultArray = {"one", "two", "three"};

        assertEquals(str1, str2);
        assertTrue(val1 < val2);
        assertFalse(val1 > val2);
        assertNotNull(str1);
        assertNull(str3);
        assertSame(str4, str5);
        assertNotSame(str1, str3);
        assertArrayEquals(expextedArray, resultArray);
    }

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danieldohotar\\IdeaProjects\\Selenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");
    }

    @Test
    public void homepage() {
        String pageTitle = driver.getTitle();
        System.out.println(driver.getTitle());
        assertEquals(pageTitle, "Madison Island");
        String currentUrl = driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(), "http://qa2.dev.evozon.com/");
        boolean logo = driver.findElement(By.cssSelector(".logo")).isDisplayed();
        assertTrue(logo);
        driver.findElement(By.cssSelector(".logo")).click();
        assertEquals(currentUrl, "http://qa2.dev.evozon.com/");
        driver.navigate().to("http://qa2.dev.evozon.com/accessories/eyewear.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();
    }

    @Test
    public void account() {
        driver.findElement(By.cssSelector(".skip-account")).click();
        boolean accountListDisplayed = driver.findElement(By.cssSelector("#header-account")).isDisplayed();
        assertTrue(accountListDisplayed);
        List<WebElement> myAccountDropdowlist = driver.findElements(By.cssSelector("#header-account .first"));
        boolean found = false;
        String a = "My Account";
        for (WebElement listItem : myAccountDropdowlist) {
            if (listItem.getText().toLowerCase().equals(a.toLowerCase())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        driver.quit();
    }

    @Test
    public void languages() {
        Select se = new Select(driver.findElement(By.cssSelector("#select-language")));
        List<WebElement> languageList = se.getOptions();
        assertEquals(languageList.size(), 3);
        se.selectByVisibleText("French");
        driver.quit();
    }

    @Test
    public void search() throws InterruptedException {
        WebElement searchForWord = driver.findElement(By.cssSelector("#search"));
        searchForWord.clear();
        searchForWord.sendKeys("women");
        driver.findElement(By.cssSelector(".search-button")).click();
        String stringCotains = driver.findElement(By.cssSelector(".page-title")).getText().toUpperCase();
        String searchWord = driver.findElement(By.cssSelector("#search")).getText().toUpperCase();
        assertTrue(stringCotains.contains(searchWord));
        driver.quit();
    }

    @Test
    public void navigation() {
        List<WebElement> list = driver.findElements(By.cssSelector(".level0.parent"));
        Iterator<WebElement> it = list.iterator();
        WebElement sale = driver.findElement(By.cssSelector(".level0.nav-5"));
        sale.click();
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(), "http://qa2.dev.evozon.com/sale.html");
        driver.quit();
    }

    public WebElement getInputByTitle(String title) {
        return driver.findElement(By.cssSelector("li.active input[title='" + title + "']"));
    }

    @Test
    public void checkout() throws InterruptedException {
        WebElement search = driver.findElement(By.id("search"));
        WebElement text = driver.findElement(By.cssSelector("#search"));
        text.sendKeys("eyewear");
        search.submit();
        String stringCotains = driver.findElement(By.cssSelector(".page-title")).getText().toUpperCase();
        String searchWord = driver.findElement(By.cssSelector("#search")).getText().toUpperCase();
        assertTrue(stringCotains.contains(searchWord));
        Random rand = new Random();
        List<WebElement> resultsList = driver.findElements(By.cssSelector(".products-grid .item"));
        WebElement resultItem = resultsList.get(rand.nextInt(resultsList.size() - 1));
        String productName = resultItem.findElement(By.cssSelector(".product-name")).getText();
        resultItem.findElement(By.cssSelector("button")).click();
        Actions actions = new Actions(driver);
        WebElement mainMenu = driver.findElement(By.cssSelector("li.level0.nav-2.parent"));
        actions.moveToElement(mainMenu);
        WebElement subMenu = driver.findElement(By.cssSelector(".level1.nav-2-2"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();
        driver.findElement(By.cssSelector(".actions a[title = \"View Details\"]")).click();
        driver.findElement(By.cssSelector("#option25")).click();
        driver.findElement(By.cssSelector("#option78")).click();
        driver.findElement(By.cssSelector(" .add-to-cart [title= \"Add to Cart\"]")).click();
        driver.findElement(By.cssSelector(" .checkout-types.bottom")).click();
        driver.findElement(By.cssSelector("#onepage-guest-register-button")).click();
        getInputByTitle("First Name").sendKeys("Vasi");
        getInputByTitle("Middle Name/Initial").sendKeys("VasiLica");
        getInputByTitle("Last Name").sendKeys("VasiLica");
        getInputByTitle("Email Address").sendKeys("vasilica@yahoo.com");
        getInputByTitle("Street Address").sendKeys("Muncii");
        getInputByTitle("City").sendKeys("Cluj");
        WebElement state = driver.findElement(By.cssSelector("#billing\\:region_id"));
        state.click();
        getInputByTitle("Zip/Postal Code").sendKeys("123456789");
        Select stateCheckout = new Select(driver.findElement(By.cssSelector("#billing\\:country_id")));
        stateCheckout.selectByValue("AL");
        getInputByTitle("Telephone").sendKeys("0745123456");
        driver.findElement(By.cssSelector("#billing\\:use_for_shipping_no")).click();
        WebElement continueBtn = driver.findElement(By.cssSelector("#billing-buttons-container > button"));
        continueBtn.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping\\:firstname"))).click();
        getInputByTitle("First Name").sendKeys("Vasi");
        getInputByTitle("Middle Name/Initial").sendKeys("VasiLica");
        getInputByTitle("Last Name").sendKeys("VasiLica");
        getInputByTitle("Street Address").sendKeys("Muncii");
        getInputByTitle("City").sendKeys("Cluj");
        Select stateShipping = new Select(driver.findElement(By.name("shipping[country_id]")));
        stateShipping.selectByVisibleText("Romania");
        Select countryShipping = new Select(driver.findElement(By.name("shipping[region_id]")));
        countryShipping.selectByVisibleText("Cluj");
        getInputByTitle("Zip/Postal Code").sendKeys("123456789");
        getInputByTitle("Telephone").sendKeys("0745123456");
        WebElement continueShipping = driver.findElement(By.cssSelector("#shipping-buttons-container > button"));
        continueShipping.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("s_method_freeshipping_freeshipping"))).click();
        driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container .button"))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container .button"))).click();
        String orderPLacedMessage = "THANK YOU FOR YOUR PURCHASE!";
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sub-title"))).click();
        assertEquals(driver.findElement(By.cssSelector(".sub-title")).getText().toUpperCase(), orderPLacedMessage.toUpperCase());
        driver.quit();
    }
}


