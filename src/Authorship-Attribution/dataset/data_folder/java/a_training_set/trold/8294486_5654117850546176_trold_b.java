
 import java.io.*;
 import java.util.*;
 
 public class B {
    String compute(int[] c) {
        int N = c[0],
        R = c[1],
        O = c[2],
        Y = c[3],
        G = c[4],
        B = c[5],
        V = c[6];
        if (R > Y + B ||
            Y > R + B ||
            B > R + Y) return "IMPOSSIBLE";
        char[] v = new char[N];
        char last;
        if (R >= Y && R >= B) {
            last = v[0] = 'R';
            R--;
        } else if (Y >= R && Y >= B) {
            last = v[0] = 'Y';
            Y--;
        } else {
            last = v[0] = 'B';
            B--;
        }
        for (int i = 1 ; i < N ; i++) {
            if (last == 'R') {
                if (Y >= B) {
                    last = v[i] = 'Y';
                    Y--;
                } else {
                    last = v[i] = 'B';
                    B--;
                }
            } else if (last == 'Y') {
                if (R >= B) {
                    last = v[i] = 'R';
                    R--;
                } else {
                    last = v[i] = 'B';
                    B--;
                }
            } else {
                if (R >= Y) {
                    last = v[i] = 'R';
                    R--;
                } else {
                    last = v[i] = 'Y';
                    Y--;
                }
            }
        }
        if (v[0] == v[N-1]) {
            char T = v[N-1];
            v[N-1] = v[N-2];
            v[N-2] = T;
        }
        return new String(v);
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = Integer.parseInt(in.readLine());
            for (int t = 1 ; t <= T ; t++) {
                int[] c = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                out.printf("Case #%d: %s\n", t, compute(c));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new B().run(args);
    }
 }
