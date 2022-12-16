package Feature;

import Pages.HomePage;
import Pages.HomePageNon;
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

public final class Login {

    private final ObjectMapper objectMapper= new ObjectMapper();
//    private VerifyType pageType = new VerifyType();


    public static void login(final ActionsInputData command, final ArrayList<UsersInputData> users,
                      final ArrayList<MoviesInputData> movies, final ArrayNode output) {
        /**
         * We make sure that we can perform the action(we are on the login page)
         */
        ObjectMapper objectMapper = new ObjectMapper();
        if(LiveInfo.getInstance().getCurrentPage().getPageType().equals("login")) {
            /**
             * Action is performed successfully on the login page
             */
//            boolean sw = false;
            for(int i = 0; i < users.size(); i++) {
                if (users.get(i).getCredentials().getName().
                        equals(command.getCredentials().getName())) {
                    if(users.get(i).getCredentials().getPassword().
                            equals(command.getCredentials().getPassword())) {
                        /**
                         * User has the right username and password, so we set him as
                         * the current user
                         */
//                        sw = true;
                        LiveInfo.getInstance().setCurrentUser(users.get(i));
//                        setCurrUser(users.get(i));
                        LiveInfo.getInstance().setCurrentPage(HomePage.getInstance());
//                        setCurrPage(pageType.verifyType("HomePage"));

                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.putPOJO("error", null);
                        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                        objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
                        output.addPOJO(objectNode);
                        return;
                    } else {
                                ObjectNode objectNode = objectMapper.createObjectNode();
                                objectNode.putPOJO("error", "Error");
                                objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                                objectNode.putPOJO("currentUser", null);
                                output.addPOJO(objectNode);
                                LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());
//                setCurrPage(pageType.verifyType("HomePageNon"));
                                return;

                    }
                }
            }
        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);

        }

        /**
         * Login has failed
         */
//        for(int i = 0; i < users.size(); i++) {
//            if (!users.get(i).getCredentials().getName().
//                    equals(command.getCredentials().getName())) {
//                ObjectNode objectNode = objectMapper.createObjectNode();
//                objectNode.putPOJO("error", "Error");
//                objectNode.putPOJO("currentMoviesList", new ArrayList<>());
//                objectNode.putPOJO("currentUser", null);
//                output.addPOJO(objectNode);
//                LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());
////                setCurrPage(pageType.verifyType("HomePageNon"));
//                return;
//            }
//        }
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }


}
