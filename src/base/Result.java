package base;

public class Result {
    public String algorithm;
    public String filename;
    public int threads;
    public int occurrences;
    public long timeMs;

    public Result(String algorithm, String filename, int threads, int occurrences, long timeMs) {
        this.algorithm = algorithm;
        this.filename = filename;
        this.threads = threads;
        this.occurrences = occurrences;
        this.timeMs = timeMs;
    }
}

