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
            int n = readInt();
            int[] list = new int[n];
            for(int i = 0; i < n; i++) {
                list[i] = readInt();
            }
            Arrays.sort(list);
            int ret = Integer.MAX_VALUE;
            for(int eat = 1; eat <= 1000; eat++) {
                int curr = eat;
                for(int out: list) {
                    out -= eat;
                    curr += (out+eat-1)/eat;
                }
                ret = Math.min(ret, curr);
            }
            pw.println(ret);
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
