import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class StandingOvation {
 
    private static void debug(Object...args) {
        System.out.println(Arrays.deepToString(args));
    }
    
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);
        int T = Integer.parseInt(br.readLine());
        for (int c=1; c<=T; c++) {
            pw.println("Case #" + c + ": " + solve(br));
        }
        pw.flush();
    }
    
    private static String solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        String pat = st.nextToken();
        int add = 0;
        int sf = 0;
        for (int i=0;i<=L;i++) {
            int v = (int)(pat.charAt(i) - '0');
            if (v != 0 && sf < i) {
                add += i - sf;
                sf = i;
            }
            sf += v;
        }
        return "" + add;
    }
    
 }
