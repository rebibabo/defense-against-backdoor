import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class b {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("B-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("b.out"))));
        
        
        
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d: ", ++cc);
 
            int n = sc.nextInt();
            int m = sc.nextInt();
            int c = sc.nextInt();
            int[][] cs = new int[2][n];
            int p1 = 0;
            int p2 = 0;
            for (int i = 0; i < c; i++) {
                int set = sc.nextInt() - 1;
                int aa = sc.nextInt() - 1;
                cs[aa][set]++;
                if (aa == 0)
                    p1++;
                else
                    p2++;
            }
            int ans = Math.max(p1, Math.max(p2, cs[1][0] + cs[0][0]));
            int tt = 0;
            for (int i = 0; i < n; i++)
                tt += Math.max(0, (cs[0][i] + cs[1][i]) - ans);
            out.println(ans + " " + tt);
        }
        out.close();
    }
 
    public static class tick implements Comparable<tick> {
        int pos;
        int count;
        int person;
 
        public int compareTo(tick o) {
            if (pos != o.pos)
                return pos - o.pos;
            return o.count - count;
        }
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
