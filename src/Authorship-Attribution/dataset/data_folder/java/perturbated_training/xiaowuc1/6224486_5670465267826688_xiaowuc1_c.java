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
    
    static int[][] grid;
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("c.out")));
        final int MAX_CASES = readInt();
        grid = new int[5][5];
        grid[1] = new int[]{Integer.MAX_VALUE,1,2,3,4};
        grid[2] = new int[]{Integer.MAX_VALUE,2,-1,4,-3};
        grid[3] = new int[]{Integer.MAX_VALUE,3,-4,-1,2};
        grid[4] = new int[]{Integer.MAX_VALUE,4,3,-2,-1};
        
        int goal = mult(2, mult(3, 4));
        
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            int len = readInt();
            long repeat = readLong();
            String s = nextToken();
            int fullString = 1;
            for(int i = 0; i < s.length(); i++) {
                fullString = mult(fullString, get(s.charAt(i)));
            }
            int total = exp(fullString, repeat);
            if(total != goal) {
                pw.println("NO");
                continue;
            }
            long totalNum = len * repeat;
            long prefixNum = -1;
            int prefix = 1;
            for(int a = 0; a < totalNum && a <= 100000; a++) {
                prefix = mult(prefix, get(s.charAt(a%s.length())));
                if(prefix == get('i')) {
                    prefixNum = (a+1);
                    break;
                }
            }
            if(prefixNum == -1) {
                pw.println("NO");
                continue;
            }
            long suffixNum = -1;
            int suffix = 1;
            for(int a = 0; a < totalNum && a <= 100000; a++) {
                int index = (int)((totalNum-1-a)%s.length());
                suffix = mult(get(s.charAt(index)), suffix);
                if(suffix == get('k')) {
                    suffixNum = a+1;
                    break;
                }
            }
            if(suffixNum == -1) {
                pw.println("NO");
                continue;
            }
            if(prefixNum + suffixNum >= totalNum) {
                pw.println("NO");
                continue;
            }
            pw.println("YES");
        }
        pw.close();
    }
 
    public static int exp(int b, long e) {
        if(e == 1) return b;
        if(e%2 == 0) {
            int r = exp(b, e/2);
            return mult(r, r);
        }
        return mult(b, exp(b, e-1));
    }
    
    public static int get(char ch) {
        if(ch == 'i') return 2;
        if(ch == 'j') return 3;
        if(ch == 'k') return 4;
        throw new RuntimeException();
    }
    
    public static int mult(int a, int b) {
        if(a==0 || b==0) throw new RuntimeException("WHAT");
        if(a < 0 && b < 0) {
            return mult(-a, -b);
        }
        if(a < 0 || b < 0) {
            return -grid[Math.abs(a)][Math.abs(b)];
        }
        return grid[a][b];
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
