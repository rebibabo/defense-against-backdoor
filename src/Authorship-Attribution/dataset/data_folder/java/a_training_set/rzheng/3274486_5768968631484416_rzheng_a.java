import java.util.Arrays;
 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class A {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            Pancake[] N = new Pancake[sc.nextInt()];
            int K = sc.nextInt();
            for (int j = 0; j < N.length; j++) {
                N[j] = new Pancake(sc.nextInt(), sc.nextInt());
            }
 
            PriorityQueue<Pancake> queue = new PriorityQueue<Pancake>(11, new Comparator<Pancake>() {
 
                public int compare(Pancake o1, Pancake o2) {
                    return Double.compare(o1.A, o2.A);
                }
            });
 
            Arrays.sort(N);
            double max = 0, cur = 0;
            for (int j = 0; j < N.length; j++) {
                queue.add(N[j]);
                cur += N[j].A;
                if (queue.size() == K) {
                    max = Math.max(max, cur + Math.PI * N[j].R * N[j].R);
                    Pancake p = queue.remove();
                    cur -= p.A;
                }
            }
 
            System.out.printf("Case #" + i + ": %.9f\n", max);
        }
        sc.close();
    }
 
    private static class Pancake implements Comparable<Pancake> {
        private int R;
        private double A;
 
        private Pancake(int R, int H) {
            this.R = R;
            this.A = 2 * Math.PI * R * H;
        }
 
        public int compareTo(Pancake o) {
            return R - o.R;
        }
    }
 }