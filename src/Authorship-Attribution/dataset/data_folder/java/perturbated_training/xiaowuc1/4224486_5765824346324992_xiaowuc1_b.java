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
            int want = readInt();
            int[] list = new int[n];
            for(int i = 0; i < n; i++) {
                list[i] = readInt();
            }
            long min = -1;
            long max = 1L << 50;
            while(min != max) {
                long mid = (min+max)/2;
                if(min == mid) mid++;
                if(numCut(list, mid) >= want) {
                    max = mid-1;
                }
                else {
                    min = mid;
                }
            }
            long cut = numCut(list, min);
            for(int a = 0; a < n; a++) {
                if((min+1)%list[a] == 0) {
                    if(++cut == want) {
                        pw.println(a+1);
                        break;
                    }
                }
            }
        }
        pw.close();
    }
 
    public static long numCut(int[] list, long time) {
        long ret = 0;
        for(int out: list) {
            ret += (time / out) + 1;
            if(ret >= Integer.MAX_VALUE) break;
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
