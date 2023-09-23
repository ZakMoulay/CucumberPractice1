package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//a[text()='Welcome Admin']")

    public WebElement welcomeMessage;



    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
