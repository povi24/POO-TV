package Feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Like {
    private final ObjectMapper objectMapper = new ObjectMapper();


    public static void like(final ActionsInputData command, final ArrayList<UsersInputData> users,
                            final ArrayList<MoviesInputData> movies, final ArrayNode output) {

        /**
         * We make sure that we can perform the action(we are on the see details page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<MoviesInputData> listOfOneMovie = LiveInfo.getInstance().getCurrentMovieList();

        ObjectNode objectNode = objectMapper.createObjectNode();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("see details")) {

            /**
             * We first verify if the watch command was executed
             */
            boolean movieFound = false;
            for (int k = 0; k < LiveInfo.getInstance().getCurrentUser().getWatchedMovies().size(); k++) {
                if (LiveInfo.getInstance().getCurrentUser().getWatchedMovies().get(k).getName().equals(listOfOneMovie.get(0).getName())) {
                    /**
                     * We modify the watched section of the user
                     */
                    listOfOneMovie.get(0).setNumLikes(listOfOneMovie.get(0).getNumLikes() + 1);
                    MoviesInputData likedMovie = listOfOneMovie.get(0);
                    LiveInfo.getInstance().getCurrentUser().getLikedMovies().add(likedMovie);
                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(listOfOneMovie));
                    objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
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
