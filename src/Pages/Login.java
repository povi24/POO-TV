package Pages;

public class Login extends Page{
    public Login() {
        if(getPageType().equals("login")) {
            this.setPageType("login");
            this.getAllowedPages().add("HomePage");
        }
    }
}
