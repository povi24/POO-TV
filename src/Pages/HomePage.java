package Pages;

import Commands.CommandChangePage;

public class HomePage extends Page{
    private static HomePage instance = null;
    public static HomePage getInstance() {
        if(instance == null) {
            instance = new HomePage();

        }
        return instance;
    }

    private HomePage() {
        this.getAllowedPages().add("movies");
        this.getAllowedPages().add("logout");
        this.getAllowedPages().add("upgrades");
        this.setPageType("HomePage");
//        if(getPageType().equals("HomePage")) {
//        }
    }



}
