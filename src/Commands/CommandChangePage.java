package commands;
import Pages.SeeDetails;
import Pages.VerifyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class CommandChangePage {
    private static CommandChangePage instance = null;

    /**
     * @return instance
     */
    public static CommandChangePage getInstance() {
        if (instance == null) {
            instance = new CommandChangePage();

        }
        return instance;
    }
    private CommandChangePage() {

    }

    /**
     *
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void changePage(final ActionsInputData command,
                                  final ArrayList<UsersInputData> users,
                                  final ArrayList<MoviesInputData> movies,
                                  final ArrayNode output) {
        /**
         * Here we verify if we can access the given page
         */
        if (LiveInfo.getInstance().getCurrentPage().getAllowedPages()
                .contains(command.getPage())) {
            if (!command.getPage().equals("see details")) {
                LiveInfo.getInstance().setCurrentPage(VerifyType.verifyType(command));
            }
                if (command.getPage().equals("movies") && command.getFeature() == null) {

                    ObjectMapper objectMapper = new ObjectMapper();
                    ArrayList<MoviesInputData> allMovies = new ArrayList<>(Database
                                                .getInstance().getDatabaseMovies());
                    for (int i = 0; i < allMovies.size(); i++) {
                        MoviesInputData movie = allMovies.get(i);
                        if (movie.getCountriesBanned().contains(LiveInfo.getInstance()
                                .getCurrentUser().getCredentials().getCountry())) {
                            allMovies.remove(movie);
                            i--;
                        }
                    }

                    ArrayList<MoviesInputData> moviesToPrint = new ArrayList<>();
                    for (int j = 0; j < allMovies.size(); j++) {
                        MoviesInputData movie3 = new MoviesInputData(allMovies.get(j));
                        moviesToPrint.add(movie3);
                    }

                    LiveInfo.getInstance().setCurrentMovieList(new ArrayList<>(moviesToPrint));

                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.putPOJO("error", null);
                    objectNode.putPOJO("currentMoviesList", new ArrayList<>(LiveInfo
                                                            .getInstance().getCurrentMovieList()));
                    objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo
                                                                .getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);
                }


                if (command.getPage().equals("see details") && command.getFeature() == null) {
                    /**
                     * We define the movie list for the current user
                     */
                    ObjectMapper objectMapper = new ObjectMapper();
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    ArrayList<MoviesInputData> allMovies = LiveInfo.getInstance()
                                                            .getCurrentMovieList();

                    //all movies e lista userului curent

                    //adaugam toate numele filmelor curente
                    ArrayList<String> movieNames = new ArrayList<>();
                    //indexul filmului pentru care vrem detaliile
                    int movieIndex = 0;
                    int counter = 0;
                    for (MoviesInputData movie : allMovies) {
                        movieNames.add(movie.getName());
                        if (movie.getName().equals(command.getMovie())) {
                            movieIndex = counter;
                        }
                        counter++;
                    }

                    //string in array de movie nu e bine
                    if (movieNames.contains(command.getMovie())) {
                        LiveInfo.getInstance().setCurrentPage(SeeDetails.getInstance());
                        /**
                         * we make a list with the movie we want to see the details from
                         */

                        ArrayList<MoviesInputData> list = new ArrayList<>();
                        MoviesInputData movie = allMovies.get(movieIndex);
                        list.add(movie);
                        LiveInfo.getInstance().setCurrentMovieList(list);
                        ArrayList<MoviesInputData> moviesForPrinting = new ArrayList<>();
                        moviesForPrinting.add(new MoviesInputData(movie));

                        objectNode.putPOJO("error", null);
                        objectNode.putPOJO("currentMoviesList", moviesForPrinting);
                        objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo
                                                                .getInstance().getCurrentUser()));
                        output.addPOJO(objectNode);

                    } else {
                        objectNode.putPOJO("error", "Error");
                        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                        objectNode.putPOJO("currentUser", null);
                        output.addPOJO(objectNode);
                    }
                }
        } else {
            /**
             * output to print when we are not allowed to change page
             */
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);

        }

    }
}
