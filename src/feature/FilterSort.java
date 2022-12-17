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
import java.util.Comparator;

public final class FilterSort {
    private FilterSort() {

    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void filterSort(final ActionsInputData command,
                                  final ArrayList<UsersInputData> users,
                                  final ArrayList<MoviesInputData> movies,
                                  final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the movies page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {

            ArrayList<MoviesInputData> allMovies =
                    new ArrayList<>(Database.getInstance().getDatabaseMovies());

            /**
             * We make sure that the movie list for each user doesn't
             * contain the movie from the country where the user is banned from
             */
            for (int i = 0; i < allMovies.size(); i++) {
                MoviesInputData movie = allMovies.get(i);

                if (movie.getCountriesBanned().contains(LiveInfo
                        .getInstance().getCurrentUser().getCredentials().getCountry())) {
                    allMovies.remove(movie);
                    i--;
                }
            }

            ObjectNode objectNode = objectMapper.createObjectNode();
            if (command.getFilters().getSort().getRating() != null) {
                if (command.getFilters().getSort().getRating().equals("decreasing")) {
                    allMovies.sort(Comparator
                            .comparingDouble(MoviesInputData::getRating).reversed());
                } else {
                    allMovies.sort(Comparator
                            .comparingDouble(MoviesInputData::getRating));
                }
            }

            if (command.getFilters().getSort().getDuration() != null) {
                if (command.getFilters().getSort().getDuration().equals("decreasing")) {
                    allMovies.sort(Comparator
                            .comparingInt(MoviesInputData::getDuration).reversed());
                } else {
                    allMovies.sort(Comparator.comparingInt(MoviesInputData::getDuration));
                }
            }

            ArrayList<MoviesInputData> moviesAfterSort = new ArrayList<>();
            for (int j = 0; j < allMovies.size(); j++) {
                MoviesInputData movie3 = new MoviesInputData(allMovies.get(j));
                moviesAfterSort.add(movie3);
            }
            /**
             * We print the correct movie list
             */
            LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesAfterSort));

            objectNode.putPOJO("error", null);
            objectNode.putPOJO("currentMoviesList", new ArrayList<>(moviesAfterSort));
            objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo
                                                    .getInstance().getCurrentUser()));
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
