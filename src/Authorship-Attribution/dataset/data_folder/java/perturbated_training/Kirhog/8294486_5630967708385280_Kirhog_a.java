package codejam;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class A {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     void solve() {
         long dest = sc.nextLong();
         int n = sc.nextInt();
 
         List<Horse> horses = new ArrayList<>();
         for (int i = 0; i < n; ++i) {
             long start = sc.nextLong();
             long speed = sc.nextLong();
             Horse horse = new Horse(start, speed);
             horses.add(horse);
         }
 
         double maxTime = 0;
 
         
         Collections.sort(horses, (o1, o2) -> Long.compare(o2.start, o1.start));
 
         Horse prev = horses.get(0);
         maxTime = (dest - prev.start) / prev.speed;
         for (int i = 1; i < n; ++i) {
             Horse curr = horses.get(i);
             double time = (dest - curr.start) / curr.speed;
             maxTime = Math.max(time, maxTime);
         }
 
         double speed = dest / maxTime;
 
         out.printf("%f\n", speed);
     }
 
     static class Horse {
         long start;
         double speed;
 
         public Horse(long start, double speed) {
             this.start = start;
             this.speed = speed;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "A-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             A template = new A();
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
