package Pages;

public class Logout extends Page{

    private static Logout instance = null;

    public static Logout getInstance() {
        if(instance == null) {
            instance = new Logout();

        }
        return instance;
    }
    private Logout() {
//        if(getPageType().equals("logout")) {
        this.setPageType("logout");
        this.getAllowedPages().add("HomePageNon");

//        }
    }


}
