import java.util.Scanner;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int T = sc.nextInt();
         for (int caseNum = 1; caseNum <= T; caseNum++) {
             int sMax = sc.nextInt();
             String str = sc.next();
             int[] s = new int[sMax + 1];
             for (int i = 0; i <= sMax; i++) {
                 s[i] = str.charAt(i) - '0';
             }
 
             int added = 0;
             int standing = 0;
             for (int i = 0; i <= sMax; i++) {
                 if (standing < i) {
                     int toAdd = i - standing;
                     added += toAdd;
                     standing += toAdd;
                 }
                 standing += s[i];
             }
 
             System.out.println("Case #" + caseNum + ": " + added);
         }
     }
 }
