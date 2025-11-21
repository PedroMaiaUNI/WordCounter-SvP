package benchmark;

import java.nio.file.*;
import java.util.*;

import algoritms.*;
import base.*;

public class Runner {

    private final Config config;
    private final List<Result> results = new ArrayList<>();

    public Runner(Config config) {
        this.config = config;
    }

    public void runAll() {
        try {
            Files.list(config.datasetFolder)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(this::runForFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runForFile(Path file) {
        String text = readFile(file);

        WordCount serial = new Serial();
        //WordCount gpu = new ParallelGPU();

        // SERIAL
        runAlgorithm(serial, "SerialCPU", file, 1, text);

        // PARALLEL CPU
        for (int t : config.threadCounts) {
            runAlgorithm(new ParallelCPU(t), "ParallelCPU", file, t, text);
        }

        // GPU
        // runAlgorithm(gpu, "ParallelGPU", file, 0, text);
    }

    private void runAlgorithm(WordCount algo, String name, Path file, int threads, String text) {
        
        for (int i = 0; i < config.repetitions; i++) {
            long start = System.nanoTime();

            int occ = 0;
            try {
                occ = algo.countTotal(text, config.targetWord);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            long end = System.nanoTime();

            long totalTime =  (end - start) / 1000000;

            results.add(new Result(
                    name,
                    file.getFileName().toString(),
                    threads,
                    occ,
                    totalTime
            ));
        }
    }

    private String readFile(Path p) {
        try {
            return Files.readString(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportResults(String file) {
        Exporter.exportCSV(results, file);
    }

    public List<Result> getResults() {
        return results;
    }

}

