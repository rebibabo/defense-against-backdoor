import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Dijkstra {
 
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
    
    static final int I = 2;
    static final int J = 3;
    static final int K = 4;
    
    static int q[][] =   {{0, 0, 0, 0, 0}, 
                          {0, 1, I, J, K}, 
                          {0, I, -1, K, -J},
                          {0, J, -K, -1, I},
                          {0, K, J, -I, -1}};
    
    
    private static String solve(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<X;i++) sb.append(s);
        String p = sb.toString();
        int[]v = new int[L*X + 1];
        v[0]=t(p.charAt(0));
        int acu = v[0];
        boolean iseen=acu == I, jseen = false;
        if (iseen) acu = 1;
        for (int i=1;i<p.length();i++) {
            int sign = (int)Math.signum(v[i-1]);
            v[i] = sign * q[Math.abs(v[i-1])][t(p.charAt(i))];
            sign = (int)Math.signum(acu);
            acu = sign * q[Math.abs(acu)][t(p.charAt(i))];
            if (!iseen) {
                if (acu == I) {
                    iseen = true;
                    acu = 1;
                }
            } else {
                if (acu == J) {
                    jseen = true;
                }
            }
        }
        
        if (iseen && jseen && v[L*X-1] == -1) {
            return "YES";
        }
        
        return "NO";
    }
    
    private static int query(int x, int y, int[]v) {
        return -v[x] * v[y];
    }
 
    private static int t(char c) {
        switch (c) {
        case 'i': return I;
        case 'j': return J;
        case 'k': return K;
        }
        throw new IllegalArgumentException();
    }
 }
