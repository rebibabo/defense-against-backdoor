import java.io.*;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 public class ProblemC {
 
     String FILENAME = "problemC_small1";
 
     public String getInFileName() {
         return FILENAME + ".in";
     }
 
     public String getOutFileName() {
         return FILENAME + ".out";
     }
 
     public static void main(String[] args) throws Exception {
         new ProblemC();
     }
 
     public ProblemC() throws Exception {
         BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                 getOutFileName())));
         Scanner scan = new Scanner(in);
         int tests = scan.nextInt();
         for (int test = 1; test <= tests; test++) solve(test, scan, out);
         out.close();
         System.out.println("*** in file =  " + getInFileName());
         System.out.println("*** out file = " + getOutFileName());
     }
 
     public void solve(int testNumber, Scanner in, PrintWriter out) {
         long n = in.nextLong();
         long k = in.nextLong();
         TreeMap<Long, Long> count = new TreeMap<>();
         count.put(n, 1L);
         long useN = 0;
         while ( k > 0 ) {
             useN = count.lastKey();
 
             long take = Math.min(count.get(useN), k);
             count.merge(useN, -take, (a, b) -> a + b);
             if ( count.get(useN) == 0 ) count.remove(useN);
             if ( useN % 2 == 1 ) {
                 count.merge(useN/2, take*2, (a, b) -> a + b);
             } else {
                 count.merge(useN/2, take, (a, b) -> a + b);
                 count.merge(useN/2 - 1, take, (a, b) -> a + b);
             }
             k -= take;
         }
         String resultStr = String.format("Case #%d: %d %d", testNumber, useN/2, (useN-1)/2);
         System.out.println(resultStr);
         out.println(resultStr);
     }
 }
