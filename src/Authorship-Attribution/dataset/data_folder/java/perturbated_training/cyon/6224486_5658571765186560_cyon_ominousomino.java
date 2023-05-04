import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class OminousOmino {
 
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
 
    final static String G = "GABRIEL";
    final static String R = "RICHARD";
    private static String solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int h = Math.max(r, c);
        int w = Math.min(r, c);
        if (X == 1) {
            return G;
        }
        else if (X == 2) {
            if (h == 1) return R;
            if (h == 2) return G;
            if (h == 3 && w == 2) return G;
            if (h == 3) return R;
            if (h == 4) return G;
        }
        else if (X == 3) {
            if (h == 1) return R;
            if (h == 2) return R;
            if (h == 3 && w == 1) return R;
            if (h == 3) return G;
            if (h == 4 && w == 3) return G;
            if (h == 4) return R;
        }
        else if (X == 4) {
            if (h == 4 && w >= 3) return G;
            return R;
        }
        return "";
    }
 }
