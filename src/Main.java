import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import algoritms.*;
import base.*;

public class Main {

    public static String load(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void main(String[] args) throws Exception {

        String path = "data/Dracula-165307.txt";
        String word = "blood";

        String text = load(path);

        WordCount[] strategies = {
            new Serial()
            //new ParallelCPUCounter(),
            //new ParallelGPUCounter()
        };

        for (WordCount s : strategies) {
            long start = System.currentTimeMillis();
            int count = s.countTotal(text, word);
            long end = System.currentTimeMillis();

            System.out.println(s.getName() + ": " + count + " ocorrências em " + (end - start) + " ms");
        }
    }
}

