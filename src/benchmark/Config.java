package benchmark;

import java.nio.file.*;

public class Config {
    public final Path datasetFolder;
    public final String targetWord;
    public final int[] threadCounts;
    public final int repetitions;

    private Config(Path datasetFolder, String targetWord, int[] threadCounts, int repetitions) {
        this.datasetFolder = datasetFolder;
        this.targetWord = targetWord;
        this.threadCounts = threadCounts;
        this.repetitions = repetitions;
    }

    public static class Builder {
        private String folder;
        private String word;
        private int[] threads;
        private int reps;

        public Builder setDatasetFolder(String folder) {
            this.folder = folder;
            return this;
        }

        public Builder setTargetWord(String word) {
            this.word = word;
            return this;
        }

        public Builder setThreadCounts(int[] threads) {
            this.threads = threads;
            return this;
        }

        public Builder setRepetitions(int reps) {
            this.reps = reps;
            return this;
        }

        public Config build() {
            return new Config(
                Paths.get(folder),
                word,
                threads,
                reps
            );
        }
    }
}

