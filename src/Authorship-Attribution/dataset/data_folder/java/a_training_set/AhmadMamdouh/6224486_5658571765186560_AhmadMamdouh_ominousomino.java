import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 public class OminousOmino {
    public static void main(String[] args) throws Exception {
        InputReader r = new InputReader(new FileReader("D-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("d_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int x = r.nextInt();
            int n = r.nextInt();
            int m = r.nextInt();
            int min = Math.min(n, m);
            int max = Math.max(n, m);
            n = min;
            m = max;
            String res = "";
            String gab = "GABRIEL";
            String rich = "RICHARD";
            if (x == 1) {
                res = gab;
            } else if (x == 2) {
                res = (n * m) % 2 == 0 ? gab : rich;
            } else if (x == 3) {
                if (n == 1) {
                    res = rich;
                } else if (n == 2) {
                    res = m == 3 ? gab : rich;
                } else if (n == 3) {
                    res = gab;
                } else {
                    res = rich;
                }
            } else {
                if (n == 1) {
                    res = rich;
                } else if (n == 2) {
                    res = rich;
                } else if (n == 3) {
                    res = m==4?gab:rich ;
                } else {
                    res = gab;
                }
            }
             out.printf("Case #%d: %s\n", test++, res);
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
