package codejam.y2016.r1.z3;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     static List<Integer> bffs;
     static int N;
     static int result;
 
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r1/z3/z3.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti=1;ti<=tn;ti++) {
             int n = sc.nextInt();
             List<Integer> cur = new ArrayList<Integer>(n);
             cur.add(0);
             for (int j=0;j<n;j++) {
                 cur.add(sc.nextInt());
             }
             bffs = cur;
             result = 0;
             N = n;
             permutation(n);
             String s = "Case #" + ti + ": " + result;
             printWriter.write(s+"\n");
             System.out.println(s);
         }
         printWriter.close();
     }
 
     public static void permutation(int n) {
         List<Integer> l = new ArrayList<Integer>();
         for (int i = 1; i <= n; i++) {
             l.add(i);
         }
         permutation(new ArrayList<Integer>(), l);
     }
 
     private static void permutation(List<Integer> prefix, List<Integer> source) {
         
         int n = source.size();
         if (n == 0) {
             int cnt = 0;
             for (int i = 2; i < N; i++) {
                 Integer first = prefix.get(i-2);
                 Integer second = prefix.get(i - 1);
                 Integer third = prefix.get(i);
                 Integer bfff = bffs.get(second);
                 if (bfff.equals(first)
                     || bfff.equals(third))
                 {
                     cnt++;
                 } else {
                     break;
                 }
             }
             
             
             if (cnt>0) {
                
             }
             if (bffs.get(prefix.get(0)).equals(prefix.get(1))
                     || bffs.get(prefix.get(0)).equals(prefix.get(cnt+1)))
             {
                 if (bffs.get(prefix.get(cnt+1)).equals(prefix.get(cnt))
                         || bffs.get(prefix.get(cnt+1)).equals(prefix.get(0))) {
                     if (result < cnt + 2) {
                         result = cnt + 2;
                         
                     }
                 }
             }
         } else {
             for (int i = 0; i < n; i++) {
                 List<Integer> res = new ArrayList<Integer>();
                 res.addAll(prefix);
                 res.add(source.get(i));
                 List<Integer> s1 = new ArrayList<Integer>();
                 for (int j = 0; j < n; j++) {
                     if (i != j) {
                         s1.add(source.get(j));
                     }
                 }
                 permutation(res, s1);
             }
         }
     }
 
 }
