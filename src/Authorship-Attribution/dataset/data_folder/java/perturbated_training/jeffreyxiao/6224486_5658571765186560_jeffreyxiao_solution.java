
 import java.util.*;
 import java.io.*;
 
 public class Main {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static StringTokenizer st;
 
    public static void main (String[] args) throws IOException {
        int t = readInt();
        for (int q = 1; q <= t; q++) {
            int x = readInt();
            int r = readInt();
            int c = readInt();
            if (x == 1) {
                System.out.printf("Case #%d: GABRIEL\n",q);
            } else if (x == 2) {
                if ((r*c)%2 == 0)
                    System.out.printf("Case #%d: GABRIEL\n", q);
                else
                    System.out.printf("Case #%d: RICHARD\n", q);
            } else if (x == 3) {
                if ((r*c)%3 != 0 || (r < 3 && c < 3) || r < 2 || c < 2) {
                    System.out.printf("Case #%d: RICHARD\n", q);
                } else {
                    System.out.printf("Case #%d: GABRIEL\n", q);
                }
            } else if (x == 4) {
                if ((r*c)%4 != 0 || (r < 4 && c < 4) || r < 2 || c < 2 || (r == 2 && c == 4) || (r == 4 && c == 2))
                    System.out.printf("Case #%d: RICHARD\n", q);
                else
                    System.out.printf("Case #%d: GABRIEL\n", q);
            }
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
