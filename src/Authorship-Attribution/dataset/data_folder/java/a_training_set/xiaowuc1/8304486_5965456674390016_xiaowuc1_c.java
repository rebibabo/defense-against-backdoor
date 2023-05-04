import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class C {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("c.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            int n = readInt();
            int[] to = new int[2*n];
            int[] leave = new int[2*n];
            int[] duration = new int[2*n];
            for(int i = 0; i < leave.length; i++) {
                to[i] = readInt()-1;
                leave[i] = readInt();
                duration[i] = readInt();
            }
            int ret = Integer.MAX_VALUE;
            for(int mask = 0; mask < (1<<n); mask++) {
                int[] seen = new int[n];
                int curr = 0;
                int time = 0;
                for(int a = 0; a < 2*n; a++) {
                    if(seen[curr] == 2) {
                        time = Integer.MAX_VALUE;
                        break;
                    }
                    int first = 0;
                    if((mask&(1<<curr)) != 0) {
                        first++;
                    }
                    int second = 1 - first;
                    int use = seen[curr]++ == 1 ? second : first;
                    while(time%24 != leave[2*curr+use]) {
                        time++;
                    }
                    time += duration[2*curr+use];
                    curr = to[2*curr+use];
                }
                if(curr == 0) {
                    ret = Math.min(ret, time);
                }
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
