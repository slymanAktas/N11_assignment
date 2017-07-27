package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.browserPage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.*;


public class HomePage extends browserPage {


    public HomePage clickDahaSonraButton(){

        try{
            WebDriverWait wait=new WebDriverWait(driver,10);
            WebElement dahaSonraButton=driver.findElement(By.xpath("//button[text()='Daha Sonra']"));
            wait.until(ExpectedConditions.visibilityOf(dahaSonraButton));
            dahaSonraButton.click();
        }catch (Exception e){

        }
        return this;
    }

    public HomePage mouseOverKitapMuzikFilmOyun(){

        Actions action = new Actions(driver);
        WebElement kitapMuzikFilmOyun = driver.findElement(By.xpath(".//*[@id='contentMain']/div/nav/ul/li[8]/a"));
        action.moveToElement(kitapMuzikFilmOyun).build().perform();

        return this;
    }

    public HomePage clickKitapButton(){

        WebElement kitapButton=driver.findElement(By.xpath("//a[@title='Kitap']"));
        kitapButton.click();
        return this;
    }

    public HomePage checkUserCanClickKitapMuzikFilmButton(String expectedURL){

        String actualURL=driver.getCurrentUrl();
        assertThat(actualURL,is(equalTo(expectedURL)));
        System.out.println(actualURL);
        return this;
    }

    public HomePage clickYazarlarButton(){

        WebElement yazarlarButton=driver.findElement(By.xpath("//a[text()='Yazarlar']"));
        JavascriptExecutor je=(JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",yazarlarButton);

        yazarlarButton.click();
        return this;
    }

    public HomePage checkUserCanClickYazarlarButton(String expectedURL){

        String actuelURL=driver.getCurrentUrl();
        assertThat(expectedURL,is(equalTo(actuelURL)));
        return this;
    }

    public HomePage clickALetter(){

        List<WebElement> letters= driver.findElements(By.xpath("//span[@class='alphabetSearch']"));
        Actions action=new Actions(driver);
        action.moveToElement(letters.get(1)).click().build().perform();//Because Of is not clickable at point error

        return this;
    }

    public HomePage checkIfThereIs80Author(){

        List<WebElement> authorsBlock=driver.findElements(By.xpath("//div[@id='authorsList']/div"));
        int total = 0;

        for (int i=1;i<=authorsBlock.size();i++){

            List<WebElement> eachBlock = driver.findElements(By.xpath("//div[@id='authorsList']/div["+i+"]/ul/li"));

            total+=eachBlock.size();
        }
        System.out.println(total);

        boolean count=true;

        if (total>80) {
            count=false;
        }
        assertThat("Sayfadaki yazar sayısı 80'den fazla",count);
        return this;
    }

    public HomePage checkFistLetterIsTrue(){

        List<WebElement> authorsBlock=driver.findElements(By.xpath("//div[@id='authorsList']/div"));
        int total = 0;
        for (int i=1;i<=authorsBlock.size();i++){

            List<WebElement> eachBlock = driver.findElements(By.xpath("//div[@id='authorsList']/div["+i+"]/ul/li"));


            for (int j=1;j<=eachBlock.size();j++){
                WebElement eachAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div["+i+"]/ul/li["+j+"]/a"));
                String authorName=eachAuthor.getAttribute("innerHTML");
               // System.out.println("authorName : "+authorName);
                String firsLetter=authorName.substring(0,1);
               // System.out.println(firsLetter);
                boolean firstLetterIsA=true;
                if (firsLetter == "A"){
                    firstLetterIsA=false;
                }
                assertThat("Yazar isminin ilk harfi hatalı",firstLetterIsA);
            }
         }
        return this;
    }

    public HomePage checkLastObjectOfFirstPageIsPresentInSecondPage(){

        List<WebElement> authorsBlock=driver.findElements(By.xpath("//div[@id='authorsList']/div"));
        int lastIndexOfAuthorBlocks=authorsBlock.size();
        List<WebElement> eachAuthor = driver.findElements(By.xpath("//div[@id='authorsList']/div["+lastIndexOfAuthorBlocks+"]/ul/li"));
        int lastIndexOfEachAuthor=eachAuthor.size();
        WebElement lastAuthorOnPage = driver.findElement(By.xpath("//div[@id='authorsList']/div["+lastIndexOfAuthorBlocks+"]/ul/li["+lastIndexOfEachAuthor+"]/a"));
        String nameOflastAuthorOnFirstPage=lastAuthorOnPage.getAttribute("innerHTML");
        System.out.println(nameOflastAuthorOnFirstPage);

        driver.findElement(By.xpath("//a[@class='pageLink'][text()='2']")).click();

        ArrayList<String> namesOnSecondPage = new ArrayList<String>();

        for (int i=1;i<=authorsBlock.size();i++){

            List<WebElement> eachBlock = driver.findElements(By.xpath("//div[@id='authorsList']/div["+i+"]/ul/li"));
            for (int j=1;j<=eachBlock.size();j++){
                WebElement EachAuthor = driver.findElement(By.xpath("//div[@id='authorsList']/div["+i+"]/ul/li["+j+"]/a"));
                String authorName=EachAuthor.getAttribute("innerHTML");
                namesOnSecondPage.add(authorName);
            }
        }

        assertThat(namesOnSecondPage,not(hasItem(nameOflastAuthorOnFirstPage)));
        return this;
    }


}

