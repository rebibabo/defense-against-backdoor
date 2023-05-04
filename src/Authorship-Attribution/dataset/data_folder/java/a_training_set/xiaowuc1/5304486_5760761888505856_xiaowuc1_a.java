import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class A {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("a.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d:\n", casenum);
            int r = readInt();
            int c = readInt();
            char[][] grid = new char[r][c];
            for(int i = 0; i < r; i++) {
                String s = nextToken();
                for(int j = 0; j < c; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    if(grid[i][j] == '?') continue;
                    for(int k = i; k < r; k++) {
                        for(int l = j; l < c; l++) {
                            if(grid[i][j] == grid[k][l]) {
                                for(int a = i; a <= k; a++) {
                                    for(int b = j; b <= l; b++) {
                                        grid[a][b] = grid[i][j];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            while(true) {
                boolean change = false;
                for(int i = 0; i < grid.length; i++) {
                    for(int j = 0; j < grid[i].length; j++) {
                        while(expandV(grid, i, j)) {
                            change = true;
                        }
                    }
                }
                if(!change) {
                    for(int i = 0; i < grid.length; i++) {
                        for(int j = 0; j < grid[i].length; j++) {
                            while(expandH(grid, i, j)) {
                                change = true;
                            }
                        }
                    }
                    if(!change) {
                        break;
                    }
                }
            }
            for(char[] out: grid) {
                pw.println(new String(out));
            }
        }
        pw.close();
    }
 
    public static boolean expandV(char[][] grid, int sx, int sy) {
        int xMin = sx;
        int xMax = sx;
        int yMin = sy;
        int yMax = sy;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == grid[sx][sy]) {
                    xMin = Math.min(xMin, i);
                    xMax = Math.max(xMax, i);
                    yMin = Math.min(yMin, j);
                    yMax = Math.max(yMax, j);
                }
            }
        }
        
        if(xMax < grid.length-1) {
            boolean good = true;
            for(int a = yMin; a <= yMax; a++) {
                good &= grid[xMax+1][a] == '?';
            }
            if(good) {
                for(int a = yMin; a <= yMax; a++) {
                    grid[xMax+1][a] = grid[sx][sy];
                }
                return true;
            }
        }
        
        if(xMin > 0) {
            boolean good = true;
            for(int a = yMin; a <= yMax; a++) {
                good &= grid[xMin-1][a] == '?';
            }
            if(good) {
                for(int a = yMin; a <= yMax; a++) {
                    grid[xMin-1][a] = grid[sx][sy];
                }
                return true;
            }
        }
        return false;
    }
 
    public static boolean expandH(char[][] grid, int sx, int sy) {
        int xMin = sx;
        int xMax = sx;
        int yMin = sy;
        int yMax = sy;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == grid[sx][sy]) {
                    xMin = Math.min(xMin, i);
                    xMax = Math.max(xMax, i);
                    yMin = Math.min(yMin, j);
                    yMax = Math.max(yMax, j);
                }
            }
        }
        
        if(yMax < grid[0].length - 1) {
            boolean good = true;
            for(int a = xMin; a <= xMax; a++) {
                good &= grid[a][yMax+1] == '?';
            }
            if(good) {
                for(int a = xMin; a <= xMax; a++) {
                    grid[a][yMax+1] = grid[sx][sy];
                }
                return true;
            }
        }
        
        if(yMin > 0) {
            boolean good = true;
            for(int a = xMin; a <= xMax; a++) {
                good &= grid[a][yMin-1] == '?';
            }
            if(good) {
                for(int a = xMin; a <= xMax; a++) {
                    grid[a][yMin-1] = grid[sx][sy];
                }
                return true;
            }
        }
        return false;
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
