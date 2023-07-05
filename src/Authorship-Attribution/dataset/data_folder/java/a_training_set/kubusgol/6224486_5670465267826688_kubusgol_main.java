import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.BufferedReader;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 
 
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
 }
 
 class TaskC {
     private static int ONE = 1;
     private static int I = 3;
     private static int J = 5;
     private static int K = 7;
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int L = in.nextInt();
         int X = in.nextInt();
         char str[] = in.next().toCharArray();
         char input[] = new char[L * X];
         int cnt = 0;
         for (int i = 0; i < X; i++) {
             for (int j = 0; j < L; j++) {
                 input[cnt++] = str[j];
             }
         }
 
         Num all = all(input);
         if (all.value != ONE || all.sign != -1) {
             out.println("Case #"+testNumber+": NO");
             return;
         }
 
         boolean is[] = new boolean[input.length];
         boolean ks[] = new boolean[input.length];
         Num ans = parse(input[0]);
         if (ans.value == I){
             is[0] = true;
         }
         for (int i=1; i<input.length; i++){
             ans = ans.mul(parse(input[i]));
             if (ans.value == I && ans.sign==1){
                 is[i] = true;
             }
         }
 
         ans = parse(input[input.length-1]);
         if (ans.value == K){
             ks[input.length-1] = true;
         }
         for (int i=input.length-2; i>=0; i--){
             ans = parse(input[i]).mul(ans);
             if (ans.value == K && ans.sign==1){
                 ks[i] = true;
             } else {
                 ks[i] = ks[i+1];
             }
         }
 
 
         for (int i=0; i<input.length-2; i++){
             if (is[i] && ks[i+2]){
                 out.println("Case #"+testNumber+": YES");
                 return;
             }
         }
         out.println("Case #"+testNumber+": NO");
     }
 
     private Num all(char[] input) {
         Num ans = parse(input[0]);
         for (int i = 1; i < input.length; i++) {
             ans = ans.mul(parse(input[i]));
         }
         return ans;
     }
 
     private Num parse(char c) {
         return new Num(c == 'i' ? I : c == 'j' ? J : K, 1);
     }
 
 
     private static class Num {
         private int value;
         private int sign;
 
         private Num(int value, int sign) {
             this.value = value;
             this.sign = sign;
         }
 
         public Num mul(Num that) {
             int ans_sign = this.sign * that.sign;
             int ans_value = 0;
             if (this.value == ONE) {
                 if (that.value == ONE) {
                     ans_value = ONE;
                 } else if (that.value == I) {
                     ans_value = I;
                 } else if (that.value == J) {
                     ans_value = J;
                 } else if (that.value == K) {
                     ans_value = K;
                 }
             } else if (this.value == I) {
                 if (that.value == ONE) {
                     ans_value = I;
                 } else if (that.value == I) {
                     ans_value = ONE;
                     ans_sign *= -1;
                 } else if (that.value == J) {
                     ans_value = K;
                 } else if (that.value == K) {
                     ans_value = J;
                     ans_sign *= -1;
                 }
             } else if (this.value == J) {
                 if (that.value == ONE) {
                     ans_value = J;
                 } else if (that.value == I) {
                     ans_value = K;
                     ans_sign *= -1;
                 } else if (that.value == J) {
                     ans_value = ONE;
                     ans_sign *= -1;
                 } else if (that.value == K) {
                     ans_value = I;
                 }
             } else if (this.value == K) {
                 if (that.value == ONE) {
                     ans_value = K;
                 } else if (that.value == I) {
                     ans_value = J;
                 } else if (that.value == J) {
                     ans_value = I;
                     ans_sign *= -1;
                 } else if (that.value == K) {
                     ans_value = ONE;
                     ans_sign *= -1;
                 }
             }
             return new Num(ans_value, ans_sign);
         }
     }
 }
 
 class InputReader {
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
 
