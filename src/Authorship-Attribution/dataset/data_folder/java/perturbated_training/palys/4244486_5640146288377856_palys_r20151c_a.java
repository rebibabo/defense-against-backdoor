package r2015.round1c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class R20151C_A {
    
    private int t;
    
    public static void main(String[] args) {
        new R20151C_A().solve();
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
    
    private int R;
    
    private int C;
    
    private int W;
    
    private int m;
    
    public void readCase(Scanner in) {
        R = in.nextInt();
        C = in.nextInt();
        W = in.nextInt();
    }
    
    public void solveCase() {
        m = (C / W) * (R - 1);
        m += Math.max(((C - 1) / W) - 1, 0);
        m += W + (W == C? 0 : 1);
    }
    
    public void printOutput(PrintStream out) {
        out.println(m);
    }
 
 
 }
