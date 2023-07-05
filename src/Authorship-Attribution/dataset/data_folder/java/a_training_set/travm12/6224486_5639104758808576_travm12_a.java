import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 
 public class a {
    public static void main(String[] Args) throws Exception{
        FastScanner sc = new FastScanner(new File("a.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File("a.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while(t-->0){
            int ans = 0;
            int n = sc.nextInt() + 1;
            String s = sc.next();
            int cur = 0;
            for (int k = 0; k < n; k++){
                if (cur < k){
                    cur++;
                    ans++;
                }
                cur += s.charAt(k)-'0';
            }
            out.printf("Case #%d: %d%n", ++cc, ans);
        }
        out.close();
    }
    
    public static PrintWriter out;
    
    public static class FastScanner{
        StringTokenizer st;
        BufferedReader br;
        
        FastScanner(File in)throws Exception{
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
        
        String next() throws Exception{
            if (st.hasMoreTokens()){
                return st.nextToken();
            }
            st = new StringTokenizer(br.readLine());
            return next();
        }
        
        int nextInt() throws Exception{
            return Integer.parseInt(next());
        }
    }
 }
