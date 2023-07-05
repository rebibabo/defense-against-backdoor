import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.StringTokenizer;
 import java.util.Set;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "B-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("b.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             String C = in.next();
             String J = in.next();
             String ans[] = brute(C, J);
             out.println("Case #" + testNumber + ": " + ans[0] + " " + ans[1]);
         }
 
         private String[] brute(String C, String J) {
 
             int N = C.length();
             Set<String> numsC = generate(C);
             Set<String> numsJ = generate(J);
             int diff = Integer.MAX_VALUE;
             int cans = Integer.MAX_VALUE;
             int jans = Integer.MAX_VALUE;
             for (String c : numsC) {
                 for (String j : numsJ) {
                     int ci = Integer.parseInt(c);
                     int ji = Integer.parseInt(j);
 
                     int dd = Math.abs(ci - ji);
                     if (dd < diff) {
                         diff = dd;
                         cans = ci;
                         jans = ji;
                     } else if (dd == diff) {
                         if (ci < cans) {
                             cans = ci;
                             jans = ji;
                         } else if (ci == cans) {
                             if (ji < jans) {
                                 cans = ci;
                                 jans = ji;
                             }
                         }
                     }
                 }
             }
 
 
             return new String[]{pad(cans, N), pad(jans, N)};
 
         }
 
         private Set<String> generate(String c) {
             Set<String> cands = new HashSet<>();
             int N = c.length();
             int ten[] = new int[10];
             ten[0] = 1;
             for (int i = 1; i < 10; i++) ten[i] = ten[i - 1] * 10;
             for (int i = 0; i < ten[N]; i++) {
                 if (good(i, c)) cands.add(pad(i, c.length()));
             }
             return cands;
         }
 
         private boolean good(int num, String c) {
             String nstr = pad(num, c.length());
             for (int i = 0; i < c.length(); i++) {
                 if (c.charAt(i) == '?') continue;
                 if (c.charAt(i) != nstr.charAt(i)) return false;
             }
             return true;
         }
 
         private String pad(int cans, int n) {
             String ans = cans + "";
             while (ans.length() < n) {
                 ans = "0" + ans;
             }
             return ans;
         }
 
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
 
     }
 }
 
