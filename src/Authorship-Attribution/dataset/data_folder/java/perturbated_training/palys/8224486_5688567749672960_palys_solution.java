package common;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public abstract class Solution {
    
    
    public abstract void solve(Scanner in, PrintStream out) throws IOException;
    
    
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
 
 }
