package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
    public HomePage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }



    @FindBy(css = ".header-navigation-button[data-do='trigger-search']")
    private WebElement searchBtn;

    @FindBy(id = "search")
    private WebElement searchForm;

    @FindBy(id = "global-search")
    private WebElement searchTextBox;

    @FindBy(css = ".button.button-tertiary")
    private WebElement searchTextBtn;


    public HomePage clickSearchBtn()
    {
        searchBtn.click();
        return this;
    }

    public String getAttributeOfSearchArea(String attr)
    {
        return searchForm.getAttribute(attr);
    }

    public SearchPage search(String text)
    {
        wait.until(ExpectedConditions.visibilityOf(searchTextBox));
        searchTextBox.clear();
        searchTextBox.sendKeys(text);
        searchTextBtn.click();
        return new SearchPage(driver, wait);
    }

}
