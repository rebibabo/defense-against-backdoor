import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class a {
    public static int[][] winning;
 
    public static void main(String[] Args) throws Exception {
        
        FS sc = new FS(new File("A-small-attempt2.in"));
        
        
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                new File("a.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            out.printf("Case #%d:%n", ++cc);
            int r = sc.nextInt();
            int c = sc.nextInt();
            char[][] let = new char[r][c];
            boolean[][] done = new boolean[r][c];
 
            for (int i = 0; i < r; i++) {
                String s = sc.next();
                for (int j = 0; j < c; j++) {
                    let[i][j] = s.charAt(j);
                }
            }
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    if (let[i][j] != '?' && !done[i][j]) {
                        int i2 = i;
                        char ccc = let[i][j];
                        let[i][j] = '?';
                        while (i2 > 0 && let[i2 - 1][j] == '?')
                            i2--;
                        int i3 = i;
                        while (i3 < r - 1 && let[i3 + 1][j] == '?')
                            i3++;
                        int j2 = j;
                        while (j2 > 0 && check(i2, i3, j2 - 1, let))
                            j2--;
                        int j3 = j;
                        
                        
                        for (int ii = i2; ii <= i3; ii++)
                            for (int jj = j2; jj <= j3; jj++) {
                                let[ii][jj] = ccc;
                                done[ii][jj] = true;
                            }
                    }
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++) {
                    if (let[i][j] == '?')
                        let[i][j] = let[i][j - 1];
                }
 
            for (int i = 0; i < r; i++, out.printf("%n"))
                for (int j = 0; j < c; j++)
                    out.printf("%c", let[i][j]);
        }
        out.close();
    }
 
    public static boolean check(int a, int b, int c, char[][] arr) {
        for (int i = a; i <= b; i++)
            if (arr[i][c] != '?')
                return false;
        return true;
    }
 
    public static class FS {
        BufferedReader br;
        StringTokenizer st;
 
        FS(InputStream in) throws Exception {
            br = new BufferedReader(new InputStreamReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        FS(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens())
                return st.nextToken();
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
    }
 }
