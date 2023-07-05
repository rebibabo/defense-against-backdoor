package codejam.y2017.r1.z2;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/B-small-attempt2.in");
         Scanner sc = new Scanner(fileInputStream);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/r1/z2/z4output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int p = sc.nextInt();
             int[] parts = new int[n];
             for (int i = 0; i < n; i++) {
                 parts[i] = sc.nextInt();
             }
             int[][] a = new int[n][p];
             for (int i = 0; i < n; i++) {
                 for (int j = 0; j < p; j++) {
                     a[i][j] = sc.nextInt();
                 }
             }
             int cnt = 0;
             if (n == 1) {
                 for (int i = 0; i < p; i++) {
                     int j = 1;
                     for (int ij = 1; ij <= 1000000; ij++) {
                         if (0.9 * parts[0] * ij <= a[0][i] && 1.1 * parts[0] * ij >= a[0][i]) {
                             cnt++;
                             
                             break;
                         }
                         if (0.9 * parts[0]*ij>a[0][i]) {
                             break;
                         }
                     }
                 }
             }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
                 if (n == 2) {
                     List<Integer> arr = new ArrayList<Integer>();
                     for (int i : a[1]) {
                         arr.add(i);
                     }
                     int mx = arr.size()<6?100000:3000000;
                     for (int chance=0;chance<mx;chance++) {
                         Collections.shuffle(arr);
                         int ccnt = 0;
                         for (int i = 0; i < p; i++) {
                             int j = 1;
                             int start1 = (int) ((1.0d*a[0][i])/(1.1*parts[0]) - 3);
                             int start2 = (int) ((1.0d*arr.get(i))/(1.1*parts[1]) - 3);
                             int start = Math.max(start1, start2);
                             for (int ij = start; ij <= 1000000; ij++) {
                                 if (0.9 * parts[0] * ij <= a[0][i] && 1.1 * parts[0] * ij >= a[0][i]) {
                                     if (0.9 * parts[1] * ij <= arr.get(i) && 1.1 * parts[1] * ij >= arr.get(i)) {
                                         ccnt++;
                                         
                                         break;
                                     }
                                 }
                                 if (0.9 * parts[0]*ij>a[0][i]) {
                                     break;
                                 }
                                 if (0.9 * parts[1]*ij>arr.get(i)) {
                                     break;
                                 }
                             }
                         }
                         if (ccnt>cnt) {
                             cnt = ccnt;
                         }
                     }
                 }
             String res = "Case #" + ti + ": "+cnt;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 }
