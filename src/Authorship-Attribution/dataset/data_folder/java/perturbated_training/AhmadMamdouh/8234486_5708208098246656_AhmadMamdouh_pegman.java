import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class Pegman {
    static boolean[][] vis;
    static char[][] arr;
    static int res;
 
    static void go(int i, int j, int currDir, int lastX, int lastY, int lastDir) {
        if (i >= arr.length || j >= arr[0].length || i < 0 || j < 0) {
            if (lastDir == -1) {
                arr[lastX][lastY] = 'X';
                return;
            }
            arr[lastX][lastY] = get(lastDir);
            res++;
            return;
        }
        if (vis[i][j])
            return;
        vis[i][j] = true;
        char c = arr[i][j];
        if (c == '.') {
            int dir = currDir;
            if (dir == 0)
                go(i + 1, j, 0, lastX, lastY, lastDir);
            else if (dir == 1)
                go(i - 1, j, 1, lastX, lastY, lastDir);
            else if (dir == 2)
                go(i, j + 1, 2, lastX, lastY, lastDir);
            else
                go(i, j - 1, 3, lastX, lastY, lastDir);
        } else {
            int dir = get(c);
            if (dir == 0)
                go(i + 1, j, 0, i, j, currDir);
            else if (dir == 1)
                go(i - 1, j, 1, i, j, currDir);
            else if (dir == 2)
                go(i, j + 1, 2, i, j, currDir);
            else if (dir == 3)
                go(i, j - 1, 3, i, j, currDir);
            else {
                arr[i][j] = get(lastDir);
                res++;
            }
        }
    }
 
    private static void print(char[][] arr2) {
        for (char[] x : arr2) {
            for (char y : x)
                System.out.print(y);
            System.out.println();
        }
        System.out.println();
    }
 
    private static char get(int lastDir) {
        if (lastDir == 0)
            return '^';
        if (lastDir == 1)
            return 'v';
        if (lastDir == 2)
            return '<';
        return '>';
    }
 
    private static int get(char c) {
        if (c == 'v')
            return 0;
        if (c == '^')
            return 1;
        if (c == '>')
            return 2;
        if (c == '<')
            return 3;
        return 4;
    }
 
    public static void main(String[] args) throws IOException {
        
        InputReader r = new InputReader(
                new FileReader("A-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("A_small_isA.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            int m = r.nextInt();
            arr = new char[n][m];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = r.next().toCharArray();
            }
            res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != '.') {
                        vis = new boolean[n][m];
                        go(i, j, -1, i, j, -1);
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] == 'X')
                        res = -1;
                }
            }
            String sol = res == -1 ? "IMPOSSIBLE" : res + "";
            System.out.println(test + " " + sol);
            out.printf("Case #%d: %s\n", test++, sol);
        }
        out.close();
    }
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
 
        public InputReader(FileReader stream) {
            reader = new BufferedReader(stream);
            tokenizer = null;
        }
 
        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
                return null;
            }
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
    }
 }
