package codejam.y2016.qualification.z4;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.lang.reflect.Array;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution1 {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/qualification/z4/z4.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti=1;ti<=tn;ti++) {
             int k = sc.nextInt();
             int c = sc.nextInt();
             int s = sc.nextInt();
             String res = "Case #" + ti + ": ";
             if (c*s<k) {
                 res += "IMPOSSIBLE";
                 System.out.println(res);
                 printWriter.print(res + "\n");
                 continue;
             }
             Set<Long> set = new HashSet<Long>();
             for (int i=0;i<s;i++) {
                 long next = 1;
                 for (int j=0;j<c;j++) {
                     double v = (c * i + j) * Math.pow(k, c - j - 1);
                     if (next+v<=Math.pow(k,c)) {
                         next += v;
                     }
                     
                 }
                 
                 if (next>Math.pow(k,c)) {
                     break;
                 }
                 for (int j=1;j<=k;j++) {
                     set.add(((next-1)/(int)Math.pow(k,j)%k));
                 }
                 res += (i+1)+" ";
             }
             
             System.out.println(res);
             printWriter.print(res + "\n");
         }
         printWriter.close();
     }
 
 }
 
     
