import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
*
*/



public class DoubletsTest {
	
   @Test
	public void getNeighborsTest() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("tiny.txt")));
		List<String> test = doublets.getNeighbors("CAT");
		int expected = 3;
      int actual = test.size();
      assertEquals(expected, actual);
   }
   
   @Test
	public void getHammingDistanceTest() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		String one = "xyze";
		String two = "cat";
		int expected = -1;
		int actual = doublets.getHammingDistance(one, two);
		assertEquals(expected, actual);
      
      one = "car";
		two = "cat";
		expected = 1;
		actual = doublets.getHammingDistance(one, two);
		assertEquals(expected, actual);
      
      one = "eat";
		two = "cat";
		expected = 1;
		actual = doublets.getHammingDistance(one, two);
		assertEquals(expected, actual);
      
      one = "bot";
		two = "cat";
		expected = 2;
		actual = doublets.getHammingDistance(one, two);
		assertEquals(expected, actual);
      
      one = "dog";
		two = "cat";
		expected = 3;
		actual = doublets.getHammingDistance(one, two);
		assertEquals(expected, actual);



   }
   
   @Test
	public void isWordLadderTest() throws FileNotFoundException {
		WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		
		////////////////////////
		// Invalid sequences  //
		////////////////////////
		
		//test on empty list
		List<String> sequence = new ArrayList<>();
		boolean expected = false;
		boolean actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		sequence = new ArrayList<>();
		sequence.add("cat");
		sequence.add("xyz");
		expected = false;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		sequence = new ArrayList<>();
		sequence.add("xyz");
		sequence.add("cat");
		expected = false;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		sequence = new ArrayList<>();
		sequence.add("xyz");
		sequence.add("ivm");
		expected = false;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		sequence = new ArrayList<>();
		sequence.add("cat");
		sequence.add("cat");
		expected = false;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		//////////////////////
		// Valid sequences  //
		//////////////////////
		
		doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		expected = true;
		actual = doublets.isWordLadder(doublets.getLadder("VIGILANTE", "VIGILANCE"));
		assertEquals(expected, actual);
      
      // doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
// 		expected = true;
// 		actual = doublets.isWordLadder(doublets.getMinLadder("cat", "cat"));
// 		assertEquals(expected, actual);
//       
//       doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
// 		expected = true;
// 		actual = doublets.isWordLadder(doublets.getMinLadder("cat", "hot"));
// 		assertEquals(expected, actual);
      
      
      //test on a sequence of one word
		sequence = new ArrayList<>();
		sequence.add("cat");
		expected = true;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		//test on a sequence of two word
		sequence = new ArrayList<>();
		sequence.add("cat");
		sequence.add("cot");
		expected = true;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
		
		//test on a sequence of lots of words
		sequence = new ArrayList<>();
		sequence.add("cat");
		sequence.add("bat");
		sequence.add("hat");
		sequence.add("cat");
		sequence.add("mat");
		sequence.add("mot");
		sequence.add("cot");
		expected = true;
		actual = doublets.isWordLadder(sequence);
		assertEquals(expected, actual);
	}
   
   @Test
	public void getMinLadderTest() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getMinLadder("CAT", "dog");
		boolean expected = true;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
   
   @Test
	public void getMinLadderTest2() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getMinLadder("tiger", "eagle");
		boolean expected = true;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
   
   
   @Test
	public void getMinLadderTest3() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getMinLadder("ABIOGENETICALLY", "ACIDIMETRICALLY");
		boolean expected = false;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
   
   @Test
	public void getLadderTest() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getLadder("CAT", "dog");
		boolean expected = true;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
   
   @Test
	public void getLadderTest2() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getLadder("tiger", "eagle");
		boolean expected = true;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
   
   @Test
	public void getLadderTest3() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
		List<String> test = doublets.getLadder("", "cake");
		boolean expected = false;
      boolean actual = doublets.isWordLadder(test);
      assertEquals(expected, actual);
   }
}