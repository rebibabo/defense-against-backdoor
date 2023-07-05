import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class B {
    
    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }
    
    public static long lcm(long a, long b){
        return a / gcd(a, b) * b;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int B = sc.nextInt();
            final int N = sc.nextInt();
            
            int[] arr = new int[B];
            for(int i = 0; i < B; i++){
                arr[i] = sc.nextInt();
            }
            
            long lcm = arr[0];
            for(int i = 1; i < B; i++){
                lcm = lcm(lcm, arr[i]);
            }
            long count = 0;
            for(int i = 0; i < B; i++){
                count += lcm / arr[i];
            }
            
            int[] current = new int[B];
            int answer = 0;
            for(int i = 0; i <= ((N - 1) % count); i++){
                
                int min_value = Integer.MAX_VALUE;
                int min_pos = -1;
                for(int j = 0; j < B; j++){
                    if(min_value > current[j]){
                        min_value = current[j];
                        min_pos = j;
                    }
                }
                
                for(int j = 0; j < B; j++){
                    current[j] = Math.max(0, current[j] - min_value);
                }
                current[min_pos] += arr[min_pos];
                
                answer = min_pos + 1;
            }
            
            System.out.printf("Case #%d: %d\n", tt, answer);
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
