package feature;

import pages.HomePage;
import pages.HomePageNon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Login {
    private Login() {
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void login(final ActionsInputData command,
                             final ArrayList<UsersInputData> users,
                            final ArrayList<MoviesInputData> movies,
                             final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the login page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("login")) {
            /**
             * Action is performed successfully on the login page
             */
            boolean sw = false;
            for (int i = 0; i < Database.getInstance().getDatabaseUsers().size(); i++) {
                if (Database.getInstance().getDatabaseUsers().get(i).getCredentials().getName().
                        equals(command.getCredentials().getName())) {
                    if (Database.getInstance().getDatabaseUsers()
                            .get(i).getCredentials().getPassword().
                            equals(command.getCredentials().getPassword())) {
                        /**
                         * User has the right username and password, so we set him as
                         * the current user
                         */
                        sw = true;
                        LiveInfo.getInstance().setCurrentUser(Database
                                .getInstance().getDatabaseUsers().get(i));
                        LiveInfo.getInstance().setCurrentPage(HomePage.getInstance());

                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.putPOJO("error", null);
                        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                        objectNode.putPOJO("currentUser",
                                new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                        output.addPOJO(objectNode);
                        break;
                    }
                }
            }
            if (!sw) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.putPOJO("error", "Error");
                objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                objectNode.putPOJO("currentUser", null);
                output.addPOJO(objectNode);
                LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());
            }
        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);

        }
    }
}
