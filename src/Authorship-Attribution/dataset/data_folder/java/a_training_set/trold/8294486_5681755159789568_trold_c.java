
 import java.io.*;
 import java.util.*;
 
 public class C {
    void floydWarshall(double[][] d) {
        int N = d.length;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < N ; k++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = Integer.parseInt(in.readLine());
            for (int t = 1 ; t <= T ; t++) {
                int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int N = p[0];
                int Q = p[1];
                double[] maxdist = new double[N];
                double[] speed = new double[N];
                for (int i = 0 ; i < N ; i++) {
                    p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    maxdist[i] = p[0];
                    speed[i] = p[1];
                }
                double[][] d = new double[N][];
                for (int i = 0 ; i < N ; i++) {
                    d[i] = Arrays.stream(in.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
                }
                for (int i = 0 ; i < N ; i++) d[i][i] = 0;
                for (int i = 0 ; i < N ; i++) {
                    for (int j = 0 ; j < N ; j++) {
                        if (d[i][j] == -1) d[i][j] = Double.POSITIVE_INFINITY;
                    }
                }
                floydWarshall(d);
                floydWarshall(d);
                for (int i = 0 ; i < N ; i++) {
                    for (int j = 0 ; j < N ; j++) {
                        if (d[i][j] > maxdist[i]) d[i][j] = Double.POSITIVE_INFINITY;
                        else d[i][j] = d[i][j] / speed[i];
                    }
                }
                floydWarshall(d);
                floydWarshall(d);
                StringBuilder sb = new StringBuilder();
                for (int q = 0 ; q < Q ; q++) {
                    p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    int u = p[0]-1;
                    int v = p[1]-1;
                    sb.append(" ").append(d[u][v]);
                }
                out.printf("Case #%d:%s\n", t, sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new C().run(args);
    }
 }
