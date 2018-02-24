import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** Test add method case "collection empty". **/
   @Test public void Add() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      int expected = 1;
      int actual = test.size();
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add method case "element greater than rear". **/
   @Test public void Add2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(3);
      test.add(2);
      test.add(4);
      int expected = 4;
      int actual = test.size();
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add method case "element smaller than front". **/
   @Test public void Add3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(2);
      test.add(1);
      int expected = 1;
      int actual = test.front.element;
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add method case "element already in list". **/
   @Test public void Add4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(2);
      boolean expected = false;
      boolean actual = test.add(2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add method case "element inserted in middle". **/
   @Test public void Add5() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(2);
      int expected = 2;
      int actual = test.front.next.element;
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add remove case "element to remove is at the end". **/
   @Test public void remove() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(2);
      boolean expected = true;
      boolean actual = test.remove(4);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add remove case "element to remove is at the beginning". **/
   @Test public void remove2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(2);
      test.remove(1);
      int expected = 2;
      int actual = test.front.element;
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add remove case "element to remove is in the middle". **/
   @Test public void remove3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(2);
      test.remove(2);
      int expected = 4;
      int actual = test.front.next.element;
      Assert.assertEquals(expected, actual);
   }
   
   /** Test add remove case "element to remove is in the middle". **/
   @Test public void remove4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.remove(2);
      test.remove(1);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      
      boolean expected = true;
      boolean actual = test2.equals(test);
      Assert.assertEquals(expected, actual);
   }
     
   /** Test add contains case "element to find is in the middle". **/
   @Test public void contains() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(3);
      test.add(2);
      
      boolean expected = true;
      boolean actual = test.contains(3);
      Assert.assertEquals(expected, actual);
   }
   // contains(4); add(4); contains(4); add(1); add(5); add(3); add(1); add(2); contains(3); remove(4); contains(4); remove(1); remove(5) contains(5);
   /** Test contains case "element to find is at the beginning". **/
   @Test public void containsXL() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      Assert.assertEquals(false, test.contains(4));
      test.add(4);
      Assert.assertEquals(true, test.contains(4));
      test.add(1);
      test.add(5);
      test.add(3);
      test.add(1);
      test.add(2);
      Assert.assertEquals(true, test.contains(3));
      test.remove(4);
      Assert.assertEquals(false, test.contains(4));
      test.remove(1);
      test.remove(5);
      Assert.assertEquals(false, test.contains(5));
      
      
        }
   
   /** Test contains case "element to find is at the beginning". **/
   @Test public void contains2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(4);
      test.add(3);
      test.add(2);
      
      boolean expected = true;
      boolean actual = test.contains(1);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test contains case "element not in the list but in the range". **/
   @Test public void contains3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(6);
      test.add(3);
      test.add(2);
      
      boolean expected = false;
      boolean actual = test.contains(5);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test contains case "element not in the list and below range". **/
   @Test public void contains4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      boolean expected = false;
      boolean actual = test.contains(1);
      Assert.assertEquals(expected, actual);
   }
   
    /** Test contains case "element not in the list and below range". **/
   @Test public void contains5() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      boolean expected = false;
      boolean actual = test.contains(1);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test contains case "element not in the list and below range". **/
   @Test public void contains6() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(4);
      test.add(6);
      test.add(3);
      test.add(2);
      test.remove(3);
      
      boolean expected = false;
      boolean actual = test.contains(3);
      Assert.assertEquals(expected, actual);
   }
   
   
   /************************************************/
   /*       tests for equals(Set<T> s)             */
   /************************************************/
   
   
   /** Test equals case "sets are equal". **/
   @Test public void equals() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      test2.add(2);
      
      boolean expected = true;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "sets are not equal". **/
   @Test public void equals2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "Linkeset is empty". **/
   @Test public void equals3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "set is empty". **/
   @Test public void equals4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "both sets empty is empty". **/
   @Test public void equals5() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      
      boolean expected = true;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for equals(LinkedSet<T> s)       */
   /************************************************/
   
   /** Test equals case "sets are equal". **/
   @Test public void equalsL() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      test2.add(2);
      
      boolean expected = true;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "sets are not equal". **/
   @Test public void equalsL2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "Linkeset is empty". **/
   @Test public void equalsL3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(6);
      test2.add(3);
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "set is empty". **/
   @Test public void equalsL4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(2);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      
      boolean expected = false;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test equals case "both sets empty is empty". **/
   @Test public void equalsL5() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      
      boolean expected = true;
      boolean actual = test.equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for union(Set<T> s)              */
   /************************************************/
   
   /** Test equals case "sets are equal". **/
   @Test public void unionSet() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(4);
      test.add(2);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(6);
      test2.add(3);
      test2.add(2);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(2);
      test3.add(5);
      test3.add(3);
      
      boolean expected = true;
      boolean actual = test.union(test2).equals(test3);
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for union(LinkedSet<T> s)              */
   /************************************************/
   
   /** Test union **/
   @Test public void unionSetL() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(10);
      test.add(6);
      test.add(4);
      test.add(2);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(6);
      test2.add(3);
      test2.add(2);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(2);
      test3.add(5);
      test3.add(3);
      
      boolean expected = true;
      boolean actual = test3.equals(test.union(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /** Test union. **/
   @Test public void unionSetL2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      // test2.add(5);
//       test2.add(6);
//       test2.add(3);
//       test2.add(2);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(2);
      test3.add(5);
      test3.add(3);
      
      boolean expected = true;
      boolean actual = test.equals(test.union(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /** Test union. **/
   @Test public void unionSetL3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();

      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(11);
      test3.add(13);
      
      
      LinkedSet<Integer> test4 = new LinkedSet<Integer>();
      test4.add(1);
      test4.add(2);
      test4.add(4);
      test4.add(6);
      test4.add(10);
      
      LinkedSet<Integer> test5 = new LinkedSet<Integer>();
      test5.add(4);
      test5.add(6);
      test5.add(10);
      
      // Integer a = test3.rear.element;
//       Integer c = test.rear.element;
      
      /*boolean expected = true;
      boolean actual = test.equals(test.union(test2));
      Assert.assertEquals(expected, actual);*/
      
      Assert.assertEquals("[1, 2, 3]", test.union(test2).toString());
      
      Assert.assertEquals("[1, 2, 3]", test.toString());
      Assert.assertEquals("[4, 6, 10, 11, 13]", test3.toString());
      Assert.assertEquals("[1, 2, 4, 6, 10]", test4.toString());
      
      Assert.assertEquals("[]", test2.toString());
      
      Assert.assertEquals("[1, 2, 3, 4, 6, 10]", test.union(test5).toString());

   }
   
   /** Test union. **/
   @Test public void unionSetL4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      // test.add(1);
//       test.add(2);
//       test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(1);
      test2.add(2);
      test2.add(3);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(2);
      test3.add(5);
      test3.add(3);
      
      boolean expected = true;
      boolean actual = test2.equals(test.union(test2));
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void unionSetL5() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      // test.add(1);
//       test.add(2);
//       test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      // test2.add(1);
//       test2.add(2);
//       test2.add(3);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(10);
      test3.add(6);
      test3.add(4);
      test3.add(2);
      test3.add(5);
      test3.add(3);
      
      boolean expected = true;
      boolean actual = test2.equals(test.union(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for intersection(LinkedSet<T> s)              */
   /************************************************/
   
   /** Test intersection case "". **/
   @Test public void intersection() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(2);
      
      
      boolean expected = true;
      boolean actual = test.intersection(test2).equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
    /** Test intersection case "". **/
   @Test public void intersection2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      test.add(5);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(2);
      test2.add(5);
      
      
      boolean expected = true;
      boolean actual = test.intersection(test2).equals(test2);
      Assert.assertEquals(expected, actual);
   }
   
   /** Test intersection case "". **/
   @Test public void intersection3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      test.add(5);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(10);
      test2.add(7);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      
      
      boolean expected = true;
      boolean actual = test.intersection(test2).equals(test3);
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for intersection(Set<T> s)              */
   /************************************************/
   
   /** Test intersection case "". **/
   @Test public void intersectionSet() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(1);
      test2.add(2);
      test2.add(3);
      test2.add(10);
      
      
      
      
      boolean expected = true;
      boolean actual = test.equals(test.intersection(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /************************************************/
   /*       tests for comp (LinkedSet<T> s)              */
   /************************************************/
   
   /** Test comp case "". **/
   @Test public void complement() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(1);
      test2.add(15);
      test2.add(3);
      test2.add(10);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(2);
      
      
      boolean expected = true;
      boolean actual = test3.equals(test.complement(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /** Test comp case "". **/
   @Test public void complement2() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(2);
      test2.add(1);
      test2.add(3);
      
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      
      
      
      boolean expected = true;
      boolean actual = test3.equals(test.complement(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /** Test comp case "". **/
   @Test public void complement3() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(8);
      test2.add(6);
      
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      
      
      
      boolean expected = true;
      boolean actual = test.equals(test.complement(test2));
      Assert.assertEquals(expected, actual);
   }
   
   /** Test comp case "". **/
   @Test public void complement4() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      LinkedSet<Integer> test2 = new LinkedSet<Integer>();
      
      
      
      
      boolean expected = true;
      boolean actual = test.equals(test.complement(test2));
      Assert.assertEquals(expected, actual);
   }


   /************************************************/
   /*       tests for comp (Set<T> s)              */
   /************************************************/
   
   /** Test comp case "". **/
   @Test public void complementSet() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      test.add(1);
      test.add(2);
      test.add(3);
      
      Set<Integer> test2 = new LinkedSet<Integer>();
      test2.add(5);
      test2.add(1);
      test2.add(15);
      test2.add(3);
      test2.add(10);
      
      LinkedSet<Integer> test3 = new LinkedSet<Integer>();
      test3.add(2);
      
      
      boolean expected = true;
      boolean actual = test3.equals(test.complement(test2));
      Assert.assertEquals(expected, actual);
   }

   


   
   /************************************************/
   /*       tests  nested classes                  */
   /************************************************/

   
   /** copySet testing **/
   @Test public void copySet() {
      LinkedSet<Integer> test = new LinkedSet<Integer>();
      
      test.add(10);
      test.add(6);
      test.add(3);
      test.add(11);
      test.add(112);
      
      
      test.copySet();
      
      boolean expected = true;
      boolean actual = test.equals(test.copySet());
      Assert.assertEquals(expected, actual);
   }

}
