import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class C {
 
    private static class Hiker {
        double pos;
        double speed;
 
        public Hiker(double pos, double speed) {
            this.pos = pos;
            this.speed = speed;
        }
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
 
            List<Hiker> hikers = new ArrayList<Hiker>();
 
            for (int i = 0; i < n; i++) {
                int d = in.nextInt();
                int h = in.nextInt();
                int m = in.nextInt();
 
                for (int j = 0; j < h; j++) {
                    Hiker hiker = new Hiker(d, 360.0d / (m + j));
                    hikers.add(hiker);
                }
            }
 
            int res = hikers.size();
 
            for (int i = 0; i < hikers.size(); i++) {
                Hiker hiker = hikers.get(i);
                double time = (360 - hiker.pos) / hiker.speed;
 
                int res1 = 0;
                for (int j = 0; j < hikers.size(); j++) {
                    if (j != i) {
                        Hiker other = hikers.get(j);
                        double dist = time * other.speed;
 
                        if (dist < 360 - other.pos) {
                            res1 += 1;
                        } else {
                            dist -= (360 - other.pos);
                            res1 += Math.floor(dist / 360);
 
                        }
                    }
                }
                if (res1 < res) {
                    res = res1;
                }
            }
 
            out.println("Case #" + test + ": " + res);
        }
 
        in.close();
        out.close();
    }
 }
