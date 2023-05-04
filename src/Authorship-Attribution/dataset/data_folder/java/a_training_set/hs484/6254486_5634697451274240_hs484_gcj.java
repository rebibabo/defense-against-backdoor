package gcj2016.qual.b;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "B-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            fout.write(String.format("Case #%d: ", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    void solve(Scanner sc, PrintWriter out) {
        char[] s = sc.next().toCharArray();
        int n = s.length;
        int step = 0;
        for ( ; count(s, '+') < n; step++) {
            int k = 0;
            for ( ; k < n && s[k] == s[0]; k++) {
                
            }
            for (int i = 0; i < k; i++) {
                s[i] = (s[i] == '+') ? '-': '+';
            }
        }
        out.println(step);
        return;
    }
    
    int count(char[] s, char c) {
        int count = 0;
        for (int i = 0; i < s.length; i++) if (s[i] == c) count++;
        return count;
    }
    
 }
