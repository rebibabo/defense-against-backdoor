package codejam.y2017.r12.z1;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/A-small-attempt0.in");
         Scanner sc = new Scanner(fileInputStream);
         
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2017/r12/z1/z1output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int d = sc.nextInt();
             int n = sc.nextInt();
             double minSpeed = Double.MAX_VALUE;
             for (int i=0;i<n;i++) {
                 double v = sc.nextInt();
                 double speed = sc.nextInt();
                 double time = (d-v)/speed;
 
                 double x = d / time;
                 if (x<minSpeed) {
                     minSpeed = x;
                 }
             }
             String res = "Case #" + ti + ": " + minSpeed;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
 
 }
