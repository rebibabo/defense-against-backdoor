
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
 
    double go(int S, String key, String pat) {
        int K = key.length();
        int M = pat.length();
        for (int m = 0 ; m < M ; m++) if (key.indexOf(pat.charAt(m)) == -1) return 0;
        int loop = 1;
        while (! pat.startsWith(pat.substring(loop))) loop++;
        double bananas = 1 + (S - pat.length()) / loop;
        int[][] kmp = new int[256][M];
        kmp[pat.charAt(0)][0] = 1; 
        for (int X = 0, j = 1; j < M ; j++) {
            for (int c = 0 ; c < 256 ; c++) {
                kmp[c][j] = kmp[c][X];
            }
            kmp[pat.charAt(j)][j] = j+1;
            X = kmp[pat.charAt(j)][X];
        } 
        double[][] dp = new double[S+1][M];
        dp[0][0] = 1;
        for (int s = 0 ; s < S ; s++) {
            for (int m = 0 ; m < M ; m++) {
                for (int k = 0 ; k < K ; k++) {
                    int state = kmp[key.charAt(k)][m];
                    if (state == M) {
                        dp[s+1][M-loop] += dp[s][m] / K;
                        bananas -= dp[s][m] / K;
                    } else {
                        dp[s+1][state] += dp[s][m] / K;
                    }
                }
            }
        }
        return bananas;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int[] input = getInts(in);
                int S = input[2];
                String key = in.readLine();
                String pat = in.readLine();
                out.printf("Case #%d: %e\n", CASE, go(S, key, pat));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new B().run(args);
    }
 }
