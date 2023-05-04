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
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("a.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            String s = nextToken();
            LinkedList<Character> f = new LinkedList<Character>();
            LinkedList<Character> b = new LinkedList<Character>();
            while(s.length() > 0) {
                int max = s.length()-1;
                for(int i = s.length()-1; i >= 0; i--) {
                    if(s.charAt(i) > s.charAt(max)) {
                        max = i;
                    }
                }
                f.addLast(s.charAt(max));
                for(int i = s.length()-1; i > max; i--) {
                    b.addFirst(s.charAt(i));
                }
                s = s.substring(0, max);
            }
            for(char out: f) {
                pw.print(out);
            }
            for(char out: b){
                pw.print(out);
            }
            pw.println();
        }
        pw.close();
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
