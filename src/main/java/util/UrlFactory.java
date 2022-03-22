package util;

public enum UrlFactory
{
    BASE_URL("https://www.lely.com"),
    EN_URL(BASE_URL,"/en"),
    TECH_DOC_URL(BASE_URL,"/techdocs/");

    public final String pageUrl;

    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }
}
