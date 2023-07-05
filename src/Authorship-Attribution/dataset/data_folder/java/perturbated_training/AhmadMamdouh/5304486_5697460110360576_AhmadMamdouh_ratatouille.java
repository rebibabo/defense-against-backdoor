import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 public class Ratatouille {
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int p = r.nextInt();
            int[] required = new int[n];
            for (int i = 0; i < required.length; i++) {
                required[i] = r.nextInt();
            }
            LinkedList<Integer>[] available = new LinkedList[n];
            for (int i = 0; i < n; i++) {
                available[i] = new LinkedList<Integer>();
                for (int j = 0; j < p; j++) {
                    available[i].add(r.nextInt());
                }
                Collections.sort(available[i]);
            }
            long units = 1;
            int res=0;
            long max = (long) 1e6+1000;
            outer: while (!available[0].isEmpty()) {
                long top = available[0].remove();
                loop: while (units<=max) {
                    if (90 * units * required[0] > top * 100) {
                        continue outer;
                    }
                    if (top * 100 > 110 * units * required[0]) {
                        units++;
                        continue loop;
                    }
                    int[] toBeRemoved = new int[available.length];
                    boolean fail = false;
                    for (int i = 1; i < available.length; i++) {
                        boolean can = false;
                        for (int j = 0; j < available[i].size(); j++) {
                            long first = available[i].get(j);
                            if (90 * units * required[i] <= first * 100
                                    && first * 100 <= 110 * units *required[i]) {
                                can = true;
                                toBeRemoved[i] = available[i].get(j);
                                break;
                            }
                        }
                        if (!can) {
                            fail = true;
                        }
                    }
                    if (fail) {
                        units++;
                    } else {
                        res++;
                        for (int i = 1; i < toBeRemoved.length; i++) {
                            available[i].remove((Integer)toBeRemoved[i]);
                        }
                        break loop;
                    }
                }
            }
            System.out.printf("Case #%d: %d\n", test, res);
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
