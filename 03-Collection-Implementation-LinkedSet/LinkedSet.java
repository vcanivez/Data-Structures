import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Vincent Canivez (canivvs@auburn.edu)
 * @version 2016-07-12 @ 6:15
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }
   
   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order. O(N)
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if (element == null) {
         return false;
      }
       
      Node newElem = new Node(element);
       
       // case if the collection is empty or smaller than any element in the list
      if (size == 0 || front.element.compareTo(element) > 0) {
         return addFront(element);
      }
       // case if the element is greater than any element in the list
      if (rear.element.compareTo(element) < 0) {
         return addRear(element);
      }
         
       // case if the element is to be inserted in the middle
       // or if it already is in the list
      Node n = ceiling(element);
          
      if (n.element.equals(element)) {
         return false;
      }
   
      return addMid(n, element);
   }
   
      

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order. O(N)
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
       // return false if:
       // - element is null or 
       // - list is empty or 
       // - element is smaller smaller element in the list or 
       // - element is greater than great element in list
      if (element == null || front == null || front.element.compareTo(element) > 0 
          || rear.element.compareTo(element) < 0) {
         return false;
      }
          
       // case if the element is equal to the greatest element in the list
      if (rear.element.equals(element) && size > 1) {
         rear = rear.prev;
         rear.next = null;
         size--;
         return true;
      }
       
       // case if the element is equal the first element in the list
      if (front.element.equals(element)) {
         if (size > 1) {
            front.next.prev = null;
            front = front.next;
            size--;
         }
         else {
            
            front = null;
            size--;
            rear = null;
         }
         return true;
      }
       
       // look for the element to be remove in middle of list
     
      Node n = front;
      int index = 1;
    
      while (!n.element.equals(element) && index < size) {
         n = n.next;
         index++;
      }
       
      if (index == size) {
         return false;
      }
      n.prev.next = n.next;
      n.next.prev = n.prev;
      size--;
      n = null;
   
      return true;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if (element == null || front == null || front.element.compareTo(element) > 0 
          || rear.element.compareTo(element) < 0) {
         return false;
      }
      
      return element.equals(ceiling(element).element);
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order. O(N^2)
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (size != s.size()) {
         return false;
      }
      
      Iterator<T> itr = s.iterator();
      while (itr.hasNext()) {
         if (!contains(itr.next())) {
            return false;
         }
      }
      return true;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order. O(N)
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (size != s.size()) {
         return false;
      }
   
      Iterator itr = iterator();
      Iterator sItr = s.iterator();
      while (itr.hasNext()) {
         if (!itr.next().equals(sItr.next())) {
            return false;
         }
      }
      return true;
   }


   /**
    * Returns a set that is the union of this set and the parameter set. O(N x M)
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s) {
      if (s.size() == 0) {
         return this;
      }
      
      if (size == 0) {
         return s;
      }
      
      LinkedSet<T> copy = this.copySet();
      
      Iterator<T> itr = s.iterator();
      while (itr.hasNext()) {
         T rearItem = rear.element;
         T current = itr.next();
         copy.add(current);
      }
      
      return copy;
   }


   /**
    * Returns a set that is the union of this set and the parameter set. O(Max(M, N)
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s) {
      
      LinkedSet<T> unionSet = new LinkedSet<T>();
      
      if (size == 0 && s.size() == 0) {
         return unionSet;
      }
      
      if (s.size() == 0) {
         return this.copySet();
      }
      
      if (size == 0) {
         return s.copySet();
      }
      
      Iterator<T> itr = iterator();
      Iterator<T> sItr = s.iterator();
      T a = itr.next();
      T b = sItr.next();
      
      while (a != null && b != null) {
         if (a.compareTo(b) > 0) {
            unionSet.addRear(b);
            if (sItr.hasNext()) {
               b = sItr.next();
            }
            else {
               b = null;
            }
         }
         else if (a.compareTo(b) < 0) {
            unionSet.addRear(a);
            if (itr.hasNext()) {
               a = itr.next();
            }
            else {
               a = null;
            }
         }
         else {
            unionSet.addRear(a);
            if (itr.hasNext()) {
               a = itr.next();
            }
            else {
               a = null;
            }
            if (sItr.hasNext()) {
               b = sItr.next();
            }
            else {
               b = null;
            }
         }
      }
      
      while (a != null) {
         unionSet.addRear(a);
         if (itr.hasNext()) {
            a = itr.next();
         }
         else {
            a = null;
         }
      }
      
      while (b != null) {
         unionSet.addRear(b);
         if (sItr.hasNext()) {
            b = sItr.next();
         }
         else {
            b = null;
         }
      }
         
      return unionSet;
      
   }
   

   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      LinkedSet<T> interSet = new LinkedSet<T>();
      if (s.size() == 0 || size == 0) {
         return interSet;
      }
      
      
      Iterator<T> itr = s.iterator();
      
      while (itr.hasNext()) {
         T currentElem = itr.next();
         if (this.contains(currentElem)) {
            interSet.add(currentElem);
         }
      }
      
      return interSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> interSet = new LinkedSet<T>();
      if (s.size() == 0 || size == 0) {
         return interSet;
      }
      
            
      Node u = interSet.front;
      Node n = front;
      Node m = s.front;
      
      int i = 0;
      int j = 0;
      
      while (i < size && j < s.size()) {
         if (m.element.compareTo(n.element) < 0) {
            m = m.next;
            j++;
         }
         else if (m.element.equals(n.element)) {
            if (interSet.isEmpty()) {
               Node temp = new Node(n.element);
               
               u = temp;
               interSet.front = u;
               n = n.next;
               i++;
               m = m.next;
               j++;
               interSet.size++;
            }
            
            
            else{
               Node temp = new Node(n.element);
               
               temp.prev = u;
               u.next = temp;
               u = u.next;
               n = n.next;
               i++;
               m = m.next;
               j++;
               interSet.size++;
            }
         }
         else {
            n = n.next;
            i++;
         }
                        
      }
      interSet.rear = u;   
      
      return interSet;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> compSet = new LinkedSet<T>();
      if (size == 0) {
         return compSet;
      }
      if (s.size() == 0) {
         return this.copySet();
      }
         
      Node n = front;
      boolean isComplement = true;
      int i = 0;
         
      while (i < size) {
         
         isComplement = true;
         Iterator<T> itr = s.iterator();
         while (itr.hasNext()) {
               
            if (n.element.equals(itr.next())) {
               n = n.next;
               i++;
               isComplement = false;
               break;
            }
            
         }
         if (isComplement) {
            compSet.add(n.element);
            n = n.next;
            i++;   
         }
      }
      
      return compSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> compSet = new LinkedSet<T>();
      if (size == 0) {
         return compSet;
      }
      
      if (s.size == 0) {
         return this.copySet();
      }
      
      Node n = front;
      Node u = compSet.front;
      Node m = s.front;
      
      
      if (n.element.compareTo(s.rear.element) > 0 
          || this.rear.element.compareTo(m.element) < 0) {
         return this.copySet();
      }
      
      int i = 0;
      int j = 0;
      
      while (i < size && j < s.size()) {
         if (n.element.compareTo(m.element) < 0) {      
            if (compSet.isEmpty()) {
               Node temp = new Node(n.element);
                              
               compSet.front = temp;
               compSet.size++;
               n = n.next;
               i++;
               
            }
            else {
               Node temp = new Node(n.element);
               
               temp.prev = u;               
               u.next = temp;
               compSet.size++;
               u = u.next;
               n = n.next;
               i++;
            }
         }
         else if (m.element.equals(n.element)) {
            n = n.next;
            i++;
            m = m.next;
            j++;
         }
         else {
            m = m.next;
            j++;
         }
                        
      }
         
         
      compSet.rear = u;
      return compSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }   
  
  /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new LinkedDescIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerSetLinkedIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////
   
   private class LinkedIterator implements Iterator<T> { 
      
      Node current = front;
   
   
   /**
   * Returns true if this iterator has at least one more element
   * to deliver in the iteration.
   *
   * @return true if at least one more element, false otherwise
   *
   */
      public boolean hasNext() {
         return (current != null);
      }
   
   
   /**
   * Returns the next element in the iteration. If there are no
   * more elements in this iteration, a NoSuchElementException is
   * thrown.
   *
   * @return the next element in the iteration
   *
   */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         T result = current.element;
         current = current.next;
      
         return result; 
      
      }
   /**
      * Remove is not supported in this iteration.
      *
      */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
   private class LinkedDescIterator implements Iterator<T> { 
      
      Node current = rear;
   
   
   /**
   * Returns true if this iterator has at least one more element
   * to deliver in the iteration.
   *
   * @return true if at least one more element, false otherwise
   *
   */
      public boolean hasNext() {
         return (current != null);
      }
   
   
   /**
   * Returns the next element in the iteration. If there are no
   * more elements in this iteration, a NoSuchElementException is
   * thrown.
   *
   * @return the next element in the iteration
   *
   */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
      
         T result = current.element;
         current = current.prev;
      
         return result; 
      
      }
   /**
      * Remove is not supported in this iteration.
      *
      */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
      
   private class PowerSetLinkedIterator implements Iterator<Set<T>> { 
      
      int count = (int)Math.pow(2, size) - 1;
      int current = 0;
   
   
   /**
   * Returns true if this iterator has at least one more element
   * to deliver in the iteration.
   *
   * @return true if at least one more element, false otherwise
   *
   */
      public boolean hasNext() {
         return (current <= count);
      }
   
   
   /**
   * Returns the next element in the iteration. If there are no
   * more elements in this iteration, a NoSuchElementException is
   * thrown.
   *
   * @return the next element in the iteration
   *
   */
      public Set<T> next()
      {
         if (!hasNext())
         {
            throw new NoSuchElementException();
         }
      
         Set<T> result = findSet(current);
         current++;
      
         return result; 
      
      }
   /**
      * Remove is not supported in this iteration.
      *
      */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
    /**
    * Computes and return the next subset in the iteration.
    *
    * @return	the next subset in the iteration
    *
    */
   public Set<T> findSet(int positionIn) {
      int position = positionIn;
      String binaryString = Integer.toBinaryString(position);
      int stringSize = binaryString.length();
        
      LinkedSet<T> subSet = new LinkedSet<T>();
         
      Iterator<T> itr = iterator();
         
      int j = 0;
      int n = stringSize - 1;
         
      while (n >= 0) {
         if (binaryString.charAt(n) == '1') {
            subSet.addRear(itr.next());
            j++;
         }
         n--;
      }
      subSet.size = j;
         
      return subSet;
   }
    
      
   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }
   /**
    * Copies sets.
    */
   public LinkedSet<T> copySet() {
      
      Iterator<T> itr = iterator();
      
      LinkedSet<T> copiedSet = new LinkedSet<T>();
      
      if (itr.hasNext()) {
         Node temp = new Node(itr.next());
         copiedSet.front = temp;
         copiedSet.rear = temp;   
         copiedSet.size++;
         
         while (itr.hasNext()) {
            temp = new Node(itr.next());
            copiedSet.rear.next = temp;
            temp.prev = copiedSet.rear;
            copiedSet.rear = temp;
            copiedSet.size++;
         }
      }
      
      return copiedSet;
         
   }
   
   private boolean addFront(T element) {
      Node temp = new Node(element);
      
      if (size == 0) {
         front = temp;
         rear = temp;
         size++;
         return true;
      }
      
      front.prev = temp;
      temp.next = front;
      front = temp;
      size++;
      return true;  
   }
   
   private boolean addRear(T element) {
      Node temp = new Node(element);
      
      if (size == 0) {
         front = temp;
         rear = temp;
         size++;
         return true;
      }
      
      rear.next = temp;
      temp.prev = rear;
      rear = temp;
      size++;
      return true;  
   }
   
   private boolean addMid(Node ceilingIn, T element) {
      Node temp = new Node(element);
      Node ceiling = ceilingIn;
      
      temp.next = ceiling;
      temp.prev = ceiling.prev;
      ceiling.prev.next = temp;
      ceiling.prev = temp;
      size++;      
      
      return true;  
   }
   
   private Node ceiling (T element) {
      Node n = front;
      
      while (n.element.compareTo(element) < 0) {
         n = n.next;
      }
      
      return n;
   }

}
