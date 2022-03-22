import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.HomePage;
import pages.SearchPage;
import util.UrlFactory;

import java.util.stream.IntStream;

import static util.Constants.SEARCH_WORD;

@Execution(ExecutionMode.CONCURRENT)
@Tag("Search")
public class SearchTest extends BaseTest
{
    HomePage homePage;

    @BeforeEach
    public void before()
    {
        super.before();
        homePage = goToUrl(new HomePage(driver, wait), UrlFactory.EN_URL.pageUrl);
        homePage.clickAcceptCookieBtn(homePage);
    }

    @Description("Enter a word and check if the results contain this word")
    @Test
    public void testSearchAWord()
    {
        Assertions.assertFalse(homePage.getAttributeOfSearchArea("class").contains("active"));

        homePage.clickSearchBtn();
        Assertions.assertTrue(homePage.getAttributeOfSearchArea("class").contains("active"));

        SearchPage searchPage = homePage.search(SEARCH_WORD);
        int activePage = 1;
        do
        {
            int finalActivePage = activePage;
            IntStream.range(0, searchPage.getSizeOfSearchItems()).forEach(i -> Assertions.assertTrue(searchPage.getDescription(i).toLowerCase().contains(SEARCH_WORD), finalActivePage + ". page " + (i + 1) + ". description does not contain " + SEARCH_WORD + "--> \n" + searchPage.getDescription(i)));
            searchPage.clickNextPageBtn();
            activePage++;
        }
        while (activePage != (searchPage.getSizeOfPageLinks() - 1));
    }
}
