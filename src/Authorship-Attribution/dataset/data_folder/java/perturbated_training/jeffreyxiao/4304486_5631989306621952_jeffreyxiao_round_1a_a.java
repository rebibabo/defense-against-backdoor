package contest.codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class Round_1A_A {
 
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
 
    static int T;
    
    public static void main (String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
        out = new PrintWriter(new FileWriter("out.txt"));
 
        T = readInt();
        
        for (int t = 1; t <= T; t++) {
            char[] in = readLine().toCharArray();
            String res = "";
            res += in[0];
            for (int i = 1; i < in.length; i++) {
                if (in[i] < res.charAt(0))
                    res += in[i];
                else
                    res = in[i] + res;
            }
            out.printf("Case #%d: %s\n", t, res);
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
 
