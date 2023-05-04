
 import java.io.*;
 import java.util.*;
 
 public class A {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    int rev(int n) {
        int r = 0;
        while (n > 0) {
            r = 10 * r + (n % 10);
            n /= 10;
        }
        return r;
    }
 
    int eval(int N) {
        int[] from = new int[N+1];
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.offer(1);
        while (!q.isEmpty()) {
            int i = q.poll();
            int inc = i+1;
            if (inc <= N && from[inc] == 0) {
                from[inc] = i;
                q.offer(inc);
            }
            int rev = rev(i);
            if (rev <= N && from[rev] == 0) {
                from[rev] = i;
                q.offer(rev);
            }
        }
        int count = 0;
        for (int i = N ; i > 1 ; i = from[i]) {
            count++;
        }
        return count + 1;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int N = getInt(in);
                out.printf("Case #%d: %d\n", CASE, eval(N));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new A().run(args);
    }
 }
