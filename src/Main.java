import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import base.*;
import benchmark.*;

public class Main {

    static int[] threadConfigs = {2, 4, 8, 12, 20};
    static int repetitions = 1;

    public static String load(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void main(String[] args) throws Exception {
        
        Config config = new Config.Builder()
                .setDatasetFolder("data")
                .setTargetWord("ah")
                .setThreadCounts(threadConfigs)
                .setRepetitions(repetitions)
                .build();

        Runner runner = new Runner(config);

        runner.runAll();

        terminalOutput(runner.getResults());
        csvOutput(runner);
        
    }

    public static void terminalOutput(List<Result> results){
        System.out.println("\n========== RESULTADOS ==========\n");

        results.forEach(r -> System.out.printf(
                "%s | arquivo=%s | threads=%d | ocorrências=%d | tempo=%d ms%n",
                r.algorithm,
                r.filename,
                r.threads,
                r.occurrences,
                r.timeMs
        ));
    }

    public static void csvOutput(Runner runner){
        runner.exportResults("results/benchmark.csv");
    }
}

