package codejam.y2016.r2.z3;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/C-small-attempt0 (2).in");
         
         Scanner sc = new Scanner(fileInputStream);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r2/z3/z2.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
 
 
 
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             sc.nextLine();
             String [] ss1 = new String[n];
             String [] ss2 = new String[n];
             for (int i=0;i<n;i++) {
                 String s = sc.nextLine();
                 String[] split = s.split(" ");
                 ss1[i] = split[0];
                 ss2[i] = split[1];
             }
             int cnt = 1<<n;
             int mx = 0;
             for (int i=0;i<cnt;i++) {
                 Set<String> first = new HashSet<String>(n);
                 Set<String> second = new HashSet<String>(n);
                 int k = i;
                 int cntt = 0;
                 int kx = 0;
                 boolean [] xx = new boolean[n];
                 while (k > 0) {
                     if (k % 2 == 1) {
                         first.add(ss1[kx]);
                         second.add(ss2[kx]);
                         cntt++;
                         xx[kx] = true;
                     }
                     k = k/2;
                     kx++;
                 }
                 boolean flag = true;
                 for (int j=0;j<n;j++) {
                     if (!(first.contains(ss1[j]) && second.contains(ss2[j]))) {
                         flag = false;
                         break;
                     }
                 }
                 if (flag) {
                     if (n-cntt>mx) {
                         mx = n - cntt;
                     }
                 }
             }
             String res = "Case #" + ti + ": " + mx;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
 }
