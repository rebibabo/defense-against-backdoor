package durazom.codejam.pancakerevenge;
 
 import durazom.codejam.countingsheep.*;
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
 
 
 
 public class PancakeRevengeCase extends Case {
 
     private long flips = 0l;
     private String pancakeTower ="";
 
     public long getFlips() {
         return flips;
     }
 
     public void setPancakeTower(String pancakeTower) {
         this.pancakeTower = pancakeTower;
     }
 
 
     @Override
     public boolean solve() {
         
         StringBuilder sb = new StringBuilder();
         
         for( int i = 0 ; i < pancakeTower.length() ; i++ ){
             
             sb.append("+");
             
         }
        
         String target = sb.toString();
         
         this.flips = 0;
         
         while( !pancakeTower.equals(target) ){
             
             int lowestBlankPancake = pancakeTower.lastIndexOf("-");
             
             pancakeTower = buildNewTower(pancakeTower, lowestBlankPancake);
             
             this.flips++;
             
         }
         
         solved = true;
 
         return solved;
 
     }
     
     private String buildNewTower( String original, int lastIndex ){
         
         StringBuilder sb = new StringBuilder();
         
         for( int i = 0 ; i < original.length() ; i++ ){
             
             char pancake = original.charAt(i);
             
             if( i <= lastIndex ){
                 
                 if( pancake == '+' ){
                     pancake = '-';
                 }else{
                     pancake = '+';
                 }   
             }
             
             sb.append(pancake);
             
         }
         
         return sb.toString();
         
     }
     
    
     
 
 }
