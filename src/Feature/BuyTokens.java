package Feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.util.ArrayList;

public class BuyTokens {

    public static void buyTokens(final ActionsInputData command, final ArrayList<UsersInputData> users,
                                          final ArrayList<MoviesInputData> movies, final ArrayNode output) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("upgrades")) {
            int tokensNumber = LiveInfo.getInstance().getCurrentUser().getTokensCount();
            int balanceNumber = Integer.parseInt(LiveInfo.getInstance().getCurrentUser().getCredentials().getBalance());
            if (balanceNumber >= command.getCount()) {
                LiveInfo.getInstance().getCurrentUser().setTokensCount(tokensNumber + command.getCount());
                String finalBalance = Integer.toString(balanceNumber - command.getCount());
                LiveInfo.getInstance().getCurrentUser().getCredentials().setBalance(finalBalance);
            } else {
                objectNode.putPOJO("error", "Error");
                objectNode.putPOJO("currentMoviesList", new ArrayList<>());
                objectNode.putPOJO("currentUser", null);
                output.addPOJO(objectNode);
            }
        } else {
            objectNode.putPOJO("error", "Error");
            objectNode.putPOJO("currentMoviesList", new ArrayList<>());
            objectNode.putPOJO("currentUser", null);
            output.addPOJO(objectNode);
        }
    }
}
