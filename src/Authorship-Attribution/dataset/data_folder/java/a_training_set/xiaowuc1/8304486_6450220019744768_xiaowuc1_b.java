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
    static Semaphore processorSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    static Semaphore inputSemaphore = new Semaphore(1);
    static Semaphore outputSemaphore = new Semaphore(0);
    static String[] output;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("b.out")));
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
 
        int[][] g;
        int n;
        int[] ret;
 
        public boolean valid() {
            int[] give = new int[n];
            int[] receive = new int[n];
            for(int i = 0; i < g.length; i++) {
                give[g[i][0]] += ret[i];
                receive[g[i][1]] += ret[i];
            }
            for(int i = 0; i < n; i++) {
                if(give[i] != receive[i]) return false;
            }
            return true;
        }
        
        public boolean dfs(int index) {
            if(index == ret.length) {
                return valid();
            }
            for(int x = -n; x <= n; x++) {
                if(x==0) continue;
                ret[index] = x;
                if(dfs(index+1)) return true;
            }
            return false;
        }
 
        public void run() {
 
            n = readInt();
            int m = readInt();
            g = new int[m][2];
            for(int i = 0; i < g.length; i++) {
                g[i][0] = readInt()-1;
                g[i][1] = readInt()-1;
            }
 
            ret = new int[m];
 
            
            inputSemaphore.release();
 
            if(dfs(0)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Case #");
                sb.append(casenum);
                sb.append(":");
                for(int out: ret) {
                    sb.append(" ");
                    sb.append(out);
                }
                output[casenum-1] = sb.toString();
            }
            else {
                output[casenum-1] = "Case #" + casenum + ": IMPOSSIBLE";
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
