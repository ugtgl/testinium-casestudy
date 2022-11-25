import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    By continueButton = By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[2]/div/div[2]/button");
    By discountedPrice = By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[3]/span[1]");
    By priceWithoutDiscount = By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[3]/span[2]");
    By bodySize = By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[1]/div[1]/span[2]");
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkProductDiscount(){
        float discountValue = Float.parseFloat(driver.findElement(discountedPrice).getText().replaceAll("[^\\d.]+", ""));
        float normalValue = Float.parseFloat(driver.findElement(priceWithoutDiscount).getText().replaceAll("[^\\d.]+", ""));
        return discountValue < normalValue;
    }
    public String getBodySizeInCart(){
        return driver.findElement(bodySize).getText();
    }
    public String getPriceInCart(){
        return driver.findElement(discountedPrice).getText();
    }


    public void goToLoginPage(){
        driver.findElement(continueButton).click();
    }
}
