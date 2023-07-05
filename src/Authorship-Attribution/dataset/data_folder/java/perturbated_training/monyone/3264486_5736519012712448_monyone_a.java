import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class A {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final char[] input = sc.next().toCharArray();
            final int K = sc.nextInt();
            
            boolean all_ok = true;
            for(int i = 0; i < input.length; i++){
                if(input[i] == '-'){ all_ok = false; break; }
            }
            
            if(all_ok){
                System.out.printf("Case #%d: %d\n", tt, 0);
                continue;
            }else{
                int count = 0;
                for(int i = 0; i <= input.length - K; i++){
                    if(input[i] == '+'){ continue; }
                    
                    count++;
                    for(int j = 0; j < K; j++){
                        if(input[i + j] == '+'){ 
                            input[i + j] = '-';
                        }else{
                            input[i + j] = '+';
                        }
                    }
                }
                
                boolean ok = true;
                for(int i = 0; i < input.length; i++){
                    if(input[i] == '-'){ ok = false; break; }
                }
                
                if(ok){
                    System.out.printf("Case #%d: %s\n", tt, count);
                }else{
                    System.out.printf("Case #%d: %s\n", tt, "IMPOSSIBLE");
                }
            }
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
