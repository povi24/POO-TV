package Commands;

import Feature.Login;
import Feature.Register;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
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

        if (command.getFeature().equals("login")) {
//            Login loginAction = new Login();
//            loginAction.setCurrentStart(loginAction.getCurrentStart());

            Login.login(command, users, movies, output);

        } else if (command.getFeature().equals("register")) {
//            Register registerAction = new Register();
//            registerAction.setCurrentStart(registerAction.getCurrentStart());
            Register.register(command, users, movies, output);
        }
    }






}
