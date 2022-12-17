package Feature;

import Commands.CommandOnPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.FiltersInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class FilterContains {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void filterContains(final ActionsInputData command, final ArrayList<UsersInputData> users,
                                      final ArrayList<MoviesInputData> movies, final ArrayNode output) {

        /**
         * We make sure that we can perform the action(we are on the movies page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {

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

            ObjectNode objectNode = objectMapper.createObjectNode();
            if (command.getFilters().getContains().getActors() != null) {
                for (String actor : command.getFilters().getContains().getActors()) {
                    for (int k = 0; k < allMovies.size(); k++) {
                        if(!allMovies.get(k).getActors().contains(actor)) {
                            allMovies.remove(k);
                            k--;
                        }
                    }
                }

            }


            if (command.getFilters().getContains().getGenre() != null) {
                for (String genre : command.getFilters().getContains().getGenre()) {
                    for (int j = 0; j < allMovies.size(); j++) {
                        if(!allMovies.get(j).getGenres().contains(genre)) {
                            allMovies.remove(j);
                            j--;
                        }
                    }
                }

            }

            ArrayList<MoviesInputData> moviesAfterSortContains = new ArrayList<>();
            for(int m = 0; m < allMovies.size(); m++) {
                MoviesInputData movie2 = new MoviesInputData(allMovies.get(m));
                moviesAfterSortContains.add(movie2);
            }


           LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesAfterSortContains));

            /**
             * We print the correct movie list
             */

            objectNode.putPOJO("error", null);
            objectNode.putPOJO("currentMoviesList", new ArrayList<>(moviesAfterSortContains));
            objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
            output.addPOJO(objectNode);

        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
        }
    }
}
