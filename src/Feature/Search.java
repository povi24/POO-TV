package Feature;

import Commands.CommandOnPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Search {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void search(final ActionsInputData command, final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies, final ArrayNode output) {

        /**
         * We make sure that we can perform the action(we are on the movies page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if(LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {

            ArrayList<MoviesInputData> allMovies = new ArrayList<>(Database.getInstance().getDatabaseMovies());

            /**
             * We make sure that the movie list for each user doesn't contain the movie from the country
             * where the user is banned from
             */
            for(int i = 0; i < allMovies.size(); i++) {
                MoviesInputData movie = allMovies.get(i);

                if(movie.getCountriesBanned().contains(LiveInfo.getInstance().getCurrentUser().getCredentials().getCountry())) {
                    allMovies.remove(movie);
                    i--;
                    /**
                     * We only keep the movies that start with a specific string
                     */
                } else if(!movie.getName().startsWith("startsWith")) {
                    allMovies.remove(movie);
                    i--;
                }
            }

            ObjectNode objectNode = objectMapper.createObjectNode();
//            objectNode.putPOJO("error", null);

            ArrayList<MoviesInputData> moviesAfterSearch = new ArrayList<>();
            for(int j = 0; j < allMovies.size(); j++) {
                MoviesInputData movie2 = new MoviesInputData(allMovies.get(j));
                moviesAfterSearch.add(movie2);
            }

            LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesAfterSearch));


            /**
             * We print the correct movie list
             */
            objectNode.putPOJO("error", null);
            objectNode.putPOJO("currentMoviesList", new ArrayList<>(moviesAfterSearch));
            objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
            output.addPOJO(objectNode);
//            return;
        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
//            return;
        }


    }
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
