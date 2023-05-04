import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class D {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("d.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            int n = readInt();
            int k = readInt();
            String[][] grid = new String[n][n];
            while(k-- > 0) {
                String s = nextToken();
                int r = readInt()-1;
                int c = readInt()-1;
                grid[r][c] = s;
            }
            ArrayList<String> ans = new ArrayList<String>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] != null && grid[i][j].equals("o")) continue;
                    if(canPlaceRowCol(grid, i, j) && canPlaceDiag(grid, i, j)) {
                        grid[i][j] = "o";
                        ans.add("o " + (i+1) + " " + (j+1));
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] != null) continue;
                    if(canPlaceRowCol(grid, i, j)) {
                        grid[i][j] = "x";
                        ans.add("x " + (i+1) + " " + (j+1));
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] != null) continue;
                    if(canPlaceDiag(grid, i, j)) {
                        grid[i][j] = "+";
                        ans.add("+ " + (i+1) + " " + (j+1));
                    }
                }
            }
            
            int ret = 0;
            for(String[] out: grid) {
                for(String out2: out) {
                    if(out2 == null) continue;
                    if(out2.equals("o")) ret += 2;
                    else ret++;
                }
            }
            pw.println(ret + " " + ans.size());
            for(String out: ans) {
                pw.println(out);
            }
        }
        pw.close();
    }
 
    public static boolean canPlaceRowCol(String[][] grid, int i, int j) {
        for(int k = 0; k < grid[i].length; k++) {
            if(k != j && grid[i][k] != null && !grid[i][k].equals("+")) return false;
            if(k != i && grid[k][j] != null && !grid[k][j].equals("+")) return false;
        }
        return true;
    }
    
    public static boolean canPlaceDiag(String[][] grid, int i, int j) {
        for(int k = -grid.length; k <= grid.length; k++) {
            int nx = i + k;
            int ny = j + k;
            if(nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length && (nx != i)) {
                if(grid[nx][ny] != null && !grid[nx][ny].equals("x")) return false;
            }
            nx = i + k;
            ny = j - k;
            if(nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length && (nx != i)) {
                if(grid[nx][ny] != null && !grid[nx][ny].equals("x")) return false;
            }
        }
        return true;
    }
    
    
    
    public static int readInt() {
        return Integer.parseInt(nextToken());
    }
 
    public static long readLong() {
        return Long.parseLong(nextToken());
    }
 
    public static double readDouble() {
        return Double.parseDouble(nextToken());
    }
 
    public static String nextToken() {
        while(st == null || !st.hasMoreTokens())    {
            try {
                if(!br.ready()) {
                    pw.close();
                    System.exit(0);
                }
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return st.nextToken();
    }
 
    public static String readLine() {
        st = null;
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 
 }
