package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.Stack;
 
 
 public class Round1CC implements GoogleCodeJam2015.Problem {
   
   private int C;
   private int D;
   private int V;
   private int[] d;
   private int ans;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     C = Integer.parseInt(args[0]);
     D = Integer.parseInt(args[1]);
     V = Integer.parseInt(args[2]);
     
     d = new int[D];
     args = input.readLine().split(" ");
     for(int i=0; i<D; ++i){
       d[i] = Integer.parseInt(args[i]);
     }
   }
 
   @Override
   public void solve() {
     int minMissing = D + 1;
     for(int i=0; i<D; ++i){
       if(d[i] != (i+1)){
         minMissing = i+1;
         break;
       }
     }
     
     PriorityQueue<Integer> nums = new PriorityQueue<>();
     for(int i=0; i<D; ++i){
       nums.add(d[i]);
     }
     int minPossible = (minMissing * (minMissing - 1)) / 2;
     int curr = minPossible + 1;
     while(curr <= V){
       if(!canBeAddedTo(nums, curr)){
         nums.add(minMissing);
         ++ans;
         ++minMissing;
         int newCurr = (minMissing * (minMissing - 1)) / 2;
         ++curr;
         if(newCurr > curr){
           curr = newCurr;
         }
       } else {
         ++curr;
       }
     }
     
   }
   
   private static boolean canBeAddedTo(PriorityQueue<Integer> nums, int left){
     if(left == 0){
       return true;
     }
     
     Stack<Integer> tried = new Stack<>();
     boolean result = false;
     while(true){
       if(nums.isEmpty()){
         break;
       }
 
       int num = nums.poll();
       tried.push(num);
       if(num == left){
         result = true;
         break;
       }
 
       if(num > left){
         break;
       }
 
       left -= num;
       result = canBeAddedTo(nums, left);
       if(result){
         break;
       }
       left += num;
     }
     
     while(!tried.isEmpty()){
       nums.add(tried.pop());
     }
     return result;
   }
   
   @Override
   public String getSolution() {
     return Integer.toString(ans);
   }
   
 }
