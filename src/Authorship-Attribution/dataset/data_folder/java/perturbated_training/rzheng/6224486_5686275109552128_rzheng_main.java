import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("B-small-attempt1.in"));
        System.setOut(new PrintStream("B-small-attempt1.out"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int D = sc.nextInt(), count = 0, result = Integer.MAX_VALUE;
            PriorityQueue<Plate> pq = new PriorityQueue<Plate>();
            for (int j = 0; j < D; j++) {
                pq.add(new Plate(sc.nextInt(), 1));
            }
            while (pq.peek().pancake != 1) {
                result = Math.min(result, count + pq.peek().pancake);
                Plate plate = pq.poll();
                plate.pancake = (plate.pancake + 1) / 2;
                count += plate.count;
                plate.count *= 2;
                pq.add(plate);
            }
            System.out.println("Case #" + i + ": " + (result == Integer.MAX_VALUE ? 1 : result));
        }
        sc.close();
    }
 
    private static class Plate implements Comparable<Plate> {
        private int pancake, count;
 
        private Plate(int pancake, int count) {
            this.pancake = pancake;
            this.count = count;
        }
 
        public int compareTo(Plate o) {
            return o.pancake - pancake;
        }
    }
 }