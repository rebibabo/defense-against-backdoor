
 import java.io.*;
 import java.util.*;
 
 public class D {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    long pow(long a, long b) {
        long r = 1;
        while (b > 0) {
            if ((b&1) == 1) r *= a;
            a = a * a;
            b >>= 1;
        }
        return r;
    }
 
    String compute(int K, int C, int S) {
        if (K > C * S) return " IMPOSSIBLE";
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < K ; i += C) {
            long s = 1;
            for (int j = 0 ; j < C ; j++) {
                s += pow(K, j) * (Math.min(i + j, K - 1));
            }
            res.append(" ").append(s);
        }
        return res.toString();
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] param = getInts(in);
                int K = param[0];
                int C = param[1];
                int S = param[2];
                out.printf("Case #%d:%s\n", t, compute(K,C,S));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new D().run(args);
    }
 }
