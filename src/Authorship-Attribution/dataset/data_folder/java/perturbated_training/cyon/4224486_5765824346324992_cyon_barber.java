import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Barber {
 
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
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[]bar=new int[B];
        int lcm = 1;
        for(int i=0;i<B;i++) {
            bar[i] = Integer.parseInt(st.nextToken());
            lcm = lcm(lcm, bar[i]);
        }
        
        int[]z = new int[B*lcm+1];
        int cn = 1;
        for (int i=0;i<lcm;i++) {
            for (int j=0;j<B;j++) {
                if (i % bar[j] == 0) {
                    z[cn] = j+1;
                    
                    cn++;
                }
            }
        }
        
        int r = N % (cn-1);
        if (r == 0) r = (cn-1);
        
        return "" + z[r];
    }
 
    static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a%b);
    }
 }
