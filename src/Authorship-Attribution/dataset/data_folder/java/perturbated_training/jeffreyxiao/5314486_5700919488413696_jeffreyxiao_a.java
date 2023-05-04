package contest.codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class A {
 
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
 
    static int T, N, P;
    static int[] G;
    
    public static void main (String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        br = new BufferedReader(new FileReader("A-small-attempt0.in"));
        out = new PrintWriter(new FileWriter("out.txt"));
 
        T = readInt();
        
        for (int t = 1; t <= T; t++) {
            N = readInt();
            P = readInt();
            
            G = new int[N];
            
            for (int i = 0; i < N; i++)
                G[i] = readInt();
            
            if (P == 2) {
                int even = 0;
                int odd = 0;
                for (int i = 0; i < N; i++)
                    if (G[i] % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                out.printf("Case #%d: %d\n", t, even + (odd + 1) / 2);
            } else if (P == 3) {
                int[] remainder = new int[3];
                
                for (int i = 0; i < N; i++)
                    remainder[G[i] % 3]++;
                
                int pairs = Math.min(remainder[1], remainder[2]);
                remainder[1] -= pairs;
                remainder[2] -= pairs;
                
                int ans = remainder[0] + pairs + (remainder[1] + 2) / 3 + (remainder[2] + 2) / 3;
                out.printf("Case #%d: %d\n", t, ans);
            }
        }
        
        out.close();
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
 
