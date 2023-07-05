import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 
 public class C_small {
    
    public static void reverse(int[] array, int begin, int end){ 
        for(int i = begin, j = end - 1; i < j; i++, j--) { swap(array, i, j); }
    }
    
    public static void swap(int[] array, int fst, int snd){
        int tmp = array[fst];
        array[fst] = array[snd];
        array[snd] = tmp;
    }
    
    @SafeVarargs
    public static boolean next_permutation(int ... perm) {
        for(int i = perm.length - 1; i > 0; i--){
            if(perm[i - 1] < perm[i]){
                int j = perm.length;
                while(perm[i - 1] > perm[--j]);
 
                swap(perm, i - 1, j);
                reverse(perm, i, perm.length); 
 
                return true;
            }
        }
 
        reverse(perm, 0, perm.length); 
        return false;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            
            int[] bff = new int[N];
            for(int i = 0; i < N; i++){
                bff[i] = sc.nextInt() - 1;
            }
            
            int answer = 0;
            for(int bit = 1; bit < 1 << N; bit++){
                final int length = Integer.bitCount(bit);
                
                int[] perms = new int[length];
                for(int b = bit, i = 0; i < length; i++){
                    perms[i] = Integer.numberOfTrailingZeros(Integer.lowestOneBit(b));
                    b -= Integer.lowestOneBit(b);
                }
                
                LOOP:
                do{
                    for(int i = 0; i < length; i++){
                        final int prev = (length + i - 1) % length;
                        final int next = (i + 1) % length;
                        
                        if(bff[perms[i]] != perms[next] && bff[perms[i]] != perms[prev]){
                            continue LOOP;
                        }
                    }
                    
                    answer = Math.max(answer, length);
                }while(next_permutation(perms));
            }
            
            
            System.out.printf("Case #%d: %s\n", tt, answer);
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
