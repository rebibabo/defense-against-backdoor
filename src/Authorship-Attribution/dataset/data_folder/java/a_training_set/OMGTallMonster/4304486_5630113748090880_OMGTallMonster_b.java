import java.util.*;
 
 class B {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       int N = scan.nextInt();
       int[] heightCounts = new int[2501];
       for (int i = 0; i < 2*N-1; i++) {
          for (int j = 0; j < N; j++) {
             heightCounts[scan.nextInt()]++;
          }
       }
       StringJoiner out = new StringJoiner(" ");
       for (int i = 0; i < heightCounts.length; i++) {
          if (heightCounts[i]%2 == 1) {
             out.add(i+"");
          }
       }
       return out.toString();
    }
 }
