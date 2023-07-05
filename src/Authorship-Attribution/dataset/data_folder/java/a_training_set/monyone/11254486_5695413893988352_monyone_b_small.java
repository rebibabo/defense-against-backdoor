import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 
 public class B_small {
    
    public static int prob_answer = Integer.MAX_VALUE;
    public static int min_c_answer = Integer.MAX_VALUE;
    public static int min_j_answer = Integer.MAX_VALUE;
    public static char[] c_answer = null;
    public static char[] j_answer = null;
    
    public static void dfs(int fst_pos, int snd_pos, char[] fst, char[] snd){
        if(fst_pos < fst.length){
            if(fst[fst_pos] != '?'){
                dfs(fst_pos + 1, snd_pos, fst, snd);
            }else{
                for(int i = 0; i <= 9; i++){
                    fst[fst_pos] = (char)(i + '0');
                    dfs(fst_pos + 1, snd_pos, fst, snd);
                }
                fst[fst_pos] = '?';
            }
        }else if(snd_pos < snd.length){
            if(snd[snd_pos] != '?'){
                dfs(fst_pos, snd_pos + 1, fst, snd);
            }else{
                for(int i = 0; i <= 9; i++){
                    snd[snd_pos] = (char)(i + '0');
                    dfs(fst_pos, snd_pos + 1, fst, snd);
                }
                snd[snd_pos] = '?';
            }
        }else{
            int fst_value = 0, snd_value = 0;
            for(int i = 0; i < fst.length; i++){
                fst_value *= 10; fst_value += (fst[i] - '0');
            }
            for(int i = 0; i < snd.length; i++){
                snd_value *= 10; snd_value += (snd[i] - '0');
            }
            
            
            final int abs_diff = Math.abs(fst_value - snd_value);
            if(abs_diff > prob_answer){ return; }
            if(abs_diff == prob_answer && min_c_answer < fst_value){ return; }
            if(abs_diff == prob_answer && min_c_answer == fst_value && min_j_answer < snd_value){ return; }
            prob_answer = abs_diff;
            min_c_answer = fst_value;
            min_j_answer = snd_value;
            c_answer = fst.clone();
            j_answer = snd.clone();
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final char[] C = sc.next().toCharArray();
            final char[] J = sc.next().toCharArray();
            
            prob_answer = Integer.MAX_VALUE;
            min_c_answer = Integer.MAX_VALUE;
            min_j_answer = Integer.MAX_VALUE;
            
            dfs(0, 0, C, J);
            System.out.printf("Case #%d: %s %s\n", tt, String.valueOf(c_answer), String.valueOf(j_answer));
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
