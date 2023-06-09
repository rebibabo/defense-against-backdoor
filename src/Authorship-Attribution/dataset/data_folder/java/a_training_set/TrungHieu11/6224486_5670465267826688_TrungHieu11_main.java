import java.io.IOException;
 import java.io.OutputStreamWriter;
 import java.io.BufferedWriter;
 import java.util.Locale;
 import java.util.InputMismatchException;
 import java.io.FileOutputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.FileInputStream;
 import java.io.File;
 import java.util.NoSuchElementException;
 import java.io.Writer;
 import java.math.BigInteger;
 import java.io.FilenameFilter;
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
     private int sign = 1;
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.print("Case #" + testNumber + ": ");
         int count = in.readInt();
         int times = in.readInt();
         String s = in.readString();
         StringBuilder builder = new StringBuilder();
         for (int i = 0; i < times; i++) {
             builder.append(s);
         }
         s = builder.toString();
 
         count = s.length();
         int[] letterI = new int[count];
         int[] letterK = new int[count];
 
         char prev = '?';
         sign = 1;
         for (int i = 0; i < count; i++) {
             if (prev == '?') {
                 prev = s.charAt(i);
             } else {
                 prev = combine(prev, s.charAt(i));
             }
             letterI[i] = sign * (prev == 'i' ? 1 : 0);
         }
 
         prev = '?';
         sign = 1;
         for (int i = count - 1; i >= 0; i--) {
             if (prev == '?') {
                 prev = s.charAt(i);
             } else {
                 prev = combine(s.charAt(i), prev);
             }
             letterK[i] = sign * (prev == 'k' ? 1 : 0);
         }
 
         boolean haveAnswer = false;
         for (int i = 0; i < count; i++) {
             if (letterI[i] > 0) {
                 prev = '?';
                 sign = 1;
                 for (int j = i + 1; j < count; j++) {
                     if (prev == '?') {
                         prev = s.charAt(j);
                     } else {
                         prev = combine(prev, s.charAt(j));
                     }
                     if (prev == 'j' && sign > 0 && j + 1 < count && letterK[j + 1] > 0) {
                         haveAnswer = true;
                         break;
                     }
                 }
                 if (haveAnswer) {
                     break;
                 }
             }
         }
 
         out.printLine(haveAnswer ? "YES" : "NO");
     }
 
     private char combine(char x, char y) {
         if (x == 'i') {
             if (y == 'i') {
                 sign *= -1;
                 return '?';
             }
             if (y == 'j') {
                 return 'k';
             }
             if (y == 'k') {
                 sign *= -1;
                 return 'j';
             }
         } else if (x == 'j') {
             if (y == 'i') {
                 sign *= -1;
                 return 'k';
             }
             if (y == 'j') {
                 sign *= -1;
                 return '?';
             }
             if (y == 'k') {
                 return 'i';
             }
         } else {
             if (y == 'i') {
                 return 'j';
             }
             if (y == 'j') {
                 sign *= -1;
                 return 'i';
             }
             if (y == 'k') {
                 sign *= -1;
                 return '?';
             }
         }
         return '?';
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
 
     public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }
 
     public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }
 
     public void close() {
        writer.close();
    }
 
 }
 
