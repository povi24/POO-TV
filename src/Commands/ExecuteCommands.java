package Commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
import fileio.Input;

public class ExecuteCommands {

    public static void executeCommands(final Input input, final ArrayNode output) {
        for(ActionsInputData actionsInputData : input.getActions()) {

            if(actionsInputData.getType().equals("on page")) {
                //CommandOnPage.onPage(actionsInputData, input.getUsers(), input.getMovies(), output);
                CommandOnPage.getInstance();
                CommandOnPage.getInstance().onPage(actionsInputData, input.getUsers(), input.getMovies(), output);
            }

            if(actionsInputData.getType().equals("change page")) {
                //CommandChangePage.changePage(actionsInputData, input.getUsers(), input.getMovies(), output);
                CommandChangePage.getInstance();
                CommandChangePage.getInstance().changePage(actionsInputData, input.getUsers(), input.getMovies(), output);
            }
        }
    }
}
