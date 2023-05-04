import java.io.BufferedReader;
 import java.io.Closeable;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class B_Small {
    
    public static class Pair implements Comparable<Pair> {
        char color;
        int pops;
        
        public Pair(char color, int pops) {
            super();
            this.color = color;
            this.pops = pops;
        }
        
        public int compareTo(Pair arg0){
            return Integer.compare(arg0.pops, this.pops);
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        for(int tt = 1; tt <= T; tt++){
            final int N = sc.nextInt();
            
            final int R = sc.nextInt();
            final int O = sc.nextInt();
            final int Y = sc.nextInt();
            final int G = sc.nextInt();
            final int B = sc.nextInt();
            final int V = sc.nextInt();
            
            Pair[] colors = new Pair[3];
            colors[0] = new Pair('R', R);
            colors[1] = new Pair('Y', Y);
            colors[2] = new Pair('B', B);
            Arrays.sort(colors);
            
            char[] output = new char[N];
            {
                for(int i = 0, count = 0; i < N && count < colors[0].pops; i += 2, count++){
                    output[i] = colors[0].color;
                }
                
            }
            {
                
                for(int i = N - 1, count = 0; i >= 0 && count < colors[1].pops; i -= 2, count++){
                    if(output[i] != '\0'){ i++; count--; continue; }
                    
                    output[i] = colors[1].color;
                }
                
            }
            {
                for(int i = 0, count = 0; i < N && count < colors[2].pops; i++, count++){
                    if(output[i] != '\0'){ count--; continue; }
                    
                    output[i] = colors[2].color;
                }
            }
            
            
            boolean ok = true;
            for(int i = 0; i < N; i++){
                if(output[i] == '\0'){ ok = false; break; }
                final int prev = (N + i - 1) % N;
                final int next = (i + 1) % N;
                
                if(output[i] == output[prev]){ ok = false; break; }
                if(output[i] == output[next]){ ok = false; break; }
            }
            
            System.out.printf("Case #%d: %s\n", tt, ok ? String.valueOf(output) : "IMPOSSIBLE");
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
