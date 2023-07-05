import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class D {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int K = sc.nextInt();
            final int C = sc.nextInt();
            final int S = sc.nextInt();
            
            if(K == 1){ 
                System.out.printf("Case #%d: 1\n", tt);
            }else if(C == 1){
                if(S < K){
                    System.out.printf("Case #%d: IMPOSSIBLE\n", tt);
                }else{
                    StringBuilder answer = new StringBuilder();
                    for(int i = 1; i <= S; i++){
                        answer.append(i == 1 ? "" : " ").append(i);
                    }
                    
                    System.out.printf("Case #%d: %s\n", tt, answer.toString());
                }
            }else{
                if(S < K - 1){
                    System.out.printf("Case #%d: IMPOSSIBLE\n", tt);
                }else{
                    StringBuilder answer = new StringBuilder();
                    for(int i = 2; i <= K; i++){
                        answer.append(i == 2 ? "" : " ").append(i);
                    }
                    
                    System.out.printf("Case #%d: %s\n", tt, answer.toString());
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
