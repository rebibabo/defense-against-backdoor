
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
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int N = getInt(in);
                BitSet[] sentences = new BitSet[N];
                Map<String,Integer> words = new HashMap<>();
                for (int i = 0 ; i < N ; i++) {
                    sentences[i] = new BitSet();
                    for (String word : in.readLine().split(" ")) {
                        Integer idx = words.get(word);
                        if (idx == null) {
                            idx = words.size();
                            words.put(word, idx);
                        }
                        sentences[i].set(idx);
                    }
                }
                int min = words.size();
                for (int l = 1 ; l < 1 << N ; l += 4) {
                    BitSet en = new BitSet();
                    BitSet fr = new BitSet();
                    for (int i = 0 ; i < N ; i++) {
                        if ((l >> i &1) == 0) {
                            en.or(sentences[i]);
                        } else {
                            fr.or(sentences[i]);
                        }
                    }
                    en.and(fr);
                    min = Math.min(min, en.cardinality());
                }
                out.printf("Case #%d: %d\n", CASE, min);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
