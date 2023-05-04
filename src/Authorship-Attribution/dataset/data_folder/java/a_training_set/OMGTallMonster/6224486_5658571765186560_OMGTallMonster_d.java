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
 
    public static Object solve(Scanner scan) {
       int X = scan.nextInt(), R = scan.nextInt(), C = scan.nextInt();
       int d1 = Math.max(R,C);
       int d2 = Math.min(R,C);
       if (X == 4 && d2 == 2) {
          return "RICHARD";
       }
       boolean c1 = X > d1, c2 = (X+1)/2 > d2, c3 = (d1*d2)%X != 0;
 
       return c1 || c2 || c3 ? "RICHARD" : "GABRIEL";
    }
 }
