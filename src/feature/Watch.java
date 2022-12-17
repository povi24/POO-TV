package feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Watch {
    private Watch() {
    }

    /**
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void watch(final ActionsInputData command,
                             final ArrayList<UsersInputData> users,
                             final ArrayList<MoviesInputData> movies,
                             final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the see details page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<MoviesInputData> listOfOneMovie = LiveInfo.getInstance().getCurrentMovieList();

        ObjectNode objectNode = objectMapper.createObjectNode();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("see details")) {
            /**
             * We first verify if the purchase command was executed
             */
            boolean movieFound = false;
            for (int k = 0; k < LiveInfo.getInstance().getCurrentUser()
                    .getPurchasedMovies().size(); k++) {
                if (LiveInfo.getInstance().getCurrentUser().getPurchasedMovies()
                        .get(k).getName().equals(listOfOneMovie.get(0).getName())) {
                    /**
                     * We modify the watched section of the user
                     */
                    LiveInfo.getInstance().getCurrentUser().getWatchedMovies()
                                                    .add(listOfOneMovie.get(0));
                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(listOfOneMovie));
                    objectNode.putPOJO("currentUser",
                            new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);

                    movieFound = true;
                    break;
                }
            }
            if (!movieFound) {
                objectNode.putPOJO("error", "Error");
                objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                objectNode.putPOJO("currentUser", null);
                output.addPOJO(objectNode);
            }
        } else {
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
        }
    }
}
