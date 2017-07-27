package pages;

import helpers.FluentWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.browserPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage extends browserPage {

    FluentWait wait =new FluentWait();

    public LoginPage clickGirisYapButton(){

        WebElement girisYapButton=driver.findElement(By.xpath("//a[text()='Giriş Yap']"));
        wait.fluentWait(By.xpath("//a[text()='Giriş Yap']"),driver);
        girisYapButton.click();
        return this;
    }

    public LoginPage loginViaFacebookIleGirisButton(String Email ,String Password){

        String winHandleBefore = driver.getWindowHandle();

        WebElement facebookIleGirisButton=driver.findElement(By.xpath(".//*[@id='loginForm']/div[4]"));
        facebookIleGirisButton.click();

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebElement emailTextField=driver.findElement(By.id("email"));
        emailTextField.sendKeys(Email);

        WebElement passwordTextField=driver.findElement(By.id("pass"));
        passwordTextField.sendKeys(Password);

        WebElement loginButton=driver.findElement(By.id("u_0_2"));
        Actions actions = new Actions(driver);
        actions.moveToElement(loginButton).click().build().perform();

        try{
            WebElement wrongPasswordText=driver.findElement(By.xpath(".//*[@id='error_box']/div[2]"));
            wait.fluentWait(By.xpath(".//*[@id='error_box']/div[2]"),driver);
            boolean flag=true;
            if (wrongPasswordText.isDisplayed()){
                flag=false;
                driver.switchTo().window(winHandleBefore);
                assertThat("Hatalı Şifre Girildi",flag);
            }
        }catch (Exception e){

        }

        driver.switchTo().window(winHandleBefore);
        driver.get("http://www.n11.com/");


        return this;
    }

    public LoginPage checkUserIsLogin(String expectedEmail){

        WebElement Email=driver.findElement(By.xpath("//a[@class='username']"));
        String actualUserEmail=Email.getAttribute("innerHTML");
        System.out.println("userEmail : "+actualUserEmail);

        assertThat(expectedEmail,is(equalTo(actualUserEmail)));
        return this;
    }



}
