import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class A_1C {
    static int col, w;
    static Short[][][] dp = new Short[1 << 10][1 << 10][10];
 
    static short go(int mask, int response, int pivot) {
        if (end(mask, pivot))
            return 0;
        if (dp[mask][response][pivot] != null)
            return dp[mask][response][pivot];
        short res = Short.MAX_VALUE;
        for (int i = 0; i < col; i++) {
            if ((mask & (1 << i)) == 0) {
                short temp = 0;
                for (int j = 0; j <= col - w; j++) {
                    int x = response(mask, response, i, j, pivot);
                    if (x != -1)
                        temp = (short) Math.max(temp,
                                1 + go(mask | (1 << i), x, j));
                }
                res = (short) Math.min(res, temp);
            }
        }
        return dp[mask][response][pivot] = res;
    }
 
    private static int response(int mask, int response, int guess,
            int newPivot, int pivot) {
        for (int k = 0; k < w; k++) {
            if ((mask & (1 << (k + newPivot))) > 0) {
                if ((response & (1 << (k + newPivot))) == 0) {
                    return -1;
                }
            }
        }
        for (int k = 0; k < col; k++) {
            if ((response & (1 << k)) > 0) {
                if (k >= newPivot && k < newPivot + w) {
 
                } else {
                    return -1;
                }
            }
        }
        if (guess >= newPivot && guess < newPivot + w)
            return response | (1 << guess);
        else
            return response;
    }
 
    private static boolean end(int mask, int pivot) {
        for (int i = 0; i < w; i++)
            if ((mask & (1 << (pivot + i))) == 0)
                return false;
        return true;
    }
 
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("A-small-attempt0(2).in"));
        PrintWriter out = new PrintWriter(new FileWriter("A_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int row = r.nextInt();
            col = r.nextInt();
            w = r.nextInt();
            int res = 1 << 28;
            dp = new Short[1 << 10][1 << 10][10];
            for (int i = 0; i <= col - w; i++) {
                res = Math.min(res, go(0, 0, 1));
            }
            System.out.println(test + " " + res);
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
