
 import java.io.*;
 import java.util.*;
 
 public class C {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    int encounters(int[][] M) {
        int N = 0;
        for (int i = 0 ; i < M.length ; i++) N += M[i][1];
        double[][] H = new double[N][2];
        int index = 0;
        for (int i = 0 ; i < M.length ; i++) {
            for (int j = 0 ; j < M[i][1] ; j++) {
                H[index][0] = M[i][0];
                H[index][1] = M[i][2] + j;
                index++;
            }
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0 ; i < N ; i++) {
            double time = (360 - H[i][0]) / H[i][1];
            double speed = 360 / time;
            int val = 0;
            for (int j = 0 ; j < N ; j++) if (j != i) {
                if (H[j][1] > speed) {
                    double catchup = (360 - H[i][0]) / (H[j][1] - speed);
                    if (catchup < time) {
                        val += 1 + (int)Math.floor((time - catchup) / (H[j][1] - speed));
                    }
                } else if (H[j][1] < speed) {
                    double catchup = H[i][0] / (speed - H[j][1]);
                    if (catchup < time) {
                        val += 1 + (int)Math.floor((time - catchup) / (speed - H[j][1]));
                    }
                }
            }
            best = Math.min(best, val);
        }
        return best;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int N = getInt(in);
                int[][] M = new int[N][];
                for (int i = 0 ; i < N ; i++) {
                    M[i] = getInts(in);
                }
                out.printf("Case #%d: %d\n", CASE, encounters(M));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
