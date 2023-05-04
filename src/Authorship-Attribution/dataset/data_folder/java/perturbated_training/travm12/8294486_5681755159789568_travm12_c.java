import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 public class c {
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("C-small-attempt0.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("c.out"))));
        int cc = 0;
 
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d:", ++cc);
 
            int n = sc.nextInt();
 
            int q = sc.nextInt();
            int[] hSp = new int[n];
            int[] hDi = new int[n];
            for (int i = 0; i < n; i++) {
                hDi[i] = sc.nextInt();
                hSp[i] = sc.nextInt();
            }
            long[][] path = new long[n][n];
            long oo = (1l << 50);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    long x = sc.nextInt();
                    if (x == -1)
                        x = oo;
                    path[i][j] = x;
                }
            }
 
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        path[j][k] = Math.min(path[j][k], path[j][i] + path[i][k]);
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            for (int i = 0; i < q; i++) {
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                boolean[] done = new boolean[n];
                pq.add(new Pair(u, 0));
                double ans = 0;
                while (!pq.isEmpty()) {
                    Pair ps = pq.poll();
                    if (done[ps.loc])
                        continue;
                    
                    
                    done[ps.loc] = true;
                    if (ps.loc == v) {
                        ans = ps.time;
                    }
                    for (int j = 0; j < n; j++) {
                        if (!done[j] && path[ps.loc][j] <= hDi[ps.loc]) {
                            
                            
                            
                            pq.add(new Pair(j, ps.time + (path[ps.loc][j] * 1.0 / hSp[ps.loc])));
                        }
                    }
                }
                out.print(" " + ans);
            }
            out.println();
        }
        out.close();
    }
 
    public static class Pair implements Comparable<Pair> {
        double time;
        int loc;
 
        Pair(int i, double x) {
            loc = i;
            time = x;
        }
 
        public int compareTo(Pair o) {
            return Double.compare(time, o.time);
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
