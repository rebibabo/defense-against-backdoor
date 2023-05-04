import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class CMultithreadBadSlow {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    static Semaphore processorSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    static Semaphore inputSemaphore = new Semaphore(1);
    static Semaphore outputSemaphore = new Semaphore(0);
    static String[] output;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("c.out")));
        final int MAX_CASES = readInt();
        output = new String[MAX_CASES];
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            processorSemaphore.acquire();
            inputSemaphore.acquire();
            new WorkThread(casenum).start();
            System.out.println("Started " + casenum + " out of " + MAX_CASES + " for input");
        }
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            outputSemaphore.acquire();
            System.out.println("Acquired " + casenum + " out of " + MAX_CASES + " for output");
        }
        for(String out: output) {
            pw.println(out);
        }
        pw.close();
    }
 
    static class WorkThread extends Thread {
        public int casenum;
 
        int[] to;
        
        public WorkThread(int casenum) {
            super();
            this.casenum = casenum;
        }
        
        int ret = 0;
        
        public void check(ArrayList<Integer> list) {
            if(list.size() <= ret) return;
            for(int i = 0; i < list.size(); i++) {
                int index = list.indexOf(to[list.get(i)]);
                if(index == -1) return;
                if(index != (i+list.size()-1) % list.size() && index != (i+1) % list.size()) {
                    return;
                }
            }
            ret = Math.max(ret, list.size());
        }
        
        public void dfs(ArrayList<Integer> list) {
            check(list);
            for(int i = 0; i < to.length; i++) {
                if(list.indexOf(i) == -1) {
                    list.add(i);
                    dfs(list);
                    list.remove(list.size()-1);
                }
            }
        }
        
        public void run() {
 
 
            int n = readInt();
            to = new int[n];
            for(int i = 0; i < n; i++) {
                to[i] = readInt()-1;
            }
            
            inputSemaphore.release();
 
            dfs(new ArrayList<Integer>());
 
            
 
            output[casenum-1] = "Case #" + (casenum) + ": " + ret;
 
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
