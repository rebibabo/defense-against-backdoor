import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Unhapp {
 
    private static void debug(Object... args) {
        System.out.println(Arrays.deepToString(args));
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);
        int T = Integer.parseInt(br.readLine());
        for (int c = 1; c <= T; c++) {
            pw.println("Case #" + c + ": " + solve(br));
        }
        pw.flush();
    }
 
    private static String solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int rc = R*C;
        int minun = Integer.MAX_VALUE;
        for(int i=0;i<(1<<rc);i++) {
            
            int habs = 0;
            boolean[][]h = new boolean[R][C];
            int rcn = 0;
            int ccn = 0;
            for (int j=0;j<rc;j++) {
                
                if ( ((i>>j)&1) == 1) {
                    habs++;
                    h[rcn][ccn] = true;
                }
                
                ccn++;
                if (ccn == C) {
                    ccn = 0;
                    rcn++;
                }
            }
            if (habs == N) {
                minun = Math.min(minun, calc(h));
            }
            
        }
        
        return "" + minun;
    }
 
    private static int calc(boolean[][] h) {
        int unh = 0;
        for (int i=0;i<h.length;i++) {
            for(int j=0;j<h[i].length;j++) {
                if (!h[i][j])continue;
                if (j > 0) {
                    if (h[i][j-1]) unh++;
                }
                if (i > 0) {
                    if (h[i-1][j]) unh++;
                }
                
            }
        }
        
        return unh;
    }
 }
