import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 import static java.lang.Math.abs;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class B {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     static final double EPS = 1e-9;
     private double[] rr;
     private double[] xx;
     private double v;
     private double x;
     private double t;
     private boolean cant;
 
     double temp(double v0, double t0, double v1, double t1) {
         return (v0 * t0 + v1 * t1) / (v0 + v1);
     }
 
     void solve() {
         cant = false;
 
         int n = sc.nextInt();
         v = sc.nextDouble();
         x = sc.nextDouble();
 
         rr = new double[n];
         xx = new double[n];
         for (int i = 0; i < n; ++i) {
             rr[i] = sc.nextDouble();
             xx[i] = sc.nextDouble();
         }
 
         t = 0;
 
         if (n == 1) {
             solve1();
         } else {
             double[] rc = rr.clone();
             double[] xc = xx.clone();
 
             solve2();
             double t1 = t;
 
             rr = rc;
             xx = xc;
 
             swap(rr, 0, 1);
             swap(xx, 0, 1);
 
             solve2();
             double t2 = t;
 
             t = Math.min(t1, t2);
         }
 
         if (cant || t < 0) {
             out.printf("IMPOSSIBLE\n");
         } else {
             out.printf("%.9f\n", t);
         }
     }
 
     private void solve1() {
         double r1 = rr[0];
         double x1 = xx[0];
         if (abs(x1 - x) > EPS) {
             cant = true;
             return;
         }
 
         t = v / r1;
     }
 
     private void solve2() {
         double r1 = rr[0];
         double x1 = xx[0];
         double r2 = rr[1];
         double x2 = xx[1];
 
         if (Math.abs(x2 - x1) < EPS) {
             rr[0] = r1 + r2;
             solve1();
             return;
         }
 
         if (Math.abs(x - x1) < EPS) {
             solve1();
             return;
         }
         if (Math.abs(x - x2) < EPS) {
             swap(rr, 0, 1);
             swap(xx, 0, 1);
             solve1();
             return;
         }
 
         if (cant(x1, x2)) {
             cant = true;
             return;
         }
 
         double r12 = r1 + r2;
         double x12 = (r1 * x1 + r2 * x2) / r12;
 
         double t1 = ((x - x12) * v) / ((x1 - x12) * r1);
         double t12 = (v - r1 * t1) / r12;
         double T1 = t1 + t12;
         if (cant(x1, x12)) {
             T1 = Double.POSITIVE_INFINITY;
         }
 
         t1 = ((x - x2) * v) / ((x1 - x2) * r1);
         double t2 = (v - r1 * t1) / r2;
         double T2 = t1 + t2;
 
         t = Math.min(T1, T2);
     }
 
     boolean cant(double x1, double x2) {
         if (x - x1 > EPS && x - x2 > EPS) {
             return true;
         }
         if (x1 - x > EPS && x2 - x > EPS) {
             return true;
         }
         return false;
     }
 
     private void swap(double[] a, int x, int y) {
         double t = a[x];
         a[x] = a[y];
         a[y] = t;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
 
         String file = "B-small-attempt2";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             B template = new B();
             template.caseNumber = caseNumber;
             template.solve();
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
