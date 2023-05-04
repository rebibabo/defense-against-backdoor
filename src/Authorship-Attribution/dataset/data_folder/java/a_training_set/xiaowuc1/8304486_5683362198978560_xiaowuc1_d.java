import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class D {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    static Semaphore processorSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    static Semaphore inputSemaphore = new Semaphore(1);
    static Semaphore outputSemaphore = new Semaphore(0);
    static String[] output;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("d.out")));
        final int MAX_CASES = readInt();
        output = new String[MAX_CASES];
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            processorSemaphore.acquire();
            inputSemaphore.acquire();
            new WorkThread(casenum).start();
        }
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            outputSemaphore.acquire();
        }
        for(String out: output) {
            pw.println(out);
        }
        pw.close();
    }
 
    static class WorkThread extends Thread {
        public int casenum;
 
        public WorkThread(int casenum) {
            super();
            this.casenum = casenum;
        }
        public void run() {
            
            int r = readInt();
            int c = readInt();
            int n = readInt();
            long d = readInt();
 
            int[] x = new int[n];
            int[] y = new int[n];
            int[] v = new int[n];
            for(int i = 0; i < n; i++) {
                x[i] = readInt()-1;
                y[i] = readInt()-1;
                v[i] = readInt();
            }
            
            
            inputSemaphore.release();
            
            long[][] min = new long[r][c];
            long[][] max = new long[r][c];
            for(int i = 0; i < r; i++) {
                Arrays.fill(min[i], 1);
                Arrays.fill(max[i], Long.MAX_VALUE);
            }
            for(int i = 0; i < n; i++) {
                for(int a = 0; a < r; a++) {
                    for(int b = 0; b < c; b++) {
                        int dist = Math.abs(a - x[i]) + Math.abs(b - y[i]);
                        long minAllowed = v[i] - dist * d;
                        long maxAllowed = v[i] + dist * d;
                        min[a][b] = Math.max(min[a][b], minAllowed);
                        max[a][b] = Math.min(max[a][b], maxAllowed);
                    }
                }
            }
            boolean bad = false;
            long ret = 0;
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    bad |= min[i][j] > max[i][j];
                    ret += max[i][j];
                    ret %= 1000000007;
                }
            }
            if(bad) {
                output[casenum-1] = "Case #" + casenum + ": IMPOSSIBLE";
            }
            else {
                output[casenum-1] = "Case #" + casenum + ": " + ret;
            }
            
            
            
            
            
            outputSemaphore.release();
            processorSemaphore.release();
        }
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
