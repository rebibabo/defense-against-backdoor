
 import java.io.*;
 import java.util.*;
 
 public class A {
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = Integer.parseInt(in.readLine());
            for (int t = 1 ; t <= T ; t++) {
                int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int D = param[0];
                int N = param[1];
                double[] time = new double[N];
                for (int i = 0 ; i < N ; i++) {
                    param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    int k = param[0];
                    int s = param[1];
                    time[i] = (D-k)/(double)s;
                }
                for (int i = N - 2 ; i >= 0 ; i--) {
                    time[i] = Math.max(time[i], time[i+1]);
                }
                double s = D / time[0];
                out.printf("Case #%d: %s\n", t, s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new A().run(args);
    }
 }
