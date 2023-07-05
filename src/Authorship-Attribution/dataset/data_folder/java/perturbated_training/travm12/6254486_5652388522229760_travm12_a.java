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
 
 public class a {
    public static void main(String[] Args) throws Exception {
        FS sc = new FS(new File("A-small-attempt0.in"));
 
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("a.out"))));
 
        int t = sc.nextInt();
        int cc = 0;
        while (t-- > 0) {
            int n = sc.nextInt();
            HashSet<Integer> seen = new HashSet<Integer>();
            HashSet<Integer> left = new HashSet<Integer>();
            for (int i = 0; i < 10; i++)
                left.add(i);
            int cur = n;
            while (!seen.contains(cur)) {
                seen.add(cur);
                int i = cur;
                while (i != 0) {
                    left.remove(i % 10);
                    i /= 10;
                }
                if (left.size() == 0) {
                    break;
                }
                cur = cur + n;
            }
            out.printf("Case #%d: %s%n", ++cc, "" + (left.size() == 0 ? cur : "INSOMNIA"));
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
