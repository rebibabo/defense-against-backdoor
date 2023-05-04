package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class QualsA implements GoogleCodeJam2015.Problem {
 
   private int shynessMax;
   private int[] peopleCounts;
   private int friendsNeeded;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     shynessMax = Integer.parseInt(args[0]);
     peopleCounts = new int[shynessMax + 1];
     for(int i=0; i<shynessMax + 1; ++i){
       peopleCounts[i] = Integer.parseInt(Character.toString(args[1].charAt(i)));
     }
   }
 
   @Override
   public void solve() {
     friendsNeeded = 0;
     int standingUp = 0;
     
     for(int i=0; i<=shynessMax; ++i){
       if(standingUp < i){
         friendsNeeded += i - standingUp;
         standingUp = i;
       }
       standingUp += peopleCounts[i];
     }
   }
 
   @Override
   public String getSolution() {
     return Integer.toString(friendsNeeded);
   }
   
 }
