import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryCreator {

    /**
     * Constructor for DictionaryCreator for "IonDictionary.txt"
     * @throws FileNotFoundException this exception is thrown when the file is not found
     */
    public DictionaryCreator() throws FileNotFoundException {
        Dictionary d=this.readFile("IonDictionary.txt");
        this.printMenu(d);
    }

    /**
     * Constructor for DictionaryCreator for filename
     * @param filename file that the dictionary is being created for
     * @throws FileNotFoundException this exception is thrown when the file is not found
     */
    public DictionaryCreator(String filename) throws FileNotFoundException {
        Dictionary d=this.readFile(filename);
        this.printMenu(d);
    }

    /**
     * This function checks if the file exists
     * @param filename the file that is checked to see if it exists
     * @return true to indicate that the file exists or an exception is thrown
     * @throws FileNotFoundException this exception is thrown when the file is not found
     */
    public boolean fileExists(String filename) throws FileNotFoundException {
        File f=new File(filename);
        boolean exists=f.exists();
        if(!exists){
            throw new FileNotFoundException("The file '"+filename+"' does not exist.");
        }
        return exists;
    }

    /**
     * This function reads the file and adds the word and counts to a dictionary
     * @param filename the file that is read
     * @return the dictionary created from the file
     * @throws FileNotFoundException this exception is thrown when the file is not found
     */
    public Dictionary readFile(String filename) throws FileNotFoundException {
        boolean b=fileExists(filename);
        try{
            this.fileExists(filename);
        } catch(FileNotFoundException e){
            throw new RuntimeException();
        }
        Dictionary d=this.createADictionary();
        File f=new File(filename);
        Scanner scan=new Scanner(f);
        while(scan.hasNextLine()){
            String line=scan.nextLine();
            DictionaryItem dict= this.splitWordCountPair(line);
            if(dict!=null){
                d.addWordToDictionary(dict);
            }
        }
        return d;
    }

    /**
     * Creates a dictionary
     * @return the new dictionary
     */
    public Dictionary createADictionary(){
        Dictionary d=new Dictionary();
        return d;
    }

    /**
     * This function splits a String into 2 strings to separate the word and count
     * @param line the String to be split
     * @return a DictionaryItem with the new word and count
     */
    public DictionaryItem splitWordCountPair(String line){
        if(line.isEmpty()){
            return null;
        }
        DictionaryItem d=new DictionaryItem("",0);
        String [] splits=line.split("\\|", 2);
        if(splits.length==1){
            return null;
        }
        splits[0]=splits[0].trim();
        splits[1]=splits[1].trim();
        String s=splits[0];
        if(splits[0].isEmpty()){
            return null;
        }
        try{
            if(splits[1].isEmpty()){
                throw new NumberFormatException();
            }
            int i=Integer.parseInt(splits[1]);
            d.setWord(s);
            d.setCount(i);
            return d;
        } catch (NumberFormatException e){
            return d;
        }
    }

    /**
     * This function prints the menu from which the user chooses one of three options
     * @param dict the Dictionary created from the file
     */
    public void printMenu(Dictionary dict){
        System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
        System.out.println("1: To print all the words in the dictionary, choose 1");
        System.out.println("2: To search a word in the dictionary, choose 2");
        System.out.println("3: To quit the program, choose 3");
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String s=scan.nextLine();
            try{
                int a=Integer.parseInt(s);
                if(a<1 || a>3){
                    System.out.println("ERROR! Please enter a number between 1 and 3.");
                    continue;
                }
                boolean b = this.processMenuItem(a, scan, dict);
                if(!b){
                    System.out.println("Thanks for using Ion Dictionary! Bye!");
                    break;
                }
            } catch(NumberFormatException e){
                System.out.println("ERROR! Please enter a number between 1 and 3.");
            }
            System.out.println("1: To print all the words in the dictionary, choose 1");
            System.out.println("2: To search a word in the dictionary, choose 2");
            System.out.println("3: To quit the program, choose 3");
        }

    }

    /**
     * A helper method to process the menu item chose by the user
     * @param menuItem the option chosen by the user
     * @param scan a scanner to read user input
     * @param dict the dictionary created from the file
     * @return true for options 1 and 2 but false for option 3
     */
    private boolean processMenuItem(int menuItem, Scanner scan, Dictionary dict){
        if(menuItem==1){
            System.out.println("All the words mentioned in the Ion book!");
            dict.printDictionary();
            return true;
        }else if(menuItem==3){
            return false;
        } else{
            System.out.println("Please enter the word you would like to search:");
            String s=scan.nextLine();
            boolean b = dict.hasWord(s);
            if(!b){
                System.out.println("The word '" + s + "' does not exist in the Ion dictionary!");
                return true;
            } else{
                int count=dict.searchDictionary(s);
                System.out.println("The word '"+s+"' occurred "+count+" times in the book!");
                return true;
            }
        }

    }

}
