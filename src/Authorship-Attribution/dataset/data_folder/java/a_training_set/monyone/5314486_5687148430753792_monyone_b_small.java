import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B_Small {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            final int C = sc.nextInt();
            final int M = sc.nextInt();
            
            int[] ps = new int[M];
            int[] cs = new int[M];
            for(int i = 0; i < M; i++){
                ps[i] = sc.nextInt() - 1;
                cs[i] = sc.nextInt() - 1;
            }
            
            if(C != 2){ continue; }
            
            int[] fst_count = new int[N];
            int[] snd_count = new int[N];
            for(int i = 0; i < M; i++){
                if(cs[i] == 0){
                    fst_count[ps[i]]++;
                }else{
                    snd_count[ps[i]]++;
                }
            }
            
            int[] ride_answers = new int[4];
            int[] use_answers = new int[4];
            for(int i = 0; i < 4; i++){
                final boolean fst_sign = ((i / 1) % 2) == 0;
                final boolean snd_sign = ((i / 2) % 2) == 0;
                
                int[] _fst_count = new int[N];
                int[] _snd_count = new int[N];
                System.arraycopy(fst_count, 0, _fst_count, 0, N);
                System.arraycopy(snd_count, 0, _snd_count, 0, N);
                
                
                
                
                if(fst_sign){
                    for(int snd = 1; snd < N; snd++){
                        if(_snd_count[snd] == 0){ continue; }
                        
                        final int common = Math.min(_fst_count[0], _snd_count[snd]);
                    
                        ride_answers[i] += common;
                        _fst_count[0] -= common;
                        _snd_count[snd] -= common;
                    }
                }else{
                    for(int snd = N - 1; snd >= 1; snd--){
                        if(_snd_count[snd] == 0){ continue; }
                        
                        final int common = Math.min(_fst_count[0], _snd_count[snd]);
                    
                        ride_answers[i] += common;
                        _fst_count[0] -= common;
                        _snd_count[snd] -= common;
                    }
                }
                
                if(snd_sign){
                    for(int fst = 1; fst < N; fst++){
                        if(_fst_count[fst] == 0){ continue; }
                        
                        final int common = Math.min(_fst_count[fst], _snd_count[0]);
                            
                        ride_answers[i] += common;
                        _fst_count[fst] -= common;
                        _snd_count[0] -= common;
                    }
                }else{
                    for(int fst = N - 1; fst >= 1; fst--){
                        if(_fst_count[fst] == 0){ continue; }
                            
                        final int common = Math.min(_fst_count[fst], _snd_count[0]);
                            
                        ride_answers[i] += common;
                        _fst_count[fst] -= common;
                        _snd_count[0] -= common;
                    }
                }
                
                for(int fst = 1; fst < N; fst++){
                    if(_fst_count[fst] == 0){ continue; }
                    
                    for(int snd = 1; snd < N; snd++){
                        if(_snd_count[snd] == 0){ continue; }
                        if(fst == snd){ continue; }
                        
                        final int common = Math.min(_fst_count[fst], _snd_count[snd]);
                        
                        ride_answers[i] += common;
                        _fst_count[fst] -= common;
                        _snd_count[snd] -= common;
                    }
                }
                
                ride_answers[i] += _fst_count[0] + _snd_count[0];
                for(int same = 1; same < N; same++){
                    ride_answers[i] += Math.max(_fst_count[same], _snd_count[same]);
                }
                
                
                
                for(int same = 1; same < N; same++){
                    use_answers[i] += Math.min(_fst_count[same], _snd_count[same]);
                }
            }
            
            int min = 0;
            for(int i = 1; i < 4; i++){
                if(ride_answers[i] < ride_answers[min] || ride_answers[i] == ride_answers[min] && use_answers[i] < use_answers[min]){
                    min = i;
                }
            }
            
            System.out.printf("Case #%d: %d %d\n", tt, ride_answers[min], use_answers[min]);
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
