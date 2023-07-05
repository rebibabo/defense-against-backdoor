
 package durazom.oversizedpancakeflipper;
 
 import durazom.primepalindrome.*;
 import durazom.util.Case;
 
 
 public class OversizedPancakeFlipperCase implements Case {
 
     int cases = 1;
     
     @Override
     public String solve(String caseString) {
         
         int flips = 0;
         String[] split = caseString.split(" ");
         char[] pancakes = split[0].toCharArray();
         Integer K = Integer.parseInt(split[1]);
         
         for( int i = 0 ; i < pancakes.length ; i++ ){
             
             if( pancakes[i]== '-' ){
                 
                 pancakes = flip( pancakes, i, K );
                 
                 flips++;
                 
             }
             
         }
         
         String result = "Case #"+(cases++)+": ";
         
         if( validate(pancakes) ){
             result +=flips;
         }else{
             result +="IMPOSSIBLE";
         }
         
         return result;
         
     }
     
     
     private char[] flip( char[] pancakes, int fromIndex, int size ){
         
         if( fromIndex + size > pancakes.length ){
             return pancakes;
         }
         
         for( int i = fromIndex ; i < fromIndex+size && i < pancakes.length ; i++ ){
             
             pancakes[i] = pancakes[i]=='+'?'-':'+';
             
         }
         
         return pancakes;   
     }
     
     private boolean validate(char[] pancakes){
         
         for( int i = 0 ; i < pancakes.length ; i++ ){
             
             if( pancakes[i]== '-' ){
                 
                 return false;
                 
             }
             
         }
         
         return true;
         
     }
     
     
     
 }
