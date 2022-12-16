package Commands;

import Pages.Page;
import Pages.VerifyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public  class CommandChangePage {

    private static CommandChangePage instance = null;
    public static CommandChangePage getInstance() {
        if(instance == null) {
            instance = new CommandChangePage();

        }
        return instance;
    }
    private CommandChangePage() {

    }
    public static void changePage(final ActionsInputData command,
                                  final ArrayList<UsersInputData> users,
                                  final ArrayList<MoviesInputData> movies,
                                  final ArrayNode output){
        /**
         * Here we verify if we can access the given page
         */
        if(LiveInfo.getInstance().getCurrentPage().getAllowedPages().contains(command.getPage())) {
                LiveInfo.getInstance().setCurrentPage(VerifyType.verifyType(command));

//            System.out.println("aici vad ce pagina am" + command.getPage());
//            System.out.println("aici vad ce feature am " + command.getFeature() );


                if(command.getPage().equals("movies") && command.getFeature() == null) {
                    System.out.println("ce feature am aici la changepage" + command.getFeature());

//                    System.out.println("intra in if-ul de nu am feature?");
                    ObjectMapper objectMapper = new ObjectMapper();
                    ArrayList<MoviesInputData> allMovies = LiveInfo.getInstance().getCurrentMovieList();
                    for (int i = 0; i < allMovies.size(); i++) {
                        MoviesInputData movie = allMovies.get(i);
                        if (movie.getCountriesBanned().contains(LiveInfo.getInstance().getCurrentUser().getCredentials().getCountry())) {
                            allMovies.remove(movie);
                            i--;
                        }
                    }

                    ArrayList<MoviesInputData> moviesToPrint = new ArrayList<>();
                    for(int j = 0; j < allMovies.size(); j++) {
                        MoviesInputData movie3 = new MoviesInputData(allMovies.get(j));
                        moviesToPrint.add(movie3);
                    }

                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.putPOJO("error", null);
                    System.out.println("care e lista de movies in commandchangepage cand la movies n-am niciun feature" + LiveInfo.getInstance().getCurrentMovieList());
                    objectNode.putPOJO("currentMoviesList", new ArrayList<MoviesInputData>(LiveInfo.getInstance().getCurrentMovieList()));
                    objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                    output.addPOJO(objectNode);
                }


                if (command.getPage().equals("see details") && command.getFeature() == null) {
                    System.out.println("intra pe if-ul de see details din commandchangepage?");
                    /**
                     * We define the movie list for the current user
                     */
                    ObjectMapper objectMapper = new ObjectMapper();
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    ArrayList<MoviesInputData> allMovies = LiveInfo.getInstance().getCurrentMovieList();
                    for (int i = 0; i < allMovies.size(); i++) {
                        MoviesInputData movie = allMovies.get(i);
                        if (movie.getCountriesBanned().contains(LiveInfo.getInstance().getCurrentUser().getCredentials().getCountry())) {
                            allMovies.remove(movie);
                            i--;
                        }
                    }
                    System.out.println("ce lista de movies am in see details dupa ce sunt banate?(ar trebui sa am 0)" + allMovies);
                    //all movies e lista userului curent


                    if(allMovies.contains(command.getMovie())) {
                        for (int j = 0; j < allMovies.size(); j++) {
                            System.out.println("imi intra in for-ul de la SEEDETAILS la commandchangepage?");
                            {
                                /**
                                 * we make a list with the movie we want to see the details from
                                 */

                                ArrayList<MoviesInputData> list = new ArrayList<>();
                                MoviesInputData movie = allMovies.get(j);
                                list.add(movie);
                                LiveInfo.getInstance().setCurrentMovieList(list);
                                ArrayList<MoviesInputData> moviesForPrinting = new ArrayList<>();
                                moviesForPrinting.add(new MoviesInputData(movie));

                                System.out.println("ce filme am in see details la movies for printing" + moviesForPrinting);
                                objectNode.putPOJO("error", null);
                                objectNode.putPOJO("currentMoviesList", moviesForPrinting);
                                objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                                output.addPOJO(objectNode);

                            }
                        }
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
            return;

        }

    }
}
