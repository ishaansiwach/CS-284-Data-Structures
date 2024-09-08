/**
 * Ishaan Siwach
 * I pledge my honor that I have abided by the Stevens Honor System
 */

public class BinaryNumber {
    private int length;
    private int [] data;

    /**
     * Constructor for Binary Number
     * @param length the length of the BinaryNumber (number of bits)
     */
    public BinaryNumber(int length){
        if(length<0){
            throw new IllegalArgumentException("Length is less than zero");
        }
        else{
            this.length=length;
            data=new int[length];
            for(int x=0; x<length; x++){
                data[x]=0;
            }
        }
    }

    /**
     * Constructor for BinaryNumber
     * @param str the BinaryNumber as a string
     */
    public BinaryNumber(String str){
        length=str.length();
        data=new int[this.length];
        for(int x=0; x<length; x++){
            data[x]=Character.getNumericValue(str.charAt(x));
        }
    }

    /**
     * Gives the length of the BinaryNumber
     * @return length of BinaryNumber
     */
    public int getLength(){
        return length;
    }

    /**
     * GIven an index, it returns the digit of the BinaryNumber at that index
     * @param index location of the digit that is to be returned from the BinaryNumber
     * @return digit of BinaryNumber at the given index
     */
    public int getDigit(int index){
        if(index>=data.length){
            throw new ArrayIndexOutOfBoundsException("Index is out of range for the BinaryNumber");
        }
        else{
            return data[index];
        }
    }

    /**
     * Returns the BinaryNumber as an array
     * @return the array of BinaryNumber
     */
    public int [] getInnerArray(){
        return data;
    }

    /**
     * returns an array by comparing the digits at each place and checking if at least
     * one of them is a 1 and if this is true there is a 1 at that spot and otherwise
     * there is a 0 at that spot in the new array
     * @param bn1 the first BinaryNumber
     * @param bn2 the second BinaryNumber
     * @return an array of the same size as both parameters by comparing the digits at each location
     */
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
        if(bn1.length==bn2.length){
            int[] n= new int[bn1.length];
            for(int x=0; x<bn1.length;x++){
                if(bn1.getDigit(x)==1 || bn2.getDigit(x)==1){
                    n[x]=1;
                }
                else{
                    n[x]=0;
                }
            }
            return n;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Binary Numbers are not of the same length.");
        }
    }

    /**
     * returns an array by comparing the digits at each place and checking if both
     * of them are a 1 and if this is true there is a 1 at that spot and otherwise
     * there is a 0 at that spot in the new array
     * @param bn1 the first BinaryNumber
     * @param bn2 the second BinaryNumber
     * @return an array of the same size as both parameters by comparing the digits at each location
     */
    public static int [] bwand(BinaryNumber bn1, BinaryNumber bn2){
        if(bn1.length==bn2.length){
            int[] n= new int[bn1.length];
            for(int x=0; x<bn1.length;x++){
                if(bn1.getDigit(x)==1 && bn2.getDigit(x)==1){
                    n[x]=1;
                }
                else{
                    n[x]=0;
                }
            }
            return n;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Binary Numbers are not of the same length.");
        }
    }

    /**
     * shifts the bits of the BinaryNumber to the left or right and by a specified amount
     * @param direction direction of shifting the bits
     * @param amount how many spots the bits have to be shifted
     */
    public void bitShift(int direction, int amount){
        if(amount!=0){

            if(direction==1){
                int[] temp=new int[amount];
                for(int x=0; x<length-amount; x++){
                    temp[x]=data[x];
                }
                length-=amount;
                data=temp;
            }
            else if (direction==-1){
                int[] temp=new int[amount+length];
                for(int x=0; x<length;x++){
                    temp[x]=data[x];
                }
                length+=amount;
                for(int x=length; x<temp.length; x++) {
                    temp[x] = 0;
                }
                data=temp.clone();
            }
            else{
                throw new IllegalArgumentException("Input for direction is invalid. Should be 1 or -1.");
            }
        }

    }

    /**
     * adds two BinaryNumbers and changes the BinaryNumber that called the function to make it the sum of the two
     * @param bn1 the BinaryNumber to be added to the one that called the function
     */
    public void add(BinaryNumber bn1) {
        if (this.length != bn1.length) {
            if (this.length < bn1.length) {
                int[] n = new int[bn1.length];
                int diff = bn1.length - this.length;
                for (int x = 0; x < diff; x++) {
                    n[x] = 0;
                }
                for (int x = 0; x < this.length; x++) {
                    n[x + diff] = this.data[x];
                }
                this.length = bn1.length;
                this.data = n.clone();
            } else {
                int[] n = new int[this.length];
                int diff = this.length - bn1.length;
                for (int x = 0; x < diff; x++) {
                    n[x] = 0;
                }
                for (int x = 0; x < bn1.length; x++) {
                    n[x + diff] = bn1.data[x];
                }
                bn1.length = this.length;
                bn1.data = n.clone();
            }
        }
        int carry = 0;
        for (int x = 0; x < this.length; x++) {
            int sum = this.data[this.length - 1 - x] + bn1.data[bn1.length - 1 - x] + carry;
            if (sum == 2) {
                sum = 0;
                carry = 1;
            } else if (sum == 3) {
                sum = 1;
                carry = 1;
            }
            if (x == this.length - 1 && carry == 1) {
                int[] n = new int[this.length+1];
                for (int y = 0; y < 1; y++) {
                    n[y] = 1;
                }
                for (int y = 0; y < this.length; y++) {
                    n[y + 1] = this.data[y];
                }
                this.length += 1;
                this.data = n.clone();
                sum = 0;
                break;
            } else {
                this.data[this.length - 1 - x] = sum;
                sum = 0;
            }
        }

    }

    /**
     * Converts the BinaryNumber to a string
     * @return BinaryNumber as a string
     */
    public String toString(){
        String s="";
        for(int x=0; x<length; x++){
            s = s + data[x];
        }
        return s;
    }

    /**
     * Converts the BinaryNumber to decimal form
     * @return BinaryNumber as a decimal
     */
    public int toDecimal(){
        int n = 0;
        for(int x=0; x<length; x++){
            if(data[data.length-1-x]==1){
                n += (int) Math.pow(2, x);
            }
        }
        return n;
    }


}
