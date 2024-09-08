//Ishaan Siwach
//I pledge my honor that I have abided by the Stevens Honor System
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    final Integer[] primes =
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                    31, 37, 41, 43, 47, 53, 59, 61, 67,
                    71, 73, 79, 83, 89, 97, 101};
    private Map<Character,Integer> letterTable;
    private Map<Long,ArrayList<String>> anagramTable;

    /**
     * Creates the letterTable by putting each letter with its corresponding
     * prime number using primes
     */
    public void buildLetterTable() {
        letterTable=new HashMap<Character, Integer>();
        // Complete
        for(int i=0; i<primes.length; i++){
            letterTable.put((char) (97+i), primes[i]);
        }
        for(char i: letterTable.keySet()){
            System.out.println(i+"="+letterTable.get(i));
        }
    }

    /**
     * Constructor, calls buildLetterTable and initializes anagramTable
     */
    public Anagrams() {
        buildLetterTable();
        anagramTable = new HashMap<Long,ArrayList<String>>();
    }

    /**
     * Adds a word to the anagramTable
     * @param s the word to be added
     */
    public void addWord(String s) {
        // Complete
        long hash=myHashCode(s);
        ArrayList<String> words=new ArrayList<String>();
        if(anagramTable.containsKey(hash)){
            words=anagramTable.get(hash);
        }
        words.add(s);
        anagramTable.put(hash, words);
    }

    /**
     * computes the hash score of the word
     * @param s word for which the score will be computed
     * @return score of the word
     */
    public long myHashCode(String s) {
        // Complete
        if(s.isEmpty()){
            throw new IllegalArgumentException("String is empty");
        }
        long score=1;
        for(int i=0; i<s.length(); i++){
            score*=letterTable.get(s.charAt(i));
        }
        return score;
    }

    /**
     * Method for processing the file that contains all the words
     * @param s the file name
     * @throws IOException throws exception if an error occurs
     */
    public void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            this.addWord(strLine);
        }
        br.close();
    }

    /**
     * gets the entry with the highest hash score in anagramTable
     * @return the ArrayList containing the hash score and list of words associated with it
     */
    public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
        // Complete
        int max=0;
        ArrayList<Map.Entry<Long,ArrayList<String>>> words=new ArrayList<Map.Entry<Long,ArrayList<String>>>();
        for(long i: anagramTable.keySet()){
            if(anagramTable.get(i).size()>max){
                max=anagramTable.get(i).size();
            }
        }
        for(long i: anagramTable.keySet()){
            if(anagramTable.get(i).size()==max){
                Map.Entry<Long, ArrayList<String>> entry= Map.entry(i, anagramTable.get(i));
                words.add(entry);
            }
        }
        return words;
    }

    /**
     * Main method for Anagrams
     * @param args main method
     */
    public static void main(String[] args) {
        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();
        try {
            a.processFile("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time: "+ seconds);
        System.out.println("List of max anagrams: "+ maxEntries);
    }
}
