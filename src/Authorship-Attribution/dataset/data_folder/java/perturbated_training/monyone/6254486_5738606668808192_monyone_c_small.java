import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class C_small {
    
    public static String keta(final int k, final String str){
        if(str.length() > k){
            return str.substring(0, k);
        }else if(str.length() < k){
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            while(sb.length() < k){
                sb.insert(0, "0");
            }
            return sb.toString();
        }else{
            return str;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            final int J = sc.nextInt();
            
            final Random rand = new Random();
            final int random_range = (1 << (N - 2));
            
            System.out.printf("Case #%d:\n", tt);
            for(int q = 0; q < J; q++){
                LOOP:
                while(true){
                    final String value_str = "1" + keta(N - 2, Integer.toBinaryString(rand.nextInt(random_range))) + "1";
                    
                    StringBuilder answer = new StringBuilder();
                    
                    for(int base = 2; base <= 10; base++){
                        final long value_based = Long.parseLong(value_str, base);
                        
                        boolean ok = false;
                        for(int i = 2; i <= (int)(Math.sqrt(value_based)); i++){
                            if(value_based % i == 0 && value_based / i != 1){
                                answer.append(" ").append(i);
                                ok = true;
                                break;
                            }
                        }
                        
                        if(!ok){
                            continue LOOP;
                        }
                    }
                    
                    System.out.println(value_str + answer.toString());
                    break;
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
