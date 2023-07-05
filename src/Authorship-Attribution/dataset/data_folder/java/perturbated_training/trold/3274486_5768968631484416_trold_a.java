
 import java.io.*;
 import java.util.*;
 
 class Pancake {
    double r, h;
 
    Pancake(String line) {
        double[] d = Arrays.stream(line.split(" ")).mapToDouble(Double::parseDouble).toArray();
        this.r = d[0];
        this.h = d[1];
    }
 
    public String toString() {
        return r + " " + h + " " + sideArea();
    }
 
    double sideArea() {
        return h * 2 * Math.PI * r;
    }
 }
 
 public class A {
    String compute(Pancake[] P, int K) {
        int N = P.length;
        Arrays.sort(P, (a, b) -> (int)Math.signum(b.sideArea() - a.sideArea()));
        double max = 0;
        for (int i = 0 ; i < N ; i++) {
            double v = Math.PI * P[i].r * P[i].r + P[i].sideArea();
            for (int j = 0 ; j < K - 1 ; j++) {
                if (j >= i) {
                    v += P[j+1].sideArea();
                } else {
                    v += P[j].sideArea();
                }
            }
            max = Math.max(max, v);
        }
        return "" + max;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = Integer.parseInt(in.readLine());
            for (int t = 1 ; t <= T ; t++) {
                int[] arg = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int N = arg[0];
                int K = arg[1];
                Pancake[] P = new Pancake[N];
                for (int i = 0 ; i < N ; i++) {
                    P[i] = new Pancake(in.readLine());
                }
                out.printf("Case #%d: %s\n", t, compute(P, K));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new A().run(args);
    }
 }
