package codejam.y2016.qualification.z3;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.lang.reflect.Array;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/qualification/z2/z2.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti=1;ti<=tn;ti++) {
             int n = sc.nextInt();
             int j = sc.nextInt();
             String s = "Case #" + ti + ":";
             System.out.println(s);
             int jcnt = 0;
             if (n<=16) {
                 int cnt = 1<<n-2;
                 for (int i=0;i<cnt;i++) {
                     if (j==jcnt) {
                         break;
                     }
                     
                     boolean [] b = new boolean[n-2];
                     int k = i;
                     int cntt = n-3;
                     while (k>0) {
                         if (k % 2 == 1) {
                             b[cntt] = true;
                         }
                         k = k/2;
                         cntt--;
                     }
                     String res = "1";
                     for (int ii=0;ii<n-2;ii++) {
                         res += b[ii]?"1":"0";
                     }
                     res+="1 ";
                     
                     for (int dg=2;dg<=10;dg++) {
                         double y = 1 + Math.pow(dg,n-1);
                         int base = 1;
                         for (int idx=n-3;idx>=0;idx--) {
                             base *= dg;
                             if (b[idx]) {
                                 y += base;
                             }
                         }
                         int prime = isPrime(y);
                         if (prime==0) {
                             break;
                         }
                         
                         res += prime+ " ";
                         if (dg==10) {
                             System.out.println(res);
                             jcnt++;
                         }
                     }
                 }
             }
         }
         printWriter.close();
     }
 
     private static int isPrime(double y) {
         for (int i=2;i<=Math.sqrt(y);i++) {
             if (y%i==0) {
                 return i;
             }
         }
         return 0;
     }
 
 }
