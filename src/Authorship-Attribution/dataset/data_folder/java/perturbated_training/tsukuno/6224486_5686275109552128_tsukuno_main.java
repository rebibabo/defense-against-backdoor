import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
       int N = cin.nextInt();
       int arr[] = new int[N];
       for(int i=0; i<N; ++i) {
         arr[i] = cin.nextInt();
       }
 
       Arrays.sort(arr);
 
       int ret = arr[N - 1];
 
       for(int step = 1; step < ret; ++step) {
 
         int divide = 0;
 
         for(int i=0; i<N; ++i) {
           divide += (arr[i] - 1) / step;
         }
 
         ret = Math.min(ret, step + divide);
 
       }
 
       System.out.println("Case #" + C + ": " + ret);
 
     }
 
   }
 
 }
