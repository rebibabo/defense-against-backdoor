import java.io.File;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Scanner;
 import java.util.Set;
 
 public class C {
 
    private static long findMinInCommon(int pos, String[] sentences,
            Set<String> english, Set<String> franch, Set<String> common) {
        if (pos == sentences.length) {
            return common.size();
        }
        String[] words = sentences[pos].split(" ");
 
        
        Set<String> added = new HashSet<String>();
 
        for (String word : words) {
            if (!english.contains(word)) {
                english.add(word);
                added.add(word);
                if (franch.contains(word)) {
                    common.add(word);
                }
            }
        }
 
        long min1 = findMinInCommon(pos + 1, sentences, english, franch, common);
 
        english.removeAll(added);
        common.removeAll(added);
 
        
        added.clear();
        for (String word : words) {
            if (!franch.contains(word)) {
                franch.add(word);
                added.add(word);
                if (english.contains(word)) {
                    common.add(word);
                }
            }
        }
 
        long min2 = findMinInCommon(pos + 1, sentences, english, franch, common);
 
        franch.removeAll(added);
        common.removeAll(added);
 
        return Math.min(min1, min2);
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
 
            in.nextLine();
 
            Set<String> english = new HashSet<String>(), franch = new HashSet<String>(), common = new HashSet<String>();
 
            String sencence = in.nextLine();
            for (String word : sencence.split(" ")) {
                english.add(word);
            }
 
            sencence = in.nextLine();
            for (String word : sencence.split(" ")) {
                franch.add(word);
                if (english.contains(word)) {
                    common.add(word);
                }
            }
 
            String[] sentences = new String[n - 2];
 
            for (int i = 0; i < n - 2; i++) {
                sentences[i] = in.nextLine();
            }
 
            out.println("Case #" + test + ": "
                    + findMinInCommon(0, sentences, english, franch, common));
 
        }
 
        in.close();
        out.close();
    }
 
 }
