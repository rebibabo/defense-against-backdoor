import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class A {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("a.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            String s = nextToken();
            boolean[] good = new boolean[s.length()];
            for(int i = 0; i < s.length(); i++) {
                good[i] = s.charAt(i) == '+';
            }
            int k = readInt();
            int ret = 0;
            for(int i = 0; i + k <= good.length; i++) {
                if(!good[i]) {
                    for(int a = 0; a < k; a++) {
                        good[i+a] = !good[i+a];
                    }
                    ret++;
                }
            }
            int amt = 0;
            for(boolean out: good) {
                if(!out) amt++;
            }
            if(amt > 0) pw.println("IMPOSSIBLE");
            else pw.println(ret);
        }
        pw.close();
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
