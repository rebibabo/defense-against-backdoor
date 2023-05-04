package ortools_test;
 
 import com.google.ortools.linearsolver.MPConstraint;
 import com.google.ortools.linearsolver.MPSolver;
 import com.google.ortools.linearsolver.MPVariable;
 
 import java.io.*;
 import java.util.HashMap;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class ProblemC {
     static {
         System.out.println("Static stuff....");
         String property = System.getProperty("java.library.path");
         System.out.println("lib path #" + property + "#");
         StringTokenizer parser = new StringTokenizer(property, ";");
         while (parser.hasMoreTokens()) {
             System.err.println(parser.nextToken());
         }
 
         System.loadLibrary("jniortools");
     }
 
 
     String FILENAME = "problemC_small";
 
 
 
     public String getInFileName() {
         return FILENAME + ".in";
     }
 
     public String getOutFileName() {
         return FILENAME + ".out";
     }
 
     public static void main(String[] args) throws Exception {
         new ProblemC();
     }
 
     public ProblemC() throws Exception {
         
 
 
         BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                 getOutFileName())));
         Scanner scan = new Scanner(in);
         int tests = scan.nextInt();
         for (int test = 0; test < tests; test++) {
             MPSolver solver = new MPSolver("kip",
                     MPSolver.OptimizationProblemType.valueOf("CBC_MIXED_INTEGER_PROGRAMMING"));
             int rows = scan.nextInt();
             int cols = scan.nextInt();
             char[][] map = new char[rows][cols];
             for ( int i = 0; i < rows; i++) map[i] = scan.next().toCharArray();
             HashMap<Integer, MPVariable> horVar = new HashMap<Integer, MPVariable>();
             HashMap<Integer, MPVariable> verVar = new HashMap<Integer, MPVariable>();
             for ( int r = 0; r < rows; r++) {
                 for (int c = 0; c < cols; c++) {
                     if ( map[r][c] == '-' || map[r][c] == '|') {
                         MPVariable h = solver.makeBoolVar("h_"+r+"_"+c);
                         MPVariable v = solver.makeBoolVar("v_"+r+"_"+c);
                         MPConstraint constr = solver.makeConstraint();
                         constr.setBounds(1.0, 1.0);
                         constr.setCoefficient(h, 1.0);
                         constr.setCoefficient(v, 1.0);
                         horVar.put(100*r+c, h);
                         verVar.put(100*r+c, v);
                     }
                 }
             }
             for ( int r = 0; r < rows; r++) {
                 for ( int c = 0; c < cols; c++) {
                     if ( map[r][c] == '.') {
                         int kLeft = c - 1;
                         while ( kLeft >= 0 && map[r][kLeft] == '.') kLeft--;
                         boolean laserLeft = kLeft >= 0 && ( map[r][kLeft] == '|' || map[r][kLeft] =='-');
 
                         int kRight = c + 1;
                         while ( kRight < cols && map[r][kRight] == '.') kRight++;
                         boolean laserRight = kRight < cols && (map[r][kRight] == '|' || map[r][kRight] =='-');
                         if ( laserLeft && laserRight ) {
                             MPConstraint constr = solver.makeConstraint();
                             constr.setBounds(0.0, 0.0);
                             MPVariable hLeft = horVar.get(100*r + kLeft);
                             constr.setCoefficient(hLeft, 1.0);
                             MPVariable hRight = horVar.get(100*r + kRight);
                             constr.setCoefficient(hRight, 1.0);
                         }
                         MPConstraint covered = solver.makeConstraint();
                         covered.setBounds(1.0, 5.0);
                         if ( laserLeft ) {
                             MPVariable hLeft = horVar.get(100*r + kLeft);
                             covered.setCoefficient(hLeft, 1.0);
 
                         }
                         if ( laserRight ) {
                             MPVariable hRight = horVar.get(100*r + kRight);
                             covered.setCoefficient(hRight, 1.0);
                         }
 
                         
 
                         int kTop = r - 1;
                         while ( kTop >= 0 && map[kTop][c] == '.' ) kTop--;
                         boolean laserTop = kTop >= 0 && "-|".indexOf(map[kTop][c]) >= 0;
                         int kBottom = r + 1;
                         while ( kBottom < rows && map[kBottom][c] == '.') kBottom++;
                         boolean laserBottom = kBottom < rows && "-|".indexOf(map[kBottom][c]) >= 0;
                         if ( laserBottom && laserTop ) {
                             MPConstraint constr = solver.makeConstraint();
                             constr.setBounds(0.0, 0.0);
                             MPVariable vBottom = verVar.get(100*kBottom + c);
                             constr.setCoefficient(vBottom, 1.0);
                             MPVariable vTop = verVar.get(100*kTop + c);
                             constr.setCoefficient(vTop, 1.0);
                         }
                         if ( laserBottom ) {
                             MPVariable vBottom = verVar.get(100*kBottom + c);
                             covered.setCoefficient(vBottom, 1.0);
                         }
                         if ( laserTop ) {
                             MPVariable vTop = verVar.get(100*kTop + c);
                             covered.setCoefficient(vTop, 1.0);
                         }
                     }
                 }
             }
             MPSolver.ResultStatus status = solver.solve();
 
 
 
 
 
             boolean allOk = status == MPSolver.ResultStatus.OPTIMAL;
             String resultStr = String.format("Case #%d: ", test + 1);
             if ( allOk ) {
                 resultStr += "POSSIBLE\n";
                 for ( int r = 0; r < rows; r++) {
                     for ( int c = 0; c < cols; c++) {
                         if ( "-|".indexOf(map[r][c]) >= 0 ) {
                             MPVariable h = horVar.get(100*r + c);
                             map[r][c] = h.solutionValue() > 0 ? '-' : '|';
                         }
                     }
                 }
 
                 for ( char[] a : map ) resultStr += new String(a) +"\n";
             } else resultStr += "IMPOSSIBLE\n";
 
             
 
             System.out.print(resultStr);
             out.print(resultStr);
         }
         out.close();
         System.out.println("*** in file =  " + getInFileName());
         System.out.println("*** out file = " + getOutFileName());
     }
 
 }
