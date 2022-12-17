package pages;

public final class Logout extends Page {

    private static Logout instance = null;

    /**
     * @return instance
     */
    public static Logout getInstance() {
        if (instance == null) {
            instance = new Logout();

        }
        return instance;
    }
    private Logout() {
            this.setPageType("HomePageNon");
            this.getAllowedPages().add("register");
            this.getAllowedPages().add("login");
    }


}
