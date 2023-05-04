import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 
 public class A {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            
            int[] arr = new int[N];
            int max = 0;
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
                max = Math.max(max, arr[i]);
            }
            
            
            
            long fst = 0;
            for(int i = 0; i < N - 1; i++){
                fst += Math.max(0, arr[i] - arr[i + 1]);
            }
            
            long snd = Long.MAX_VALUE;
            for(int rate = 0; rate <= max; rate++){
                long total_eat = 0;
                
                boolean flag = true;
                for(int i = 0; i < N - 1; i++){
                    final long rest = Math.max(0, arr[i] - rate);
                    total_eat += Math.min(arr[i], rate);
                    
                    if(rest > arr[i + 1]){
                        flag = false;
                        break;
                    }
                }
                
                if(flag){
                    snd = Math.min(snd, total_eat);
                }
            }
            
            
            
            System.out.printf("Case #%d: %d %d\n", tt, fst, snd);
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
