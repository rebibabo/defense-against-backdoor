package r2017.round1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Scanner;
 
 public class R2017_1C_B {
    
    private final static String CASE = "Case #%d: ";
    
    private int t;
    
    public static void main(final String[] args) {
        new R2017_1C_B().solve();
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
    
    int Ac, Aj;
    static class Activity {
        int start;
        int end;
        Activity(int start, int duration) {
            this.start = start;
            this.end = duration;
        }
    }
    
    ArrayList<Activity> C, J;
    
    private void readCase(final Scanner in) {
        Ac = in.nextInt();
        Aj = in.nextInt();
        
        C = new ArrayList<>();
        J = new ArrayList<>();
        
        for (int i = 0; i < Ac; i++) {
            C.add(new Activity(in.nextInt(), in.nextInt()));
        }
        for (int i = 0; i < Aj; i++) {
            J.add(new Activity(in.nextInt(), in.nextInt()));
        }
    }
    
    int sol = 2;
    int noon = 720;
    
    private void solveCase() {
        sol = 2;
        Collections.sort(C, Comparator.comparing(a -> a.start));
        Collections.sort(J, Comparator.comparing(a -> a.start));
        if (Ac == 0 && Aj == 1) {
            if (J.get(0).start < noon && J.get(0).end > noon) {
                sol = 3;
            }
        } else if (Ac == 1 && Aj == 0) {
            if (C.get(0).start < noon && C.get(0).end > noon) {
                sol = 3;
            }
        } else if (Ac == 0 && Aj == 2) {
            if (J.get(0).start < noon && J.get(1).end > noon) {
                sol = 3;
            }
            if (J.get(1).end - J.get(0).start > noon) {
                sol = 4;
            }
        } else if (Ac == 2 && Aj == 0) {
            if (C.get(0).start < noon && C.get(1).end > noon) {
                sol = 3;
            }
            if (C.get(1).end - C.get(0).start > noon) {
                sol = 4;
            }
        } else {
            if ((C.get(0).start < noon && C.get(0).end > noon) || (J.get(0).start < noon && J.get(0).end > noon)) {
                sol = 3;
            }
        }
    }
    
    private void printOutput(final PrintStream out) {
        out.print(sol);
    }
 }
