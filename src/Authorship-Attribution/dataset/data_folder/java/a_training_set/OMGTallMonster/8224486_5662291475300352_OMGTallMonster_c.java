import java.util.*;
 
 class C {
    public static class Hiker {
       public int start, speed;
       public Hiker(int st, int sp) { start = st; speed = sp; }
       public String toString() { return speed+","+start; }
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
       int N = scan.nextInt();
       ArrayList<Hiker> hikers = new ArrayList<Hiker>();
       for (int i = 0; i < N; i++) {
          int d = scan.nextInt(), h = scan.nextInt(), m = scan.nextInt();
          for (int j = 0; j < h; j++) {
             hikers.add(new Hiker(d, m+j));
          }
       }
       if (hikers.size() <= 1) {
          return 0;
       }
       Hiker a = hikers.get(0);
       Hiker b = hikers.get(1);
       if (a.speed < b.speed) {
          a = hikers.get(1);
          b = hikers.get(0);
       }
       if (b.speed*(720-a.start) < a.speed*(360-b.start)) {
          return 1;
       } else {
          return 0;
       }
    }
 }
