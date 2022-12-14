package Feature;

import Pages.HomePageNon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Logout {
    private final ObjectMapper objectMapper= new ObjectMapper();


    public static void logout(final ActionsInputData command, final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies, final ArrayNode output) {

        ObjectMapper objectMapper = new ObjectMapper();
        if(LiveInfo.getInstance().getCurrentPage().getPageType().equals("logout")) {
            LiveInfo.getInstance().setCurrentUser(null);
            LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());

        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
        }



    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
