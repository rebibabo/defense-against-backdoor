
 import java.io.*;
 import java.util.*;
 
 public class B {
    int getint(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
 
    String compute(int[][] C, int[] I) {
        int N = I.length;
        int P = C[0].length;
        int packets = 0;
        int[] cur = new int[N];
        while (true) {
            int min = 0;
            int max = Integer.MAX_VALUE;
            for (int i = 0 ; i < N ; i++) {
                int amount = C[i][cur[i]];
                int minamountperpacket = (I[i] * 9 + 9) / 10;
                int maxamountperpacket = I[i] * 11 / 10;
                int minpackets = (amount + maxamountperpacket - 1) / maxamountperpacket;
                int maxpackets = amount / minamountperpacket;
                min = Math.max(min, minpackets);
                max = Math.min(max, maxpackets);
            }
            if (min <= max) {
                packets++;
                for (int i = 0 ; i < N ; i++) cur[i]++;
            } else {
                int best = 0;
                double ratio = C[0][cur[0]] / (double) I[0];
                for (int i = 1 ; i < N ; i++) {
                    double nratio = C[i][cur[i]] / (double) I[i];
                    if (nratio < ratio) {
                        best = i;
                        ratio = nratio;
                    }
                }
                cur[best]++;
            }
            for (int c : cur) if (c == P) return ""+packets;
        }
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getint(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] param = getInts(in);
                int N = param[0];
                int P = param[1];
                int[] I = getInts(in);
                int[][] C = new int[N][];
                for (int n = 0 ; n < N ; n++) {
                    C[n] = getInts(in);
                    Arrays.sort(C[n]);
                }
                out.printf("Case #%d: %s\n", t, compute(C, I));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new B().run(args);
    }
 }
