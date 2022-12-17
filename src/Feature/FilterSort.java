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
public final class FilterSort {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void filterSort(final ActionsInputData command, final ArrayList<UsersInputData> users,
                                  final ArrayList<MoviesInputData> movies, final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the movies page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {

            ArrayList<MoviesInputData> allMovies = new ArrayList<>(Database.getInstance().getDatabaseMovies());

            System.out.println("in functia filterSort, sa imi printeze movies inainte de banare" + allMovies);

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

            System.out.println("ce filme am dupa ce am banat ce trebuia in functia de filterSort" + allMovies);

            ObjectNode objectNode = objectMapper.createObjectNode();
            if(command.getFilters().getSort().getDuration() != null) {
                System.out.println("imi intra pe iful de sortare dupa durata?");
                if (command.getFilters().getSort().getDuration().equals("decreasing")) {
                    allMovies.sort((movie1, movie2) -> movie1.getDuration() - movie2.getDuration());
                    System.out.println("ce am in movies dupa ce sorteaza lista goala" +allMovies);
                } else {
                    allMovies.sort((movie1, movie2) -> movie2.getDuration() - movie1.getDuration());
                    System.out.println("ce am in movies dupa ce sorteaza lista goala dupa durata" +allMovies);

                }
            }

            if(command.getFilters().getSort().getRating() != null) {
                System.out.println("imi intra pe iful de rating?");
                if (command.getFilters().getSort().getRating().equals("decreasing")) {
                    allMovies.sort((movie1, movie2) -> Double.compare(movie1.getRating(), movie2.getRating()));
                    System.out.println("ce am in movies dupa ce sorteaza lista goala dupa rating" +allMovies);

                } else {
                    allMovies.sort((movie1, movie2) -> Double.compare(movie1.getRating(), movie2.getRating()));
                }
            }

            System.out.println("ce am in movies dupa sortari inainte sa fac o lista noua" + allMovies);

            ArrayList<MoviesInputData> moviesAfterSort = new ArrayList<>();
            for(int j = 0; j < allMovies.size(); j++) {
                MoviesInputData movie3 = new MoviesInputData(allMovies.get(j));
                moviesAfterSort.add(movie3);
            }

            System.out.println("ce am in movies dupa ce adaug in lista noua " + moviesAfterSort);

            /**
             * We print the correct movie list
             */

            LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesAfterSort));

            objectNode.putPOJO("error", null);
            objectNode.putPOJO("currentMoviesList", new ArrayList<>(moviesAfterSort));
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
