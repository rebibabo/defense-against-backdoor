import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "C-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("c.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         int R;
         int C;
 
         private boolean handleHor(int[][] graph, int r, int c, ArrayList<Integer> beamIndexes) {
             if (beamIndexes.size() > 1) {
                 for (int b : beamIndexes) {
                     if (graph[r][b] == 3) return false;
                     graph[r][b] = 2;
                     for (int i = r - 1; i >= 0; i -= 1) {
                         if (graph[i][b] >= 1) return false;
                         if (graph[i][b] == -1) break;
                     }
                     for (int i = r + 1; i < R; i += 1) {
                         if (graph[i][b] >= 1) return false;
                         if (graph[i][b] == -1) break;
                     }
                 }
             } else if (beamIndexes.size() == 0) {
                 int left = c - 1;
                 while (left >= 0 && graph[r][left] >= 0) {
                     int up = r - 1;
                     int down = r + 1;
                     while (up >= 0 && graph[up][left] >= 0) {
                         if (graph[up][left] == 3) return false;
                         if (graph[up][left] == 1) graph[up][left] = 2;
                         up -= 1;
                     }
                     while (down < R && graph[down][left] >= 0) {
                         if (graph[down][left] == 3) return false;
                         if (graph[down][left] == 1) graph[down][left] = 2;
                         down += 1;
                     }
                     left -= 1;
                 }
             }
             return true;
         }
 
         private boolean handleVer(int[][] graph, int c, int r, ArrayList<Integer> beamIndexes) {
             if (beamIndexes.size() > 1) {
                 for (int b : beamIndexes) {
                     if (graph[b][c] == 2) return false;
                     graph[b][c] = 3;
                     for (int i = c - 1; i >= 0; i -= 1) {
                         if (graph[b][i] >= 1) return false;
                         if (graph[b][i] == -1) break;
                     }
                     for (int i = c + 1; i < C; i += 1) {
                         if (graph[b][i] >= 1) return false;
                         if (graph[b][i] == -1) break;
                     }
                 }
             } else if (beamIndexes.size() == 0) {
                 int up = r - 1;
                 while (up >= 0 && graph[up][c] == 0) {
                     int left = c - 1;
                     int right = c + 1;
                     while (left >= 0 && graph[up][left] >= 0) {
                         if (graph[up][left] == 2) return false;
                         if (graph[up][left] == 1) graph[up][left] = 3;
                         left -= 1;
                     }
                     while (right < C && graph[up][right] >= 0) {
                         if (graph[up][right] == 2) return false;
                         if (graph[up][right] == 1) graph[up][right] = 3;
                         right += 1;
                     }
                     up -= 1;
                 }
             }
             return true;
         }
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             R = in.nextInt();
             C = in.nextInt();
             int[][] graph = new int[R][C];
             for (int i = 0; i < R; i += 1) {
                 String s = in.next();
                 for (int j = 0; j < C; j += 1) {
                     char c = s.charAt(j);
                     if (c == '#') graph[i][j] = -1;
                     else if (c == '|' || c == '-') graph[i][j] = 1;
                 }
             }
             for (int r = 0; r < R; r += 1) {
                 ArrayList<Integer> beamIndexes = new ArrayList<>();
                 for (int c = 0; c < C; c += 1) {
                     if (graph[r][c] == -1) {
                         if (!handleHor(graph, r, c, beamIndexes)) {
                             out.println("IMPOSSIBLE");
                             return;
                         }
                         beamIndexes.clear();
                     } else if (graph[r][c] >= 1) {
                         beamIndexes.add(c);
                     }
                 }
                 if (!handleHor(graph, r, C, beamIndexes)) {
                     out.println("IMPOSSIBLE");
                     return;
                 }
             }
             for (int c = 0; c < C; c += 1) {
                 ArrayList<Integer> beamIndexes = new ArrayList<>();
                 for (int r = 0; r < R; r += 1) {
                     if (graph[r][c] == -1) {
                         if (!handleVer(graph, c, r, beamIndexes)) {
                             out.println("IMPOSSIBLE");
                             return;
                         }
                         beamIndexes.clear();
                     } else if (graph[r][c] >= 1) {
                         beamIndexes.add(r);
                     }
                 }
                 if (!handleVer(graph, c, R, beamIndexes)) {
                     out.println("IMPOSSIBLE");
                     return;
                 }
             }
             boolean[][] ok = new boolean[R][C];
             for (int r = 0; r < R; r += 1) {
                 for (int c = 0; c < C; c += 1) {
                     if (graph[r][c] == 2) {
                         
                         int up = r - 1;
                         int down = r + 1;
                         while (up >= 0 && graph[up][c] == 0) {
                             ok[up][c] = true;
                             up -= 1;
                         }
                         while (down < R && graph[down][c] == 0) {
                             ok[down][c] = true;
                             down += 1;
                         }
                     } else if (graph[r][c] == 3) {
                         
                         int left = c - 1;
                         int right = c + 1;
                         while (left >= 0 && graph[r][left] == 0) {
                             ok[r][left] = true;
                             left -= 1;
                         }
                         while (right < C && graph[r][right] == 0) {
                             ok[r][right] = true;
                             right += 1;
                         }
                     }
                 }
             }
             for (int r = 0; r < R; r += 1) {
                 for (int c = 0; c < C; c += 1) {
                     if (graph[r][c] == 0 && !ok[r][c]) {
                         boolean found = false;
                         int left = c - 1;
                         while (left >= 0 && graph[r][left] >= 0) {
                             if (graph[r][left] == 1) graph[r][left] = 3;
                             if (graph[r][left] == 3) {
                                 found = true;
                                 break;
                             }
                             left -= 1;
                         }
                         if (found) continue;
 
                         int up = r - 1;
                         while (up >= 0 && graph[up][c] >= 0) {
                             if (graph[up][c] == 1) graph[up][c] = 2;
                             if (graph[up][c] == 2) {
                                 found = true;
                                 break;
                             }
                             up -= 1;
                         }
                         if (found) continue;
                         int right = c + 1;
                         while (right < C && graph[r][right] >= 0) {
                             if (graph[r][right] == 1) graph[r][right] = 3;
                             if (graph[r][right] == 3) {
                                 found = true;
                                 break;
                             }
                             right += 1;
                         }
                         if (found) continue;
 
                         int down = r + 1;
                         while (down < R && graph[down][c] >= 0) {
                             if (graph[down][c] == 1) graph[down][c] = 2;
                             if (graph[down][c] == 2) {
                                 found = true;
                                 break;
                             }
                             down += 1;
                         }
                         if (found) continue;
                         out.println("IMPOSSIBLE");
                         return;
                     }
                 }
             }
             out.println("POSSIBLE");
             for (int r = 0; r < R; r += 1) {
                 for (int c = 0; c < C; c += 1) {
                     if (graph[r][c] == -1) out.print('#');
                     else if (graph[r][c] == 0) out.print('.');
                     else if (graph[r][c] == 1 || graph[r][c] == 2) out.print('|');
                         
                     else if (graph[r][c] == 3) out.print('-');
                     
                 }
                 out.println();
             }
         }
 
     }
 
     static class InputReader {
         public BufferedReader reader;
         public StringTokenizer tokenizer;
 
         public InputReader(InputStream stream) {
             reader = new BufferedReader(new InputStreamReader(stream));
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
 
