package Feature;

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

public final class Filter {
    public static void filter(final ActionsInputData command, final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies, final ArrayNode output) {


        /**
         * We make sure that we can perform the action(we are on the movies page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("movies")) {

            if(command.getFilters().getSort() != null &&  command.getFilters().getContains() != null){
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
                //acum in allmoviesaftersortcontains am lista dupa ce am dat contains

                if(command.getFilters().getSort().getDuration() != null) {
                    System.out.println("imi intra pe iful de sortare dupa durata?");
                    if (command.getFilters().getSort().getDuration().equals("decreasing")) {
                        moviesAfterSortContains.sort((movie1, movie2) -> movie1.getDuration() - movie2.getDuration());
                        System.out.println("ce am in movies dupa ce sorteaza lista goala" +allMovies);
                    } else {
                        moviesAfterSortContains.sort((movie1, movie2) -> movie2.getDuration() - movie1.getDuration());
                        System.out.println("ce am in movies dupa ce sorteaza lista goala dupa durata" +allMovies);

                    }
                }

                if(command.getFilters().getSort().getRating() != null) {
                    System.out.println("imi intra pe iful de rating?");
                    if (command.getFilters().getSort().getRating().equals("decreasing")) {
                        moviesAfterSortContains.sort((movie1, movie2) -> Double.compare(movie1.getRating(), movie2.getRating()));
                        System.out.println("ce am in movies dupa ce sorteaza lista goala dupa rating" +allMovies);

                    } else {
                        moviesAfterSortContains.sort((movie1, movie2) -> Double.compare(movie1.getRating(), movie2.getRating()));
                    }
                }

                ArrayList<MoviesInputData> moviesfinal = new ArrayList<>();
                for(int j = 0; j < moviesAfterSortContains.size(); j++) {
                    MoviesInputData movie3 = new MoviesInputData(allMovies.get(j));
                    moviesfinal.add(movie3);
                }

                LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesfinal));
                /**
                 * We print the correct movie list
                 */

                objectNode.putPOJO("error", null);
                objectNode.putPOJO("currentMoviesList", new ArrayList<>(moviesfinal));
                objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                output.addPOJO(objectNode);
            }
           else if (command.getFilters().getSort() != null) {
                System.out.println("Intra pe if-ul cu sortare din functia FILTER?");
                FilterSort.filterSort(command, users, movies, output);
            }

            else if (command.getFilters().getContains() != null) {
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
