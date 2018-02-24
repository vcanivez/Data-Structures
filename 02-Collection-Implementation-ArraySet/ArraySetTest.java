import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class ArraySetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   /** test size for add method **/
   @Test public void add1() {
      ArraySet test = new ArraySet();
      test.add(2);
      int expected = 1;
      int actual = test.size();
      Assert.assertEquals(expected, actual);
   }
   
   /** test boolean return false for add method **/
   @Test public void add4() {
      ArraySet test = new ArraySet();
      test.add(2);
      boolean expected = false;
      boolean actual = test.add(2);
      Assert.assertEquals(expected, actual);
   }
   
   /** test boolean return true for add method **/
   @Test public void add5() {
      ArraySet test = new ArraySet();
      test.add(2);
      boolean expected = true;
      boolean actual = test.add(3);
      Assert.assertEquals(expected, actual);
   }
   
   /** test resize for add method **/
   @Test public void add2() {
      ArraySet test = new ArraySet();
      test.add(2);
      test.add(1);
      test.add(9);
      test.add(10);
      int expected = 4;
      int actual = test.elements.length;
      Assert.assertEquals(expected, actual);
   }
   
   /** test sorting and duplicates for add method **/
   @Test public void add3() {
      ArraySet test = new ArraySet();
      test.add(2);
      test.add(1);
      test.add(9);
      test.add(1);
      Comparable expected = 9;
      Comparable actual = test.elements[2];
      Assert.assertEquals(expected, actual);
   }
   
   /** test remove method **/
   @Test public void remove() {
      ArraySet test = new ArraySet();
      test.add(2);
      test.add(1);
      test.add(9);
      test.add(1);
      boolean expected = true;
      boolean actual = test.remove(9);
      Assert.assertEquals(expected, actual);
   }
   
   /** test remove method **/
   @Test public void remove2() {
      ArraySet test = new ArraySet();
      test.add(2);
      test.add(1);
      test.add(9);
      test.add(1);
      boolean expected = false;
      boolean actual = test.remove(3);
      Assert.assertEquals(expected, actual);
   }
   
   /** test remove method **/
   @Test public void remove3() {
      ArraySet test = new ArraySet();
      test.add(2);
      test.add(3);
      test.add(9);
      test.add(1);
      test.add(6);
      test.remove(6);
      test.remove(1);
      test.remove(9);
      test.remove(3);
      int expected = 4;
      int actual = test.elements.length;
      Assert.assertEquals(expected, actual);
   }
   
   /** test union method **/
   @Test public void unionTestSet() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> s3 = new ArraySet2<Integer>();
      s3.add(1);
      s3.add(2);
      s3.add(3);
      
      
      Set<Integer> s4 = test.union(s3);
      
      boolean expected = true;
      boolean actual = test.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test union (set) method **/
   @Test public void unionTestSet2() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      
      
      Set<Integer> s3 = new ArraySet2<Integer>();
      s3.add(1);
      s3.add(2);
      s3.add(3);
      
      
      Set<Integer> s4 = test.union(s3);
      
      boolean expected = true;
      boolean actual = s3.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
    /** test union (Arrayset) method **/
   @Test public void unionTest3() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(1);
      s3.add(2);
      s3.add(3);
      
      
      Set<Integer> s4 = test.union(s3);
      
      boolean expected = true;
      boolean actual = s3.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test union (Arrayset) method **/
   @Test public void unionTest4() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(3);
      s3.add(4);
      s3.add(2);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      s5.add(1);
      s5.add(2);
      s5.add(3);
      s5.add(4);
      
      
      Set<Integer> s4 = test.union(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      //test.add(1);
      //test.add(2);
      //test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(1);
      s3.add(2);
      s3.add(3);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest2() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      //s3.add(1);
      //s3.add(2);
      //s3.add(3);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      s5.add(1);
      s5.add(2);
      s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest3() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(1);
      s3.add(2);
      s3.add(3);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest4() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> s3 = new ArraySet2<Integer>();
      s3.add(2);
      s3.add(3);
      s3.add(1);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest5() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(2);
      s3.add(3);
      s3.add(4);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (Arrayset) method **/
   @Test public void complementTest6() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(3);
      s3.add(5);
      s3.add(6);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      s5.add(1);
      s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test complement (set) method **/
   @Test public void complementTest7() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> s3 = new ArraySet2<Integer>();
      s3.add(3);
      s3.add(4);
      s3.add(2);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.complement(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }

   
   /** test intersect (Arrayset) method **/
   @Test public void intersectTest() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      //s3.add(2);
      //s3.add(3);
      //s3.add(4);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      //s5.add(2);
      //s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.intersection(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test intersect (Arrayset) method **/
   @Test public void intersectTest2() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      ArraySet<Integer> s3 = new ArraySet<Integer>();
      s3.add(2);
      s3.add(3);
      s3.add(4);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      s5.add(2);
      s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.intersection(s3);
      
      boolean expected = true;
      boolean actual = s5.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   /** test intersect (set) method **/
   @Test public void intersectTest3() {
      ArraySet<Integer> test = new ArraySet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> s3 = new ArraySet2<Integer>();
      s3.add(2);
      s3.add(1);
      s3.add(3);
      
      ArraySet<Integer> s5 = new ArraySet<Integer>();
      //s5.add(1);
      s5.add(2);
      s5.add(3);
      //s5.add(4);
      
      
      Set<Integer> s4 = test.intersection(s3);
      
      boolean expected = true;
      boolean actual = test.equals(s4);
      Assert.assertEquals(expected, actual);
   }
   
   
   
   
}
