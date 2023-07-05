import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class DStupidMultithreaded {
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
        public int numRows,numCols;
        public WorkThread(int casenum) {
            super();
            this.casenum = casenum;
 
        }
        public void run() {
 
            numRows = readInt();
            numCols = readInt();
            hashes = new HashSet<Long>();
            
            inputSemaphore.release();
 
            output[casenum-1] = "Case #" + casenum + ": " + solve();
 
            
 
 
 
            outputSemaphore.release();
            processorSemaphore.release();
        }
        Set<Long> hashes;
        public int solve() {
            return dfs(new int[numRows][numCols], 0);
        }
        public long hash(int[][] grid, int shiftC) {
            final long RMOD = 80974743864643L;
            final long CMOD = 88494744610229L;
            long ret = 0;
            for(int i = 0; i < grid.length; i++) {
                long colHash = 0;
                for(int j = 0; j < grid[i].length; j++) {
                    int shiftJ = (j+shiftC) % numCols;
                    colHash *= 3137;
                    colHash += grid[i][shiftJ];
                    colHash %= CMOD;
                }
                ret *= 101;
                ret += colHash;
                ret %= RMOD;
            }
            return ret;
        }
        public int dfs(int[][] grid, int r) {
            if(r == grid.length) {
                if(!valid(grid)) return 0;
                long hash = hash(grid, 0);
                if(hashes.contains(hash)) return 0;
                for(int a = 0; a < numCols; a++) hashes.add(hash(grid, a));
                return 1;
            }
            if(r-2 >= 0 && !validate(grid, r-2)) return 0;
            int ret = 0;
            int scale = 1;
            for(int a = 0; a < numCols; a++) scale *= 3;
            for(int mask = 0; mask < scale; mask++) {
                int temp = mask;
                for(int i = 0; i < numCols; i++) {
                    grid[r][i] = (temp%3)+1;
                    temp /= 3;
                }
                ret += dfs(grid, r+1);
            }
            return ret;
        }
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};
        public int get(int[][] grid, int r, int c) {
            if(r < 0 || r >= grid.length || c < 0 || c >= grid[r].length) return -1;
            return grid[r][c];
        }
 
        public void deepPrint(int[][] grid) {
            for(int[] out: grid) {
                pw.println(Arrays.toString(out));
            }
            pw.println();
        }
 
        public boolean valid(int[][] grid, int i, int j) {
            int seen = 0;
            for(int k = 0; k < dx.length; k++) {
                if(get(grid, i+dx[k], (j+dy[k]+numCols)%numCols) == grid[i][j]) seen++;
            }
            if(grid[i][j] != seen) return false;
            return true;
        }
 
        public boolean validate(int[][] grid, int i) {
            for(int j = 0; j < grid[i].length; j++) {
                if(!valid(grid, i, j)) return false;
            }
            return true;
        }
 
        public boolean valid(int[][] grid) {
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(!valid(grid, i, j)) return false;
                }
            }
            return true;
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
