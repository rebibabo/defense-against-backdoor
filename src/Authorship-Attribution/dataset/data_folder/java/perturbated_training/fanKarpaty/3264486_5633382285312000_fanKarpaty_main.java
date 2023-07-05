import java.io.*;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         System.setOut(new PrintStream(new File("output.txt")));
         Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
 
         int T = in.nextInt();
         in.nextLine();
         for (int t = 1; t <= T; t++) {
             char[] num = in.nextLine().trim().toCharArray();
             int lastChanged = num.length;
             boolean minusOne = false;
             for (int i = num.length - 1; i >= 0; i--) {
                 if (minusOne) {
                     num[i]--;
                     minusOne = false;
                     if (num[i] < '0') {
                         num[i] = '9';
                         lastChanged = i;
                         minusOne = true;
                         continue;
                     }
                 }
                 boolean greater = true;
                 for (int j = i - 1; j >= 0; j--) {
                     if (num[j] > num[i]) {
                         greater = false;
                         break;
                     }
                 }
 
                 if (!greater) {
                     minusOne = true;
                    lastChanged = i;
                    num[i] = '9';
                 }
             }
 
             int i = 0;
             while (num[i] == '0')i++;
 
             for (int j = lastChanged; j < num.length; j++)
                 num[j] = '9';
 
 
 
             System.out.println("Case #" + t + ": " + String.valueOf(num, i, num.length - i));
         }
     }
 }
