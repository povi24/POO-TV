package Commands;

import Pages.Page;
import Pages.VerifyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public  class CommandChangePage {

    private static CommandChangePage instance = null;
    public static CommandChangePage getInstance() {
        if(instance == null) {
            instance = new CommandChangePage();

        }
        return instance;
    }
    private CommandChangePage() {

    }
    public static void changePage(final ActionsInputData command,
                                  final ArrayList<UsersInputData> users,
                                  final ArrayList<MoviesInputData> movies,
                                  final ArrayNode output){
        /**
         * Here we verify if we can access the given page
         */
        if(LiveInfo.getInstance().getCurrentPage().getAllowedPages().contains(command.getPage())) {
                LiveInfo.getInstance().setCurrentPage(VerifyType.verifyType(command));
        } else {
            /**
             * output to print when we are not allowed to change page
             */
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
            return;

        }

    }


}
