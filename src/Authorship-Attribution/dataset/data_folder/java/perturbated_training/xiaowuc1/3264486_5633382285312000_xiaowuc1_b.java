import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class B {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("b.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            String s = nextToken();
            pw.println(Long.parseLong(dfs("", s, true)));
        }
        pw.close();
    }
 
    public static String dfs(String s, String source, boolean tight) {
        if(s.length() == source.length()) {
            return s;
        }
        if(!tight) {
            return dfs(s + "9", source, false);
        }
        if(s.length() == 0) {
            String poss = dfs(source.substring(0, 1), source, true);
            if(poss != null) return poss;
            return dfs((char)(source.charAt(0) - 1) + "", source, false);
        }
        for(char ch = '9'; ch >= s.charAt(s.length()-1); ch--) {
            if(ch > source.charAt(s.length())) continue;
            String poss = dfs(s + ch, source, ch == source.charAt(s.length()));
            if(poss != null) return poss;
        }
        return null;
    }
    
    public static int readInt() {
        return Integer.parseInt(nextToken());
    }
 
    public static long readLong() {
        return Long.parseLong(nextToken());
    }
 
    public static double readDouble() {
        return Double.parseDouble(nextToken());
    }
 
    public static String nextToken() {
        while(st == null || !st.hasMoreTokens())    {
            try {
                if(!br.ready()) {
                    pw.close();
                    System.exit(0);
                }
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return st.nextToken();
    }
 
    public static String readLine() {
        st = null;
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 
 }
