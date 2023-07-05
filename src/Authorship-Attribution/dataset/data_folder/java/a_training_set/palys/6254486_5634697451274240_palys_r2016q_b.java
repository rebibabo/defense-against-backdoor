package r2016.roundq;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class R2016Q_B {
    
    private final static String CASE = "Case #%d: ";
    
    private int t;
    
    public static void main(final String[] args) {
        new R2016Q_B().solve();
    }
    
    public void solve(final Scanner in, final PrintStream out) throws IOException {
        readNumberCases(in);
        for (int i = 0; i < numberCases(); i++) {
            printCaseLabel(i, out);
            readCase(in);
            solveCase();
            printOutput(out);
            out.println();
        }
    }
 
    private String folder() {
        return this.getClass().getSimpleName() + "_cases";
    }
 
    private String inFolder() {
        return folder() + "/in";
    }
    
    private String outFolder() {
        return folder() + "/out";
    }
    
    private void ensureDirectoryExists(final File f) {
        if (!f.isDirectory()) {
            f.mkdir();
        }
    }
    
    private String filePath(final String file, final String dir) {
        return dir + "/" + file;
    }
    
    private String caseLabel(final int n) {
        return String.format(CASE, n + 1);
    }
    
    private void printCaseLabel(final int n, final PrintStream out) {
        out.print(caseLabel(n));
    }
    
    private void solve() {
        
        final File directory = new File(folder());
        final File inDirectory = new File(inFolder());
        final File outDirectory = new File(outFolder());
        
        ensureDirectoryExists(directory);
        ensureDirectoryExists(outDirectory);
        ensureDirectoryExists(inDirectory);
        
        for (final String in : inDirectory.list()) {
            final String fileNameCore = in.replace(".in", "");
            final String outFileName = fileNameCore + ".out";
            
            final File inFile = new File(filePath(in, inFolder()));
            final File outFile = new File(filePath(outFileName, outFolder()));
            try (final PrintStream ps =  new PrintStream(outFile);
                    final Scanner s = new Scanner(inFile)){
                solve(s, ps);
            } catch (final IOException e) {
                e.printStackTrace();
            } 
        }
    }
    
    private int numberCases() {
        return t;
    }
    
    private void readNumberCases(final Scanner in) {
        t = in.nextInt();
    }
    
    private String S;
    
    private int solution;
    
    private void readCase(final Scanner in) {
        S = in.next();
    }
    
    private void solveCase() {
        solution = 0;
        if (S.length() > 0) {
            for (int i = 1; i < S.length(); i++) {
                if (S.charAt(i) != S.charAt(i - 1)) {
                    solution++;
                }
            }
            if (S.charAt(S.length() - 1) == '-') {
                solution++;
            }
        }
    }
    
    private void printOutput(final PrintStream out) {
        out.print(solution);
    }
 }
