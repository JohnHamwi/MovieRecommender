
import datastructures.dictionary.LinkedDictionary;
import datastructures.list.LinkedList;
import java.io.IOException;

/**
 * Represents an Corpus.
 *
 */
public class Corpus {

    //the collection that store the sordbag with  the string of filename
    private LinkedDictionary<String, WordBag> collection;
    //the docFreq dictionary that store the frequency of unique words
    private LinkedDictionary<String, Integer> docFreq;

    /**
     * Creates an corpus initializes the collection and docFreq;
     */
    public Corpus() {
        collection = new LinkedDictionary<>();
        docFreq = new LinkedDictionary<>();
    }

    /**
     * get the Word by Title
     *
     * @param title the movies title
     * @return the WordBag of the specified title
     */
    private WordBag getWordByTitle(String title) {
        WordBag wb = null;
        Object[] keys = collection.getKeys();
        for (Object k : keys) {
            if (k.toString().equalsIgnoreCase(title)) {
                wb = collection.getValue(k.toString());
            }
        }
        return wb;

    }

    /**
     * get k Top Recommendations searched by title
     *
     * @param title the movies title
     * @param k the size to get
     * @return LinkedList of the recommendations
     */
    public LinkedList<Option> getTopKRecommendations(String title, int k) {
        LinkedList<Option> rec = new LinkedList<>();
        LinkedList<Option> rec1 = new LinkedList<>();
        Object[] keys = collection.getKeys();

        WordBag toCompare = getWordByTitle(title);
        if (toCompare == null) {
            toCompare = new WordBag(title);
        }
        int position = 1;
        for (Object key : keys) {
            WordBag wb = collection.getValue(key.toString());
            Option o = new Option(key.toString(), wb.getSimilarity(toCompare));
            rec.insert(position, o);
            position++;
        }

        rec.truncate(k);
        Object[] p = rec.toArray();
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length; j++) {
                Option o1 = rec.getEntry(i + 1);
                Option o2 = rec.getEntry(j + 1);

                if (o1.getSimilarity() < o2.getSimilarity()) {
                    Object tmp = p[i];
                    p[i] = p[j];
                    p[j] = tmp;
                }
            }

        }

        int pos = 1;
        for (Object l : p) {
            String[] r = l.toString().split(" \\(");
            double d = Double.valueOf(r[1].substring(0, r[1].indexOf(')') - 1));
            Option o = new Option(r[0].trim(), d);
            rec1.insert(pos, o);
            pos++;
        }
        return rec1;
    }
    /**
     * add the Document with label and filename fname
     *
     * @param label the label of the document
     * @param fname the file name that contains the words
     * 
     */
    public void addDocument(String label, String fname) throws IOException {
        WordBag wb = new WordBag();
        wb.loadDocument(fname);
        collection.add(label, wb);
        LinkedDictionary<String, Double> word = wb.getwordVec();

        Object[] k = word.getKeys();

        for (Object l : k) {
            if (word.getValue(l.toString()) == 1) {
                int f = word.getValue(l.toString()).intValue();
                docFreq.add(l.toString(), f + 1);
            } else {
                docFreq.add(l.toString(), 1);
            }
        }

    }
    /**
     * apply the TFIDF on the whole dictionary
     */
    public void applyTFIDF() {
        LinkedDictionary<String, Double> idf = new LinkedDictionary<>();
        Object[] keys = docFreq.getKeys();
        for (Object k : keys) {
            Double i = 1 + (Math.log(keys.length / (k.toString().length() + 1)));
            idf.add(k.toString(), i);

        }
    }

}
