import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 public class A_1B {
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("A-small-attempt0(2).in"));
        PrintWriter out = new PrintWriter(new FileWriter("A_small.txt"));
        int T = r.nextInt();
        int[] limit = new int[5 * 1000000];
        limit[1] = 1;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(1);
        while (!q.isEmpty()) {
            int front = q.remove();
            if (front + 1 < limit.length && limit[front + 1] == 0) {
                q.add(front + 1);
                limit[front + 1] = limit[front] + 1;
            }
            StringBuilder s = new StringBuilder(front + "");
            int rev = new Integer(s.reverse().toString());
            if (rev < limit.length && limit[rev] == 0) {
                q.add(rev);
                limit[rev] = limit[front] + 1;
            }
        }
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            
            out.printf("Case #%d: %d\n", test++, limit[n]);
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
