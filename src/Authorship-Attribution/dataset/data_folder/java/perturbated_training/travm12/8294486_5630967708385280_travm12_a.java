import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class a {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("A-small-attempt0.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("a.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d: ", ++cc);
            double d = sc.nextInt();
            int n = sc.nextInt();
            double time = 0;
            for (int i = 0; i < n; i++) {
                int p = sc.nextInt();
                int s = sc.nextInt();
                double ttime = (d - p) / (s * 1.0);
                if (time < ttime)
                    time = ttime;
            }
            out.println((d / time));
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
