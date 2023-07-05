package codejam.y2016.r1.z1;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/A-small-attempt0 (1).in");
         
         Scanner sc = new Scanner(fileInputStream);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r1/z1/z1.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
 
 
 
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             String s = sc.next();
             String result = "" + s.charAt(0);
             for (int i = 1; i < s.length(); i++) {
                 if (s.charAt(i) >= result.charAt(0)) {
                     result = s.charAt(i) + result;
                 } else {
                     result = result + s.charAt(i);
                 }
             }
             String res = "Case #" + ti + ": " + result;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 }
