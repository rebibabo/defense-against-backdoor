
 import java.io.*;
 import java.util.*;
 
 public class A {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    String calc(int N) {
        if (N == 0) return "INSOMNIA";
        int d = 0;
        for (int res = N ;; res += N) {
            for (int i = res ; i > 0 ; i /= 10) {
                d |= 1 << (i % 10);
            }
            if (d == (1 << 10) - 1) return Integer.toString(res);
        }
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int N = getInt(in);
                out.printf("Case #%d: %s\n", t, calc(N));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new A().run(args);
    }
 }
