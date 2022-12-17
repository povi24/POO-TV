package feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Constants;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Purchase {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void purchase(final ActionsInputData command,
                                final ArrayList<UsersInputData> users,
                                 final ArrayList<MoviesInputData> movies,
                                final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the see details page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("see details")) {
            ObjectNode objectNode = objectMapper.createObjectNode();

            ArrayList<MoviesInputData> listOfOneMovie =
                    LiveInfo.getInstance().getCurrentMovieList();

            if (LiveInfo.getInstance().getCurrentUser()
                    .getCredentials().getAccountType().equals("premium")) {
                if (LiveInfo.getInstance().getCurrentUser()
                        .getNumFreePremiumMovies() >= Constants.PURCHASE_MOVIE) {
                    LiveInfo.getInstance().getCurrentUser()
                            .setNumFreePremiumMovies(LiveInfo.getInstance().getCurrentUser()
                                    .getNumFreePremiumMovies() - Constants.PURCHASE_MOVIE);

                    /**
                     * we add the film in the purchased section
                     */
                    LiveInfo.getInstance().getCurrentUser()
                            .getPurchasedMovies().add(listOfOneMovie.get(0));

                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(listOfOneMovie));
                    objectNode.putPOJO("currentUser",
                            new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);
                } else if (LiveInfo.getInstance().getCurrentUser()
                        .getTokensCount() >= Constants.TOKENS) {
                        LiveInfo.getInstance().getCurrentUser()
                                .setTokensCount(LiveInfo.getInstance()
                                        .getCurrentUser().getTokensCount() - Constants.TOKENS);

                    /**
                     * we add the film in the purchased section
                     */
                    LiveInfo.getInstance().getCurrentUser()
                            .getPurchasedMovies().add(listOfOneMovie.get(0));
                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(listOfOneMovie));
                    objectNode.putPOJO("currentUser",
                            new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);
                } else {
                    objectNode = objectMapper.createObjectNode();
                    objectNode.putPOJO("error", "Error");
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                    objectNode.putPOJO("currentUser", null);
                    output.addPOJO(objectNode);
                }
            } else {
                if (LiveInfo.getInstance().getCurrentUser().getTokensCount() >= Constants.TOKENS) {
                    LiveInfo.getInstance().getCurrentUser()
                            .setTokensCount(LiveInfo.getInstance()
                                    .getCurrentUser().getTokensCount() - Constants.TOKENS);
                    /**
                     * we add the film in the purchased section
                     */
                    LiveInfo.getInstance().getCurrentUser()
                            .getPurchasedMovies().add(listOfOneMovie.get(0));
                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(listOfOneMovie));
                    objectNode.putPOJO("currentUser",
                            new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);
                } else {
                    objectNode = objectMapper.createObjectNode();
                    objectNode.putPOJO("error", "Error");
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                    objectNode.putPOJO("currentUser", null);
                    output.addPOJO(objectNode);
                }
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
