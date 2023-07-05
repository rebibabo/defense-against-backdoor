package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class QualsB implements Problem {  
   
   private String initial;
   private boolean[] current;
   private int length;
   private long flipCount;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     initial = input.readLine();
   }
   
   private boolean isCompleted(){
     for(int i=0; i<length; ++i){
       if(current[i] == false){
         return false;
       }
     }
     return true;
   }
   
   private void flipTopN(int n){
     boolean[] toFlip = new boolean[n];
     for(int i=0; i<n; ++i){
       toFlip[i] = current[i];
     }
     
     for(int i=0; i<n; ++i){
       current[n - 1 - i] = !toFlip[i];
     }
   }
   
   private int getFirstBlankFromTheBottom(){
     int n = 0;
     for(int i=length-1; i>=0; --i){
       if(false == current[i]){
         n = i + 1;
         break;
       }
     }
     return n;
   }
   
   private int getLastHappyFromTheTop(){
     int n = 0;
     for(int i=0; i<length; ++i){
       if(false == current[i]){
         n = i;
         break;
       }
     }
     return n;
   }
   
   private void doFlip(){
     ++flipCount;
     
     int firstBlank = getFirstBlankFromTheBottom();
     int lastHappy = getLastHappyFromTheTop();
     
     if(lastHappy == 0){
       flipTopN(firstBlank);
     } else {
       flipTopN(lastHappy);
     }    
   }
 
   @Override
   public void solve() {
     char[] initialChars = initial.toCharArray();
     length = initialChars.length;
     current = new boolean[length];
     for(int i=0; i<length; ++i){
       current[i] = ('+' == initialChars[i]);
     }
     
     flipCount = 0;
     while(isCompleted() == false){
       doFlip();
     }
   }
 
   @Override
   public String getSolution() {
     return Long.toString(flipCount);
   }
   
 }
