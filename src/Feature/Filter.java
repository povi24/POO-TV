package Feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.FiltersInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class Filter {
    public static void filter(final ActionsInputData command, final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies, final ArrayNode output) {


        /**
         * We make sure that we can perform the action(we are on the movies page)
         */

        System.out.println("a intrat in functia mea de FILTER?");
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {
//            ArrayList<MoviesInputData> allMovies = LiveInfo.getInstance().getCurrentMovieList();
//
//            System.out.println("printeaza filmele pe care le are in functia filter inainte de a scoate ce e banat:" +
//                    allMovies);
//            /**
//             * We make sure that the movie list for each user doesn't contain the movie from the country
//             * where the user is banned from
//             */
//            for (int i = 0; i < allMovies.size(); i++) {
//                MoviesInputData movie = allMovies.get(i);
//
//                if (movie.getCountriesBanned().contains(LiveInfo.getInstance().getCurrentUser().getCredentials().getCountry())) {
//                    allMovies.remove(movie);
//                    i--;
//                }
//            }
//
//
//            ArrayList<MoviesInputData> moviesAfterSearch = new ArrayList<>();
//
//            for (int j = 0; j < allMovies.size(); j++) {
//                MoviesInputData movies2 = new MoviesInputData(moviesAfterSearch.get(j));
//                moviesAfterSearch.add(movies2);
//            }
//
//                System.out.println("ce are in lista de movies dupa ce le am scos pe alea banned(functia FILTER" + moviesAfterSearch);
//

            System.out.println("ce lista de filme am aici?" + LiveInfo.getInstance().getCurrentMovieList());
            if (command.getFilters().getSort() != null) {
                System.out.println("Intra pe if-ul cu sortare din functia FILTER?");
                FilterSort.filterSort(command, users, movies, output);
            }

            if (command.getFilters().getContains() != null) {
                FilterContains.filterContains(command, users, movies, output);
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
