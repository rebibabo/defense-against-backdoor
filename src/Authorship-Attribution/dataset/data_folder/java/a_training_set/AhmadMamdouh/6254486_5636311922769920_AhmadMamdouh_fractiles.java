import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.StringTokenizer;
 
 public class Fractiles {
    static int c, k;
    static int curr;
    static ArrayList<Long> res;
 
    static void go(int level, long previous) {
        if (level == c - 1) {
            res.add(previous);
            curr++;
            return;
        }
        long newPrev = (previous - 1) * k + Math.min(curr + 1, k);
        curr++;
        go(level + 1, newPrev);
    }
 
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            k = r.nextInt();
            c = r.nextInt();
            int s = r.nextInt();
            curr = 1;
            res = new ArrayList<Long>();
            while (curr <= k) {
                go(0, curr);
            }
            String sol = "";
            if (res.size() > s) {
                sol = " IMPOSSIBLE";
            } else {
                for (long x : res)
                    sol += " " + x;
            }
            System.out.printf("Case #%d:%s\n", test, sol);
            test++;
        }
    }
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
 
        public InputReader(FileReader stream) {
            reader = new BufferedReader(stream);
            tokenizer = null;
        }
 
        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
                return null;
            }
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 }
