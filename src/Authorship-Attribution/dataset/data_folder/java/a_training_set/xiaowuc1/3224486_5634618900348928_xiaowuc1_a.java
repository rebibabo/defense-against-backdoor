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
        pw = new PrintWriter(new BufferedWriter(new FileWriter("A.out")));
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
            
            
            inputSemaphore.release();
            
            
            int ret = 0;
            ArrayList<Character> list = new ArrayList<Character>();
            for(int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i));
                while(list.size() >= 2 && list.get(list.size()-1).equals(list.get(list.size()-2))) {
                    ret += 10;
                    list.remove(list.size()-1);
                    list.remove(list.size()-1);
                }
            }
            
            ret += (list.size()/2) * 5;
            
            
    
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
