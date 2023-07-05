import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class C_1C {
    static int v;
 
    static int go(boolean[] can, int start) {
        boolean end = true;
        for (int i = 0; i < can.length; i++) {
            if (!can[i])
                end = false;
        }
        if (end)
            return 0;
        int res = 1 << 28;
        for (int i = start; i <= v; i++) {
            if (!can[i]) {
                boolean[] newCan = new boolean[can.length];
                for (int j = 0; j < newCan.length; j++) {
                    newCan[j] = can[j];
                }
                newCan[i] = true;
                for (int j = 1; j <= v; j++) {
                    if (can[j]) {
                        if (i + j < can.length)
                            newCan[i + j] = true;
                    }
                }
                boolean c = true;
                for(int k=start; k<=i; k++){
                    if(!newCan[k]){
                        c = false;
                    }
                }
                if(c)
                res = Math.min(res, 1 + go(newCan, i+1));
            }
        }
        return res;
    }
 
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(new FileReader("C-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new FileWriter("C_small.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int c = r.nextInt();
            int d = r.nextInt();
            v = r.nextInt();
            int[] arr = new int[d];
            for (int i = 0; i < d; i++) {
                arr[i] = r.nextInt();
            }
            boolean[] can = new boolean[v + 1];
            for (int i = 0; i < (1 << d); i++) {
                int s = 0;
                for (int j = 0; j < d; j++) {
                    if ((i & (1 << j)) > 0) {
                        s += arr[j];
                    }
                }
                if (s < can.length)
                    can[s] = true;
            }
            int res = go(can,0);
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
