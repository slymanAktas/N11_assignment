package helpers;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by suleymanaktas on 24/07/2017.
 */
public class ScreenShotTaker {

    public static void captureScreenShot(WebDriver driver,String screenShotName) throws IOException {



        try {

            TakesScreenshot ts=(TakesScreenshot)driver;

            File source=ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(source,new File("./Screenshot/"+screenShotName+".png"));

        }catch (Exception e){

            System.out.println("Exception while taking screenshot"+e.getMessage());

        }
    }

    public static void  compareWitTwoScreenshotIsDifferent() throws IOException {

        String firstScreen="firstScreen";

        String secondScreen="secondScreen";

        BufferedImage firstImage=ImageIO.read(new File("./Screenshot/"+firstScreen+".png"));

        BufferedImage secondImage=ImageIO.read(new File("./Screenshot/"+secondScreen+".png"));

        ImageDiffer imgDiff = new ImageDiffer();

        ImageDiff diff = imgDiff.makeDiff(firstImage,secondImage);

        Assert.assertTrue(diff.hasDiff());

    }

    public static void  compareWitTwoScreenshotIsSame() throws IOException {

        String firstScreen="firstScreen";

        String secondScreen="secondScreen";

        BufferedImage firstImage=ImageIO.read(new File("./Screenshot/"+firstScreen+".png"));

        BufferedImage secondImage=ImageIO.read(new File("./Screenshot/"+secondScreen+".png"));

        ImageDiffer imgDiff = new ImageDiffer();

        ImageDiff diff = imgDiff.makeDiff(firstImage,secondImage);

        Assert.assertFalse(diff.hasDiff());

    }

}
