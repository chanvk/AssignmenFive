package assignment5;

import java.io.*;
import java.util.*;

/**
 *
 * @author kimchan
 */
public class Client {
    public static void main(String[] args) throws Exception {

        long wordsNotFound = 0;
        long compsFound = 0;
        int[] count = new int[1];
        long wordsFound = 0;
        long compsNotFound = 0;

        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<>();
        }//for

        File rDict = new File("random_dictionary.txt");

        Scanner input = new Scanner(rDict);

        while (input.hasNextLine()) {
            String dictWord = input.nextLine();
            dictWord = dictWord.toLowerCase();
            dictionary[dictWord.charAt(0) - 97].insert(dictWord);
        }//while

        File book = new File("oliver.txt");
        Scanner bookScan = new Scanner(book);

        while (bookScan.hasNext()) {
            try {
                String wordToBeSearched = bookScan.next();
                wordToBeSearched = wordToBeSearched.toLowerCase();
                wordToBeSearched = wordToBeSearched.replaceAll("[^a-z]", "");
                int searchIndex = (wordToBeSearched.charAt(0) - 97);

                if (dictionary[searchIndex].search(wordToBeSearched, count)) {
                    wordsFound++;
                    compsFound += count[0];

                } // if
                else {
                    wordsNotFound++;
                    compsNotFound += count[0];

                } //else
            } //try
            catch (StringIndexOutOfBoundsException ex) {
                bookScan.next();
            } //catch
        }//while

        double avgCompsWordsNotFound = compsNotFound / wordsNotFound;
        System.out.println("Average for word not found: " + avgCompsWordsNotFound);

        double avgCompsWordsFound = compsFound / wordsFound;
        System.out.println("Average for words found: " + avgCompsWordsFound);
        
        System.out.println("wordsFound: " + wordsFound);
        System.out.println("wordsNotFound: " + wordsNotFound);
        System.out.println("compsFound: " + compsFound);
        System.out.println("compsNotFound: " +compsNotFound);
    } //main

} // class
