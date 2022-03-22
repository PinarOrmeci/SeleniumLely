package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage
{
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "cookienotice-button-accept")
    private WebElement acceptCookieBtn;

    public <T extends BasePage> T clickAcceptCookieBtn(T page)
    {
        acceptCookieBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(acceptCookieBtn));
        return page;
    }

    public ArrayList<String> getTabs()
    {
        return new ArrayList<>(driver.getWindowHandles());
    }
}
