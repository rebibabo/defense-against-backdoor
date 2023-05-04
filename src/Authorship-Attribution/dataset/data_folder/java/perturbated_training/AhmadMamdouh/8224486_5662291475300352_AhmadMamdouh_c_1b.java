import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class C_1B {
    public static void main(String[] args) throws IOException {
 
        InputReader r = new InputReader(new FileReader("C-small-1-attempt1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("C_small_1.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            int[][] arr = new int[n][3];
            int res = -1;
            int total = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = r.nextInt();
                }
                total += arr[i][1];
            }
            if (total == 1) {
                res = 0;
            } else {
                if (n == 1) {
                    res = 0;
                } else {
                    double first = -1.0 * arr[0][0] * arr[0][2] / 360.0;
                    double second = -1.0 * arr[1][0] * arr[1][2] / 360.0;
                    while (first < 0)
                        first += arr[0][2];
                    while (second < 0)
                        second += arr[1][2];
                    double min = Math.min(first, second);
                    double max = Math.max(first,second);
                    if(2*min < max)
                        res = 1;
                    else
                        res = 0;
                }
            }
            System.out.println(res);
            out.printf("Case #%d: %d\n", test++, res);
        }
        out.close();
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
    }
 }
