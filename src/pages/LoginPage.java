package pages;

public final class LoginPage extends Page {

    private static LoginPage instance = null;

    /**
     * @return instance
     */
    public static LoginPage getInstance() {
        if (instance == null) {
            instance = new LoginPage();

        }
        return instance;
    }

    private LoginPage() {
        this.setPageType("login");
        this.getAllowedPages().add("HomePage");
    }

}
