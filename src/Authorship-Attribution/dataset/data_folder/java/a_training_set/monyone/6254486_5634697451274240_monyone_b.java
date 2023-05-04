import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class B {
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final char[] inputs = sc.next().toCharArray();
            final int n = inputs.length;
            
            long[] all_up_cost   = new long[n];
            long[] all_down_cost = new long[n];
            Arrays.fill(all_up_cost, Integer.MAX_VALUE);
            Arrays.fill(all_down_cost, Integer.MAX_VALUE);
            
            all_up_cost[0]   = inputs[0] == '+' ? 0 : 1;
            all_down_cost[0] = inputs[0] == '+' ? 1 : 0;
            
            for(int i = 1; i < n; i++){
                if(inputs[i] == '+'){
                    all_up_cost[i] = Math.min(all_up_cost[i], all_up_cost[i - 1]); 
                    all_up_cost[i] = Math.min(all_up_cost[i], all_down_cost[i - 1] + 1); 
                    
                    all_down_cost[i] = Math.min(all_down_cost[i], all_up_cost[i - 1] + 1); 
                    all_down_cost[i] = Math.min(all_down_cost[i], all_down_cost[i - 1] + 1 + 1); 
                }else{
                    all_down_cost[i] = Math.min(all_down_cost[i], all_down_cost[i - 1]); 
                    all_down_cost[i] = Math.min(all_down_cost[i], all_down_cost[i - 1] + 1); 
                    
                    all_up_cost[i] = Math.min(all_up_cost[i], all_down_cost[i - 1] + 1); 
                    all_up_cost[i] = Math.min(all_up_cost[i], all_up_cost[i - 1] + 2); 
                }
            }
            
            System.out.printf("Case #%d: %d\n", tt, all_up_cost[n - 1]);
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
