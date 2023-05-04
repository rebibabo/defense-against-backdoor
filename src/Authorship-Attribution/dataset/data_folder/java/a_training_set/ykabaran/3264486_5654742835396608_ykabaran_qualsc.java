package googlecodejam2017;
 
 import googlecodejam2017.GoogleCodeJam2017.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 
 public class QualsC implements Problem {
 
    private int N;
    private int K;
 
    private boolean[] state;
    private Choice lastChoice;
 
    @Override
    public void setup(BufferedReader input) throws IOException {
        String[] args = input.readLine().split(" ");
        N = Integer.parseInt(args[0]);
        K = Integer.parseInt(args[1]);
    }
 
    private int getLeftDistance(int n) {
        for (int i = n - 1; i >= 0; --i) {
            if (state[i]) {
                return n - i - 1;
            }
        }
 
        return n;
    }
 
    private int getRightDistance(int n) {
        for (int i = n + 1; i < N; ++i) {
            if (state[i]) {
                return i - n - 1;
            }
        }
 
        return N - n - 1;
    }
 
    private ArrayList<Choice> filterChoicesMaxOfMin(ArrayList<Choice> all) {
        int l = all.size();
        int maxMin = 0;
        for (int i = 0; i < l; ++i) {
            Choice choice = all.get(i);
            int choiceMin = choice.getMin();
            if (choiceMin > maxMin) {
                maxMin = choiceMin;
            }
        }
 
        ArrayList<Choice> choices = new ArrayList<>(l);
        for (int i = 0; i < l; ++i) {
            Choice choice = all.get(i);
            int choiceMin = choice.getMin();
            if (choiceMin < maxMin) {
                continue;
            }
            choices.add(choice);
        }
        return choices;
    }
 
    private ArrayList<Choice> filterChoicesMaxOfMax(ArrayList<Choice> all) {
        int l = all.size();
        int maxMax = 0;
        for (int i = 0; i < l; ++i) {
            Choice choice = all.get(i);
            int choiceMax = choice.getMax();
            if (choiceMax > maxMax) {
                maxMax = choiceMax;
            }
        }
 
        ArrayList<Choice> choices = new ArrayList<>(l);
        for (int i = 0; i < l; ++i) {
            Choice choice = all.get(i);
            int choiceMax = choice.getMax();
            if (choiceMax < maxMax) {
                continue;
            }
            choices.add(choice);
        }
        return choices;
    }
 
    private Choice getNextChoice() {
        ArrayList<Choice> choices = new ArrayList<>(N);
        for (int i = 0; i < N; ++i) {
            if (state[i]) {
                continue;
            }
 
            choices.add(new Choice(i, getLeftDistance(i), getRightDistance(i)));
        }
 
        choices = filterChoicesMaxOfMin(choices);
        choices = filterChoicesMaxOfMax(choices);
 
        return choices.get(0);
    }
 
    @Override
    public void solve() {
        state = new boolean[N];
        for (int i = 0; i < N; ++i) {
            state[i] = false;
        }
 
        for (int i = 0; i < K; ++i) {
            lastChoice = getNextChoice();
            state[lastChoice.getI()] = true;
        }
    }
 
    @Override
    public String getSolution() {
        return lastChoice.getMax() + " " + lastChoice.getMin();
    }
 
    private static class Choice {
 
        private final int i;
        private final int left;
        private final int right;
 
        public Choice(int i, int left, int right) {
            this.i = i;
            this.left = left;
            this.right = right;
        }
 
        public int getI() {
            return i;
        }
 
        public int getMin() {
            return Math.min(left, right);
        }
 
        public int getMax() {
            return Math.max(left, right);
        }
 
    }
 
 }
