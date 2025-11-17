package base;

public abstract class WordCount implements CountType {
    protected String normalize(String text) {
        return text.toLowerCase().replaceAll("[^a-zà-ú0-9 ]", " ");
    }

    protected String[] splitWords(String text) {
        return normalize(text).split("\\s+");
    }
}
