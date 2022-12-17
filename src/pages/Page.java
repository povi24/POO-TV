package pages;

import java.util.ArrayList;

public abstract class Page {
    private String pageType = null;
    private ArrayList<String> allowedPages = new ArrayList<>();

    /**
     * @return pageType
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * @param pageType
     */
    public void setPageType(final String pageType) {
        this.pageType = pageType;
    }

    /**
     * @return allowedPages
     */
    public ArrayList<String> getAllowedPages() {
        return allowedPages;
    }

    /**
     * @param allowedPages
     */
    public void setAllowedPages(final ArrayList<String> allowedPages) {
        this.allowedPages = allowedPages;
    }
}
