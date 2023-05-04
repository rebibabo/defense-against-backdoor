import java.util.*;
 
 class D {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static long baseConv(int[] digits, int radix) {
       long out = 0;
       for (int digit : digits) {
          out = out*radix + digit;
       }
       return out;
    }
 
    public static Object solve(Scanner scan) {
       int K = scan.nextInt(), C = scan.nextInt(), S = scan.nextInt();
       if (S*C < K) {
          return "IMPOSSIBLE";
       }
       StringJoiner out = new StringJoiner(" ");
       for (int k = 0; k < K; k += C) {
          int[] arr = new int[C];
          for (int i = 0; i < C && i+k < K; i++) {
             arr[i] = i+k;
          }
          out.add(""+(baseConv(arr, K)+1));
       }
       return out;
    }
 }
