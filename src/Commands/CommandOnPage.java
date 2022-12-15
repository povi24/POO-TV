package Commands;

import Feature.Filter;
import Feature.Login;
import Feature.Logout;
import Feature.Register;
import Feature.Search;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
import fileio.FiltersInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public class CommandOnPage {

    private static CommandOnPage instance = null;
    public static CommandOnPage getInstance() {
        if(instance == null) {
            instance = new CommandOnPage();

        }
        return instance;
    }

    public void onPage(final ActionsInputData command,
                              final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies,
                              final ArrayNode output) {
        System.out.println("ajunge macar pana aici?");
        System.out.println(command.getFeature());

        FiltersInputData filter = new FiltersInputData();
        if (command.getFeature().equals("login")) {
//            Login loginAction = new Login();
//            loginAction.setCurrentStart(loginAction.getCurrentStart());

            Login.login(command, users, movies, output);


        } else if (command.getFeature().equals("register")) {
//            Register registerAction = new Register();
//            registerAction.setCurrentStart(registerAction.getCurrentStart());
            Register.register(command, users, movies, output);

        } else if (command.getFeature().equals("search")) {
//            System.out.println("sa vedem daca ajunge in search ");
            Search.search(command, users, movies, output);
//            System.out.println("a trecut de search?");
        } else if (command.getFeature().equals("filter")) {
            System.out.println("intra pe if-ul cu comanda FILTER?");
            Filter.filter(command, users, movies, output);

            System.out.println("care e lista mea de filme dupa ce efectuez actiune FILTER " + LiveInfo.getInstance().getCurrentMovieList());
        }

    }






}
