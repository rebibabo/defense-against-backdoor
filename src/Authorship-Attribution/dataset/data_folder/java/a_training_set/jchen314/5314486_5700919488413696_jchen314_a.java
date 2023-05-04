import java.util.Scanner;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int N = sc.nextInt();
             int P = sc.nextInt();
             int[] G = new int[N];
             int[] remainders = new int[P];
             int sum = 0;
             for (int i = 0; i < N; i++) {
                 G[i] = sc.nextInt();
                 remainders[G[i] % P]++;
                 sum = (sum + G[i]) % P;
             }
 
             int total = remainders[0] + 1;
             if (sum == 0) {
                 total--;
             }
 
             for (int i = 1; i < P; i++) {
                 int num = i != P - i ? Math.min(remainders[i], remainders[P - i]) : remainders[i] / 2;
                 remainders[i] -= num;
                 remainders[P - i] -= num;
                 total += num;
             }
 
             if (P == 4 && remainders[2] > 0) {
                 if (remainders[1] % 4 >= 2) {
                     remainders[1] -= 2;
                     remainders[2]--;
                     total++;
                 } else if (remainders[3] % 4 >= 2) {
                     remainders[3] -= 2;
                     remainders[2]--;
                     total++;
                 }
             }
 
             for (int i = 1; i < P; i++) {
                 total += remainders[i] / P;
             }
 
             System.out.print("Case #" + caseNum + ": ");
             System.out.println(total);
         }
     }
 }
