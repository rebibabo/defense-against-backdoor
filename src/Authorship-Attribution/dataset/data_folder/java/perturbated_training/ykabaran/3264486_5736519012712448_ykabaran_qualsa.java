package googlecodejam2017;
 
 import googlecodejam2017.GoogleCodeJam2017.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 public class QualsA implements Problem {
 
    private static final String IMPOSSIBLE = "IMPOSSIBLE";
 
    private boolean[] state;
    private int size;
    private int numFlips;
 
    @Override
    public void setup(BufferedReader input) throws IOException {
        String[] args = input.readLine().split(" ");
 
        String stateStr = args[0];
        int n = stateStr.length();
        state = new boolean[n];
        for (int i = 0; i < n; ++i) {
            state[i] = stateStr.charAt(i) == '+';
        }
 
        size = Integer.parseInt(args[1]);
    }
 
    private void flip(int start, int end) {
        for (int i = start; i < end; ++i) {
            state[i] = !state[i];
        }
    }
 
    @Override
    public void solve() {
        numFlips = 0;
 
        for (int i = 0, l = state.length - size; i <= l; ++i) {
            if (state[i]) {
                continue;
            }
            flip(i, i + size);
            ++numFlips;
        }
 
        for (int i = state.length - (size - 1), l = state.length; i < l; ++i) {
            if (!state[i]) {
                numFlips = -1;
                break;
            }
        }
    }
 
    @Override
    public String getSolution() {
        if (numFlips < 0) {
            return IMPOSSIBLE;
        }
        return Integer.toString(numFlips);
    }
 
 }
