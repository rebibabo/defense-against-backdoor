
 package durazom.tidynumbers;
 
 import durazom.util.Case;
 import java.math.BigInteger;
 
 
 public class TidyNumbersCase implements Case {
 
     int cases = 1;
     
     @Override
     public String solve(String caseString) {
         
         
         char[] number = caseString.toCharArray();
         String resultingNumber = caseString;
         
         
         
         int chosenDigitIndex = 0;
         boolean foundDecrease = false;
         for( int i = 0 ; i < number.length-1 ; i++ ){
             
             if( Integer.parseInt( number[i]+"" ) > Integer.parseInt( number[i+1]+"" )){
                 chosenDigitIndex = i;
                 foundDecrease = true;
                 break;
             }
             
         }
         
         
         if(foundDecrease)
         {
             resultingNumber = "";
         
             while( chosenDigitIndex > 0 && number[chosenDigitIndex-1] == number[chosenDigitIndex]  ){
 
                 chosenDigitIndex--;
             }
 
             
 
             if( number[chosenDigitIndex]!='1' ){
 
                 for(  int i = 0 ; i < number.length ; i++ ){
 
                     if( i == chosenDigitIndex ){
 
                         resultingNumber+= Integer.parseInt( number[i]+"" )-1;
 
                     }else{
 
                         if( i < chosenDigitIndex ){
 
                             resultingNumber+= number[i];
 
                         }else{
 
                             resultingNumber+= '9';
 
                         }
 
                     }
 
                 }
 
             }else{
 
                 for(  int i = 0 ; i < number.length-1 ; i++ ){
 
                     resultingNumber+= '9';
 
                 }
 
             }
         
         }
         
         String result = "Case #"+(cases++)+": ";
         
         result += resultingNumber;
         
         return result;
         
     } 
 }
