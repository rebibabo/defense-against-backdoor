import java.io.*;
 import java.util.*;
 
 public class bffs {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "C-small-attempt1";
 
   private static void main2() throws IOException {
     int n = in.nextInt();
     int[] next = new int[n];
     for (int i = 0; i < n; i++) next[i] = in.nextInt()-1;
     int[] indeg = new int[n];
     for (int i = 0; i < n; i++) {
       indeg[next[i]]++;
     }
     
     int[] queue = new int[n];
     int front = 0, back = 0;
     boolean[] vis = new boolean[n];
     for (int i = 0; i < n; i++) if (indeg[i] == 0) {
       queue[back++] = i;
       vis[i] = true;
     }
     
     while(front<back) {
       int cur = queue[front++];
       if (!vis[next[cur]] && --indeg[next[cur]] == 0) {
         queue[back++] = next[cur];
         vis[next[cur]] = true;
       }
     }
     
     int ans = 0, ans2 = 0;
     int[] comp = new int[n];
     Arrays.fill(comp, -1);
     boolean[] root = new boolean[n];
     int[] maxdepth = new int[n];
     int cidx = 0;
     for (int i = 0; i < n; i++) {
       if (!vis[i]) {
         int t = i;
         int clen = 0;
         do {
           vis[t] = true;
           t = next[t];
           clen++;
         } while (!vis[t]);
         if (clen != 2)
           ans = Math.max(ans, clen);
         if (clen == 2) {
           ans2 += 2;
           comp[i] = cidx;
           comp[next[i]] = cidx;
           root[i] = true;
           root[next[i]] = true;
           cidx++;
         }
       }
     }
     int[] comp2 = new int[n];
     for (int j = back-1; j >= 0; j--) {
       int node = queue[j];
       if(comp[next[node]] != -1) {
         if (root[next[node]]) comp2[node] = next[node];
         else comp2[node] = comp2[next[node]];
         maxdepth[node] = maxdepth[next[node]]+1;
         comp[node] = comp[next[node]];
       }
     }
     int[] max = new int[cidx];
     int[] max2 = new int[cidx];
     int[] id = new int[cidx];
     int[] id2 = new int[cidx];
     Arrays.fill(id, -1);
     Arrays.fill(id2, -1);
     for (int i = 0; i < n; i++) {
       if (comp[i] != -1) {
         if (maxdepth[i] > max[comp[i]]) {
           int xi = comp2[i];
           if (xi != id2[comp[i]]) {
             id2[comp[i]] = xi;
             max2[comp[i]] = max[comp[i]];
           }
           max[comp[i]] = maxdepth[i];
           id[comp[i]] = xi;
         } else if (maxdepth[i] > max2[comp[i]] && comp2[i] != id[comp[i]]) {
           max2[comp[i]] = maxdepth[i];
           id2[comp[i]] = comp2[i];
         }
       }
     }
     for (int i = 0; i < cidx; i++) ans2 += max[i] + max2[i];
     
     out.println(Math.max(ans, ans2));
   }
 
   public static void main(String[] args) throws IOException {
     if (SUBMIT) {
       in = new InputReader(new FileInputStream(new File(NAME + ".in")));
       out = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".out")));
     } else {
       in = new InputReader(System.in);
       out = new PrintWriter(System.out, true);
     }
 
     int numCases = in.nextInt();
     for (int test = 1; test <= numCases; test++) {
       out.print("Case #" + test + ": ");
       main2();
     }
 
     out.close();
     System.exit(0);
   }
 
   static class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
       reader = new BufferedReader(new InputStreamReader(stream), 32768);
       tokenizer = null;
     }
 
     public String next() {
       while (tokenizer == null || !tokenizer.hasMoreTokens()) {
         try {
           tokenizer = new StringTokenizer(reader.readLine());
         } catch (IOException e) {
           throw new RuntimeException(e);
         }
       }
       return tokenizer.nextToken();
     }
 
     public int nextInt() {
       return Integer.parseInt(next());
     }
   }
 }
