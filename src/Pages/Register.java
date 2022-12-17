package Pages;

public class Register extends Page{

    private static Register instance = null;

    public static Register getInstance() {
        if(instance == null) {
            instance = new Register();

        }
        return instance;
    }
    private Register() {
        this.setPageType("register");
        this.getAllowedPages().add("HomePageNon");
    }


}
