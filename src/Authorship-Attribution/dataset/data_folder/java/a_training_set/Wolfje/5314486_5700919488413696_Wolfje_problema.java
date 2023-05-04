import java.io.*;
 import java.util.HashMap;
 import java.util.Scanner;
 
 public class ProblemA {
 
     String FILENAME = "problemA_small";
 
 
 
     public String getInFileName() {
         return FILENAME + ".in";
     }
 
     public String getOutFileName() {
         return FILENAME + ".out";
     }
 
     public static void main(String[] args) throws Exception {
         new ProblemA();
     }
 
     public ProblemA() throws Exception {
         BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                 getOutFileName())));
         Scanner scan = new Scanner(in);
         int tests = scan.nextInt();
         for (int test = 0; test < tests; test++) {
             int n = scan.nextInt();
             p = scan.nextInt();
             int[] x = new int[p];
             for ( int i = 0; i < n; i++ ) {
                 int z = scan.nextInt();
                 x[z % p]++;
             }
             int result = x[0];
             memo = new HashMap<Long, Integer>();
             int z = go(0, x, n - x[0]);
             String resultStr = String.format("Case #%d: %d", test + 1, result + z);
             
 
             System.out.println(resultStr);
             out.println(resultStr);
         }
         out.close();
         System.out.println("*** in file =  " + getInFileName());
         System.out.println("*** out file = " + getOutFileName());
     }
 
     int p;
     HashMap<Long, Integer> memo;
 
     public int go(int mod, int[] count, int left) {
         if ( left == 0 ) return 0;
         long id = 0;
         for ( int i = 0; i < p; i++ ) id = 1000*id + count[i];
         id = 10*id + mod;
         int result = 0;
         if ( !memo.containsKey(id) ) {
             for ( int i = 1; i < p; i++ ) {
                 if ( count[i] == 0 ) continue;
                 count[i]--;
                 result = Math.max(result, go((mod + i) % p, count, left - 1));
                 count[i]++;
             }
             if ( mod == 0 ) result++;
             memo.put(id, result);
         } else result = memo.get(id);
         return result;
     }
 }
