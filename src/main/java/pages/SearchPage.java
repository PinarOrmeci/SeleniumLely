package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends BasePage
{
    public SearchPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    @FindBy(css = "h3 + .item-description")
    private List<WebElement> descriptions;

    @FindBy(css = ".page-link")
    private List<WebElement> pageLinks;

    @FindBy(css = ".page.page-next")
    private WebElement nextBtn;

    public int getSizeOfSearchItems()
    {
        return descriptions.size();
    }

    public String getDescription(int i)
    {
        return descriptions.get(i).getText();
    }

    public int getSizeOfPageLinks()
    {
        return pageLinks.size();
    }

    public SearchPage clickNextPageBtn()
    {
        nextBtn.click();
        return this;
    }
}
