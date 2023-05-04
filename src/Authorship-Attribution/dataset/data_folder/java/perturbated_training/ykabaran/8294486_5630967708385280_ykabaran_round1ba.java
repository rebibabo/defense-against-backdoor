package googlecodejam2017;
 
 import googlecodejam2017.GoogleCodeJam2017.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 public class Round1BA implements Problem {
 
    private long D;
    private int N;
    private PosAndSpeed[] initial;
 
    private double longestTime;
    private double maxSpeed;
 
    @Override
    public void setup(BufferedReader input) throws IOException {
        String[] args = input.readLine().split(" ");
        D = Long.parseLong(args[0]);
        N = Integer.parseInt(args[1]);
 
        initial = new PosAndSpeed[N];
        for (int i = 0; i < N; ++i) {
            initial[i] = new PosAndSpeed(input.readLine());
        }
    }
 
    @Override
    public void solve() {
        longestTime = 0;
 
        for (int i = 0; i < N; ++i) {
            PosAndSpeed curr = initial[i];
            double currTime = (double) (D - curr.pos) / curr.speed;
            if (currTime > longestTime) {
                longestTime = currTime;
            }
        }
 
        maxSpeed = D / longestTime;
    }
 
    @Override
    public String getSolution() {
        return Double.toString(maxSpeed);
    }
 
    private static class PosAndSpeed {
 
        private long pos;
        private long speed;
 
        public PosAndSpeed(String arg) {
            String[] args = arg.split(" ");
            pos = Long.parseLong(args[0]);
            speed = Long.parseLong(args[1]);
        }
    }
 
 }
