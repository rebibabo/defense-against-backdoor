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
        public void run() {
            
            
            int r = readInt();
            int c = readInt();
            long[] need = new long[r];
            for(int i = 0; i < r; i++) {
                need[i] = readInt();
            }
            long[][] g = new long[r][c];
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    g[i][j] = readInt() * 100L;
                }
            }
            
            
            
            inputSemaphore.release();
            
            int ret = 0;
            
            if(r == 1) {
                for(long out: g[0]) {
                    long a = 1;
                    while(110*a*need[0] < out) {
                        a++;
                    }
                    if(90*a*need[0] <= out && out <= 110*a*need[0]) {
                        ret++;
                    }
                }
            }
            else {
                int[][] dp = new int[1<<c][1<<c];
                for(int i = 0; i < dp.length; i++) {
                    Arrays.fill(dp[i], -1);
                }
                dp[0][0] = 0;
                for(int a = 0; a < dp.length; a++) {
                    for(int b = 0; b < dp[a].length; b++) {
                        if(dp[a][b] < 0) continue;
                        ret = Math.max(ret, dp[a][b]);
                        for(int i = 0; i < c; i++) {
                            if((a&(1<<i)) != 0) continue;
                            for(int j = 0; j < c; j++) {
                                if((b&(1<<j)) != 0) continue;
                                long scale = Math.max(g[0][i] / 110 / need[0], g[1][j] / 110 / need[1]);
                                while(110*scale*need[0] < g[0][i] || 110*scale*need[1] < g[1][j]) {
                                    scale++;
                                }
                                if(90*scale*need[0] <= g[0][i] && g[0][i] <= 110*scale*need[0] && 90*scale*need[1] <= g[1][j] && g[1][j] <= 110*scale*need[1]) {
                                    dp[a|(1<<i)][b|(1<<j)] = Math.max(dp[a|(1<<i)][b|(1<<j)], dp[a][b] + 1);
                                }
                            }
                        }
                    }
                }
            }
            output[casenum-1] = "Case #" + casenum + ": " + ret;
            
            
            
            
            
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
