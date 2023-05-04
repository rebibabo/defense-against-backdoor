import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class c {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("C-small-attempt0.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("c.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int j = sc.nextInt();
            
            
            
            
            out.printf("Case #%d:%n", ++cc);
            for (int i = 0; i < j; i++) {
                out.print("1");
                for (int k = 0; k < n / 2 - 1; k++) {
                    out.printf("%d%d", (i >> k) & 1, (i >> k) & 1);
                }
                out.print("1");
                for (int k = 2; k <= 10; k++) {
                    out.printf(" %d", k + 1);
                }
                out.printf("%n");
            }
 
        }
        out.close();
    }
 
    public static class FS {
        BufferedReader br;
        StringTokenizer st;
 
        FS(InputStream in) throws Exception {
            br = new BufferedReader(new InputStreamReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        FS(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens())
                return st.nextToken();
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
    }
 }
