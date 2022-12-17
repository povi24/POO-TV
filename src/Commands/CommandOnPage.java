package commands;

import Feature.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
import fileio.FiltersInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;

import java.util.ArrayList;

public class CommandOnPage {

    private static CommandOnPage instance = null;
    public static CommandOnPage getInstance() {
        if(instance == null) {
            instance = new CommandOnPage();

        }
        return instance;
    }

    private CommandOnPage() {
    }

    public void onPage(final ActionsInputData command,
                       final ArrayList<UsersInputData> users,
                       final ArrayList<MoviesInputData> movies,
                       final ArrayNode output) {

        FiltersInputData filter = new FiltersInputData();
        if (command.getFeature().equals("login")) {
            Login.login(command, users, movies, output);
        } else if (command.getFeature().equals("register")) {
            Register.register(command, users, movies, output);

        } else if (command.getFeature().equals("search")) {
            Search.search(command, users, movies, output);
        } else if (command.getFeature().equals("filter")) {
            Filter.filter(command, users, movies, output);
        } else if (command.getFeature().equals("purchase")) {
            Purchase.purchase(command, users, movies, output);
        } else if (command.getFeature().equals("watch")) {
            Watch.watch(command, users, movies, output);
        } else if (command.getFeature().equals("like")) {
            Like.like(command, users, movies, output, Database.getInstance());
        } else if (command.getFeature().equals("rate")) {
            RateTheMovie.rate(command, users, movies, output, Database.getInstance());
        } else if (command.getFeature().equals("buy premium account")) {
            BuyPremiumAccount.buyPremiumAccounts(command, users, movies, output);
        } else if (command.getFeature().equals("buy tokens")) {
            BuyTokens.buyTokens(command, users, movies, output);
        } else {
            System.out.println("command.feature() inexistent");
        }
    }
}
