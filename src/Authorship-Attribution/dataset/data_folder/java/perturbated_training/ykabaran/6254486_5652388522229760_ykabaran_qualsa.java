package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class QualsA implements Problem {
   
   private static final String INSOMNIA = "INSOMNIA";
   
   private long startNumber;
   private long lastNumber;
   private boolean[] encounteredDigits;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     startNumber = Long.parseLong(input.readLine());
   }
   
   private boolean allDigitsEncountered(){
     for(int i=0; i<10; ++i){
       if(encounteredDigits[i] == false){
         return false;
       }
     }
     return true;
   }
   
   private void setLastNumber(long newNumber){
     lastNumber = newNumber;
     
     long number = lastNumber;
     while(number > 0){
       int digit = (int) (number % 10);
       encounteredDigits[digit] = true;
       number /= 10;
     }
   }
 
   @Override
   public void solve() {
     if(startNumber < 1){
       lastNumber = 0;
       return;
     }
     
     encounteredDigits = new boolean[10];
     for(int i=0; i<10; ++i){
       encounteredDigits[i] = false;
     }
     
     long coefficient = 1;
     do {
       setLastNumber(startNumber * coefficient);
       ++coefficient;
     } while(allDigitsEncountered() == false);
   }
 
   @Override
   public String getSolution() {
     if(lastNumber == 0){
       return INSOMNIA;
     }
     return Long.toString(lastNumber);
   }
   
 }
