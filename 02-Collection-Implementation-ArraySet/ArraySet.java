import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * ArraySet.java.
 *
 * Provides an implementation of the Set interface using an
 * array as the underlying data structure. Values in the array
 * are kept in ascending natural order and, where possible,
 * methods take advantage of this. Many of the methods with parameters
 * of type ArraySet are specifically designed to take advantage
 * of the ordered array implementation.
 *
 * @author Vincent Canivez (canivvs@auburn.edu)
 * @version	2016-06-28
 *
 */
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {

   T[] elements;
   int size;

   /**
    * Instantiates an empty set.
    */
   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
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
    * @return  true if this collection contains no elements,
    *               false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }

   /**
    * Return a string representation of this ArraySet.
    *
    * @return a string representation of this ArraySet
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
     * Ensures the collection contains the specified element. Elements are
     * maintained in ascending natural order at all times. Neither duplicate nor
     * null values are allowed.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
   public boolean add(T element) {
      //check if element is null or already in the list
      if (element == null || contains(element)) {
         return false;
      }
      
      // inserts element if array is empty
      if (isEmpty()) {
         elements[0] = element;
         size++;
         return true;
      }
      // finds the position for the element
      int i = 0;
      for (i = 0; i < size(); i++) {
         if (elements[i].compareTo(element) > 0) {
            break;
         }
      }
      
      //check array is full
      if (size() == elements.length) {
         //if array full create a new one that is twice the size
         @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Comparable[elements.length * 2];
         
         // inserts element
         for (int j = 0; j < i; j++) {
            temp[j] = elements[j];
         }
         temp[i] = element;
         if (!((size + 1) == i)) {
            for (int j = i + 1; j < size + 1; j++) {
               temp[j] = elements[j - 1];
            }
         }
         elements = temp;
         size++;
         return true;
      }
      else {
         if (i > size) {
            elements[i] = element;
            size++;
            return true;
         }
         for (int j = size; j > i; j--) {
            elements[j] = elements[j - 1];
         }
         elements[i] = element;
         size++;
      }
      return true;
   }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. Elements are maintained in ascending natural
     * order at all times.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
   public boolean remove(T element) {
      //check if element is null
      if (element == null) {
         return false;
      }
      //check if the element is in the list
      if (contains(element)) {
         int index = find(element);
         while (index + 1 < elements.length - 1 && elements[index + 1] != null) {
            elements[index] = elements[index + 1];
            index++;
         }
         elements[index] = null;
         double dsize = --size;
         double dlength = elements.length;
         if (dsize / dlength < 0.25) {
            @SuppressWarnings("unchecked")
               T[] temp = (T[]) new Comparable[elements.length / 2];
            for (int i = 0; i < size; i++) {
               temp[i] = elements[i];
               elements = temp;
            }
         } 
         return true;   
      }
      return false;
   }

   /**
    * Searches for specified element in this collection.(O(LogN))
    *
    * @param   element  The element whose presence in this collection
    *                   is to be tested.
    * @return  true if this collection contains the specified element,
    *               false otherwise.
    */
   public boolean contains(T element) {
      //check if element is null
      if (element == null || size == 0) {
         return false;
      }
      //binary search to find the element
      int div = 2;
      int mid = size / div;
      
      do {
         div = div * 2;
         if (elements[mid].equals(element)) {
            return true;
         }
         else if (elements[mid].compareTo(element) < 0) {
            mid = mid + size / div;
         }
         else {
            mid = mid - size / div;
         }
      }
      
      while (div <= size);
            
      return (elements[0].equals(element) || elements[size - 1].equals(element));
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order. O(NlogN)
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (size != s.size()) {
         return false;
      }
      Iterator<T> itr = s.iterator();
      
      while (itr.hasNext()) {
         if (!this.contains(itr.next())) {
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
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(ArraySet<T> s) {
      if (this.size() != s.size) {
         return false;
      }
      for (int i = 0; i < size; i++) {
         if (!this.elements[i].equals(s.elements[i])) {
            return false;
         }
      }
      return true;
   }

   /**
    * Returns a set that is the union of this set and the parameter set. O(N x M)
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(Set<T> s) {
      
      if (size == 0 && s.size() == 0) {
         return null;
      }
      if (this.equals(s)) {
         return this;
      }
      ArraySet<T> unionSet = new ArraySet<T>();
      if (size != 0) {
         unionSet.resize(size);
      }
      
      for (int i = 0; i < size; i++) {
         unionSet.elements[i] = elements[i]; 
         unionSet.size = size;
      }
                  
      Iterator<T> itr = s.iterator();
      
      while (itr.hasNext()) {
         T nextElement = itr.next();
         unionSet.add(nextElement);
      }
      
      return unionSet;
   }

   /**
    * Returns a set that is the union of this set and the parameter set. O(max(N,M)
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(ArraySet<T> s) {
      if (size == 0 && s.size() == 0) {
         return this;
      }
      if (this.equals(s) || s.size() == 0) {
         return this;
      }
      if (size == 0) {
         return s;
      }
      
      @SuppressWarnings("unchecked")
         T[] temp = (T[]) new Comparable[size + s.size()];
      ArraySet<T> unionSet = new ArraySet<T>();
      unionSet.elements = temp;
      
      int i = 0;
      int j = 0;
      int k = 0;
      if (size < s.size()) {
         while (i < s.size() && j < size) {
            if (s.elements[i].compareTo(this.elements[j]) < 0) {
               unionSet.elements[k] = s.elements[i];
               unionSet.size++;
               k++;
               i++;
            }
            else if (s.elements[i].equals(this.elements[j])) {
               unionSet.elements[k] = s.elements[i];
               unionSet.size++;
               k++;
               i++;
               j++;
            }
            else {
               unionSet.elements[k] = elements[j];
               unionSet.size++;
               k++;
               j++;
            }
                        
         }
         if (i < s.size()) {
            int x = i;
            while (x < size) {
               unionSet.elements[k] = s.elements[x];
               unionSet.size++;
               x++;
               k++;
            }
         }
         
         if (j < size) {
            int x = j;
            while (x < s.size()) {
               unionSet.elements[k] = elements[x];
               unionSet.size++;
               x++;
               k++;
            }
         }
      }
      else {   
         while (j < size && i < s.size()) {
            if (s.elements[i].compareTo(this.elements[j]) < 0) {
               unionSet.elements[k] = s.elements[i];
               unionSet.size++;
               k++;
               i++;
            }
            else if (s.elements[i].equals(this.elements[j])) {
               unionSet.elements[k] = s.elements[i];
               unionSet.size++;
               k++;
               i++;
               j++;
            }
            else {
               unionSet.elements[k] = elements[j];
               unionSet.size++;
               k++;
               j++;
            }
                        
         }
         if (i < s.size()) {
            int x = i;
            while (x < size) {
               unionSet.elements[k] = s.elements[x];
               unionSet.size++;
               x++;
               k++;
            }
         }
         
         if (j < size) {
            int x = j;
            while (x < s.size()) {
               unionSet.elements[k] = elements[x];
               unionSet.size++;
               x++;
               k++;
            }
         }
      }
      return unionSet;
   }


   /**
    * Returns a set that is the intersection of this set
    * and the parameter set. O(N * M)
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      
      if (s.size() == 0) {
         return s;
      }
      int minArraySize;
      ArraySet<T> interSet = new ArraySet<T>();
      if (size < s.size()) {
         minArraySize = size;
      }
      else {
         minArraySize = s.size();
      }
      interSet.resize(minArraySize);
      interSet.size = minArraySize;
      
      Iterator<T> itr = s.iterator();
      
      int i = 0;
      while (itr.hasNext()) {
         T nextElem = itr.next();
         if (this.contains(nextElem)) {
            interSet.elements[i] = nextElem;
            i++;
         }
      }
      
      return interSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.O(max(N, M))
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(ArraySet<T> s) {
      
      if (s.size() == 0) {
         return s;
      }
      int minArraySize;
      ArraySet<T> interSet = new ArraySet<T>();
      if (size < s.size()) {
         minArraySize = size;
      }
      else {
         minArraySize = s.size();
      }
      interSet.resize(minArraySize);
      
      int i = 0;
      int j = 0;
      int k = 0;
      while (j < size && i < s.size()) {
         if (s.elements[i].compareTo(this.elements[j]) < 0) {
            i++;
         }
         else if (s.elements[i].equals(this.elements[j])) {
            interSet.elements[k] = s.elements[i];
            interSet.size++;
            k++;
            i++;
            j++;
         }
         else {
            j++;
         }
                        
      }
         
      
      return interSet;
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.O(N * M)
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
     
      if (s.size() == 0) {
         return this;
      }
            
      ArraySet<T> compSet = new ArraySet<T>();
      for (int i = 0; i < size; i++) {
         compSet.add(elements[i]);
      }
      
      
      Iterator<T> itr = s.iterator();
      int i = 0;
      
      while (itr.hasNext()) {
         T nextElem = itr.next();
         for (i = 0; i < compSet.size(); i++) {
            if (compSet.elements[i].equals(nextElem)) {
               compSet.remove(compSet.elements[i]);
               break;
            }
         }
         
      }
      
      return compSet;
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.O(max(N, M))
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(ArraySet<T> s) {
      if (size == 0 || s.size() == 0 || elements[0].compareTo(s.elements[s.size() - 1]) > 0 
          || elements[size - 1].compareTo(s.elements[0]) < 0) {
         return this;
      }
      int maxArraySize;
      ArraySet<T> compSet = new ArraySet<T>();
      if (size > s.size()) {
         maxArraySize = size;
      }
      else {
         maxArraySize = s.size();
      }
      compSet.resize(maxArraySize);
     
      int i = 0;
      int j = 0;
      int k = 0;
      while (j < size && i < s.size()) {
         if (elements[i].compareTo(s.elements[j]) < 0) {
            compSet.elements[k] = elements[i];
            compSet.size++;
            i++;
            k++;
         }
         else if (s.elements[j].equals(this.elements[i])) {
            i++;
            j++;
         }
         else {
            j++;
         }
                        
      }
         
         
      
      return compSet;
   }



   /**
    * Returns an iterator over the elements in this ArraySet.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> iterator() {
   
      // ALMOST ALL THE TESTS DEPEND ON THIS METHOD WORKING CORRECTLY.
      // MAKE SURE YOU GET THIS ONE WORKING FIRST.
      // HINT: JUST USE THE SAME CODE/STRATEGY AS THE ARRAYBAG CLASS
      // FROM LECTURE. THE ONLY DIFFERENCE IS THAT YOU'LL NEED THE
      // ARRAYITERATOR CLASS TO BE NESTED, NOT TOP-LEVEL.
   
      return new ArrayIterator<T>(elements, size);
   }

      
   /**
    * Returns an iterator over the members of the power set
    * of this ArraySet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new PowerSetArrayIterator();
   }
   
   public class PowerSetArrayIterator implements Iterator<Set<T>> {
   
   /** the array of elements to be iterated over. */
      private T[] items; 
   
   /** the number of elements in the array. */
      private int count;
      
   /** the current position in the iteration. */
      private int current;
      
   
   /**
    * Initializes the iterator on a given array with
    * a given number of elements.
    * 
    */	
      public PowerSetArrayIterator()
      {
         items = elements;
         count = (int)Math.pow(2, size) - 1;
         current = 0;
      }
   
   
   /**
    * Returns true if this iterator has at least one more element
    * to deliver in the iteration.
    *
    * @return	true if at least one more element, false otherwise
    *
    */
      public boolean hasNext()
      {
         return (current <= count);
      }
   
   
   /**
    * Returns the next element in the iteration. If there are no
    * more elements in this iteration, a NoSuchElementException is
    * thrown.
    *
    * @return	the next element in the iteration
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
    * Computes and return the next subset in the iteration.
    *
    * @return	the next subset in the iteration
    *
    */
      public Set<T> findSet(int positionIn) {
         int position = positionIn;
         String binaryString = Integer.toBinaryString(position);
         int stringSize = binaryString.length();
        
         ArraySet<T> subSet = new ArraySet<T>();
         
         subSet.resize(stringSize);
         
         int i = 0;
         int j = 0;
         int n = stringSize - 1;
         
         while (n >= 0) {
            if (binaryString.charAt(n) == '1') {
               subSet.elements[j] = elements[i];
               j++;
            }
            n--;
            i++;
         }
         subSet.size = j;
         
         return subSet;
      }
   
   
   /**
    * Remove is not supported in this iteration.
    *
    */
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
   
   private int find(T element) {
      int i = 0;
      for (i = 0; i < size(); i++) {
         if (elements[i].equals(element)) {
            break;
         }
      }
      return i;
   }
      
   public class ArrayIterator<T> implements Iterator<T>
   {
   
   /** the array of elements to be iterated over. */
      private T[] items; 
   
   /** the number of elements in the array. */
      private int count;
      
   /** the current position in the iteration. */	
      private int current;
      
   
   /**
    * Initializes the iterator on a given array with
    * a given number of elements.
    *
    * @param collection	the array to e iterated over
    * @param size			the number of elements in the array
    * 
    */	
      public ArrayIterator(T[] collection, int size)
      {
         items = collection;
         count = size;
         current = 0;
      }
   
   
   /**
    * Returns true if this iterator has at least one more element
    * to deliver in the iteration.
    *
    * @return	true if at least one more element, false otherwise
    *
    */
      public boolean hasNext()
      {
         return (current < count);
      }
   
   
   /**
    * Returns the next element in the iteration. If there are no
    * more elements in this iteration, a NoSuchElementException is
    * thrown.
    *
    * @return	the next element in the iteration
    *
    */
      public T next()
      {
         if (!hasNext())
         {
            throw new NoSuchElementException();
         }
      
         T result = items[current];
         current++;
      
         return result; 
      
      }
   
   
   /**
    * Remove is not supported in this iteration.
    *
    */
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
   
   /**
    * Returns an iterator over the elements in this ArraySet.
    * The elements are returned in descending order.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> descendingIterator() {
      return new DescArrayIterator<T>(elements, size);
   }

   
   public class DescArrayIterator<T> implements Iterator<T>
   {
   
   /** the array of elements to be iterated over. */
      private T[] items; 
   
   /** the number of elements in the array. */
      private int count;
      
   /** the current position in the iteration. */	
      private int current;
      
   
   /**
    * Initializes the iterator on a given array with
    * a given number of elements.
    *
    * @param collection	the array to e iterated over
    * @param size	the number of elements in the array
    * 
    */	
      public DescArrayIterator(T[] collection, int size)
      {
         items = collection;
         count = size;
         current = size - 1;
      }
   
   
   /**
    * Returns true if this iterator has at least one more element
    * to deliver in the iteration.
    *
    * @return	true if at least one more element, false otherwise
    *
    */
      public boolean hasNext()
      {
         return (current >= 0);
      }
   
   
   /**
    * Returns the next element in the iteration. If there are no
    * more elements in this iteration, a NoSuchElementException is
    * thrown.
    *
    * @return	the next element in the iteration
    *
    */
      public T next()
      {
         if (!hasNext())
         {
            throw new NoSuchElementException();
         }
      
         T result = items[current];
         current--;
      
         return result; 
      
      }
   
   
   /**
    * Remove is not supported in this iteration.
    *
    */
      public void remove()
      {
         throw new UnsupportedOperationException();
      }
   }
   
   /**
    * Resize the backing array.
    */
   private void resize(int capacity) {
      T[] a = Arrays.<T>copyOf(elements, capacity);
      elements = a;
   }   
   
}
