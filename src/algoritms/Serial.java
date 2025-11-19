package algoritms;

import base.WordCount;

public class Serial extends WordCount{

    @Override
    public String getName(){
        return "Serial";
    }
    
    @Override
    public int countTotal(String text, String word){
        String[] words = splitWords(text);
        String targetWord = word.toLowerCase();
        
        int count = 0;

        for(String w : words){
            if (w.equals(targetWord) == true){
                count++;
            }
        }
        return count;
    }
}
