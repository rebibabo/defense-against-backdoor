package codejam.y2017.r12.z3;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/C-small-attempt0.in");
         Scanner sc = new Scanner(fileInputStream);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/r12/z3/z3output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         DecimalFormat df = new DecimalFormat("#");
         df.setMaximumFractionDigits(8);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int q = sc.nextInt();
             int[] e = new int[n];
             int[] s = new int[n];
             for (int i = 0; i < n; i++) {
                 e[i] = sc.nextInt();
                 s[i] = sc.nextInt();
 
             }
             double[][] graph = new double[n][n];
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < n; j++) {
                     graph[i][j] = sc.nextInt();
                 }
             }
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < q; i++) {
                 int u = sc.nextInt();
                 int v = sc.nextInt();
                 List<Horse> horses = new ArrayList<Horse>();
                 
                 for (int city = 0; city < n - 1; city++) {
                     List<Horse> nh = new ArrayList<Horse>();
                     double minTime = Double.MAX_VALUE;
                     for (Horse horse1 : horses) {
                         double ct = horse1.timeSpent + graph[city - 1][city] / horse1.speed;
                         if (ct < minTime) {
                             minTime = ct;
                         }
                         
                         if (horse1.remaining >= graph[city - 1][city] + graph[city][city + 1]) {
                             Horse h2 = new Horse(horse1.remaining - (int)graph[city - 1][city], horse1.speed, ct);
                             nh.add(h2);
                         }
                     }
                     Horse current = new Horse(e[city], s[city], city==0?0.0:minTime);
                     nh.add(current);
                     horses = nh;
                     
                 }
                 double minTime = Double.MAX_VALUE;
                 for (Horse horse : horses) {
                     double ct = horse.timeSpent + graph[n-2][n-1] / horse.speed;
                     if (ct < minTime) {
                         minTime = ct;
                     }
                 }
                 String format = df.format(minTime);
                 if (format.charAt(0) == '.') {
                     format = "0"+format;
                 }
                 sb = sb.append(format).append(" ");
                 
             }
 
             sc.nextLine();
 
             String res = "Case #" + ti + ": "+sb.toString().trim();
             printWriter.write(res + "\n");
             System.out.println(res);
 
 
 
         }
         printWriter.close();
     }
 
     public static class Horse {
         final int remaining;
         final int speed;
         final double timeSpent;
 
         public Horse(int remaining, int speed, double timeSpent) {
             this.remaining = remaining;
             this.speed = speed;
             this.timeSpent = timeSpent;
         }
 
         @Override
         public String toString() {
             return "Horse{" +
                     "remaining=" + remaining +
                     ", speed=" + speed +
                     ", timeSpent=" + timeSpent +
                     '}';
         }
     }
 
 }
