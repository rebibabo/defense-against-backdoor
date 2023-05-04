
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
 
    boolean alwaysTileable(int X, int R, int C) {
        
        assert 0 < X && X < 5 && 0 < R && R < 5 && 0 < C && C < 5;
        int min = Math.min(R, C);
        if (R * C % X != 0) return false;  
        switch (X) {
            case 1:
            case 2:
                return true;
            case 3:
                return min > 1;
            case 4:
                return min > 2;
        }
        throw new IllegalArgumentException("YOU PROMISSED IT WAS A SMALL INSTANCE!!! HOW COULD YOU!");
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] input = getInts(in);
                int X = input[0];
                int R = input[1];
                int C = input[2];
                if (alwaysTileable(X, R, C)) {
                    out.printf("Case #%d: GABRIEL\n", t);
                } else {
                    out.printf("Case #%d: RICHARD\n", t);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new D().run(args);
    }
 }
