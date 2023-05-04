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
    static Semaphore processorSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    static Semaphore inputSemaphore = new Semaphore(1);
    static Semaphore outputSemaphore = new Semaphore(0);
    static String[] output;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("a.out")));
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
 
            String s = nextToken();
            orig = s;
            
            inputSemaphore.release();
 
 
            ret = new HashSet<String>();
            dfs(s);
 
            output[casenum-1] = "Case #" + casenum + ": " + (ret.size());
 
            
 
 
 
            outputSemaphore.release();
            processorSemaphore.release();
        }
        Set<String> ret;
        String orig;
        public void dfs(String s) {
            if(!ret.add(s)) return;
            int num = 0;
            for(int i = 0; i < s.length(); i++) {
                num += s.charAt(i) - '0';
            }
            if(num > s.length()) return;
            int[] amt = new int[s.length()];
            for(int i = 0; i < s.length(); i++) {
                amt[i] = s.charAt(i) - '0';
            }
            dfs2(new StringBuilder(), amt, s.length(), num);
        }
        public void dfs2(StringBuilder sb, int[] x, int left, int consume) {
            if(consume > left) return;
            if(left == 0) dfs(sb.toString());
            else {
                sb.append(0);
                dfs2(sb, x, left-1, consume);
                sb.deleteCharAt(sb.length()-1);
                for(int i = 0; i < x.length; i++) {
                    if(x[i] > 0) {
                        sb.append((i+1));
                        x[i]--;
                        dfs2(sb, x, left-1, consume-1);
                        sb.deleteCharAt(sb.length()-1);
                        x[i]++;
                    }
                }
            }
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
