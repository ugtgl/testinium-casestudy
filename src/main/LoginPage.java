import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    By email = By.xpath("//*[@id=\"n-input-email\"]");
    By password = By.xpath("//*[@id=\"n-input-password\"]");
    By homePageButton = By.xpath("//*[@id=\"home\"]/div[4]/header/div/div/div[2]/a");
    By loginButton = By.xpath("//*[@id=\"login\"]/button");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillUserInformation()throws Exception{
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/main/"+ "userinfo.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        driver.findElement(this.email).sendKeys(records.get(0).get(0));
        driver.findElement(this.password).sendKeys(records.get(0).get(1));
    }
    public boolean isLoginButtonDisplayed(){
        return driver.findElement(loginButton).isDisplayed();
    }

    public void goToHomePage(){
        driver.findElement(homePageButton).click();
    }
}
