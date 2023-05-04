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
            System.err.println(casenum);
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
            
            
            int n = readInt();
            int[] preq = new int[n];
            for(int i = 0; i < n; i++) {
                preq[i] = readInt()-1;
            }
            String s = nextToken();
            int m = readInt();
            String[] cool = new String[m];
            for(int i = 0; i < m; i++) {
                cool[i] = nextToken();
            }
            
            
            inputSemaphore.release();
            
            int[] ret = new int[m];
            
            final int ITERATION_COUNT = 200000;
 
            Set<String> seen = new HashSet<String>();
            
            for(int qqq = 0; qqq < ITERATION_COUNT; qqq++) {
                boolean[] done = new boolean[n];
                StringBuilder sb = new StringBuilder();
                StringBuilder key = new StringBuilder();
                LinkedList<Integer> q = new LinkedList<Integer>();
                for(int i = 0; i < n; i++) {
                    q.add(i);
                }
                Collections.shuffle(q);
                while(!q.isEmpty()) {
                    int curr = q.removeFirst();
                    if(preq[curr] == -1 || done[preq[curr]]) {
                        key.append(curr);
                        key.append("/");
                        sb.append(s.charAt(curr));
                        done[curr] = true;
                    }
                    else {
                        q.add(curr);
                    }
                }
                if(!seen.add(key.toString())) continue;
                String fin = sb.toString();
                for(int i = 0; i < m; i++) {
                    if(fin.indexOf(cool[i]) >= 0) {
                        ret[i]++;
                    }
                }
            }
                
            StringBuilder ans = new StringBuilder();
            ans.append("Case #" + casenum + ":");
            for(int i = 0; i < m; i++) {
                ans.append(" ");
                ans.append(ret[i] * 1.0 / seen.size());
            }
            output[casenum-1] = ans.toString();
            
            
            
            
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
