package r2017.round1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Scanner;
 
 public class R2017_1C_A {
    
    private final static String CASE = "Case #%d: ";
    
    private int t;
    
    public static void main(final String[] args) {
        new R2017_1C_A().solve();
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
    
    int N;
    int K;
    
    long res;
    
    private static class Pancake {
        int R;
        int H;
        long RH;
        
        Pancake(int R, int H) {
            this.R = R;
            this.H = H;
            this.RH = ((long)R) * H;
        }
    }
    
    ArrayList<Pancake> pancakes;
    
    private void readCase(final Scanner in) {
        N = in.nextInt();
        K = in.nextInt();
        
        pancakes = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            pancakes.add(new Pancake(in.nextInt(), in.nextInt()));
        }
    }
    
    private void solveCase() {
        Collections.sort(pancakes, Comparator.comparing(p -> -p.R));
        res = 0;
        for (int i = 0; i < N; i++) {
            long r = 0;
            r += ((long)pancakes.get(i).R) * (pancakes.get(i).R);
            r += 2 * ((long)pancakes.get(i).R) * (pancakes.get(i).H);
            int j = 0;
            int count = 1;
            
            ArrayList<Pancake> p = new ArrayList<>(pancakes.subList(i+1, N));
            Collections.sort(p, Comparator.comparing(x -> -x.RH));
            
            while (j < p.size() && count < K) {
                if (p.get(j).R <= pancakes.get(i).R) {
                    count++;
                    r += 2 * ((long)p.get(j).R) * (p.get(j).H);
                }
                j++;
            }
            
            if (count == K) {
                res = Math.max(res,  r);
            }
            
        }
    }
    
    private void printOutput(final PrintStream out) {
        out.print(BigDecimal.valueOf(res).multiply(BigDecimal.valueOf(Math.PI)));
    }
 }
