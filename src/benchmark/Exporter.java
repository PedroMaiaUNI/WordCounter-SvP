package benchmark;
import java.io.PrintWriter;
import java.util.List;

import base.Result;

public class Exporter {

    public static void exportCSV(List<Result> results, String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {

            pw.println("algorithm,filename,threads,occurrences,time_ms");

            for (Result r : results) {
                pw.printf("%s,%s,%d,%d,%d%n",
                        r.algorithm,
                        r.filename,
                        r.threads,
                        r.occurrences,
                        r.timeMs);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

