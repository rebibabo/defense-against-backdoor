import java.io.*;
 import java.util.Scanner;
 
 public class ProblemB {
 
     String FILENAME = "problemB_small";
 
     public String getInFileName() {
         return FILENAME + ".in";
     }
 
     public String getOutFileName() {
         return FILENAME + ".out";
     }
 
     public static void main(String[] args) throws Exception {
         new ProblemB();
     }
 
     public ProblemB() throws Exception {
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
         String result = largestTidy("" + n);
         String resultStr = String.format("Case #%d: %s", testNumber, result);
         System.out.println(resultStr);
         out.println(resultStr);
     }
 
     public String largestTidy(String s) {
         if ( s.length() == 1 ) return s.charAt(0) == '0' ? "" : s;
         int prev = 0;
         int k = 0;
         while ( k < s.length() && s.charAt(k) - '0' >= prev) {
             prev = s.charAt(k) - '0';
             k++;
         }
         if ( k == s.length() ) return s;
         long n = Long.valueOf(s.substring(0, k));
         String result = largestTidy("" + (n-1));
         while ( k < s.length() ) {
             result += '9';
             k++;
         }
         return result;
     }
 }
