import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 
 public class A_small {
    
    public static int solve(final int C, final int W){
        final int prob_W = W * 2 - 1;
        
        if(C < W){
            return Integer.MIN_VALUE;
        }else if(C == W){
            return W;
        }else if(C <= prob_W){
            final int rest = C - W;
            
            
            
            return (C - 2 * rest) + (rest + 1);
        }else{
            return Math.max(solve(prob_W, W) - 1, solve(C - W, W)) + 1;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int R = sc.nextInt();
            final int C = sc.nextInt();
            final int W = sc.nextInt();
            
            int answer = 0;
            if(W == 1){
                answer = R * C;
            }else{
                answer = solve(C, W);
            }
            
            
            System.out.printf("Case #%d: %d\n", tt, answer);
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
