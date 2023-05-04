import java.io.BufferedInputStream;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.TreeSet;
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int r = sc.nextInt();
             int c = sc.nextInt();
             sc.nextLine();
             char[][] m = new char[r][c];
             boolean[] empty = new boolean[r];
             Set<Character> all = new TreeSet<>();
             for (int j = 0; j < r; j++) {
                 String s = sc.nextLine();
                 s.getChars(0, c, m[j], 0);
                 empty[j] = true;
                 for (int k = 0; k < c; k++) {
                     all.add(m[j][k]);
                     if (m[j][k] != '?') {
                         empty[j] = false;
                     }
                 }
             }
             all.remove('?');
             if (all.size() == r * c) {
                 print(i, m);
                 continue;
             }
             for (int j = 0; j < r; j++) {
                 if (empty[j]) {
                     continue;
                 }
                 int ind = 0;
                 while (m[j][ind] == '?') {
                     ind++;
                 }
                 for (int k = 0; k < ind; k++) {
                     m[j][k] = m[j][ind];
                 }
                 for (int k = ind + 1; k < c; k++) {
                     if (m[j][k] == '?') {
                         m[j][k] = m[j][k-1];
                     }
                 }
             }
             int ind = 0;
             while (empty[ind]) {
                 ind++;
             }
             for (int j = 0; j < ind; j++) {
                 m[j] = m[ind];
             }
             for (int j = ind + 1; j < r; j++) {
                 if (empty[j]) {
                     m[j] = m[j-1];
                 }
             }
             print(i, m);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, char[][] answer) {
         System.out.println("Case #" + caseNum + ":");
         for (int i = 0; i < answer.length; i++) {
             System.out.println(String.valueOf(answer[i]));
         }
     }
 
 }
