import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductPage {
    WebDriver driver;
    By showMoreButton = By.xpath("//*[@id=\"pagedListContainer\"]/div[2]/div[2]/button");
    By products = By.xpath("//*[contains(concat( \" \", @class, \" \" ), concat( \" \", \"product__title\", \" \" ))]");
    By checkOutButton = By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[3]/a");
    By allowCookiesButton = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    By bodySizeText = By.xpath("//*[@id=\"product-133586\"]/div/div[1]/div/div/div/div[7]/label");
    By bodySizeButton = By.xpath("//*[@id=\"product-133586\"]/div/div[1]/div/div/div/div[7]");
    By productPrice = By.xpath("//*[@id=\"product-133586\"]/div/div[2]/div/div[2]/div/span[2]");
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void allowCookies(){
        driver.findElement(allowCookiesButton).click();
    }

    public void scrollToShowMoreButton(){
        JavascriptExecutor j = (JavascriptExecutor)this.driver;
        j.executeScript("arguments[0].scrollIntoView({'block':'center','inline':'center'})", driver.findElement(showMoreButton));
    }

    public void clickShowMoreButton(){
        driver.findElement(showMoreButton).click();
    }
    public boolean isOnSecondPage(){
        return driver.getCurrentUrl().equals("https://www.network.com.tr/search?searchKey=ceket&page=2");
    }
    public void hoverToFirstDiscountedProduct(){
        Actions act = new Actions(driver);
        WebElement firstDiscountedProduct = driver.findElements(products).get(0);
        act.moveToElement(firstDiscountedProduct).build().perform();
    }

    public String getBodySizeOfFirstDiscountedProduct(){
        return driver.findElement(bodySizeText).getAttribute("innerHTML");
    }
    public String getPriceOfFirstDiscountedProduct(){
        return driver.findElement(productPrice).getText();
    }
    public void selectProduct(){
        driver.findElement(bodySizeButton).click();
    }

    public void goToCheckOut(){
        driver.findElement(checkOutButton).click();
    }

}
