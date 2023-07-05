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
    public static int[][] winning;
 
    public static void main(String[] Args) throws Exception {
        
         FS sc = new FS(new File("A-small-attempt0.in"));
 
 
 
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
         new File("a.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d: ", ++cc);
            String n = sc.next();
            int ans = 0;
            String next = "";
            while (true) {
                next = n.replaceAll("CC", "");
                next = next.replaceAll("JJ", "");
                ans += (n.length() - next.length()) * 5;
                if (next.length() == n.length())
                    break;
                n = next;
            }
            
            ans += 5 * (n.length() / 2);
            
            out.println(ans);
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
