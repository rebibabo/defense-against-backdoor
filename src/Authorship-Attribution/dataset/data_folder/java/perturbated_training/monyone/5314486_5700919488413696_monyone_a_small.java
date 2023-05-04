import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class A_Small {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            final int P = sc.nextInt();
            
            int[] mod_count = new int[P];
            for(int i = 0; i < N; i++){
                mod_count[sc.nextInt() % P]++;
            }
            
            int answer = 0;
            if(P == 2){
                answer = mod_count[0] + (mod_count[1] + 1) / 2;
            }else if(P == 3){
                final int common = Math.min(mod_count[1], mod_count[2]);
                mod_count[1] -= common;
                mod_count[2] -= common;
                
                answer = mod_count[0] + common + ((mod_count[1] + 2) / 3) + ((mod_count[2] + 2) / 3);
            }
            
            System.out.printf("Case #%d: %d\n", tt, answer);
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
