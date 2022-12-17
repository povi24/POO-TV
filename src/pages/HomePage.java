package pages;

public final class HomePage extends Page {
    private static HomePage instance = null;

    /**
     * @return instance
     */
    public static HomePage getInstance() {
        if (instance == null) {
            instance = new HomePage();

        }
        return instance;
    }

    private HomePage() {
        this.getAllowedPages().add("movies");
        this.getAllowedPages().add("logout");
        this.getAllowedPages().add("upgrades");
        this.setPageType("HomePage");

    }
}
