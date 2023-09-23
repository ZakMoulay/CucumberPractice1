package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public static void openBrowserAndLunchApplication() {
        ConfigReader.readProperties(Constants.ConfigReaderPath);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // this method is going to initialize all the objects availble inside the method
        initializePageObjects();
    }

    public static void closeBrowser() {
        if ( driver != null )
            driver.quit();
    }

    public static void sendText(String text, WebElement element){
        element.clear();  // reason why use own method
        element.sendKeys((text));
    }


    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait;
    }
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        //we write this line because cucumber accepts array of byte for screenshot
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        //in case if it doesn't find file name or path it will throw an exception

        try{
            FileUtils.copyFile(screenShot,
                    new File(Constants.SCREENSHOT_FILEPATH + fileName+" "
                            +getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        //it returns the current date and time in java
        Date date = new Date();
        //this function sdf used to format the date as per the pattern we are passing
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //this line is going to return the formatted date
        return sdf.format(date);
    }

    public static void selectDropDownByVisibleText(String path,String text) {

        WebElement mdd = driver.findElement(By.xpath(path));
        Select sel1 = new Select(mdd);
        sel1.selectByVisibleText(text);
    }

    // Switch to a specific window handle by providing its handle
    public static void switchToWindowByHandle(WebDriver driver, String targetWindowHandle) {
        Set<String> windowHandles = driver.getWindowHandles();

        windowHandles
                .stream()
                .filter(handle -> handle.equals(targetWindowHandle))
                .findFirst()
                .ifPresent(handle -> {
                    driver.switchTo().window(handle);
                    System.out.println("Switched to Window Handle: " + handle);
                });
    }

    // Switch to a frame by its name or id attribute
    public static void switchToFrame(WebDriver driver, String frameName) {
        driver.switchTo().frame(frameName);
    }

    // Switch to a frame by its index
    public static void switchToFrame(WebDriver driver, int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    // Switch to a frame using a WebElement representing the frame
    public static void switchToFrame(WebDriver driver, WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    // Select a dropdown option by visible text (name)
    public static void selectDDValue(WebDriver driver, WebElement dropdownElement, String visibleText) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(visibleText);
    }
    // Select a dropdown option by index
    public static void selectDDValue(WebDriver driver, WebElement dropdownElement, int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }
    // Select a dropdown option by value
    public static void selectDDValue(WebDriver driver,  String value, WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }



}
