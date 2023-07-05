import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 
 public class A {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final long N = sc.nextInt();
            
            if(N == 0){
                System.out.printf("Case #%d: %s\n", tt, "INSOMNIA");
                continue;
            }
            
            boolean[] used = new boolean[10];
            for(int time = 1; ; time++){
                for(final char ch : ((N * time) + "").toCharArray()){
                    used[Character.getNumericValue(ch)] = true;
                }
                
                boolean ok = true;
                for(int i = 0; i < used.length; i++){
                    if(!used[i]){
                        ok = false;
                        break;
                    }
                }
                
                if(ok){
                    System.out.printf("Case #%d: %s\n", tt, time * N);
                    break;
                }
            }
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
