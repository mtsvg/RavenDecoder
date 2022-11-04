package com.example.ravendecoder;

/***
 * Class used to create word objects pairing the word to a count of it occurrence
 */
public class WordObject {
    String word;
    int count;

    /***
     * constructer for the word object
     * @param aWord the word to be stored in the word variable of the class
     * @param aCount the count to be stored in the count variable
     */
    public WordObject(String aWord, int aCount) {
        word = aWord;
        count = aCount;
    }

    /***
     * getter for the word name paramenter
     * @return returns word
     */
    public String getWord() {
        return word;
    }

    /***
     * setter for word name vairable
     * @param word the word parsed from poem
     */
    public void setWord(String word) {
        this.word = word;
    }

    /***
     * getter for the count variable
     * @return returns the count associated with the word object
     */
    public int getCount() {
        return count;
    }

    /***
     * setter for count variable
     * @param count the amount of times the word has occured
     */
    public void setCount(int count) {
        this.count = count;
    }

}

