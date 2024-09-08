public class DictionaryItem {
    private String word;
    private int count;

    /**
     * Constructor for Dictionary
     * @param w the word stored in the DictionaryItem
     * @param c the number of times the word appears
     */
    public DictionaryItem(String w, int c){
        this.word=w;
        this.count=c;
    }

    /**
     * Setter for word
     * @param w new word
     */
    public void setWord(String w){
        this.word=w;
    }

    /**
     * Setter for count
     * @param c new count
     */
    public void setCount(int c){
        this.count=c;
    }

    /**
     * Getter for word
     * @return the word
     */
    public String getWord(){
        return word;
    }

    /**
     * Getter for count
     * @return the count
     */
    public int getCount(){
        return count;
    }

}
