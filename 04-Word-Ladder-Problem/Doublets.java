import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Vincent Canivez(canivvs@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-07-30
 */
public class Doublets implements WordLadderGame {

    ////////////////////////////////////////////
    // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
    ////////////////////////////////////////////

    // A word ladder with no words. Used as the return value for the ladder methods
    // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;


    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

    ///////////////////////////////////////////////////////////////////////////////
    // Fill in implementations of all the WordLadderGame interface methods here. //
    ///////////////////////////////////////////////////////////////////////////////
    
    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
   public int getHammingDistance(String str1, String str2) {
      String str1lc = str1.toLowerCase();
      String str2lc = str2.toLowerCase();
      
      if (str1lc.length() != str2lc.length()) {
         return -1;
      }
      int hammingD = 0;
      int length = str1.length();
      for (int i = 0; i < length; i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            hammingD++;
         }
      }
      return hammingD;
   }  

   /**
    * Returns a word ladder from start to end. If multiple word ladders exist,
    * no guarantee is made regarding which one is returned. If no word ladder exists,
    * this method returns an empty list.
    *
    * Depth-first search with backtracking must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a word ladder from start to end
    */
   public List<String> getLadder(String start, String end) {
      String startlc = start.toLowerCase();
      String endlc = end.toLowerCase();
      
      if (startlc.length() != endlc.length()) {
         return EMPTY_LADDER;
      }
      
      Deque<String> stack = new ArrayDeque<>();
      TreeSet<String> bannedWords = new TreeSet<String>();
      List<String> wordladder = new ArrayList<>(stack.size()); 
      
      if (startlc.equals(endlc)) {
         wordladder.add(startlc);
         return wordladder;
      }
      
      if (getNeighbors(startlc).size() == 0) {
         return EMPTY_LADDER;
      }
      
      String lastFirstNeighbor = getNeighbors(startlc).get(getNeighbors(startlc).size()-1);
      
      stack.addFirst(startlc);
      while (!stack.peek().equals(endlc)  && !bannedWords.contains(lastFirstNeighbor)) {
         List<String> neighbors = getNeighbors(stack.peek());
         if (neighbors.size() == 0) {
            bannedWords.add(stack.pop());
         }
         int i = 0;
         while (i < neighbors.size()) {
            if (!stack.contains(neighbors.get(i)) && !bannedWords.contains(neighbors.get(i))) {
               stack.addFirst(neighbors.get(i));
               break;
            }
            i++;
         }
         if (i == neighbors.size()) {
            bannedWords.add(stack.pop());
         }
         
      }
      
      if (stack.peek().equals(endlc)) {
         
         int index = stack.size() - 1;
         while (index >= 0) {
            wordladder.add(stack.removeLast());
            index--;
         }
         return wordladder;
      }
      return EMPTY_LADDER;
   }


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   public List<String> getMinLadder(String start, String end) {
   
      String startlc = start.toLowerCase();
      String endlc = end.toLowerCase();
            
      if (startlc.length() != endlc.length()) {
         return EMPTY_LADDER;
      }
      
      Deque<Node> parentToBeChck = new ArrayDeque<>();   
      TreeSet<String> alreadyVisitedWords = new TreeSet<String>();
      List<String> minLadderList = new ArrayList<>();
      List<String> neighbors = new ArrayList<>();
      
      Node nodeStart = new Node(startlc);
      Node path = new Node();
      Node temp = new Node();
      
      parentToBeChck.addFirst(nodeStart);
      
      if (startlc.equals(endlc)) {
         minLadderList.add(startlc);
         return minLadderList;
      }
      if (getNeighbors(startlc).size() == 0) {
         return EMPTY_LADDER;
      }
      // loops until the end value is found OR if there is no more words to compare
      while (!endlc.equals(path.element) && parentToBeChck.size() != 0) {
         
         // 1)  
         neighbors = getNeighbors(parentToBeChck.peek().element);      
         // 2
         temp = parentToBeChck.pop();
         alreadyVisitedWords.add(temp.element);
         
         // 3 Check if values in neighbors are also in parentChck or parentToBeChck
         for (String s : neighbors) {
            if (s.equals(endlc)) {
               path = new Node(s, temp);
               
               Deque<String> minLAdder = new ArrayDeque<>();
               
               while (path != null) {
                  minLAdder.addFirst(path.element);
                  path = path.next;
               }
                  
               int i = 0;
               int minLAdderSize = minLAdder.size();
               while (i < minLAdderSize) {
                  minLadderList.add(minLAdder.pop());
                  i++;
               }
               return minLadderList;   
            }
            if (!alreadyVisitedWords.contains(s)) {
               parentToBeChck.addLast(new Node(s, temp));
               alreadyVisitedWords.add(s);  
            }
         }      
      }   
      return EMPTY_LADDER;
   }


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
   public List<String> getNeighbors(String word) {
      String stringLc = word.toLowerCase();
      List<String> neighbors = new ArrayList<>();
      for (int i = 0; i < stringLc.length(); i++) {
      
         char[] letters = stringLc.toCharArray();
         for (char ch = 'a'; ch <= 'z'; ch++) {
            letters[i] = ch;
            String b = new String(letters);
            if (isWord(b) && !neighbors.contains(b) && !b.equals(stringLc)) {
               neighbors.add(b);
            }
         }
      }
      return neighbors;
   }
    
    /**
     * Returns the total number of words in the current lexicon.
     *
     * @return number of words in the lexicon
     */
   public int getWordCount() {
      return lexicon.size();
   }


    /**
     * Checks to see if the given string is a word.
     *
     * @param  str the string to check
     * @return     true if str is a word, false otherwise
     */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.size() == 0) {
         return false;
      }
      
      int ladderSize = sequence.size();
      for (int i = 0; i < ladderSize - 1; i++) {
         String seq1 = sequence.get(i);
         String seq2 = sequence.get(i + 1);
         if (getHammingDistance(seq1, seq2) != 1 || !isWord(seq1) || !isWord(seq2)) {
            return false;
         }
      }
      
      return true;
   }
   
}

/**
    * Defines a node class.
    */
class Node {
      /** the value stored in this node. */
   String element;
      /** a reference to the node after this node. */
   Node next;
      
   
      /**
       * Instantiate an empty node.
       */
   public Node() {
      element = null;
      next = null;
   }
   
      /**
       * Instantiate a node that containts element
       * and with no node after it.
       */
   public Node(String e) {
      element = e;
      next = null;
   }
      
      /**
       * Instantiate a node that containts element
       * and with no node after it.
       */
   public Node(String e, Node nextNode) {
      element = e;
      next = nextNode;
   }
}

