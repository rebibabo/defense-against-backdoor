import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class CountingSheep {
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            String res = "";
            if (n == 0)
                res = "INSOMNIA";
            else {
                boolean[] digits = new boolean[10];
                int base = n;
                while (true) {
                    int x = n;
                    while (x > 0) {
                        int y = x % 10;
                        digits[y] = true;
                        x /= 10;
                    }
                    boolean can = true;
                    for (boolean b : digits) {
                        if (!b)
                            can = false;
                    }
                    if (can) {
                        break;
                    }
                    n += base;
                }
                res = n + "";
            }
            System.out.printf("Case #%d: %s\n", test, res);
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
