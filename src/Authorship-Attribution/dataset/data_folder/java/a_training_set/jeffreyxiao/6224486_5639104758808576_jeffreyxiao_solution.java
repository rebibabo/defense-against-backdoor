
 import java.util.*;
 import java.io.*;
 
 public class Standing_Ovation {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
    
    public static void main (String[] args) throws IOException {
        int t = readInt();
        for (int q = 1; q <= t; q++) {
            int smax = readInt();
            char[] in = next().toCharArray();
            int res = 0;
            int curr = 0;
            for (int i = 0; i <= smax; i++) {
                if (curr < i) {
                    res += i - curr;
                    curr = i;
                }
                curr += in[i] - '0';
            }
            System.out.printf("Case #%d: %d\n",q, res);
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
