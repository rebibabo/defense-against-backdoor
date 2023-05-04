import java.math.BigInteger;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int B[] = new int[sc.nextInt()], N = sc.nextInt(), count = 0;
            BigInteger lcm = BigInteger.ONE;
            PriorityQueue<Barber> pq = new PriorityQueue<Barber>();
            for (int j = 0; j < B.length; j++) {
                B[j] = sc.nextInt();
                pq.add(new Barber(j, B[j]));
                lcm = lcm.multiply(BigInteger.valueOf(B[j])).divide(lcm.gcd(BigInteger.valueOf(B[j])));
            }
            int lcmint = lcm.intValue();
            for (int M : B) {
                count += lcmint / M;
            }
            for (int j = 0; j < (N - 1) % count; j++) {
                Barber barber = pq.remove();
                barber.time += barber.M;
                pq.add(barber);
            }
            System.out.println("Case #" + i + ": " + (1 + pq.remove().B));
        }
        sc.close();
    }
 
    private static class Barber implements Comparable<Barber> {
        private int B;
        private int M;
        private long time;
 
        private Barber(int B, int M) {
            this.B = B;
            this.M = M;
        }
 
        public int compareTo(Barber o) {
            if (time < o.time) {
                return -1;
            } else if (time > o.time) {
                return 1;
            } else {
                return B - o.B;
            }
        }
    }
 }