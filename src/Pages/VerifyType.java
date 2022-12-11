package Pages;

public class VerifyType {
    public Page verifyType(final String pageType) {
        switch (pageType) {
            case "HomePage" -> new HomePage();
            case "HomePageNon" -> new HomePageNon();
            case "register" -> new Register();
            case "login" -> new Login();
            case "logout" -> new Logout();
            case "movies" -> new Movies();
            case "see details" -> new SeeDetails();
            case "upgrades" -> new Upgrades();
            default -> {
                return null;
            }
        };
        return null;
    }
}
