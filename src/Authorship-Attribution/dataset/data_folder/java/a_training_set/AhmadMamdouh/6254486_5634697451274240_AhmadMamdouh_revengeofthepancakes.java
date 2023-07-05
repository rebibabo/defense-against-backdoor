import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 public class RevengeOfThePancakes {
    static class P {
        String s;
        int cost;
 
        public P(String ss, int c) {
            s = ss;
            cost = c;
        }
    }
 
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            String s = r.next();
            String end = "";
            while (end.length() < s.length())
                end += "+";
            LinkedList<P> q = new LinkedList<P>();
            HashSet<String> vis = new HashSet<String>();
            q.add(new P(s, 0));
            vis.add(s);
            int res = -1;
            while (!q.isEmpty()) {
                P front = q.remove();
                if (front.s.compareTo(end) == 0) {
                    res = front.cost;
                    break;
                }
                for (int i = 1; i <= s.length(); i++) {
                    String n = "";
                    for (int j = 0; j < i; j++) {
                        if (front.s.charAt(j) == '-')
                            n += "+";
                        else
                            n += "-";
                    }
                    for (int j = i; j < s.length(); j++)
                        n += front.s.charAt(j);
                    if (!vis.contains(n)) {
                        vis.add(n);
                        q.add(new P(n, front.cost + 1));
                    }
                }
            }
            System.out.printf("Case #%d: %s\n", test, res);
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
