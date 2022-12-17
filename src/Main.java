import commands.ExecuteCommands;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }
    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        /**
        Here we read the input
         */
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        for (int i = 0; i < inputData.getUsers().size(); i++) {
            UsersInputData user = new UsersInputData(inputData.getUsers().get(i));
            Database.getInstance().getDatabaseUsers().add(user);
        }

        for (int i = 0; i < inputData.getMovies().size(); i++) {
            MoviesInputData movie = new MoviesInputData(inputData.getMovies().get(i));
            Database.getInstance().getDatabaseMovies().add(movie);
        }

        LiveInfo.getInstance().initializeApp();
        ExecuteCommands.executeCommands(inputData, output);

        /**
         * Here we write the results at output
         */
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
        Database.getInstance().resetDatabase();


    }
}
