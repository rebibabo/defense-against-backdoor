package r2017.roundq;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class R2017_Q_C {
    
    private final static String CASE = "Case #%d: ";
    
    private int t;
    
    public static void main(final String[] args) {
        new R2017_Q_C().solve();
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
    
    long n;
    long k;
    
    private void readCase(final Scanner in) {
        n = in.nextLong();
        k = in.nextLong();
    }
    
    long min;
    long max;
    
    private void solveCase() {
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add(-n);
        
        while (k-- > 0) {
            long biggest = -q.poll();
            if (biggest == 2) {
                q.add(-1L);
                min = 0L;
                max = 1L;
            } else if (biggest > 2) {
                max = biggest >> 1;
                min = (biggest - 1) >> 1;
                q.add(-min);
                q.add(-max);
            } else {
                min = 0L;
                max = 0L;
            }
            
        }
    }
    
    private void printOutput(final PrintStream out) {
        out.print(max + " " + min);
    }
 }
