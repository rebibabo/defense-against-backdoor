package codejam.y2016.qualification.z2;
 
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
             String n = sc.next();
             boolean [] arr = new boolean[n.length()];
             for (int i=0;i<n.length();i++) {
                 arr[i] = n.charAt(i)=='+';
             }
             int length = n.length();
             while (length>0 && arr[length-1]) {
                 length--;
             }
             int res = 0;
             while (length>0) {
                 res++;
                 if (!arr[0]) {
                     flip(length, arr);
                 } else {
                     int t = 1;
                     while (arr[t]) {
                         t++;
                     }
                     flip(t, arr);
                 }
                 while (length>0 && arr[length-1]) {
                     length--;
                 }
             }
 
 
 
 
 
 
 
 
 
 
             
             String s = "Case #" + ti + ": " + res;
             printWriter.write(s+"\n");
             System.out.println(s);
         }
         printWriter.close();
     }
 
     private static void flip(int k, boolean[] b) {
         boolean [] temp = new boolean[k];
         for (int i=0;i<k;i++) {
             temp[i] = !b[i];
         }
         for (int i=0;i<k;i++) {
             b[k-i-1] = temp[i];
         }
     }
 }
