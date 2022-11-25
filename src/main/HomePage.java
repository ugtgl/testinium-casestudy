import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By searchBar = By.id("search");
    By myCartButton = By.xpath( "/html/body/div[2]/header/div/div/div[3]/div[3]/button");
    By removeProductFromCartButton = By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[2]/div/div[3]");
    By confirmRemovalButton = By.xpath("/html/body/div[5]/div[2]/div/div[2]/button[2]");
    By cartEmptyText = By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[2]/span");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOnHomePage(){
        return driver.getCurrentUrl().equals("https://www.network.com.tr/");
    }

    public void searchProduct(String searchText){
        driver.findElement(searchBar).sendKeys(searchText + Keys.ENTER);
    }
    public void openMyCartSection(){
        driver.findElement(myCartButton).click();
    }
    public void removeProductFromCart(){
        driver.findElement(removeProductFromCartButton).click();
        driver.findElement(confirmRemovalButton).click();
    }
    public String checkCartEmptyStatus(){
        driver.findElement(myCartButton).click();
        return driver.findElement(cartEmptyText).getText();
    }
}
