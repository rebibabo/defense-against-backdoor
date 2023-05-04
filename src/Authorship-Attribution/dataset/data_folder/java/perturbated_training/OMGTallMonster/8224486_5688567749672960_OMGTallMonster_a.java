import java.util.*;
 
 class A {
    public static int[] answers = new int[1000001];
    static {
       for (int i = 1; i < answers.length; i++) {
          int r = rev(i);
          if (i%10 == 0 || r >= i)
             answers[i] = answers[i-1]+1;
          else
             answers[i] = Math.min(answers[i-1], answers[rev(i)])+1;
       }
    }
 
    public static int rev(int n) {
       int out = 0;
       while( n != 0 ) {
          out = out * 10 + n%10;
          n /= 10;
       }
       return out;
    }
 
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       return answers[scan.nextInt()];
    }
 }
