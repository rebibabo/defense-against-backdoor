import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class MushroomMonster {
    public static void main(String[] args) throws Exception {
        InputReader r = new InputReader(new FileReader("A-small-attempt0(2).in"));
        PrintWriter out = new PrintWriter(new FileWriter("A_Round1_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = r.nextInt();
            }
            int a = 0, b = 0, maxEat = 0;
            for (int i = 1; i < arr.length; i++) {
                a += Math.max(0, arr[i - 1] - arr[i]);
                maxEat = Math.max(maxEat, arr[i - 1] - arr[i]);
            }
            for (int i = 1; i < arr.length; i++) {
                int d = arr[i - 1];
                b += d < maxEat ? d : maxEat;
            }
            out.printf("Case #%d: %d %d\n", test++, a, b);
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
