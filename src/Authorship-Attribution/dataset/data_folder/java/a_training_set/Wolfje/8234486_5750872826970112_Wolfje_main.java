import java.util.Scanner;
 import java.io.IOException;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FilenameFilter;
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
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskB {
     public void solve(int testNumber, Scanner in, PrintWriter out) {
         int n = in.nextInt();
         double V = in.nextDouble();
         double X = in.nextDouble();
         double[] r = new double[n];
         double[] c = new double[n];
         for ( int i = 0; i < n; i++ ) {
             r[i] = in.nextDouble();
             c[i] = in.nextDouble();
         }
         double result = -1.0;
         if ( n == 2 && same(c[0], c[1])) {
             r[0] = Math.max(r[0], r[1]);
             n = 1;
 }
         if ( n == 1) {
             if ( same(c[0], X) ) result = V/r[0];
             else result = -1.0;
         } else if ( n == 2 ) {
             double ub = Math.max(c[0], c[1]);
             double lb = Math.min(c[0], c[1]);
             if ( X/lb < 1-1e-6 || X/ub > 1+1e-6) result = -1.0;
 else if ( same(X, c[0])) result = V/r[0];
             else if ( same(X, c[1])) result = V/c[1];
             else {
                 double y = r[0]*(X-c[0])/(r[1]*(c[1]-X));
                 double unitV = r[0] + y*r[1];
                 double units = V/unitV;
 result = units*Math.max(1.0, y);
                 double v0 = V*(X-c[1])/(c[0]-c[1]);
                 double v1 = V - v0;
                 double result2 = Math.max(v0/r[0], v1/r[1]);
 result = result2;
             }
         }
         out.printf("Case #%d: ", testNumber);
         if ( result < -0.5 ) out.println("IMPOSSIBLE");
         else out.printf("%.12f\n", result);
     }
 
     boolean same(double a, double b) {
         return Math.abs(a - b ) < 1e-6;
     }
 }
 
