package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TechnicalDocumentsPage extends BasePage
{
    public TechnicalDocumentsPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
    }

    @FindBy(id = "select2-id_q-container")
    private WebElement selectContainer;

    @FindBy(css = "input.select2-search__field")
    private WebElement searchInputArea;

    @FindBy(css = "#select2-id_q-results li:nth-of-type(1)")
    private WebElement result;

    @FindBy(css = "section[data-id='#tab-spare-parts-catalog']")
    private WebElement catalog;

    @FindBy(css = ".result-item-footer p.buttons a:not(.icon-pdf)")
    private List<WebElement> viewDocumentLinks;

    @FindBy(css=".result-item-footer p.buttons a.icon-pdf")
    private List<WebElement> downloadBtns;

    public TechnicalDocumentsPage clickSelectContainer()
    {
        wait.until(ExpectedConditions.elementToBeClickable(selectContainer));
        selectContainer.click();
        return this;
    }

    public TechnicalDocumentsPage filter(String key)
    {
        clickSelectContainer();
        wait.until(ExpectedConditions.visibilityOf(searchInputArea));
        searchInputArea.sendKeys(key);
        result.click();
        wait.until(ExpectedConditions.visibilityOf(viewDocumentLinks.get(0)));
        return this;
    }

    public boolean isDisplayedCatalog()
    {
        return catalog.isDisplayed();
    }

    public int getSizeOfCatalog()
    {
        return viewDocumentLinks.size();
    }

    public TechnicalDocumentsPage clickViewDocument(int index)
    {
        viewDocumentLinks.get(index).click();
        return this;
    }

    public void goBackToOriginalWindow(ArrayList<String> tabs, String originalWindow)
    {
        driver.switchTo().window(tabs.get(1));
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    public int getSizeOfDownloadBtn()
    {
        return downloadBtns.size();
    }

    public TechnicalDocumentsPage clickDownloadBtn(int index)
    {
        downloadBtns.get(index).click();
        try{
            Thread.sleep(10000);
        }
        catch(InterruptedException ie){
        }
        return this;
    }

    public String getPdfName(int index){
        String[] nameWithExtension = downloadBtns.get(index).getAttribute("href").split("name=");
        return nameWithExtension[1].split("&mode=download")[0];
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }
}
