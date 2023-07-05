package codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class R1C_C {
 
    static BufferedReader br;
    static PrintWriter pr;
    static StringTokenizer st;
 
    public static void main (String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
        pr = new PrintWriter(new FileWriter("out.txt"));
 
        int tt = readInt();
        for (int qq = 1; qq <= tt; qq++) {
            int c = readInt();
            int d = readInt();
            int v = readInt();
            int[] D = new int[d];
            for (int i = 0; i < d; i++) {
                D[i] = readInt();
            }
            boolean[] dp = new boolean[v+1];
            dp[0] = true;
            int notDone = v;
            for (int i = 0; i < d; i++) {
                for (int j = v; j >= 0; j--) {
                    if (!dp[j] && j - D[i] >= 0 && dp[j-D[i]]) {
                        dp[j] = true;
                        notDone--;
                    }
                }
            }
            int ans = 0;
            while (notDone > 0) {
                int index = 0;
                for (int i = 1; i <= v; i++) {
                    if (!dp[i]) {
                        index = i;
                        break;
                    }
                }
                for (int i = v; i >= 0; i--) {
                    if (!dp[i] && i-index >= 0 && dp[i-index]) {
                        dp[i] = true;
                        notDone--;
                    }
                }
                ans++;
            }
            pr.printf("Case #%d: %d\n", qq, ans);
        }
 
        pr.close();
    }
 
    static String next () throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
 
    static long readLong () throws IOException {
        return Long.parseLong(next());
    }
 
    static int readInt () throws IOException {
        return Integer.parseInt(next());
    }
 
    static double readDouble () throws IOException {
        return Double.parseDouble(next());
    }
 
    static char readCharacter () throws IOException {
        return next().charAt(0);
    }
 
    static String readLine () throws IOException {
        return br.readLine().trim();
    }
 }
 
