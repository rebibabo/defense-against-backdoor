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
       String s = scan.nextLine();
       return min(s.substring(0,1), s.substring(1));
    }
 
    public static String min(String curr, String remainder) {
       if (remainder.length() == 0) return curr;
       String a = min(remainder.charAt(0)+curr,remainder.substring(1));
       String b = min(curr+remainder.charAt(0),remainder.substring(1));
       return a.compareTo(b) < 1 ? b : a;
    }
 }
