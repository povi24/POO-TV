package pages;

public final class Register extends Page {

    private static Register instance = null;

    /**
     * @return instance
     */
    public static Register getInstance() {
        if (instance == null) {
            instance = new Register();

        }
        return instance;
    }
    private Register() {
        this.setPageType("register");
        this.getAllowedPages().add("HomePageNon");
    }


}
