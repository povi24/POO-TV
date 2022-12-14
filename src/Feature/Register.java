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

public final class Register {

    //    private VerifyType pageType = new VerifyType();

    public static void register(final ActionsInputData command, final ArrayList<UsersInputData> users,
                         final ArrayList<MoviesInputData> movies, final ArrayNode output) {
        ObjectMapper objectMapper= new ObjectMapper();
        /**
         * We make sure that we can perform the action(we are on the register page)
         */
        if(LiveInfo.getInstance().getCurrentPage().getPageType().equals("register")) {
            /**
             * Action is performed successfully on the register page
             */
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getCredentials().getName().
                        equals((command.getCredentials().getName()))) {
                    if(users.get(i).getCredentials().getPassword().
                            equals(command.getCredentials().getPassword())) {
                        /**
                         * The user is already registered with this credentials so he is redirected
                         * to the HomePageNon
                         */
                        LiveInfo.getInstance().setCurrentPage(HomePageNon.getInstance());


                        ObjectNode objectNode = objectMapper.createObjectNode();
                        objectNode.putPOJO("error", "Error");
                        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                        objectNode.putPOJO("currentUser", null);
                        output.addPOJO(objectNode);
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
         * We add an user with the new credentials and move on HomePage
         */
        users.add(new UsersInputData(command.getCredentials()));
        LiveInfo.getInstance().setCurrentUser(new UsersInputData(command.getCredentials()));
//        setCurrUser(new UsersInputData(command.getCredentials()));

        LiveInfo.getInstance().setCurrentPage(HomePage.getInstance());

        //setCurrPage(pageType.verifyType("HomePage"));
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.putPOJO("error", null);
        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
        objectNode.putPOJO("currentUser", new UsersInputData(LiveInfo.getInstance().getCurrentUser()));
        output.addPOJO(objectNode);
    }





}
