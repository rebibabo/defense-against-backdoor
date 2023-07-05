import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "B";
 
 
    
 
     final String filename= problem+"-small-attempt1";
    
 
    public void solve() throws Exception {
        int D = iread();
        int[] p = new int[D + 1001];
        int ans = 0;
        for (int i = 0; i < D; i++) {
            p[i] = iread();
            ans = Math.max(ans, p[i]);
        }
        for (int min = 1; min < ans; min++) {
            int specials = 0;
            for (int i = 0; i < D; i++)
                specials += (p[i] - 1) / min;
            int res = min + specials;
            ans = Math.min(ans, res);
        }
        out.write(ans + "");
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