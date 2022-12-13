package Pages;

import fileio.ActionsInputData;

public class VerifyType {
    public static Page verifyType(final ActionsInputData command) {
        switch (command.getPage()) {
            case "HomePage" -> {
                return HomePage.getInstance();
            }
            case "HomePageNon" -> {
                return  HomePageNon.getInstance();
            }
            case "register" -> {
                return Register.getInstance();
            }
            case "login" -> {
                return LoginPage.getInstance();
            }
            case "logout" -> {
                return Logout.getInstance();
            }
            case "movies" -> {
                return  Movies.getInstance();
            }
            case "see details" -> {
                return SeeDetails.getInstance();
            }
            case "upgrades" -> {
                return Upgrades.getInstance();
            }
            default -> {
                return null;
            }
        }
    }
}
