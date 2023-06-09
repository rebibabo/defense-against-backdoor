import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "A";
 
 
     final String filename= problem+"-small-attempt0";
    
    
 
    public void solve() throws Exception {
        int N = iread();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = iread();
        }
        int A = 0, B = 0, C = 0;
        for (int i = 0; i + 1 < N; i++) {
            A += Math.max(a[i] - a[i + 1], 0);
            B = Math.max(B, a[i] - a[i+1]);
        }
        for (int i = 0; i + 1 < N; i++) {
            C += Math.min(B, a[i]);
        }
        out.write(A + " " + C);
    }
 
    public void solve_gcj() throws Exception {
        int tests = iread();
        for (int test = 1; test <= tests; test++) {
            out.write("Case #" + test + ": ");
            solve();
            out.write("\n");
        }
    }
 
    public void run() {
        try {
            
            
            in = new BufferedReader(new FileReader(filename + ".in"));
            out = new BufferedWriter(new FileWriter(filename + ".out"));
            solve_gcj();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
 
    public int iread() throws Exception {
        return Integer.parseInt(readword());
    }
 
    public double dread() throws Exception {
        return Double.parseDouble(readword());
    }
 
    public long lread() throws Exception {
        return Long.parseLong(readword());
    }
 
    BufferedReader in;
 
    BufferedWriter out;
 
    public String readword() throws IOException {
        StringBuilder b = new StringBuilder();
        int c;
        c = in.read();
        while (c >= 0 && c <= ' ')
            c = in.read();
        if (c < 0)
            return "";
        while (c > ' ') {
            b.append((char) c);
            c = in.read();
        }
        return b.toString();
    }
 
    public static void main(String[] args) {
        try {
            Locale.setDefault(Locale.US);
        } catch (Exception e) {
 
        }
        new Thread(new Main()).start();
        
    }
 }