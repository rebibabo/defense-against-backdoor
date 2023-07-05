
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
 
    boolean test(long n) {
        long last = 9;
        while (n > 0) {
            long cur = n % 10;
            if (cur > last) return false;
            last = cur;
            n /= 10;
        }
        return true;
    }
 
    String compute(long n) {
        if (test(n)) return "" + n;
        for (long d = 10 ;; d *= 10) {
            long cand = n - n % d - 1;
            if (test(cand)) return "" + cand;
        }
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                long n = Long.parseLong(in.readLine());
                out.printf("Case #%d: %s\n", t, compute(n));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new B().run(args);
    }
 }
