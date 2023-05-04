package codejam.y2017.r12.z2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Solution2 {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/B-small-attempt1.in");
         Scanner sc = new Scanner(fileInputStream);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/r12/z2/z2output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int r = sc.nextInt();
             int o = sc.nextInt();
             int y = sc.nextInt();
             int g = sc.nextInt();
             int b = sc.nextInt();
             int v = sc.nextInt();
             sc.nextLine();
             char c1 = ' ';
             char c2 = ' ';
             char c3 = ' ';
             int v1 = 0;
             int v2 = 0;
             int v3 = 0;
             
             if (r >= y && r >= b) {
                 c1 = 'R';
                 v1 = r;
                 if (y >= b) {
                     c2 = 'Y';
                     v2 = y;
                     c3 = 'B';
                     v3 = b;
                 } else {
                     c3 = 'Y';
                     v3 = y;
                     c2 = 'B';
                     v2 = b;
                 }
             } else {
                 if (y >= r && y >= b) {
                     c1 = 'Y';
                     v1 = y;
                     if (r >= b) {
                         c2 = 'R';
                         v2 = r;
                         c3 = 'B';
                         v3 = b;
                     } else {
                         c3 = 'R';
                         v3 = r;
                         c2 = 'B';
                         v2 = b;
                     }
                 } else {
                     c1 = 'B';
                     v1 = b;
                     if (r >= y) {
                         c2 = 'R';
                         v2 = r;
                         c3 = 'Y';
                         v3 = y;
                     } else {
                         c3 = 'R';
                         v3 = r;
                         c2 = 'Y';
                         v2 = y;
                     }
                 }
             }
             StringBuilder sb = new StringBuilder();
             while (v1 > v2) {
                 sb.append(c1);
                 v1--;
                 sb.append(c3);
                 v3--;
             }
             if (v3 < 0) {
                 sb = new StringBuilder().append("IMPOSSIBLE");
             } else {
                while (v1>0) {
                    sb.append(c1);
                    v1--;
                    sb.append(c2);
                    v2--;
                    if (v3>0) {
                        sb.append(c3);
                        v3--;
                    }
                }
             }
             
             String s = sb.toString();
             if (!"IMPOSSIBLE".equals(s)) {
                 int cr = 0;
                 int cy = 0;
                 int cb = 0;
                 for (int i = 0; i < s.length(); i++) {
                     if (s.charAt(i) == 'R') {
                         cr++;
                     }
                     if (s.charAt(i) == 'Y') {
                         cy++;
                     }
                     if (s.charAt(i) == 'B') {
                         cb++;
                     }
                     if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                         System.out.println(s);
                         System.out.println(i);
 
                         throw new RuntimeException();
                     }
                 }
                 if (s.charAt(s.length() - 1) == s.charAt(0)) {
                     throw new RuntimeException();
                 }
                 if (cr!=r || cb!=b || cy!=y) {
                     throw new RuntimeException();
                 }
             }
             String res = "Case #" + ti + ": " + s;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
 
 }
