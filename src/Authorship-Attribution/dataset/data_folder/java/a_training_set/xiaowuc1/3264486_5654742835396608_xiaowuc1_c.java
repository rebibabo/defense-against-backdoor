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
            TreeMap<Long, Long> dp = new TreeMap<Long, Long>();
            long n = readLong();
            dp.put(n, 1L);
            long k = readLong()-1;
            while(k > 0) {
                k -= update(dp, k);
            }
            long key = dp.lastKey();
            long a = key / 2;
            long b = key-1-a;
            pw.println(a + " " + b);
        }
        pw.close();
    }
 
    public static long update(TreeMap<Long, Long> dp, long amt) {
        long lastKey = dp.lastKey();
        long upd = Math.min(dp.get(lastKey), amt);
        long a = (lastKey-1)/2;
        long b = (lastKey-1)-a;
        change(dp, a, upd);
        change(dp, b, upd);
        change(dp, lastKey, -upd);
        return upd;
    }
    
    public static void change(Map<Long, Long> m, long k, long v) {
        if(!m.containsKey(k)) m.put(k, 0L);
        long nv = v + m.get(k);
        if(nv == 0) m.remove(k);
        else m.put(k, nv);
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
