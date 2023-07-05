package codejam.y2017.qualification.z2;
 
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/qualification/z2/output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         sc.nextLine();
         boolean [] tidy = new boolean[1001];
         int [] answers = new int[1001];
         int lastTidy = 1;
         for (int i=1;i<=1000;i++) {
             String s = String.valueOf(i);
             tidy[i] = true;
             for (int j=0;j<s.length()-1;j++) {
                 if (s.charAt(j)>s.charAt(j+1)) {
                     tidy[i] = false;
                     break;
                 }
             }
             if (tidy[i]) {
                 lastTidy = i;
             }
             answers[i] = lastTidy;
         }
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             String s = "Case #" + ti + ": " + answers[n];
             printWriter.write(s + "\n");
             System.out.println(s);
         }
         printWriter.close();
     }
 
 }
