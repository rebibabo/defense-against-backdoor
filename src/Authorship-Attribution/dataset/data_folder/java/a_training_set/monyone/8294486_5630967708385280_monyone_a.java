import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class A {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            
            final long D = sc.nextLong();
            final int N = sc.nextInt();
            
            long[] Ks = new long[N];
            int[] Ss = new int[N];
            for(int i = 0; i < N; i++){
                Ks[i] = sc.nextLong();
                Ss[i] = sc.nextInt();
            }
            
            double speed = Double.MAX_VALUE;
            
            for(int i = 0; i < N; i++){
                final long curr_pos = Ks[i];
                
                final double time = (double)(D - curr_pos) / Ss[i];
                
                speed = Math.min(speed, D / time);
            }
            
            
            System.out.printf("Case #%d: %.8f\n", tt, speed);
        }
    }
    
    public static class Scanner implements Closeable {
        private BufferedReader br;
        private StringTokenizer tok;
  
        public Scanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }
  
        private void getLine() {
            try {
                while (!hasNext()) {
                    tok = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) { 
            }
        }
  
        private boolean hasNext() {
            return tok != null && tok.hasMoreTokens();
        }
  
        public String next() {
            getLine();
            return tok.nextToken();
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
  
        public void close() {
            try {
                br.close();
            } catch (IOException e) { 
            }
        }
    }
 }
