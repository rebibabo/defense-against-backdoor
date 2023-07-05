import java.io.*;
 import java.util.ArrayDeque;
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
         for (int test = 1; test <= tests; test++) solve(test, scan, out);
         out.close();
         System.out.println("*** in file =  " + getInFileName());
         System.out.println("*** out file = " + getOutFileName());
     }
 
     public void solve(int testNumber, Scanner in, PrintWriter out) {
         String s = in.next();
         int k = in.nextInt();
         ArrayDeque<Integer> flips = new ArrayDeque<Integer>();
         boolean ok = true;
         int result = 0;
         for ( int i = 0; i < s.length(); i++ ) {
             if ( flips.size() > 0 && flips.getFirst() + k - 1 < i ) flips.pop();
             int current = s.charAt(i) == '+' ? 0 : 1;
             if ( (current + flips.size()) % 2 == 1 ) {
                 
                 if ( i < s.length() - k + 1) {
                     result++;
                     flips.add(i);
                 } else ok = false;
             }
         }
 
         String resultStr = String.format("Case #%d: ", testNumber);
         if ( !ok ) resultStr += "IMPOSSIBLE";
         else resultStr += result;
         System.out.println(resultStr);
         out.println(resultStr);
     }
 }
