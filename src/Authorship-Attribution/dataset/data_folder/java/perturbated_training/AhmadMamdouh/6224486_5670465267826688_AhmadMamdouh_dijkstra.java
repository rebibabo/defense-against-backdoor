import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Dijkstra {
    static char[] arr;
    static int[][] code = { {}, { 0, 1, 2, 3, 4 }, { 0, 2, -1, 4, -3 },
            { 0, 3, -4, -1, 2 }, { 0, 4, 3, -2, -1 } };
    static int[] expected = { 2, 3, 4 };
 
    static int decode2(char c) {
        switch (c) {
        case 'i':
            return 2;
        case 'j':
            return 3;
        case 'k':
            return 4;
        }
        return -1;
    }
 
    static int multiply(int a, int b) {
        int sign = (int) (Math.signum(a) * Math.signum(b));
        int value = sign * code[Math.abs(a)][Math.abs(b)];
        if (value > 0) {
            return value;
        } else {
            return 4 + Math.abs(value);
        }
    }
 
    static int decode1(int value) {
        if (value <= 4)
            return value;
        return -(value - 4);
    }
 
    static int[][][] dp = new int[10004][10][4];
 
    static int go(int index, int currValue, int segment) {
        if (index == arr.length) {
            if (segment == 2 && currValue == 4)
                return 1;
            return 0;
        }
        if (segment >= 3)
            return 0;
        if (dp[index][currValue][segment] != -1)
            return dp[index][currValue][segment];
        int way1 = go(index + 1,
                multiply(decode1(currValue), decode2(arr[index])), segment);
        int way2 = currValue == expected[segment] ? go(index, 1, segment + 1)
                : 0;
        return dp[index][currValue][segment] = Math.max(way1, way2);
    }
 
    public static void main(String[] args) throws Exception {
        
        InputReader r = new InputReader(new FileReader("C-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new FileWriter("c_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int L = r.nextInt();
            long X = r.nextLong();
            char[] a = r.next().toCharArray();
            arr = new char[(int) (a.length * X)];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = a[i % L];
            }
            for (int[][] x : dp)
                for (int[] y : x)
                    Arrays.fill(y, -1);
            for (int i = arr.length - 1; i >= 0; i--) {
                for (int j = 1; j < 5; j++)
                    go(i, j, 0);
            }
            int res = go(0, 1, 0);
            out.printf("Case #%d: %s\n", test++, res == 0 ? "NO" : "YES");
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
