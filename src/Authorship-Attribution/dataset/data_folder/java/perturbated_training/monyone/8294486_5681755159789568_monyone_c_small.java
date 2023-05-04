import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class C_Small {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            final int Q = sc.nextInt();
            
            long[] Es = new long[N];
            int[] Ss = new int[N];
            for(int i = 0; i < N; i++){
                Es[i] = sc.nextLong();
                Ss[i] = sc.nextInt();
            }
            
            long[][] Ds = new long[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    Ds[i][j] = sc.nextLong();
                }
            }
            for(int k = 0; k < N; k++){
                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        if(Ds[i][k] < 0 || Ds[k][j] < 0){ continue; }
                        
                        if(Ds[i][j] < 0){
                            Ds[i][j] = Ds[i][k] + Ds[k][j];
                        }else{
                            Ds[i][j] = Math.min(Ds[i][j], Ds[i][k] + Ds[k][j]);
                        }
                    }
                }
            }
            
            double[][] time = new double[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    time[i][j] = Double.MAX_VALUE / 2 - 1;
                }
            }
            for(int from = 0; from < N; from++){
                for(int to = 0; to < N; to++){
                    if(from == to){ continue; }
                    if(Ds[from][to] < 0){ continue; }
                    
                    if(Es[from] < Ds[from][to]){ continue; }
                    time[from][to] = Math.min(time[from][to], (double)(Ds[from][to]) / Ss[from]);
                }
            }
            for(int k = 0; k < N; k++){
                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                    }
                }
            }
            
            int[] Us = new int[Q];
            int[] Vs = new int[Q];
            for(int i = 0; i < Q; i++){
                Us[i] = sc.nextInt() - 1;
                Vs[i] = sc.nextInt() - 1;
            }
            
            
            
            System.out.printf("Case #%d:", tt);
            for(int q = 0; q < Q; q++){
                System.out.printf(" %.8f", time[Us[q]][Vs[q]]);
            }
            System.out.println();
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
