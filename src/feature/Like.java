package feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;
import java.util.Collections;

public final class Like {
    private  Like() {
    }

    /**
     * @param command
     * @param users
     * @param movies
     * @param output
     * @param database
     */
    public static void like(final ActionsInputData command,
                            final ArrayList<UsersInputData> users,
                            final ArrayList<MoviesInputData> movies,
                            final ArrayNode output, final Database database) {

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
            for (int k = 0; k < LiveInfo.getInstance().getCurrentUser()
                                    .getWatchedMovies().size(); k++) {
                if (LiveInfo.getInstance().getCurrentUser().getWatchedMovies()
                        .get(k).getName().equals(listOfOneMovie.get(0).getName())) {

                    /**
                     * We modify the watched section of the user
                     */
                    MoviesInputData likedMovie = new MoviesInputData(listOfOneMovie.get(0));
                    likedMovie.setNumLikes(likedMovie.getNumLikes() + 1);

                    UsersInputData currentUser = LiveInfo.getInstance().getCurrentUser();
                    currentUser.getLikedMovies().add(likedMovie);

                    var pos = currentUser.getPurchasedMovies().indexOf(listOfOneMovie.get(0));
                    currentUser.getPurchasedMovies().remove(pos);
                    currentUser.getPurchasedMovies().add(pos, likedMovie);

                    pos = currentUser.getWatchedMovies().indexOf(listOfOneMovie.get(0));
                    currentUser.getWatchedMovies().remove(pos);
                    currentUser.getWatchedMovies().add(pos, likedMovie);

                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList",
                            new ArrayList<>(Collections.singleton(likedMovie)));
                    objectNode.putPOJO("currentUser",
                            new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);

                    movieFound = true;
                    for (MoviesInputData movie : database.getDatabaseMovies()) {
                        if (movie.getName().equals(likedMovie.getName())) {
                            movie.setNumLikes(likedMovie.getNumLikes());
                            break;
                        }
                    }
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
