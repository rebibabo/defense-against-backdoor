
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
 
    boolean duplicates(int[] coins) {
        for (int i = 0 ; i < coins.length ; i++) {
            for (int j = i + 1 ; j < coins.length ; j++) {
                if (coins[i] == coins[j]) return true;
            }
        }
        return false;
    }
 
    void inc(int[] coins, int from) {
        for (int i = from ; i + 1 < coins.length ; i++) {
            if (coins[i] + 1 == coins[i + 1]) coins[i] = i == from ? 1 : coins[i - 1] + 1;
            else {
                coins[i]++;
                return;
            }
        }
        coins[coins.length-1]++;
        return;
    }
 
    int go(int C, int V, int[] given, int min) {
        int G = given.length;
        int[] coins = Arrays.copyOf(given, G + min);
        for (int i = 0 ; i < min ; i++) {
            coins[G + i] = i + 1;
        }
        while (duplicates(coins)) inc(coins, G);
        while (coins[G + min - 1] <= V) {
            boolean[] pay = new boolean[V+1];
            pay[0] = true;
            for (int c : coins) for (int v = V ; v >= c ; v--) pay[v] |= pay[v-c];
            boolean payable = true;
            for (int b = 0 ; b <= V ; b++) if (!pay[b]) {
                payable = false;
                break;
            }
            if (payable) {
                return min;
            }
            do { inc(coins, G); } while (duplicates(coins));
        }
        return go(C, V, given, min + 1);
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int[] input = getInts(in);
                int C = input[0];
                int D = input[1];
                int V = input[2];
                int[] coins = getInts(in);
                out.printf("Case #%d: %d\n", CASE, go(C, V, coins, 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
