package contest.codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class Qualification_2016_D {
 
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
 
    static int T, K, C, S;
 
    public static void main (String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
        out = new PrintWriter(new FileWriter("out.txt"));
 
        T = readInt();
 
        for (int t = 1; t <= T; t++) {
            K = readInt();
            C = readInt();
            S = readInt();
            out.printf("Case #%d: ", t);
            for (long i = 1, j = 1; i <= K; i++, j += (long)(Math.pow(K, C - 1)))
                out.printf("%d ", j);
            out.println();
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
 
