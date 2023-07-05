import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 import static java.lang.Math.*;
 
 public class ProblemB {
    String PROBLEM_ID = "problemB";
 
    enum TestType {
        EXAMPLE, SMALL, LARGE
    }
 
 
     TestType TYPE = TestType.SMALL;
 
 
    public String getFileName() {
        String result = PROBLEM_ID + "_";
        switch (TYPE) {
        case EXAMPLE:
            result += "example";
            break;
        case SMALL:
            result += "small";
            break;
        case LARGE:
            result += "large";
            break;
        }
        return result;
    }
 
    public String getInFileName() {
        return getFileName() + ".in";
    }
 
    public String getOutFileName() {
        return getFileName() + ".out";
    }
 
    public static void main(String[] args) throws Exception {
        new ProblemB();
    }
 
    public ProblemB() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(getInFileName()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                getOutFileName())));
        Scanner scan = new Scanner(in);
        int tests = scan.nextInt();
        for (int test = 0; test < tests; test++) {
            int rows = scan.nextInt();
            int cols = scan.nextInt();
            int n = scan.nextInt();
            int best = Integer.MAX_VALUE;
            boolean single = rows == 1 || cols == 1;
            int cornerWeight = single ? 1 : 2;
            int boundaryWeight = single ? 2 : 3;
            int innerWeight = 4;
            for ( int mod = 0; mod <= 1; mod++) {
                int corner = 0;
                int inner = 0;
                int free = 0;
                int boundary = 0;
                for ( int r = 0; r < rows; r++) {
                    for ( int c = 0; c < cols; c++ ) {
                        if ( (r + c) % 2 == mod ) free++;
                        else {
                            if ( (r == 0 || r == rows-1) && ( c == 0 || c == cols-1 )) corner++;
                            else if ( (r == 0 || r == rows-1) ||( c == 0 || c == cols-1 )) boundary++;
                            else inner++;
                        }
                    }
                }
                int m = n;
                int cost = 0;
                
                int z = min(m, free);
                m -= z;
                
                z = min(m, corner);
                cost += cornerWeight*z;
                m -= z;
                
                z = min(m, boundary);
                cost += boundaryWeight*z;
                m -= z;
                
                z = min(m, inner);
                cost += innerWeight*z;
                m -= z;
                best = min(best, cost);             
            }
            
            String resultStr = String.format("Case #%d: %d", test + 1, best);
            
 
            System.out.println(resultStr);
            out.println(resultStr);
        }
        out.close();
        System.out.println("*** in file =  " + getInFileName());
        System.out.println("*** out file = " + getOutFileName());
    }
    
    public int bruteforce(int rows, int cols, int n) {
        boolean[][] map = new boolean[rows][cols];
        int m = rows*cols;
        int result = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << m; mask++) {
            if ( Integer.bitCount(mask) != n) continue;
            for ( boolean[] a: map) Arrays.fill(a, false);
            for ( int r = 0; r < rows; r++) {
                for ( int c = 0; c < cols; c++) {
                    int i = cols*r + c;
                    if ((mask & (1 << i)) == 0) continue;
                    map[r][c] = true;
                }
            }
            int count = 0;
            for ( int r = 0; r < rows; r++)
                for ( int c = 0; c < cols; c++) {
                    if ( !map[r][c]) continue;
                    if ( r+1 < rows && map[r+1][c]) count++;
                    if ( c+1 < cols && map[r][c+1]) count++;
                }
            result = min(result, count);
        }
        return result;
        
    }
 }
