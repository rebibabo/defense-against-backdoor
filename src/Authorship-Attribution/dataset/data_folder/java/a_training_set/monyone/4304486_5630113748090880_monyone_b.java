import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 
 public class B {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            
            int[] counts = new int[2500 + 1];
            for(int i = 0; i < 2 * N - 1; i++){
                for(int j = 0; j < N; j++){
                    counts[sc.nextInt()]++;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for(int i = 0; i <= 2500; i++){
                if(counts[i] % 2 == 0){ continue; }
                
                
                sb.append(first ? "" : " ").append(i);
                first = false;
            }
            
            System.out.printf("Case #%d: %s\n", tt, sb.toString());
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
