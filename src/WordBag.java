
import datastructures.dictionary.LinkedDictionary;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class WordBag {
    private LinkedDictionary<String, Double> wordVec;
    private int uniqueWords;
    private int totalWords;
    private int totalLines;

    /**
     * creates a new wordBag and intializes the wordVec dictionary and the other
     * instances 0
     *
     */
    public WordBag() {
        this.wordVec = new LinkedDictionary<>();
        this.uniqueWords = 0;
        this.totalWords = 0;
        this.totalLines = 0;
    }
    /**
     * creates the new wordbag object and initializes the wordVec dictionary
     * splits the title on spaces and add the words to the wordbag respectively
     *
     * @param title the movies title
     */
    public WordBag(String title) {
        this.wordVec = new LinkedDictionary<>();
        String[] t = title.split(" ");
        for (String l : t) {
            if (!this.wordVec.contains(l)) {
                this.wordVec.add(l, 1.0);
            } else {
                this.wordVec.add(l, this.getwordVec().getValue(l) + 1);
            }
        }
        this.totalLines++;
        this.totalWords += t.length;

    }

    /**
     * getWordVec returns the wordVec collection dictionary
     *
     * @return LinkedDictionary of the wordVec
     */
    public LinkedDictionary<String, Double> getwordVec() {
        return this.wordVec;
    }

    /**
     * loadDocument loads the document with the fileName fname and add the words
     * from that document to the wordVec dictionary and updates the uniqueWords,
     * totalLInes and total Words
     *
     * @param fname the movies title
     *
     */
    public void loadDocument(String fname) throws FileNotFoundException, IOException {
        ArrayList<String> Lines = (ArrayList<String>) Files.readAllLines(Path.of(fname));
        for (String l : Lines) {
            String[] p = l.split(" ");
            for (String k : p) {
                if (k.length() == 0) {
                    continue;
                }
                k = k.toLowerCase().trim();
                k = k.replaceAll("\\p{Punct}", "");
                if (k.length() > 0) {
                if (!wordVec.contains(k)) {
                    wordVec.add(k, 1.0);
                } else {
                    wordVec.add(k, wordVec.getValue(k) + 1);
                    }
                }
            }

        }

        Object[] keys = wordVec.getKeys();
        for (Object key : keys) {
            Double g = wordVec.getValue(key.toString());
            if (g == 1) {
                this.uniqueWords++;
            }
        }
        this.totalWords = wordVec.getSize();
        this.totalLines = Lines.size();
    }
    /**
     * getSimilarity gets the similarity between this wordBag to another Wordbag
     * passed
     *
     *
     * @param bag the other wordBag
     * @return double the similarity
     */
    public double getSimilarity(WordBag bag) {
        Object[] keys = this.wordVec.getKeys();
        ArrayList<String> r = new ArrayList<>();
        double summation = 0.0;
        for (Object k : keys) {
            Object f[] = bag.wordVec.getKeys();
            for (Object g : f) {
                double maxLength = Double.max(k.toString().length(), g.toString().length());
                if (maxLength > 0) {
                    summation += (maxLength - calculateCosineSimilarity(k.toString(), g.toString()));
                }
            }
        }
        return summation > 100 ? 100 : summation;
    }

    /**
     * calculate the similarity between two strings
     *
     * @param x 2ord 1
     * @param y word 2
     * @return int the similarity
     */
    private int calculateCosineSimilarity(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0 : 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }

        return T[m][n];
    }

    /**
     * getWords gets the words that are present in the wordVec dictionary
     *
     *
     * @return String[] array of words
     */
    public String[] getWords() {
        Object[] keys = this.wordVec.getKeys();
        String[] ws = new String[keys.length];
        for (int i = 0; i < ws.length; i++) {
            ws[i] = keys[i].toString();
        }
        return ws;
    }

    /**
     * clear the wordVec and all the words that are in the wordVec
     */
    public void clear() {
        this.wordVec.clear();
        if (this.wordVec.getSize() > 0) {
            this.wordVec = new LinkedDictionary<>();
            this.uniqueWords = 0;
            this.totalWords = 0;
            this.totalLines = 0;
        }
    }
    /**
     * isEmpty check if the wordVec dictionary is empty or not
     *
     * @return boolean the status if dictionary is empty or not
     */
    public boolean isEmpty() {
        return wordVec.isEmpty() || wordVec.getSize() == 0;
    }
    /**
     * toString to convert the current object to string format
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%d Lines, %d words, %d unique words.", this.totalLines, this.totalWords, this.uniqueWords);
    }
    /**
     * applyTFTIDF gets the idf dictionary and updates all the words frequency
     * by frequency of word / total words
     *
     *
     * @param idf the dictianry of words
     */
    public void applyTFIDF(LinkedDictionary<String, Double> idf) {

        Object[] keys = wordVec.getKeys();
        for (Object k : keys) {
            Double i = wordVec.getValue((String) k) / this.totalWords;
            idf.add(k.toString(), i);

        }
    }

}
