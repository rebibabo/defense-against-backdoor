import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
 
 import java.io.*;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int R, C;
     char[][] g;
 
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("C-small-attempt0 (1).in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++) {
             R = in.nextInt();
             C = in.nextInt();
             g = new char[R][C];
             for (int r = 0; r < R; r++) {
                 char[] row = in.next().toCharArray();
                 for (int c = 0; c < C; c++) {
                     g[r][c] = row[c];
                     if (g[r][c] == '|' || g[r][c] == '-') {
                         g[r][c] = 'x';
                     }
                 }
             }
 
             boolean can = solve();
             String ansStr = can ? "POSSIBLE" : "IMPOSSIBLE";
             ans.append(String.format("Case #%d: %s\n", cas, ansStr));
             if (can) {
                 for (int r = 0; r < R; r++) {
                     for (int c = 0; c < C; c++) {
                         if (g[r][c] == 'x')
                             ans.append("|");
                         else
                             ans.append(g[r][c]);
                     }
                     ans.append("\n");
                 }
             }
         }
         System.out.println(ans.toString());
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private boolean solve() {
         
         for (int r = 0; r < R; r++)
             for (int c = 0; c < C; c++) {
                 if (g[r][c] == 'x') {
                     boolean canBeVertical = !hasTorrents(r, c, 1, 0) && !hasTorrents(r, c, -1, 0);
                     boolean canBeHorizontal = !hasTorrents(r, c, 0, 1) && !hasTorrents(r, c, 0, -1);
                     if (!canBeVertical && !canBeHorizontal)
                         return false;
                     if (canBeVertical && !canBeHorizontal)
                         g[r][c] = '|';
                     if (canBeHorizontal && !canBeVertical)
                         g[r][c] = '-';
                 }
             }
 
         
         for (int k = 0; k < 6000; k++) {
             for (int r = 0; r < R; r++) {
                 for (int c = 0; c < C; c++)
                     if (g[r][c] == '.') {
                         boolean coveredVertical = coveredByTorrents(r, c, 1, 0)
                                 || coveredByTorrents(r, c, -1, 0);
                         boolean coveredHorizontal = coveredByTorrents(r, c, 0, 1)
                                 || coveredByTorrents(r, c, 0, -1);
                         if (!coveredVertical && !coveredHorizontal)
                             return false;
                         if (coveredVertical && !coveredHorizontal) {
                             make(r, c, 1, 0, '|');
                             make(r, c, -1, 0, '|');
                         }
                         if (coveredHorizontal && !coveredVertical) {
                             make(r, c, 0, 1, '-');
                             make(r, c, 0, -1, '-');
                         }
                     }
             }
         }
         return true;
     }
 
 
     private void make(int r, int c, int dR, int dC, char newOrientation) {
         do {
             r += dR;
             c += dC;
 
             if (r < 0 || r >= R || c < 0 || c >= C)
                 return;
             if (g[r][c] == '#')
                 return;
             if (g[r][c] == 'x') {
                 g[r][c] = newOrientation;
             }
         } while (true);
 
     }
 
     private boolean coveredByTorrents(int r, int c, int dR, int dC) {
         do {
             r += dR;
             c += dC;
 
             if (r < 0 || r >= R || c < 0 || c >= C)
                 return false;
             if (g[r][c] == '#')
                 return false;
             if (g[r][c] == 'x'
                     || (g[r][c] == '|' && dC == 0)
                     || (g[r][c] == '-' && dR == 0))
                 return true;
         } while (true);
     }
 
     private boolean hasTorrents(int r, int c, int dR, int dC) {
         do {
             r += dR;
             c += dC;
 
             if (r < 0 || r >= R || c < 0 || c >= C)
                 return false;
             if (g[r][c] == '#')
                 return false;
             if (g[r][c] == 'x'
                     || g[r][c] == '|'
                     || g[r][c] == '-')
                 return true;
         } while (true);
     }
 
 }
 
 class InputReader {
     BufferedReader reader;
     StringTokenizer tok;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream), 32768);
         tok = new StringTokenizer("");
     }
 
     public String next() {
         while (!tok.hasMoreTokens())
             try {
                 tok = new StringTokenizer(reader.readLine());
             } catch (IOException e) {
                 e.printStackTrace();
             }
         return tok.nextToken();
     }
 
     public int nextInt() {
         return Integer.parseInt(next());
     }
 }