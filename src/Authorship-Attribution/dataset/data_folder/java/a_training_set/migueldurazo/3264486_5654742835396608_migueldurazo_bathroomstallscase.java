
 package durazom.bathroomstalls;
 
 import durazom.tidynumbers.*;
 import durazom.util.Case;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Queue;
 import java.util.Stack;
 
 
 public class BathroomStallsCase implements Case {
 
     int cases = 1;
 
     BigInteger two = new BigInteger("2");
     BigInteger one = new BigInteger("1");
     BigInteger zero = new BigInteger("0");
     
     @Override
     public String solve(String caseString) {
 
         String[] split = caseString.split(" ");
         String emptyStalls = split[0];
         String numberOfPeople = split[1];
         
         BigInteger peopleLeft = new BigInteger( numberOfPeople );
         
         
         LinkedHashMap<String, BigInteger> sortedNumbersGrouped = new LinkedHashMap<>();
         
         
 
         BigInteger min = zero;
         BigInteger max = zero;
         
         addValueToCountingMap(sortedNumbersGrouped, emptyStalls);
         
         if (numberOfPeople.compareTo(emptyStalls) !=0 ) {
             
             BigInteger maxNumber = zero;
             BigInteger minor = zero;
             BigInteger major = zero;
 
             while (peopleLeft.compareTo( zero ) > 0  ) {
                 
                     String maxNumberString = popValueFromMap(sortedNumbersGrouped);
                 
                     maxNumber = new BigInteger( maxNumberString );
                     
                     maxNumber = maxNumber.subtract(one);
                 
                     minor = maxNumber.divide( two );
                     major = maxNumber.subtract(minor);
                     
                     if( major.compareTo(zero) > 0 ){
                         addValueToCountingMap(sortedNumbersGrouped, major.toString());
                     }
                     
                     if( minor.compareTo(zero) > 0 ){
                         addValueToCountingMap(sortedNumbersGrouped, minor.toString());
                     }
                     
                     peopleLeft = peopleLeft.subtract(one);
                     
                     if( !peopleLeft.equals(zero) ){
 
                         BigInteger spacesToRemove = sortedNumbersGrouped.get(maxNumberString);
 
                         if( spacesToRemove != null && !spacesToRemove.equals( zero ) ){
 
                             if( peopleLeft.compareTo( spacesToRemove ) < 0 ){
 
                                 spacesToRemove = peopleLeft;
 
                             }
 
                             simulatePopFromValue(sortedNumbersGrouped, maxNumberString, major.toString(), minor.toString(), spacesToRemove);
 
                             peopleLeft = peopleLeft.subtract( spacesToRemove );
 
                             System.out.println( peopleLeft );
 
                         }
 
                     }
 
             }
 
             min = minor;
             max = major;
 
         }
 
         String maxmin = max.toString() + " " + min.toString();
 
         String result = "Case #" + (cases++) + ": ";
 
         result += maxmin;
 
         return result;
 
     }
     
     private void addValueToCountingMap(  Map<String, BigInteger> map, String value ){
         
         addValueToCountingMap(map, value, one);
         
     }
     
     private void addValueToCountingMap(  Map<String, BigInteger> map, String value, BigInteger size ){
     
         if( value.equals("0") ) return;
         
         if(!map.containsKey(value) ){
             
             map.put(value, zero);
         }
         
         BigInteger sum = map.get( value );
         
         map.put( value , sum.add(size));
         
     }
     
     private String popValueFromMap( Map<String, BigInteger> map ){
         
         Entry<String,BigInteger> entry = map.entrySet().iterator().next();
 
         String key = entry.getKey();
         
         removeAmountFromValue(map, key, one);
 
         return key;
     }
     
     private void removeAmountFromValue(Map<String, BigInteger> map, String value, BigInteger amount  ){
         
         if( !map.containsKey(value) ) return;
         
         BigInteger currentAmount = map.get(value);
         map.put(value, currentAmount.subtract( amount ));
         
         if( map.get(value).equals(zero) ){
             
             map.remove(value);
             
         }
         
     }
     
     private void simulatePopFromValue( Map<String, BigInteger> map, String value,
             String maxValue, String minValue, BigInteger spaceToRemove ){
         
         removeAmountFromValue(map, value, spaceToRemove);
         addValueToCountingMap(map, maxValue, spaceToRemove);
         addValueToCountingMap(map, minValue, spaceToRemove);
         
     }
 }
