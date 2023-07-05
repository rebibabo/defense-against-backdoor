package gcj2016.qual.c;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "C-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            fout.write(String.format("Case #%d:", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    void solve(Scanner sc, PrintWriter out) {
        out.println();
        int N = sc.nextInt();
        int J = sc.nextInt();
        int[] ans = new int[11];
        for (long bits = 0; bits < 1 << (N - 2); bits++) {
            long x = (1L << (N-1)) + (bits << 1) + 1;
            
            boolean ok = true;
            for (int base = 2; base <= 10; base++) {
                ans[base] = calcDivisor(x, base);
                if (ans[base] == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.print(Long.toBinaryString(x));
                for (int base = 2; base <= 10; base++) out.print(" " + ans[base]);
                out.println();
                J--;
                if (J == 0) break;
            }
        }
    }
    
    int calcDivisor(long x, int base) {
        int[] primes = {2, 3, 5, 7, 11, 13};
        for (int p : primes) {
            if (canDivide(x, base, p)) return p;
        }
        return 0;
    }
    boolean canDivide(long x, int base, int p) {
        int s = 0;
        int a = 1;
        while (x > 0) {
            if (x % 2 == 1) {
                s += a;
            }
            a = (a * base) % p;
            x >>= 1;
        }
        return s % p == 0;
    }
 
 }
