package Commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInputData;
import fileio.Input;
import helpers.LiveInfo;

public class ExecuteCommands {

    public static void executeCommands(final Input input, final ArrayNode output) {
        for(ActionsInputData actionsInputData : input.getActions()) {

            if(actionsInputData.getType().equals("on page")) {
                //CommandOnPage.onPage(actionsInputData, input.getUsers(), input.getMovies(), output);
                CommandOnPage.getInstance();
                System.out.println(LiveInfo.getInstance().getCurrentPage().getPageType() + " actiune");
                CommandOnPage.getInstance().onPage(actionsInputData, input.getUsers(), input.getMovies(), output);

            }

            if(actionsInputData.getType().equals("change page")) {
                //CommandChangePage.changePage(actionsInputData, input.getUsers(), input.getMovies(), output);
                CommandChangePage.getInstance();
                System.out.println(LiveInfo.getInstance().getCurrentPage().getPageType() + " pagina de pe care se schimba");
                for(String pageAllowed : LiveInfo.getInstance().getCurrentPage().getAllowedPages())
                    System.out.println(pageAllowed);
                CommandChangePage.getInstance().changePage(actionsInputData, input.getUsers(), input.getMovies(), output);

            }
        }
    }
}
