import java.util.*;
 
 class A {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       scan.nextInt();
       String people = scan.next();
 
       int excess = 0, out = 0;
       for (char c : people.toCharArray()) {
          int i = c-'0';
          excess += i-1;
          if (excess < 0) {
             excess = 0;
             out++;
          }
       }
       return out;
    }
 }
