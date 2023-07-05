package codejam.y2017.qualification.z1;
 
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/qualification/z1/z1.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         sc.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             String tc = sc.nextLine();
             String[] split = tc.split(" ");
             int k = Integer.valueOf(split[1]);
             char[] all = split[0].toCharArray();
             boolean[] field = new boolean[all.length];
             for (int i = 0; i < all.length; i++) {
                 field[i] = all[i] == '+';
             }
             int currentSwitches = 0;
             for (int i = 0; i <= all.length - k; i++) {
                 if (!field[i]) {
                     for (int j = 0; j < k; j++) {
                         field[i + j] = !field[i + j];
                     }
                     currentSwitches++;
                 }
             }
             for (int i = all.length - k; i < all.length; i++) {
                 if (!field[i]) {
                     currentSwitches = -1;
                 }
             }
             if (currentSwitches == -1) {
                 String x = "Case #" + ti + ": " + "IMPOSSIBLE";
                 printWriter.write(x + "\n");
                 System.out.println(x);
                 continue;
             }
             String s = "Case #" + ti + ": " + currentSwitches;
             printWriter.write(s + "\n");
             System.out.println(s);
         }
         printWriter.close();
     }
 
 }
