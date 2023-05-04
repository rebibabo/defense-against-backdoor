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
            int n = readInt();
            int[] list = new int[n];
            for(int i = 0; i < n; i++) {
                list[i] = readInt();
            }
            pw.println(solveA(list) + " " + solveB(list));
        }
        pw.close();
    }
 
    public static long solveA(int[] list) {
        long ret = 0;
        for(int i = 0; i < list.length-1; i++) {
            ret += Math.max(-list[i+1] + list[i], 0);
        }
        return ret;
    }
    
    public static long solveB(int[] list) {
        long ret = 0;
        int maxRate = 0;
        for(int i = 0; i < list.length-1; i++) {
            maxRate = Math.max(maxRate, -list[i+1] + list[i]);
        }
        for(int i = 0; i < list.length-1; i++) {
            ret += Math.min(list[i], maxRate);
        }
        return ret;
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
