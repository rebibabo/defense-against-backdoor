import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class BathroomStalls {
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int k = r.nextInt();
            boolean[] arr = new boolean[n + 2];
            arr[0] = arr[n + 1] = true;
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(right, n + 1);
            int[] ls = new int[n];
            int[] rs = new int[n];
            int bestIndex = -1, bestMin = -1, bestMax = -1;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) {
                    ls[j] = j + 1 - left[j] - 1;
                    rs[j] = right[j] - j - 2;
                }
                bestIndex = -1;
                bestMin = -1;
                bestMax = -1;
                for (int j = 0; j < n; j++) {
                    if (arr[j + 1])
                        continue;
                    int min = Math.min(ls[j], rs[j]);
                    int max = Math.max(ls[j], rs[j]);
                    if (min > bestMin) {
                        bestIndex = j;
                        bestMin = min;
                        bestMax = max;
                    } else if (min == bestMin) {
                        if (max > bestMax) {
                            bestMax = max;
                            bestIndex = j;
                        }
                    }
                }
                arr[bestIndex + 1] = true;
                for (int j = 0; j < bestIndex; j++)
                    right[j] = Math.min(right[j], bestIndex + 1);
                for (int j = bestIndex + 1; j < n; j++)
                    left[j] = Math.max(left[j], bestIndex + 1);
            }
            System.out.printf("Case #%d: %d %d\n", test, bestMax, bestMin);
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
