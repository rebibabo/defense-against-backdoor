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
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            
            final int D = sc.nextInt();
            
            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(D, Collections.reverseOrder());
            
            for(int i = 0; i < D; i++){
                queue.add(sc.nextInt());
            }
            
            int answer = Integer.MAX_VALUE;
            int time = 0;
            while(!queue.isEmpty()){
                final int value = queue.poll();
                
                
                answer = Math.min(answer, value + time);
                
                
                if(value >= 3){
                    time++;
                    
                    if(value % 2 != 0){
                        queue.add(value / 2 + 1);
                    }else{
                        queue.add(value / 2);
                    }
                    
                    queue.add(value / 2);
                }else{
                    break;
                }
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
