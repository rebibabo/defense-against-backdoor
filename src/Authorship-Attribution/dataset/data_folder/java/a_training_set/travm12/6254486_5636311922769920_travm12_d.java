import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.StringTokenizer;
 
 public class d {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("D-small-attempt0.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("d.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            int k = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            out.printf("Case #%d:", ++cc);
            if (c * s >= k) {
                HashSet<Long> seen = new HashSet<Long>();
                int count = 0;
                long loc = 0;
                for (int r = 0; r < k; r++) {
                    if (count == 0) {
                        loc = r;
                    } else {
                        loc = loc * k;
                        loc += r;
                    }
                    count++;
                    if (count == c) {
                        out.printf(" %d", loc + 1);
                        count = 0;
                    }
                }
                int r = 0;
                while (count != 0) {
                    if (count == 0) {
                        loc = r;
                    } else {
                        loc = loc * k;
                        loc += r;
                    }
                    count++;
                    if (count == c) {
                        out.printf(" %d", loc + 1);
                        count = 0;
                    }
                }
 
                out.printf("%n");
            } else {
                out.printf(" IMPOSSIBLE%n");
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
