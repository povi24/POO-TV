package Feature;

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
            for (int i = 0; i < allMovies.size(); i++) {
                MoviesInputData movie = allMovies.get(i);
                if (movie.getCountriesBanned().contains(LiveInfo.getInstance().getCurrentUser().getCredentials().getCountry())) {
                    allMovies.remove(movie);
                    i--;
                }
            }

            ArrayList<MoviesInputData> moviesAfterSearch = new ArrayList<>();
            for (MoviesInputData movie : allMovies) {
                if (movie.getName().startsWith(command.getStartsWith())) {
                    moviesAfterSearch.add(movie);
                    System.out.println("MOVIEEEEE ADDEDDDDD!" + movie.getName());
                }
            }

            for (MoviesInputData movie : moviesAfterSearch) {
                System.out.println();
                System.out.println(movie.getName());
                System.out.println();
            }



            LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesAfterSearch));
            /**
             * We print the correct movie list
             */
            ObjectNode objectNodeBun = objectMapper.createObjectNode();
            objectNodeBun.putPOJO("error", null);
            System.out.println(moviesAfterSearch);
            ArrayList<MoviesInputData> moviesToPrint = new ArrayList<>(moviesAfterSearch);
            System.out.println(moviesToPrint);
            objectNodeBun.putPOJO("currentMoviesList", moviesToPrint);
            objectNodeBun.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
            output.add(objectNodeBun);
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
