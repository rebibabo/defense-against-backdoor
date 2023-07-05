import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class NoisyNeighbours {
    static int n, m, k;
    static boolean[][] arr;
    static int res;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
 
    static void go(int i, int j) {
        if (i == n) {
            int taken = 0;
            int v = 0;
            for (int x = 0; x < n; x++)
                for (int y = 0; y < m; y++) {
                    if (arr[x][y]) {
                        taken++;
                        for (int d = 0; d < 4; d++) {
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                if (arr[nx][ny])
                                    v++;
                            }
                        }
                    }
                }
            if (taken == k) {
                res = Math.min(res, v / 2);
            }
            return;
        }
        if (j + 1 == m) {
            go(i + 1, 0);
            arr[i][j] = true;
            go(i + 1, 0);
            arr[i][j] = false;
        } else {
            go(i, j + 1);
            arr[i][j] = true;
            go(i, j + 1);
            arr[i][j] = false;
        }
    }
 
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("B-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new FileWriter("B_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            n = r.nextInt();
            m = r.nextInt();
            k = r.nextInt();
            arr = new boolean[n][m];
            res = 1 << 28;
            go(0, 0);
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
