import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Vincent Canivez (canivvs@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 5b
 *
 * change log:
 * 6/15/16: fixed infinite loop in kmax
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      
      collCheck(coll, comp);
      
      Collection<T> collection = coll;
      T minValue = collection.iterator().next();
      
      for (T element : collection) {
         if (comp.compare(element, minValue) < 0) {
            minValue = element;
         }
      }
      return minValue;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      
      collCheck(coll, comp);
      
      Collection<T> collection = coll;
      T maxValue = collection.iterator().next();
      
      for (T element : collection) {
         if (comp.compare(element, maxValue) > 0) {
            maxValue = element;
         }
      }
      return maxValue;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      T currentElement = null;
      
      collCheck(coll, comp);
      
      List<T> list = new ArrayList<T>(coll);
      java.util.Collections.sort(list, comp);
      
      Iterator<T> itr = list.iterator();
      int i = 0;
      
      T previousElement = itr.next();
      
      while (itr.hasNext()) {
         currentElement = itr.next();
         if (!currentElement.equals(previousElement)) {
            if (++i == k) {
               break;
            }
            previousElement = currentElement;
         }
      }
      if (i != k) {
         throw new NoSuchElementException();
      }
      return previousElement;
   }
   


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
     
      collCheck(coll, comp);
      
      List<T> list = new ArrayList<T>(coll);
      java.util.Collections.sort(list, comp);
      
      int index = 1;
      int i = list.size() - 1;
      if (index != k) {
         while (i > 0) {
            if (!list.get(i).equals(list.get(i - 1))) {
               if (++index == k) {
                  i--;
                  break;
               }
               i--;
            }
            // only modification made on the 5th submission ==>
            else {
               i--;
            }
            // <==   
         }
      }
      
      if (index != k) {
         throw new NoSuchElementException();
      }
      return list.get(i);
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      collCheck(coll, comp);
      
      List<T> listResult = new ArrayList<T>();
      
      Collection<T> collection = coll;
      
      for (T element : collection) {
         if (comp.compare(element, high) <= 0 && comp.compare(element, low) >= 0) {
            listResult.add(element);
         }
      }
      if (listResult.size() == 0) {
         throw new NoSuchElementException();
      }
      
      collection = listResult;
      return collection;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      collCheck(coll, comp);
       
      Collection<T> collection = coll;
      T ceilingValue = max(collection, comp);
      
      for (T element : collection) {
         if (comp.compare(element, key) >= 0 && comp.compare(element, ceilingValue) <= 0) {
            ceilingValue = element;
         }
      }
      return ceilingValue;
         
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      
      collCheck(coll, comp);
       
      Collection<T> collection = coll;
      
      T floorValue = min(collection, comp);
      
      for (T element : collection) {
         if (comp.compare(element, key) <= 0 && comp.compare(element, floorValue) >= 0) {
            floorValue = element;
         }
      }
      return floorValue;
   }
   
   private static <T> void collCheck(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   }
}
