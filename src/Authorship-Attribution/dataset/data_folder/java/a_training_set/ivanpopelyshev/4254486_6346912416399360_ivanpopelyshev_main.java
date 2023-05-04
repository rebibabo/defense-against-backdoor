import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "B";
 
    
 
    
 
    final String filename = problem + "-small-attempt7";
 
    
 
    public void solve(int test) throws Exception {
        int N = iread(), K = iread();
        int[] S = new int[N - K + 1];
        for (int i = 0; i < S.length; i++) {
            S[i] = iread();
        }
 
        int[] len = new int[K];
 
        int sum = -S[0] % K;
        if (sum < 0)
            sum += K;
        for (int i = 0; i < K; i++) {
            int min = 0, max = 0;
            int j = i;
            int val = 0;
            for (; j + K < N; j += K) {
                val += (S[j + 1] - S[j]);
                min = Math.min(min, val);
                max = Math.max(max, val);
            }
            len[i] = max - min;
            sum += min;
            sum %= K;
            if (sum < 0)
                sum += K;
        }
        int ll = 0, s = 0;
        for (int i = 0; i < K; i++) {
            ll = Math.max(ll, len[i]);
        }
        for (int i = 0; i < K; i++) {
            s += ll - len[i];
        }
        int ans = ll;
        if (s < sum) {
            ans++;
            if (s!=0&&test>=10) {
                System.out.println("test");
                
            }
            System.out.println("increase answer in test #"+test+" s="+s+" sum="+sum);
        } else {
            if( sum!=0)
                System.out.println("not increase answer in test #"+test+" s="+s+" sum="+sum);
        }
        out.write(ans + "");
    }
 
    public void solve_gcj() throws Exception {
        int tests = iread();
        for (int test = 1; test <= tests; test++) {
            out.write("Case #" + test + ": ");
            solve(test);
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