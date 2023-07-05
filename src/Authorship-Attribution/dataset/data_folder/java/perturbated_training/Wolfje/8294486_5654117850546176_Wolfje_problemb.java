import java.io.*;
 import java.util.Arrays;
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
         for (int test = 0; test < tests; test++) {
             int n = scan.nextInt();
             int red = scan.nextInt();
             int orange = scan.nextInt();
             int yellow = scan.nextInt();
             int green = scan.nextInt();
             int blue = scan.nextInt();
             int violet = scan.nextInt();
 
 
             String z = createCycle(red - green, yellow - violet, blue - orange);
 
             if ( z != null ) {
                 z = expandFirst(z, 'R', 'G', green);
                 z = expandFirst(z, 'Y', 'V', violet);
                 z = expandFirst(z, 'B', 'O', orange);
             } else z = "IMPOSSIBLE";
             String resultStr = String.format("Case #%d: %s", test + 1, z);
             
 
             System.out.println(resultStr);
             out.println(resultStr);
         }
         out.close();
         System.out.println("*** in file =  " + getInFileName());
         System.out.println("*** out file = " + getOutFileName());
     }
 
     String expandFirst(String s, char target, char extra, int amount) {
         StringBuilder sb = new StringBuilder();
         boolean expanded = false;
         for ( int i = 0; i < s.length(); i++) {
             sb.append(s.charAt(i));
             if ( !expanded && s.charAt(i) == target) {
                 for ( int j = 0; j < amount; j++) {
                     sb.append(extra);
                     sb.append(target);
                 }
                 expanded = true;
             }
         }
         return sb.toString();
     }
 
     String createCycle(int red, int yellow, int blue) {
         if ( red < 0 || yellow < 0 || blue < 0) return null;
         Hair[] hairs = new Hair[3];
         hairs[0] = new Hair('R', red);
         hairs[1] = new Hair('Y', yellow);
         hairs[2] = new Hair('B', blue);
         Arrays.sort(hairs);
         if ( hairs[0].count > hairs[1].count + hairs[2].count) return null;
         int k = hairs[0].count;
         String[] result = new String[k];
         for ( int i = 0; i < k; i++) result[i] = "" + hairs[0].hair;
         int index = 0;
         for ( int i = 0; i < hairs[1].count; i++) {
             result[index] += hairs[1].hair;
             index = (index + 1) % k;
         }
         for ( int i = 0; i < hairs[2].count; i++) {
             result[index] += hairs[2].hair;
             index = (index + 1) % k;
         }
         StringBuilder sb = new StringBuilder();
         for ( String s: result ) sb.append(s);
         return sb.toString();
     }
 
     public class Hair implements Comparable<Hair> {
         char hair;
         int count;
         public Hair(char c, int count ) {
             hair = c;
             this.count = count;
         }
 
         @Override
         public int compareTo(Hair o) {
             return o.count - count;
         }
     }
 }
