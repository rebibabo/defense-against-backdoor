package codejam.y2017.r1.z1;
 
 import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
 
 import java.io.*;
 import java.util.*;
 import java.text.*;
 import java.math.*;
 import java.util.regex.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/A-small-attempt0.in");
         Scanner sc = new Scanner(fileInputStream);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/r1/z1/z1output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int r = sc.nextInt();
             int c = sc.nextInt();
             sc.nextLine();
             char[][] f = new char[r][];
             for (int i = 0; i < r; i++) {
                 f[i] = sc.nextLine().toCharArray();
             }
             List<Position> positions = new ArrayList<Position>();
             List<Character> chars = new ArrayList<Character>();
             for (int i = 0; i < r; i++) {
                 for (int j = 0; j < c; j++) {
                     if (f[i][j] == '?') {
                         positions.add(new Position(i, j));
                     } else {
                         chars.add(f[i][j]);
                     }
                 }
             }
             long cnt = (long) Math.pow(chars.size(), positions.size());
             boolean flag = true;
             for (int i = 0; i < cnt; i++) {
                 int ii = i;
                 for (int j = 0; j < positions.size(); j++) {
                     f[positions.get(j).row][positions.get(j).col] = chars.get(ii % chars.size());
                     ii /= chars.size();
                 }
                 flag = true;
                 for (int j=0;j<chars.size();j++) {
                     int top = r;
                     int bottom = -1;
                     int left = c;
                     int right = -1;
                     int count = 0;
                     for (int ir = 0; ir < r; ir++) {
                         for (int jc = 0; jc < c; jc++) {
                             if (f[ir][jc]==chars.get(j)) {
                                 if (ir<top) {
                                     top = ir;
                                 }
                                 if (ir>bottom) {
                                     bottom = ir;
                                 }
                                 if (jc<left) {
                                     left = jc;
                                 }
                                 if (jc>right) {
                                     right = jc;
                                 }
                                 count++;
                             }
                         }
                     }
                     if ((bottom-top+1)*(right-left+1)!=count) {
                         flag = false;
                         break;
                     }
                 }
                 if (flag) {
                     String res = "Case #" + ti + ": ";
                     printWriter.write(res + "\n");
                     System.out.println(res);
                     StringBuilder sb = new StringBuilder();
                     for (int ir = 0; ir < r; ir++) {
                         for (int jc = 0; jc < c; jc++) {
                             sb.append(f[ir][jc]);
                         }
                         sb.append("\n");
                     }
                     printWriter.write(sb.toString());
                     System.out.print(sb.toString());
                     break;
                 }
             }
         }
         printWriter.close();
     }
 
     public static class Position {
         private int row;
         private int col;
 
         public Position(int row, int col) {
             this.row = row;
             this.col = col;
         }
     }
 }
