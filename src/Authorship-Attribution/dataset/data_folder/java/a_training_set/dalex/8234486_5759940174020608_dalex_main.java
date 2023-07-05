import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.util.Collections;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.util.HashSet;
 import java.io.InputStream;
 import java.util.HashMap;
 import java.io.FilenameFilter;
 import java.util.Collection;
 import java.util.Locale;
 import java.io.OutputStreamWriter;
 import java.io.PrintStream;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.Set;
 
 
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
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskC solver = new TaskC();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskC {
     static HashMap<String, Integer> map = new HashMap<String, Integer>();
 
     static int getID(String s) {
         if (map.containsKey(s)) {
             return map.get(s);
         } else {
             int id = map.size();
             map.put(s, id);
             return id;
         }
     }
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         System.err.println("Test " + testNumber + " started");
         System.err.flush();
 
         map.clear();
 
         out.printFormat("Case #%d: ", testNumber);
         int n = in.readInt();
 
         Set<String> _englishSentence = new HashSet<String>();
         Collections.addAll(_englishSentence, in.readLine().split(" "));
         Set<String> _frenchSentence = new HashSet<String>();
         Collections.addAll(_frenchSentence, in.readLine().split(" "));
 
         Set<Integer> englishSentence = toSet(_englishSentence);
         Set<Integer> frenchSentence = toSet(_frenchSentence);
 
         int globalBoth = 0;
         {
             Set<Integer> minSet, maxSet;
             if (englishSentence.size() < frenchSentence.size()) {
                 minSet = englishSentence;
                 maxSet = frenchSentence;
             } else {
                 minSet = frenchSentence;
                 maxSet = englishSentence;
             }
             for (int word : minSet) {
                 if (maxSet.contains(word)) {
                     globalBoth++;
                 }
             }
         }
 
         n -= 2;
         Set<Integer>[] a = new Set[n];
         for (int i = 0; i < n; i++) {
             String[] sArr = in.readLine().split(" ");
             Set<String> set = new HashSet<String>();
             Collections.addAll(set, sArr);
             a[i] = toSet(set);
         }
 
         Set<Integer> englishWords = new HashSet<Integer>();
         Set<Integer> frenchWords = new HashSet<Integer>();
 
         int minAns = Integer.MAX_VALUE;
         for (int mask = 0; mask < (1 << n); mask++) {
             englishWords.clear();
             frenchWords.clear();
             for (int i = 0; i < n; i++) {
                 if ((mask & (1 << i)) == 0) {
                     for (int x : a[i]) englishWords.add(x);
                 } else {
                     for (int x : a[i]) frenchWords.add(x);
                 }
             }
 
             Set<Integer> minSet, maxSet;
             if (englishWords.size() < frenchWords.size()) {
                 minSet = englishWords;
                 maxSet = frenchWords;
             } else {
                 minSet = frenchWords;
                 maxSet = englishWords;
             }
 
             int both = 0;
             for (int word : minSet) {
                 if (englishSentence.contains(word) || frenchSentence.contains(word)) {
                     continue;
                 }
                 if (maxSet.contains(word)) {
                     both++;
                 }
             }
 
             int curAns = 0;
             for (int engWord : englishWords) {
                 if (!englishSentence.contains(engWord)) {
                     if (frenchSentence.contains(engWord) || frenchWords.contains(engWord)) {
                         curAns++;
                     }
                 }
             }
             for (int freWord : frenchWords) {
                 if (!frenchSentence.contains(freWord)) {
                     if (englishSentence.contains(freWord) || englishWords.contains(freWord)) {
                         curAns++;
                     }
                 }
             }
 
             minAns = Math.min(minAns, curAns - both + globalBoth);
         }
         out.printLine(minAns);
 
         System.err.println("Test " + testNumber + " completed");
         System.err.flush();
     }
 
     private Set<Integer> toSet(Set<String> strings) {
         Set<Integer> a = new HashSet<Integer>(strings.size());
         int n = 0;
         for (String s : strings) {
             a.add(getID(s));
         }
         return a;
     }
 }
 
 class InputReader {
 
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
 
    public InputReader(InputStream stream) {
        this.stream = stream;
    }
 
    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }
 
    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
 
    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            if (Character.isValidCodePoint(c))
                res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
 
    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }
 
    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    private String readLine0() {
        StringBuilder buf = new StringBuilder();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r')
                buf.appendCodePoint(c);
            c = read();
        }
        return buf.toString();
    }
 
    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0)
            s = readLine0();
        return s;
    }
 
    public String next() {
        return readString();
    }
 
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
 }
 
 class OutputWriter {
    private final PrintWriter writer;
 
    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }
 
    public void printFormat(String format, Object...objects) {
        writer.printf(format, objects);
    }
 
    public void close() {
        writer.close();
    }
 
    public void printLine(int i) {
        writer.println(i);
    }
 }
 
