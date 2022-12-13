package Pages;

import java.util.ArrayList;

public abstract class Page {
    private String pageType = null;
    private ArrayList<String> allowedPages = new ArrayList<>();

    public String getPageType() {
        return pageType;
    }
    public void setPageType(final String pageType) {
        this.pageType = pageType;
    }

    public ArrayList<String> getAllowedPages() {
        return allowedPages;
    }
    public void setAllowedPages(final ArrayList<String> allowedPages) {
        this.allowedPages = allowedPages;
    }
}
