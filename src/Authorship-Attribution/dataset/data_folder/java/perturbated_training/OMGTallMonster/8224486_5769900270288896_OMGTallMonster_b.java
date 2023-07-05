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
       int r = scan.nextInt(), c = scan.nextInt(), n = scan.nextInt();
 
       int cells = r*c;
       int overflow = n-(cells+1)/2;
       if (overflow <= 0) {
          return 0;
       }
       int emptyCorners, emptyEdges;
 
       if (r == 1 || c == 1) {
          if (cells%2 == 0) {
             return 1 + 2*(overflow-1);
          } else {
             return 2*overflow;
          }
       } else if (r*c % 2 == 0) {
          emptyCorners = 2;
          emptyEdges = (2*r+2*c-4-4)/2;
          return score(overflow, emptyCorners, emptyEdges);
       } else {
          emptyCorners = 0;
          emptyEdges = (r-1)/2*2 + (c-1)/2*2;
          int s1 = score(overflow, emptyCorners, emptyEdges);
 
          emptyCorners = 4;
          emptyEdges = (r-3)/2*2 + (c-3)/2*2;
          overflow++;
          int s2 = score(overflow, emptyCorners, emptyEdges);
 
          System.err.println(s1-s2);
 
          return Math.min(s1, s2);
       }
    }
 
    public static int score(int overflow, int emptyCorners, int emptyEdges) {
       if (overflow <= emptyCorners) {
          return 2*overflow;
       } else if (overflow <= emptyCorners + emptyEdges) {
          return 2*emptyCorners + 3*(overflow-emptyCorners);
       } else {
          return 2*emptyCorners + 3*emptyEdges + 4*(overflow-emptyCorners-emptyEdges);
       }
    }
 }
