
 import java.io.*;
 import java.util.*;
 
 public class B {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    int compute(char[] stack) {
        char target = '+';
        int res = 0;
        for (int i = stack.length - 1 ; i >= 0 ; i--) {
            if (stack[i] != target) {
                res++;
                target = (char)('+' + '-' - target);
            }
        }
        return res;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                char[] stack = in.readLine().toCharArray();
                out.printf("Case #%d: %d\n", t, compute(stack));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new B().run(args);
    }
 }
