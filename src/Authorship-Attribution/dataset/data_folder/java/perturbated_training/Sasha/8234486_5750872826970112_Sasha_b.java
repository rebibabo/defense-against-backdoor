import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
            double V = in.nextDouble();
            double X = in.nextDouble();
 
            double[] r = new double[n];
            double[] c = new double[n];
 
            for (int i = 0; i < n; i++) {
                r[i] = in.nextDouble();
                c[i] = in.nextDouble();
            }
 
            double minTime = 0;
 
            double equalR = 0, lessR = 0, biggerR = 0;
            ;
            int lessIndex = 0, biggerIndex = 0;
            for (int i = 0; i < n; i++) {
                if (c[i] == X) {
                    equalR += r[i];
                } else if (c[i] > X) {
                    biggerR += r[i];
                    biggerIndex = i;
                } else if (c[i] < X) {
                    lessR += r[i];
                    lessIndex = i;
                }
 
            }
            if (equalR != 0) {
                minTime = V / equalR;
            }
 
            if (biggerR > 0 && lessR > 0) {
                double t1 = V
                        * (X - c[biggerIndex])
                        / (r[lessIndex] * c[lessIndex] - r[lessIndex]
                                * c[biggerIndex]);
                double t2 = V
                        * (X - c[lessIndex])
                        / (r[biggerIndex] * c[biggerIndex] - r[biggerIndex]
                                * c[lessIndex]);
                minTime = Math.max(t1, t2);
            }
 
            out.println("Case #" + test + ": "
                    + (minTime > 0 ? minTime : "IMPOSSIBLE"));
        }
 
        in.close();
        out.close();
    }
 
 }
