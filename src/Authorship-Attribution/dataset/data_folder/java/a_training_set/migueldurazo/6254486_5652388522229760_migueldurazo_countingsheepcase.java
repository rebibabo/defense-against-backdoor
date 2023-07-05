package durazom.codejam.countingsheep;
 
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.interfaces.Case;
 import durazom.codejam.storecredit.*;
 import java.util.ArrayList;
 import java.util.Collections;
 import static java.util.Collections.list;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;
 
 
 
 public class CountingSheepCase extends Case {
 
     private long initial = 0l;
     private long fallsSleep = 0l;
     private boolean insomnia = false;
 
     public long getInitial() {
         return initial;
     }
 
     public void setInitial(long initial) {
         this.initial = initial;
     }
 
     public long getFallsSleep() {
         return fallsSleep;
     }
 
     public void setFallsSleep(long fallsSleep) {
         this.fallsSleep = fallsSleep;
     }
 
     public boolean isInsomnia() {
         return insomnia;
     }
     
     
    
 
     @Override
     public boolean solve() {
 
         Set<Integer> digitsSeen = new HashSet<>();
         
         if( initial == 0 ){
             
             insomnia = true;
             solved = true;
             
             
         }else{
             
             long counter = 1 ;
             long currentNumber = initial;
             while(true){
                 
                 if( counter == 10*10*100000 ){
                     insomnia = true;
                     break;
                 }
                 
                 digitsSeen.addAll(getDigits(currentNumber));
                 
                 if( digitsSeen.size() == 10 ){
                     fallsSleep = currentNumber;
                     break;
                 }
                 
                 currentNumber = ( initial*++counter );
                 
                 
             }
             
             solved = true;
             
         
         
         }
 
         return solved;
 
     }
     
     public Set<Integer> getDigits( Long l ){
         Set<Integer> set = new HashSet<>();
         String stringValue = String.valueOf(l);
         for( int i = 0 ; i < stringValue.length() ; i++ ){
             set.add( Integer.parseInt(stringValue.charAt(i)+"") );
         }
         return set;
     }
     
 
 }
