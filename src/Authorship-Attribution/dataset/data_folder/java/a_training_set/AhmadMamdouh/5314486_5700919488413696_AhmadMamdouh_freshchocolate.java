import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class FreshChocolate {
 
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int p = r.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = r.nextInt() % p;
            }
            int result = -1;
            if (p == 2) {
                int even = 0, odd = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == 0)
                        even++;
                    else
                        odd++;
                }
                result = even + (odd + 1) / 2;
            } else if (p==3){
                int zero = 0, one = 0, two = 0;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == 0)
                        zero++;
                    else if (arr[i] == 1)
                        one++;
                    else
                        two++;
                }
                result = zero;
                int min = Math.min(one, two);
                result += min;
                one -= min;
                two -= min;
                if (one > 0) {
                    result += (one + 2) / 3;
                } else {
                    result += (two + 2) / 3;
                }
            }
            System.out.printf("Case #%d: %d\n", test++, result);
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
