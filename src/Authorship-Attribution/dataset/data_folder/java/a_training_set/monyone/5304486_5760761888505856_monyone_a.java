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
            final int R = sc.nextInt();
            final int C = sc.nextInt();
            
            final char[][] input = new char[R][];
            for(int i = 0; i < R; i++){
                input[i] = sc.next().toCharArray();
            }
            
            while(true){
                boolean updated = false;
                for(int i = 0; i < R; i++){
                    for(int j = 0; j < C; j++){
                        if(input[i][j] != '?'){ continue; }
                        
                        if(i != 0 && input[i - 1][j] != '?'){
                            input[i][j] = input[i - 1][j];
                            updated = true;
                        }
                    }
                }
                
                for(int i = R - 1; i >= 0; i--)
                    for(int j = C - 1; j >= 0; j--){
                    if(input[i][j] != '?'){ continue; }
                    
                    if(i != R - 1 && input[i + 1][j] != '?'){
                        input[i][j] = input[i + 1][j];
                        updated = true;
                    }
                }
                
                if(!updated){ break; }
            }
            
            while(true){
                boolean updated = false;
                for(int i = 0; i < R; i++){
                    for(int j = 0; j < C; j++){
                        if(input[i][j] != '?'){ continue; }
                        
                        if(j != 0 && input[i][j - 1] != '?'){
                            input[i][j] = input[i][j - 1];
                            updated = true;
                        }
                    }
                }
                
                for(int i = R - 1; i >= 0; i--)
                    for(int j = C - 1; j >= 0; j--){
                    if(input[i][j] != '?'){ continue; }
                    
                    if(j != C - 1 && input[i][j + 1] != '?'){
                        input[i][j] = input[i][j + 1];
                        updated = true;
                    }
                }
                
                if(!updated){ break; }
            }
            
            System.out.printf("Case #%d:\n", tt);
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    System.out.print(input[i][j]);
                }
                System.out.println();
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
