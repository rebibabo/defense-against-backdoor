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
       char[] stack = scan.nextLine().toCharArray();
       char prev = stack[0];
       int count = 1;
       for (char c : stack) {
          if (c != prev) {
             count++;
             prev = c;
          }
       }
       return prev == '+' ? count-1 : count;
    }
 }
