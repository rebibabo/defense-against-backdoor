import java.util.*;
 import java.io.*;
 
 public class Codejam_2014_1A_A {
 
    static BufferedReader br;
    static PrintWriter ps;
    static StringTokenizer st;
    
    
    public static void main (String[] args) throws IOException {
        
        ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        br = new BufferedReader(new FileReader("test.txt"));
        
    
        int t = readInt();
        
        for (int q = 1; q <= t; q++) {
            int n = readInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = readInt();
            int min1 = 0;
            int maxDiff = 0;
            for (int i = 1; i < n; i++) {
                if (a[i] < a[i-1]) {
                    min1 += a[i-1] - a[i];
                    maxDiff = Math.max(maxDiff, a[i-1] - a[i]);
                }
            }
            int min2 = 0;
            for (int i = 1; i < n; i++) {
                if (maxDiff >= a[i-1])
                    min2 += a[i-1];
                else
                    min2 += maxDiff;
            }
            System.out.printf("Case #%d: %d %d\n", q, min1, min2);
        }
    }
    static String next () throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
 
    static long readLong () throws IOException {
        return Long.parseLong(next());
    }
 
    static int readInt () throws IOException {
        return Integer.parseInt(next());
    }
 
    static double readDouble () throws IOException {
        return Double.parseDouble(next());
    }
 
    static char readCharacter () throws IOException {
        return next().charAt(0);
    }
 
    static String readLine () throws IOException {
        return br.readLine().trim();
    }
 }