package algoritms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import base.WordCount;

public class ParallelCPU extends WordCount{

    private final int threads;

    public ParallelCPU(int threads){
        this.threads = threads;
    }

    @Override
    public String getName() {
        return "ParallelCPU (" + threads + " threads)";
    }

    @Override
    public int countTotal(String text, String word) throws InterruptedException, ExecutionException{

        String[] words = splitWords(text);
        String target = word.toLowerCase();

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        int chunkSize = words.length / threads;

        List<Future<Integer>> futures = new ArrayList<>();
        
        for(int i = 0; i<threads; i++){
            final int start = i * chunkSize;
            final int end = (i == threads - 1) ? words.length :start + chunkSize;

            futures.add(executor.submit(() -> {
                int localCount = 0;
                for(int j = start; j<end; j++){
                    if(words[j].equals(target)){
                        localCount++;
                    }
                }
                return localCount;
            }));
        }
        int total = 0;
        for(Future<Integer> f : futures){
            total = total + f.get();
        }

        executor.shutdown();
        return total;
    }

}
