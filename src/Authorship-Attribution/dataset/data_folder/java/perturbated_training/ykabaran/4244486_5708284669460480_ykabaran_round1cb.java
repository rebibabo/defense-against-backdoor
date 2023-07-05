package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 
 public class Round1CB implements GoogleCodeJam2015.Problem {
   
   private int K;
   private int L;
   private int S;
   private char[] k;
   private String word;
   
   private long given;
   private long maxGiven;
   private double ans;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     K = Integer.parseInt(args[0]);
     L = Integer.parseInt(args[1]);
     S = Integer.parseInt(args[2]);
     
     k = input.readLine().toCharArray();
     word = input.readLine();
   }
 
   @Override
   public void solve() {
     given = 0;
     maxGiven = 0;
     int[] chars = new int[S];
     
     boolean cont = true;
     while(cont){
       checkPermutation(toCharArr(chars));
       cont = permute(chars);
     }
     
     if(maxGiven == 0 || given == 0){
       ans = 0;
       return;
     }
     ans = Math.log(given) - Math.log(maxGiven) - K * Math.log(S);
     ans = 1 - Math.exp(ans);
     ans *= maxGiven;
   }
   
   @Override
   public String getSolution() {
     return Double.toString(ans);
   }
   
   private boolean permute(int[] arr){
     for(int i=S-1; i>=0; --i){
       ++arr[i];
       if(arr[i] < K){
         break;
       }
       if(i == 0){
         return false;
       }
       arr[i] = 0;
     }
     return true;
   }
   
   private char[] toCharArr(int[] arr){
     char[] chars = new char[S];
     for(int i=0; i<S; ++i){
       chars[i] = k[arr[i]];
     }
     return chars;
   }
   
   private void checkPermutation(char[] arr){
     String perm = new String(arr);
     int currInd = 0;
     long currGiven = 0;
     while(true){
       currInd = perm.indexOf(word, currInd);
       if(currInd < 0){
         break;
       }
       ++currInd;
       ++currGiven;
       if(currInd >= K){
         break;
       }
     }
     
     if(currGiven > maxGiven){
       maxGiven = currGiven;
     }
     given += currGiven;
   }
 }
