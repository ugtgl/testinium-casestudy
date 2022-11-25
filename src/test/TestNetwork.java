import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class TestNetwork {
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    LoginPage loginPage;
    WebDriver driver;
    @BeforeAll
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.network.com.tr/");
    }

    @Test
    @Order(1)
    public void checkHomePage(){
        homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isOnHomePage() ,
                "Not on homepage.");
    }
    @Test
    @Order(2)
    public void checkSecondPage()throws Exception{
        homePage = new HomePage(driver);
        homePage.searchProduct("ceket");
        productPage = new ProductPage(driver);
        Thread.sleep(1000);
        productPage.allowCookies();
        Thread.sleep(1000);
        productPage.scrollToShowMoreButton();
        Thread.sleep(1000);
        productPage.clickShowMoreButton();
        Thread.sleep(1000);
        Assertions.assertTrue(productPage.isOnSecondPage() ,
                "Not on second page.");
    }

    @Test
    @Order(3)
    public void checkDiscount()throws Exception{
        productPage = new ProductPage(driver);
        productPage.hoverToFirstDiscountedProduct();
        Thread.sleep(1000);
        String bodySizeToBeExpected = productPage.getBodySizeOfFirstDiscountedProduct();
        String priceOfTheProduct = productPage.getPriceOfFirstDiscountedProduct();
        productPage.selectProduct();
        productPage.goToCheckOut();
        Thread.sleep(1000);
        cartPage = new CartPage(driver);
        Assertions.assertEquals(bodySizeToBeExpected,cartPage.getBodySizeInCart());
        Assertions.assertEquals(priceOfTheProduct,cartPage.getPriceInCart());
        Thread.sleep(1000);
        cartPage = new CartPage(driver);
        Assertions.assertTrue(cartPage.checkProductDiscount() ,
                "Doesn't have any discount.");
        cartPage.goToLoginPage();
    }
    @Test
    @Order(4)
    public void checkLogin() throws Exception {
        loginPage = new LoginPage(driver);
        Thread.sleep(1000);
        loginPage.fillUserInformation();
        Thread.sleep(1000);
        Assertions.assertTrue(loginPage.isLoginButtonDisplayed(),"There is no login button.");
        loginPage.goToHomePage();
    }
    @Test
    @Order(5)
    public void checkCartEmptiness() throws Exception {
        homePage = new HomePage(driver);
        Thread.sleep(1000);
        homePage.openMyCartSection();
        Thread.sleep(1000);
        homePage.removeProductFromCart();
        Thread.sleep(1000);
        Assertions.assertEquals("Sepetiniz Henüz Boş",homePage.checkCartEmptyStatus());
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
