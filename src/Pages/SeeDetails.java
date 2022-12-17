package Pages;

public class SeeDetails extends Page{

    private static SeeDetails instance = null;

    public static SeeDetails getInstance() {
        if(instance == null) {
            instance = new SeeDetails();

        }
        return instance;
    }
    private SeeDetails() {
        this.setPageType("see details");
        this.getAllowedPages().add("HomePage");
        this.getAllowedPages().add("upgrades");
        this.getAllowedPages().add("logout");
        this.getAllowedPages().add("movies");
        this.getAllowedPages().add("see details");
//        if(getPageType().equals("see details")) {


//        }
    }


}
