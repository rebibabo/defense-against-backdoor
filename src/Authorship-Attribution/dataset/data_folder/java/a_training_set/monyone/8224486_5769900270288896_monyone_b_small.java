import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 
 public class B_Small {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int R = sc.nextInt();
            final int C = sc.nextInt();
            final int N = sc.nextInt();
            
            final int comb = R * C;
            final int BIT_MAX = 1 << comb;
            int min = Integer.MAX_VALUE;
            for(int bit = 0; bit < BIT_MAX; bit++){
                if(Integer.bitCount(bit) != N){
                    continue;
                }
                
                boolean[][] map = new boolean[R][C];
                for(int r = 0; r < R; r++){
                    for(int c = 0; c < C; c++){
                        final int pos = (r * C + c);
                        
                        map[r][c] = (bit & (1 << pos)) != 0;
                    }
                }
            
            
                int count = 0;
                for(int r = 0; r < R; r++){
                    for(int c = 0; c < C; c++){
                        if(map[r][c] && r < R - 1 && map[r + 1][c]){
                            count++;
                        }
                        if(map[r][c] && c < C - 1 && map[r][c + 1]){
                            count++;
                        }
                    }
                }
                
                min = Math.min(min, count);
            }
            
            System.out.printf("Case #%d: %d\n", tt, min);
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
