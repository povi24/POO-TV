package Pages;

public class Register extends Page{
    public Register() {
        if(getPageType().equals("register")) {
            this.setPageType("register");
            this.getAllowedPages().add("HomePageNon");
        }
    }
}
