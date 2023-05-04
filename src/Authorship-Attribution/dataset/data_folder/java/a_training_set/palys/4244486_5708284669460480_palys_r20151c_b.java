package r2015.round1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class R20151C_B {
    
    private int t;
    
    public static void main(String[] args) {
        new R20151C_B().solve();
    }
    
    public void solve(Scanner in, PrintStream out) throws IOException {
        readNumberCases(in);
        for (int i = 0; i < numberCases(); i++) {
            printCaseLabel(i, out);
            readCase(in);
            solveCase();
            printOutput(out);
        }
    }
 
    public String folder() {
        return this.getClass().getSimpleName() + "_cases";
    }
 
    public String inFolder() {
        return folder() + "/in";
    }
    
    public String outFolder() {
        return folder() + "/out";
    }
    
    private void ensureDirectoryExists(File f) {
        if (!f.isDirectory()) {
            f.mkdir();
        }
    }
    
    private String filePath(String file, String dir) {
        return dir + "/" + file;
    }
    
    protected String caseLabel(int n) {
        return "Case #" + (n+1) + ": ";
    }
    
    public void printCaseLabel(int n, PrintStream out) {
        out.print(caseLabel(n));
    }
    
    public void solve() {
        
        File folder = new File(folder());
        File inFolder = new File(inFolder());
        File outFolder = new File(outFolder());
        
        ensureDirectoryExists(folder);
        ensureDirectoryExists(outFolder);
        ensureDirectoryExists(inFolder);
        
        for (String in : inFolder.list()) {
            String fileNameCore = in.replace(".in", "");
            String outFileName = fileNameCore + ".out";
            
            
            PrintStream ps = null;
            Scanner s = null;
            try {
                File inFile = new File(filePath(in, inFolder()));
                File outFile = new File(filePath(outFileName, outFolder()));
                
                ps = new PrintStream(outFile);
                s = new Scanner(inFile);
                
                solve(s, ps);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (s != null) {
                    s.close();
                }
                
                if (ps != null) {
                    ps.close();
                }
            }
                
            
        }
        
        
    }
    
    public int numberCases() {
        return t;
    }
    
    public void readNumberCases(Scanner in) {
        t = in.nextInt();
    }
    
    private int K;
    
    private int L;
    
    private int S;
    
    private String keyboard;
    
    private String target;
    
    private double result = 0.0;
    
    private int longestPrefSuf = 0;
    
    private double[] probabilities;
    
    public void readCase(Scanner in) {
        K = in.nextInt();
        L = in.nextInt();
        S = in.nextInt();
        keyboard = in.next();
        target = in.next();
    }
    
    private void countLettersProbabilities() {
        probabilities = new double['Z' - 'A' + 1];
        System.out.println(keyboard);
        for (int i = 0; i < keyboard.length(); i++) {
            probabilities[keyboard.codePointAt(i) - 'A'] += 1.0 / keyboard.length();
        }
    }
    
    private double probabilityOf(String s) {
        double prob = 1.0;
        for (int i = 0; i < s.length(); i++) {
            prob *= probabilities[target.codePointAt(i) - 'A'];
        }
        return prob;
    }
    
    private double probabilityOfOne() {
        double prob = 1.0;
        for (int i = 0; i < target.length(); i++) {
            prob *= probabilities[target.codePointAt(i) - 'A'];
        }
        return prob;
    }
    
    private double expected() {
        countLettersProbabilities();
        double probOfOne = probabilityOfOne();
        double probOfNext = probabilityOf(target.substring(target.length() - longestPrefSuf));
        
        return probOfOne + ((S - target.length()) / longestPrefSuf) * probOfNext;
    }
    
    private boolean isEnounghLetters() {
        for (int i = 0; i < target.length(); i++) {
            if (!keyboard.contains("" + target.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private int worstCase() {
        if (S < target.length()) {
            return 0;
        }
        longestPrefSuf = target.length();
        
        for (int i = 1; i < target.length(); i++) {
            boolean found = true;
            for (int j = 0; j < target.length() - i; j++) {
                if (target.charAt(i) != target.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                longestPrefSuf = target.length() - i;
                break;
            }
        }
        
        return 1 + (S - target.length()) / longestPrefSuf;
    }
    
    public void solveCase() {
        if (!isEnounghLetters()) {
            result = 0;
        } else {
            int w = worstCase();
            double e = expected();
            result = w - e;
        }
    }
    
    public void printOutput(PrintStream out) {
        
        out.format(Locale.US, "%.7f\n", result);
    }
 
 
 }
