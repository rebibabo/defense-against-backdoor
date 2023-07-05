
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
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int D = getInt(in);
                int[] plates = getInts(in);
                int[] eaters = new int[D];
                Arrays.fill(eaters, 1);
                int best = Integer.MAX_VALUE;
                for (int time = 0 ; time < best ; time++) {
                    int rem = 0;
                    for (int d = 0 ; d < D ; d++) {
                        rem = Math.max(rem, (plates[d] + eaters[d] - 1) / eaters[d]);
                    }
                    best = Math.min(best, time + rem);
                    int slow = 0;
                    for (int d = 1 ; d < D ; d++) {
                        if (plates[d] * eaters[slow] > plates[slow] * eaters[d]) {
                            slow = d;
                        }
                    }
                    eaters[slow]++;
                }
                out.printf("Case #%d: %d\n", t, best);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new B().run(args);
    }
 }
