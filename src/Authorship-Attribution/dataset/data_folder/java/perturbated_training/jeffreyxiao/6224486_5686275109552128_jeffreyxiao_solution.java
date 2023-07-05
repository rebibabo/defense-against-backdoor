
 import java.util.*;
 import java.io.*;
 
 public class Infinite_House_Of_Pancakes {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
 
    public static void main (String[] args) throws IOException {
        int t = readInt();
        for (int q = 1; q <= t; q++) {
            int d = readInt();
            int[] in = new int[d];
            for (int i = 0; i < d; i++)
                in[i] = readInt();
            int ans = 1 << 30;
            for (int target = 1; target <= 1000; target++) {
                int res = target;
                for (int i = 0; i < d; i++) {
                    int curr = in[i];
                    if (curr > target) {
                        res += (curr + target - 1) / target - 1;
                    }
                }
                ans = Math.min(ans, res);
 
            }
            System.out.printf("Case #%d: %d\n", q, ans);
        }
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
