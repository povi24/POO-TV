package feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInputData;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Constants;
import helpers.LiveInfo;

import java.util.ArrayList;

public final class BuyPremiumAccount {
    private BuyPremiumAccount() {
    }
    /**
     *
     * @param command
     * @param users
     * @param movies
     * @param output
     */
    public static void buyPremiumAccounts(final ActionsInputData command,
                                          final ArrayList<UsersInputData> users,
                              final ArrayList<MoviesInputData> movies, final ArrayNode output) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        if (LiveInfo.getInstance().getCurrentPage().getPageType().equals("upgrades")) {
            int tokensNumber = LiveInfo.getInstance().getCurrentUser().getTokensCount();
            if (tokensNumber >= Constants.PREMIUM_ACCOUNT) {
                LiveInfo.getInstance().getCurrentUser().setTokensCount(tokensNumber
                                                        - Constants.PREMIUM_ACCOUNT);
                LiveInfo.getInstance().getCurrentUser().getCredentials().setAccountType("premium");
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
