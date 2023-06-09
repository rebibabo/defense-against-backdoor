package codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class R1C_A {
 
    static BufferedReader br;
    static PrintWriter pr;
    static StringTokenizer st;
 
    public static void main (String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
        pr = new PrintWriter(new FileWriter("out.txt"));
 
        int tt = readInt();
        for (int qq = 1; qq <= tt; qq++) {
            int ans = 0;
            int r = readInt();
            int c = readInt();
            int w = readInt();
            ans = c/w + w-1 + (c % w > 0 ? 1 : 0);
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
 
