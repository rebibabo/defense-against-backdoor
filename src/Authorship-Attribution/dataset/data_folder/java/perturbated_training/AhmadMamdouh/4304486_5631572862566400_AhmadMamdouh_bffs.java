import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class BFFs {
    static int[] bff;
    static int n;
    static int res = 0;
    static void go(int current, int mask, int[] order){
        if(mask==(1<<bff.length)-1){
            check(order);
            return;
        }
        for(int j=0;j<bff.length;j++){
            if((mask&(1<<j))==0){
                order[current] = j;
                go(current+1, mask|(1<<j), order);
            }
        }
    }
    private static void check(int[] order) {
        for (int i = 2; i <= order.length; i++) {
            boolean can = true;
            for(int j=0;j<i;j++){
                if(order[(j+1)%i]==bff[order[j]]||order[j-1>=0?j-1:i-1]==bff[order[j]])
                    continue;
                else
                    can = false;
            }
            if(can)
                res = Math.max(res, i);
        }
        
    }
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            n = r.nextInt();
            bff = new int[n];
            for (int i = 0; i < bff.length; i++) {
                bff[i] = r.nextInt() - 1;
            }
            res = 1;
            go(0,0,new int[n]);
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
