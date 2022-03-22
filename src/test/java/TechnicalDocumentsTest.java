import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.TechnicalDocumentsPage;
import util.UrlFactory;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static util.Constants.DOWNLOAD_PATH;
import static util.Constants.TECH_DOC_SEARCH_WORD;

@Execution(ExecutionMode.CONCURRENT)
@Tag("technicalDocuments")
public class TechnicalDocumentsTest extends BaseTest
{
    TechnicalDocumentsPage technicalDocumentsPage;

    @BeforeEach
    public void before()
    {
        super.before();
        technicalDocumentsPage = goToUrl(new TechnicalDocumentsPage(driver, wait), UrlFactory.TECH_DOC_URL.pageUrl);
        technicalDocumentsPage.clickAcceptCookieBtn(technicalDocumentsPage)
                .filter(TECH_DOC_SEARCH_WORD);
    }

    @Description("Filter a word. check if the catalog is seen. view document.")
    @Test
    public void testFilterAndViewDocument()
    {
        Assertions.assertTrue(technicalDocumentsPage.isDisplayedCatalog());

        IntStream.range(0, technicalDocumentsPage.getSizeOfCatalog()).forEach(i -> {
            String originalWindow = driver.getWindowHandle();
            technicalDocumentsPage.clickViewDocument(i);
            ArrayList<String> tabs = technicalDocumentsPage.getTabs();
            Assertions.assertEquals(2, tabs.size());

            technicalDocumentsPage.goBackToOriginalWindow(tabs, originalWindow);
        });
    }

    @Description("Filter a word. Download the catalog.")
    @Test
    public void testFilterAndDownload()
    {
        IntStream.range(0, technicalDocumentsPage.getSizeOfDownloadBtn()).forEach(i -> {
            technicalDocumentsPage.clickDownloadBtn(i);
            Assertions.assertTrue(technicalDocumentsPage.isFileDownloaded(DOWNLOAD_PATH, technicalDocumentsPage.getPdfName(i)));
        });
    }
}
