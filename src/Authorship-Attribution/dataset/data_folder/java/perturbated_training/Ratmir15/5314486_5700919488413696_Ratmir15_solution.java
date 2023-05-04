package r22017.z1;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.text.DecimalFormat;
 import java.util.Scanner;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         
         
         Scanner sc = new Scanner(System.in);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Downloads/codejam/src/r22017/z1/z1output.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int p = sc.nextInt();
             int [] arr = new int[n];
             for (int i=0;i<n;i++) {
                 arr[i] = sc.nextInt();
             }
             int cnt = 0;
             int cnt2 = 0;
             int cnt3 = 0;
             if (p==2) {
                 for (int i=0;i<n;i++) {
                     if (arr[i]%2==0) {
                         cnt++;
                     }
                     if (arr[i]%2==1) {
                         cnt2++;
                     }
                 }
                 cnt += (cnt2%2==0)?cnt2/2:(cnt2+1)/2;
             } else {
                 for (int i=0;i<n;i++) {
                     if (arr[i]%3==0) {
                         cnt++;
                     }
                     if (arr[i]%3==1) {
                         cnt2++;
                     }
                     if (arr[i]%3==2) {
                         cnt3++;
                     }
                 }
                 cnt += Math.min(cnt2, cnt3);
                 int rem = Math.max(cnt2, cnt3) - Math.min(cnt2, cnt3);
                 if (rem%3==0) {
                     cnt += rem/3;
                 }
                 if (rem%3==1) {
                     cnt += (rem+2)/3;
                 }
                 if (rem%3==2) {
                     cnt += (rem+1)/3;
                 }
             }
             String result = String.valueOf(cnt);
             String res = "Case #" + ti + ": " + result;
             printWriter.write(res + "\n");
             System.out.println(res);
         }
         printWriter.close();
     }
 
 
 }
