
 import java.io.*;
 import java.util.*;
 
 public class B {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    double go(double V, double X, double[] R, double[] C) {
        if (C.length == 1) {
            if (C[0] == X) return V / R[0];
            return -1;
        }
        if (C[0] == X || C[1] == X) {
            double rate = (C[0] == X ? R[0] : 0) + (C[1] == X ? R[1] : 0);
            return V / rate;
        }
        if ((C[0] < X && C[1] < X) || (C[0] > X && C[1] > X)) return -1;
        double frac = (C[1] - X) / (C[1] - C[0]);
        double t0 = V * frac / R[0];
        double t1 = V * (1 - frac) / R[1];
        return Math.max(V * frac / R[0], V * (1 - frac) / R[1]);
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                String[] words = in.readLine().split(" ");
                int N = Integer.parseInt(words[0]);
                double V = Double.parseDouble(words[1]);
                double X = Double.parseDouble(words[2]);
                double[] R = new double[N];
                double[] C = new double[N];
                for (int i = 0 ; i < N ; i++) {
                    words = in.readLine().split(" ");
                    R[i] = Double.parseDouble(words[0]);
                    C[i] = Double.parseDouble(words[1]);
                }
                double time = go(V, X, R, C);
                if (time < 0) out.printf("Case #%d: IMPOSSIBLE\n", CASE);
                else out.printf("Case #%d: %f\n", CASE, time);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new B().run(args);
    }
 }
