package codejam.y2017.qualification.z3;
 
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/qualification/z3/output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int k = sc.nextInt();
             boolean[] field = new boolean[n + 2];
             field[0] = true;
             field[n + 1] = true;
             int cmin = 0;
             int cmax = 0;
             for (int i=0;i<k;i++) {
                 cmin = 0;
                 cmax = 0;
                 int idx = 0;
                 for (int j=1;j<n+1;j++) {
                     if (!field[j]) {
                         int leftEmpty = 0;
                         while (!field[j-leftEmpty-1]) {
                             leftEmpty++;
                         }
                         int rightEmpty = 0;
                         while (!field[j+rightEmpty+1]) {
                             rightEmpty++;
                         }
                         int mn = Math.min(leftEmpty, rightEmpty);
                         int mx = Math.max(leftEmpty, rightEmpty);
                         if (mn>cmin) {
                             cmin = mn;
                             cmax = mx;
                             idx = j;
                         } else {
                             if (mn==cmin && mx>cmax) {
                                 cmin = mn;
                                 cmax = mx;
                                 idx = j;
                             }
                         }
                     }
                 }
                 field[idx] = true;
             }
             String s = "Case #" + ti + ": " + cmax+" "+cmin;
             printWriter.write(s + "\n");
             System.out.println(s);
         }
         printWriter.close();
     }
 
 }
