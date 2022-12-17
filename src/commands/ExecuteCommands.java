package commands;

import pages.HomePageNon;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
import fileio.Input;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class ExecuteCommands {
    private ExecuteCommands() {
    }

    /**
     * @param input
     * @param output
     */
    public static void executeCommands(final Input input, final ArrayNode output) {
        for (ActionsInputData actionsInputData : input.getActions()) {

            if (actionsInputData.getType().equals("on page")) {
                CommandOnPage.getInstance().onPage(actionsInputData,
                        input.getUsers(), input.getMovies(), output);
            }

            if (actionsInputData.getType().equals("change page")) {
                CommandChangePage.getInstance().changePage(actionsInputData,
                        input.getUsers(), input.getMovies(), output);
            }
        }

        Database.getInstance().getDatabaseUsers().clear();
        Database.getInstance().getDatabaseMovies().clear();
        LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());
        LiveInfo.getInstance().setCurrentUser(new UsersInputData());
        LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>());
    }
}
