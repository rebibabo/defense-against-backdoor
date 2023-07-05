import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class d {
    public static void main(String[] Args) throws Exception {
        FastScanner sc = new FastScanner(new File("d.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                "d.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            int x = sc.nextInt();
            int r = sc.nextInt();
            int c = sc.nextInt();
            boolean possible = (Math.max(r, c) >= x && x <= 7 && (r * c) % x == 0 && ((x+1) / 2) <= Math.min(c, r));
            if (possible && x == 4){
                possible = (r > 2) && (c > 2);
            }
            if (possible && x == 6){
                possible = (r>3) && (c>3); 
            }
            out.printf("Case #%d: %s%n", ++cc, possible ? "GABRIEL" : "RICHARD");
        }
        out.close();
    }
 
    public static PrintWriter out;
 
    public static class FastScanner {
        StringTokenizer st;
        BufferedReader br;
 
        FastScanner(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens()) {
                return st.nextToken();
            }
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
 
        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
 }
