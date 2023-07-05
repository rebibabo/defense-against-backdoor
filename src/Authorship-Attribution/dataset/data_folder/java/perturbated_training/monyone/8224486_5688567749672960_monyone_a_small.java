import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 
 public class A_Small {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        final int MAX = 1000000;
        
        int[] DP = new int[MAX + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[0] = 0;
        for(int i = 0; i <= MAX; i++){
            final int next = i + 1;
            DP[next] = Math.min(DP[next], DP[i] + 1);
            
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            final int reversed = Integer.parseInt(sb.reverse().toString());
            
            if(reversed <= MAX){
                DP[reversed] = Math.min(DP[reversed], DP[i] + 1);
            }
        }
        
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            
            System.out.printf("Case #%d: %d\n", tt, DP[N]);
        }
        
        
        sc.close();
    }
    
    public static class Scanner {
        private BufferedReader br;
        private StringTokenizer tok;
 
        public Scanner(InputStream is) throws IOException {
            br = new BufferedReader(new InputStreamReader(is));
        }
 
        private void getLine() throws IOException {
            while (!hasNext()) { tok = new StringTokenizer(br.readLine()); }
        }
 
        private boolean hasNext() {
            return tok != null && tok.hasMoreTokens();
        }
 
        public String next() throws IOException {
            getLine(); return tok.nextToken();
        }
 
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
        
        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        
        public void close() throws IOException {
            br.close();
        }
    }
 }
