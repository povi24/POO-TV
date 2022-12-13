import Commands.ExecuteCommands;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.LiveInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
        Here we read the input
         */
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);
        ArrayNode output = objectMapper.createArrayNode();

        ArrayList<UsersInputData> users = new ArrayList<>();
        for (int i = 0; i < inputData.getUsers().size(); i++) {
            UsersInputData user = new UsersInputData(inputData.getUsers().get(i));
            //de intrebat ce se fute aici
            users.addAll(Collections.singleton(user));
        }

        ArrayList<MoviesInputData> movies = new ArrayList<>();
        for (int i = 0; i < inputData.getMovies().size(); i++) {
            MoviesInputData movie = new MoviesInputData(inputData.getMovies().get(i));
            movies.add(movie);
        }

        LiveInfo.getInstance().initializeApp();

        ExecuteCommands.executeCommands(inputData, output);



        /**
         * Here we write the results at output
         */
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
        //char[] inputPath = args[0].toCharArray();
        //objectWriter.writeValue(new File("checker/resources/out/out_") + inputPath[inputPath.length - 6] + ".json"), output);


        char[] inPath = args[0].toCharArray();
//        String outPath = "checker/resources/out/out_" + inPath[inPath.length - 6] + ".json";
        String outPath;
        if (inPath[inPath.length - 6] == '0') {
            outPath = "checker/resources/out/" + args[1] + "10.json";
        } else {
            outPath = "checker/resources/out/" + args[1]
                    + inPath[inPath.length - 6] + ".json";
        }
        objectWriter.writeValue(new File(outPath), output);



    }
}
