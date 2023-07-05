import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Map;
 
 public class Counting2 {
 
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
        int N = Integer.parseInt(br.readLine());
        return "" + go(N);
    }
    
    private static int go(int n) {
        String nstr = Integer.toString(n);
        int ln = nstr.length();
        if (ln == 1) {
            return n;
        }
        int up = (ln)/2;
        
        int v = Integer.parseInt(nstr.substring(up));
        int subs = (v > 1) ? v - 1 : 0;
        int nn = n - subs;
        if (is101(nn)) {
            return 2 + subs + go(nn - 2);
        }
        if (nn % 10 == 0) {
            return 1 + subs + go(nn - 1);
        }
        return 1 + subs + go(rev(nn));
    }
    
    private static boolean is101(int nn) {
        int v = nn;
        boolean lastOne = nn % 10 == 1;
        v/=10;
        while(v >= 10) {
            if (v%10 != 0) return false;
            v/=10;
        }
        return v == 1 && lastOne;
    }
 
    private static int rev(int i) {
        StringBuilder v = new StringBuilder(Integer.toString(i));
        return Integer.parseInt(v.reverse().toString());
    }
 }
