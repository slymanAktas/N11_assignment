package testCases;

import helpers.ScreenShotTaker;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import pages.HomePage;
import pages.LoginPage;
import utils.browserPage;

import java.io.IOException;

public class testClass extends browserPage {

    LoginPage loginPage   = new LoginPage();
    HomePage    homePage    = new HomePage();


    @Before
    public  void beforeClass(){

        launchBrowser();

        homePage.
        clickDahaSonraButton();
    }

    @After
    public void after() {
        closeBrowser();
    }

    @Rule
    public TestRule listen=new TestWatcher() {
        @Override
        public void failed(Throwable t, Description description) {
            String errorPath=description.getClassName()+"_"+description.getMethodName();
            try {
                for(String winHandle : driver.getWindowHandles()){
                    driver.switchTo().window(winHandle);
                }
                ScreenShotTaker.captureScreenShot(driver,errorPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } ;

    @Test
    public void login_Successfully(){
        loginPage.
        clickGirisYapButton().
        loginViaFacebookIleGirisButton("suleyman@eftsoftware.com","111").
        checkUserIsLogin("suleyman@eftsoftware.com");
    }

    @Test
    public void click_Kitap_Button(){

        homePage.
        mouseOverKitapMuzikFilmOyun().
        clickKitapButton().
        checkUserCanClickKitapMuzikFilmButton("http://www.n11.com/kitap");
    }

    @Test
    public void click_Yazarlar_Button(){

        homePage.
        mouseOverKitapMuzikFilmOyun().
        clickKitapButton().
        clickYazarlarButton().
        checkUserCanClickYazarlarButton("http://www.n11.com/yazarlar");
    }

    @Test
    public void check_If_There_Is_80_Author_In_One_Page(){

        homePage.
        mouseOverKitapMuzikFilmOyun().
        clickKitapButton().
        clickYazarlarButton().
        clickALetter().
        checkIfThereIs80Author();
    }

    @Test
    public void check_Fist_Letter_Is_True(){

        homePage.
        mouseOverKitapMuzikFilmOyun().
        clickKitapButton().
        clickYazarlarButton().
        clickALetter().
        checkIfThereIs80Author().
        checkFistLetterIsTrue();
    }

    @Test
    public void check_Last_Object_Of_First_Page_Is_Present_In_Second_Page(){

        homePage.
        mouseOverKitapMuzikFilmOyun().
        clickKitapButton().
        clickYazarlarButton().
        clickALetter().
        checkIfThereIs80Author().
        checkFistLetterIsTrue().
        checkLastObjectOfFirstPageIsPresentInSecondPage();
    }

    @Test
    public void login_With_Wrong_Password(){
        loginPage.
        clickGirisYapButton().
        loginViaFacebookIleGirisButton("suleyman@eftsoftware.com","WrongPassword").
        checkUserIsLogin("suleyman@eftsoftware.com");
    }

}
