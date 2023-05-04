import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Mushroom {
 
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[]arr = new int[N];
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long maxi = 0;
        long difs = 0;
        for (int i=1;i<N;i++) {
            int dif = Math.max(0, arr[i-1] - arr[i]);
            maxi = Math.max(dif, maxi);
            difs += dif;
        }
        long difsM = 0;
        for (int i=1;i<N;i++) {
            difsM += Math.min(maxi, arr[i-1]);
        }
        return difs + " " + difsM;
    }
 }
