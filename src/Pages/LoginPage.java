package Pages;

public class LoginPage extends Page{

    private static LoginPage instance = null;

    public static LoginPage getInstance() {
        if(instance == null) {
            instance = new LoginPage();

        }
        return instance;
    }

    private LoginPage() {
//        if(getPageType().equals("login")) {
        this.setPageType("login");
        this.getAllowedPages().add("HomePage");

//        }
    }

}