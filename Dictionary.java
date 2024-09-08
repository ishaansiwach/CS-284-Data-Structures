import java.util.ArrayList;
public class Dictionary {
    private ArrayList<String> wordList;
    private ArrayList<DictionaryItem> dictArrayList;

    /**
     * Constructor for Dictionary
     */
    public Dictionary(){
        wordList=new ArrayList<String>(1300);
        dictArrayList=new ArrayList<DictionaryItem>(1300);
    }

    /**
     * Method for adding a word to the Dictionary
     * @param item the item to be added
     * @return true to indicate that the item was added
     */
    public boolean addWordToDictionary(DictionaryItem item){
        wordList.add(item.getWord());
        dictArrayList.add(item);
        return true;
    }

    /**
     * Method to print all the words in the dictionary
     */
    public void printDictionary(){
        System.out.println("All the words in the dictionary!");
        for(String s: wordList){
            System.out.println(s);
        }
    }

    /**
     * Method to indicate whether the word is in the dictionary
     * @param word the word that will be searched in the dictionary
     * @return true if the word is in the Dictionary and false if it isn't
     */
    public boolean hasWord(String word){
        for(String s: wordList){
            if(s.equals(word)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method searches for the count of a word when given the word
     * @param word the word whose count is being searched for
     * @return the number of times the word appears
     */
    public int searchDictionary(String word){
        int index=this.binarySearch(word, 0, wordList.size()-1);
        DictionaryItem item=dictArrayList.get(index);
        return item.getCount();
    }

    /**
     * A helper method for searchDictionary to find the count of a word
     * @param word the word whose count is being searched for
     * @param low the lowest possible index of the word
     * @param high the highest possible index of the word
     * @return the index of the word
     */
    private int binarySearch(String word, int low, int high){
        int index=0;
        while(low<=high){
            int mid=low+((high-low)/2);
            if(word.compareTo(wordList.get(mid))==0){
                index=mid;
                break;
            }
            if(word.compareTo(wordList.get(mid))>0){
                low=mid+1;
            }
            else if(word.compareTo(wordList.get(mid))<0){
                high=mid-1;
            }
        }
        return index;
    }

}
