import java.io.OutputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.util.Comparator;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         InputStream inputStream;
         try {
             inputStream = new FileInputStream("input.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("output.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskRatatouille solver = new TaskRatatouille();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskRatatouille {
         private int answer = 0;
 
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.print("Case #" + testNumber + ": ");
             int rowCount = in.readInt();
             int columnCount = in.readInt();
             long[] requires = IOUtils.readLongArray(in, rowCount);
             long[][] table = IOUtils.readLongTable(in, rowCount, columnCount);
 
             answer = 0;
 
             if (rowCount == 1) {
                 for (int j = 0; j < columnCount; j++) {
                     long[] curIngre = new long[rowCount];
                     for (int i = 0; i < rowCount; i++) {
                         curIngre[i] = table[i][j];
                         for (int k = 1; k <= 1000000; k++) {
                             if (possible(k, requires, curIngre)) {
                                 answer++;
                                 break;
                             }
                         }
                     }
                 }
             } else {
                 int[][] minTable = new int[rowCount][columnCount];
                 int[][] maxTable = new int[rowCount][columnCount];
 
                 ArrayUtils.fill(minTable, Integer.MAX_VALUE);
                 ArrayUtils.fill(maxTable, 0);
 
                 Node[][] nodeTable = new Node[rowCount][columnCount];
 
                 for (int i = 0; i < rowCount; i++) {
                     for (int j = 0; j < columnCount; j++) {
                         for (int k = 1; k <= 1000000; k++) {
                             if (requires[i] * k * 90 <= table[i][j] * 100 && requires[i] * k * 110 >= table[i][j] * 100) {
                                 minTable[i][j] = Math.min(minTable[i][j], k);
                                 maxTable[i][j] = Math.max(maxTable[i][j], k);
                             }
                         }
                         nodeTable[i][j] = new Node(minTable[i][j], maxTable[i][j]);
                     }
                 }
 
                 Arrays.sort(nodeTable[0], new Comparator<Node>() {
 
                     public int compare(Node o1, Node o2) {
                         if (o1.minVal == 0) {
                             return 1;
                         }
                         if (o2.minVal == 0) {
                             return -1;
                         }
 
                         if (o1.minVal == o2.minVal) {
                             return o1.maxVal - o2.maxVal;
                         }
 
                         return o1.minVal - o2.minVal;
                     }
                 });
 
                 Arrays.sort(nodeTable[1], new Comparator<Node>() {
 
                     public int compare(Node o1, Node o2) {
                         if (o1.minVal == 0) {
                             return 1;
                         }
                         if (o2.minVal == 0) {
                             return -1;
                         }
 
                         if (o1.minVal == o2.minVal) {
                             return o1.maxVal - o2.maxVal;
                         }
 
                         return o1.minVal - o2.minVal;
                     }
                 });
 
                 int x = 0;
                 int y = 0;
 
                 while (x < columnCount && y < columnCount) {
                     boolean isOk = false;
                     for (int i = nodeTable[0][x].minVal; i <= nodeTable[0][x].maxVal; i++) {
                         for (int j = nodeTable[1][y].minVal; j <= nodeTable[1][y].maxVal; j++) {
                             if (i == j) {
                                 isOk = true;
                                 break;
                             }
                         }
                         if (isOk) {
                             break;
                         }
                     }
 
                     if (isOk) {
                         x++;
                         y++;
                         answer++;
                     } else {
                         if (nodeTable[0][x].maxVal < nodeTable[1][y].minVal) {
                             x++;
                         } else {
                             y++;
                         }
                     }
                 }
 
 
             }
 
             out.printLine(answer);
         }
 
         private boolean possible(int cur, long[] requires, long[] curIngre) {
             for (int i = 0; i < requires.length; i++) {
                 if (!(curIngre[i] * 100 >= requires[i] * cur * 90 && curIngre[i] * 100 <= requires[i] * cur * 110)) {
                     return false;
                 }
             }
             return true;
         }
 
         private class Node {
             int minVal;
             int maxVal;
 
             public Node(int minVal, int maxVal) {
                 this.minVal = minVal;
                 this.maxVal = maxVal;
             }
 
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private InputReader.SpaceCharFilter filter;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (numChars == -1) {
                 throw new InputMismatchException();
             }
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0) {
                     return -1;
                 }
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public long readLong() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             long res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c)) {
                     res.appendCodePoint(c);
                 }
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
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
 
     static class ArrayUtils {
         public static void fill(int[][] array, int value) {
             for (int[] row : array) {
                 Arrays.fill(row, value);
             }
         }
 
     }
 
     static class OutputWriter {
         private final PrintWriter writer;
 
         public OutputWriter(OutputStream outputStream) {
             writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
         }
 
         public OutputWriter(Writer writer) {
             this.writer = new PrintWriter(writer);
         }
 
         public void print(Object... objects) {
             for (int i = 0; i < objects.length; i++) {
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(objects[i]);
             }
         }
 
         public void close() {
             writer.close();
         }
 
         public void printLine(int i) {
             writer.println(i);
         }
 
     }
 
     static class IOUtils {
         public static long[] readLongArray(InputReader in, int size) {
             long[] array = new long[size];
             for (int i = 0; i < size; i++) {
                 array[i] = in.readLong();
             }
             return array;
         }
 
         public static long[][] readLongTable(InputReader in, int rowCount, int columnCount) {
             long[][] table = new long[rowCount][];
             for (int i = 0; i < rowCount; i++) {
                 table[i] = readLongArray(in, columnCount);
             }
             return table;
         }
 
     }
 }
 
