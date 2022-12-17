import Commands.ExecuteCommands;
import Pages.HomePageNon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import fileio.MoviesInputData;
import fileio.UsersInputData;
import helpers.Database;
import helpers.LiveInfo;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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

        System.out.println("aici vad ce am in movies" + Database.getInstance().getDatabaseMovies());



//        LiveInfo.getInstance().initializeApp();

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
