import java.util.Arrays;
 import java.util.Scanner;
 
 public class B {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int C = sc.nextInt(), J = sc.nextInt();
            Activity[] a = new Activity[C + J];
            for (int j = 0; j < C; j++) {
                a[j] = new Activity(sc.nextInt(), sc.nextInt());
            }
            for (int j = 0; j < J; j++) {
                a[j + C] = new Activity(sc.nextInt(), sc.nextInt());
            }
            Arrays.sort(a);
 
            if (C != 2 && J != 2 || a[1].D - a[0].C <= 720 || a[0].D + 1440 - a[1].C <= 720) {
                System.out.println("Case #" + i + ": 2");
            } else {
                System.out.println("Case #" + i + ": 4");
            }
        }
        sc.close();
    }
 
    private static class Activity implements Comparable<Activity> {
        private int C, D;
 
        private Activity(int C, int D) {
            this.C = C;
            this.D = D;
        }
 
        public int compareTo(Activity o) {
            return Integer.compare(C, o.C);
        }
    }
 }