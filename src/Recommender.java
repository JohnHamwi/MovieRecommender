import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import datastructures.list.LinkedList;
import java.io.IOException;


/**
 * This is the main driver for solving the document distance problem for
 * a pair of documents.
 */
 public class Recommender
 {
   /**
    * Loads the corpus from the corpus file and adds each
    * document to the corpus.
    *
    * @param corpusName the name of the corpus to load.
    * @return a new Corpus containin all the documents.
    * @throws FileNotFoundException if the corpus file could not be found.
    */
     public static Corpus loadCorpus(String corpusName) throws FileNotFoundException, IOException   {
     String line;
     String[] entry;

     Scanner in = new Scanner(new File(corpusName));
     Corpus corpus = new Corpus();

     while (in.hasNextLine())
     {
       line = in.nextLine();  // Read the line.
       entry = line.split("#"); // Split it at the pound sign.

       // The first entry is the label the second entry is the file name.
       corpus.addDocument(entry[0], entry[1]);
     }

     //corpus.applyTFIDF(); // Use TF-IDF to improve results.
    corpus.applyTFIDF();
     return corpus;
   }

   /**
    * Runs the very simple Read, Evaluate, Print, Loop (REPL). This
    * continually prompts the user for a title and lists the recommended
    * titles
    *
    * @param corpus the corpus representing the collection of documents.
    */
   public static void doREPL(Corpus corpus)
   {
     String title = "";
     int k = 5;
     Scanner scan = new Scanner(System.in);
     LinkedList<Option> options = new LinkedList<>();

     // Run the standard read, parse, evaluate loop.
     while (!title.equals(".quit"))
     {
       System.out.print("> ");
       title = scan.nextLine();
       title = title.toLowerCase();

       // If the user didn't ask to quit, try to process the title.
       if (!title.equals("") && !title.equals(".quit") && !title.equals(".set-k"))
             System.out.println(corpus.getTopKRecommendations(title, k));

       if(title.equals(".set-k"))
       {
         do {
           System.out.print("Enter a positive value for k: ");
           k = Integer.valueOf(scan.nextLine());
         } while (k <= 0);
       }
     }
   }

     public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    String collection;
    Corpus corpus = new Corpus();

     // Prompt for the corpus to load.
     System.out.print("Please enter the name of the collections file: ");
     collection = scan.nextLine();
     try
     {
       corpus = loadCorpus(collection);
     }
     catch (FileNotFoundException ex)
     {
       System.out.println("Error: " + collection + " not found.");
       ex.printStackTrace();
       System.exit(1);
     }


     // Allow the user to get recommendations from the corpus.
     System.out.println("To quit interaction, type .quit at the prompt.");
     System.out.println("To set the value of k use .set-k at the prompt.");
     doREPL(corpus);
   }
 }
