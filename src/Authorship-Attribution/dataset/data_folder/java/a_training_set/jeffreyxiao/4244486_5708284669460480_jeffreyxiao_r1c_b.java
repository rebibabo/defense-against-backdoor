package codejam;
 
 import java.util.*;
 import java.io.*;
 
 public class R1C_B {
 
    static BufferedReader br;
    static PrintWriter pr;
    static StringTokenizer st;
 
    static String avail;
    static String desire;
    static int k, l, s;
    static int max = 0;
    static double ans = 0;
    public static void main (String[] args) throws IOException {
        
        
        br = new BufferedReader(new FileReader("in.txt"));
        pr = new PrintWriter(new FileWriter("out.txt"));
 
        int tt = readInt();
        for (int qq = 1; qq <= tt; qq++) {
            ans = 0;
            max = 0;
            k = readInt();
            l = readInt();
            s = readInt();
            avail = next();
            desire = next();
            generate(0, "");
            ans = (max * Math.pow(k, s) - ans)/Math.pow(k, s);
            pr.printf("Case #%d: %f\n", qq, ans);
        }
        
        pr.close();
    }
 
    private static void generate (int i, String str) {
        if (i == s) {
            int cnt = 0;
            for (int j = 0; j <= s - l; j++) {
                if (str.substring(j, j+l).equals(desire))
                    cnt++;
            }
            max = Math.max(max, cnt);
            ans += cnt;
 
            return;
        }
        for (int j = 0; j < k; j++) {
            generate(i+1, str+avail.charAt(j));
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
 
