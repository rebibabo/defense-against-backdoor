import java.util.Scanner;
 import java.util.List;
 import java.util.BitSet;
 import java.io.IOException;
 import java.util.Locale;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.io.OutputStream;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.FilenameFilter;
 import java.util.Collections;
 import java.io.InputStream;
 
 
 public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "C-(small|large).*[.]in";
            File directory = new File(".");
            File[] candidates = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches(regex);
                }
            });
            File toRun = null;
            for (File candidate : candidates) {
                if (toRun == null || candidate.lastModified() > toRun.lastModified())
                    toRun = candidate;
            }
            inputStream = new FileInputStream(toRun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("c.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskC {
     public void solve(int testNumber, Scanner in, PrintWriter out) {
         int n = in.nextInt();
         in.nextLine();
         HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
         ArrayList<ArrayList<Integer>> words = new ArrayList<ArrayList<Integer>>();
         for ( int i = 0; i < n; i++ ) {
             String s = in.nextLine();
             Scanner scan = new Scanner(s);
             ArrayList<Integer> indices = new ArrayList<Integer>();
             while ( scan.hasNext() ) {
                 String word = scan.next();
                 if ( !wordMap.containsKey(word)) wordMap.put(word, wordMap.size());
                 indices.add(wordMap.get(word));
             }
             Collections.sort(indices);
             words.add(indices);
         }
         words.add(new ArrayList<Integer>());
         int m = n - 1;
         BitSet[] data = new BitSet[words.size()];
         for ( int i = 0; i < words.size(); i++) {
             data[i] = new BitSet(wordMap.size());
             for ( int a: words.get(i)) data[i].set(a);
         }
         BitSet french = new BitSet(words.size());
         BitSet english = new BitSet(words.size());
         int result = wordMap.size() + 10;
         for (int mask = 0; mask < 1 << m; mask++) {
             french.clear();
             english.clear();
             french.or(data[1]);
             english.or(data[0]);
             for (int i = 0; i < m; i++) {
                 if ((mask & (1 << i)) == 0) {
                     english.or(data[i+2]);
                 } else {
                     french.or(data[i+2]);
                 }
             }
             english.and(french);
             result = Math.min(result, english.cardinality());
         }
         out.printf("Case #%d: %d\n", testNumber, result);
     }
 }
 
