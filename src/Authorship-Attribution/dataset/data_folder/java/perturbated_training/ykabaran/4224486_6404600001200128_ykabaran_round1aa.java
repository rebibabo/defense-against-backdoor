package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class Round1AA implements GoogleCodeJam2015.Problem {
 
   private int N;
   private int[] nums;
   private int m1;
   private int m2;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     N = Integer.parseInt(input.readLine());
     String[] args = input.readLine().split(" ");
     nums = new int[N];
     for(int i=0; i<N; ++i){
       nums[i] = Integer.parseInt(args[i]);
     }
   }
 
   @Override
   public void solve() {
     solve1();
     solve2();
   }
   
   private void solve1(){
     m1 = 0;
     for(int i=0; i<N-1; ++i){
       int diff = nums[i] - nums[i+1];
       if(diff > 0){
         m1 += diff;
       }
     }
   }
   
   private void solve2(){
     int rate = 0;
     for(int i=0; i<N-1; ++i){
       int diff = nums[i] - nums[i+1];
       if(diff > rate){
         rate = diff;
       }
     }
     
     m2 = 0;
     for(int i=0; i<N-1; ++i){
       int num = nums[i];
       if(num < rate){
         m2 += num;
       } else {
         m2 += rate;
       }
     }
   }
 
   @Override
   public String getSolution() {
     return m1 + " " + m2;
   }
   
 }
