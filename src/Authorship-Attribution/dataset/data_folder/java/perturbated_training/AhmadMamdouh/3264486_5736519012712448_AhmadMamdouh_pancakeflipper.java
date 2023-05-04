import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class PancakeFlipper {
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            char[] arr = r.next().toCharArray();
            int k = r.nextInt();
            boolean can = true;
            int flip = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '-') {
                    if (i + k - 1 >= arr.length) {
                        can = false;
                        break;
                    }
                    flip++;
                    for (int j = 0; j < k; j++) {
                        arr[i + j] = arr[i + j] == '+' ? '-' : '+';
                    }
                }
            }
            if (!can) {
                System.out.printf("Case #%d: IMPOSSIBLE\n", test);
            } else {
                System.out.printf("Case #%d: %d\n", test, flip);
            }
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
