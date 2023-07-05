import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.util.NoSuchElementException;
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
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.print("Case #" + testNumber + ": ");
             int count = in.readInt();
             int[] friend = IOUtils.readIntArray(in, count);
             MiscUtils.decreaseByOne(friend);
 
             int answer = 0;
             for (int mask = 0; mask < (1 << count); mask++) {
                 List<Integer> choose = new ArrayList<Integer>();
                 for (int bit = 0; bit < count; bit++) {
                     if (BitUtils.isContain(mask, bit)) {
                         choose.add(bit);
                     }
                 }
 
                 int[] A = new int[choose.size()];
                 for (int i = 0; i < choose.size(); i++) {
                     A[i] = choose.get(i);
                 }
 
                 if (A.length > 0 && isValid(A, friend)) {
                     answer = Math.max(answer, A.length);
                 }
             }
 
             out.printLine(answer);
         }
 
         private boolean isValid(int[] A, int[] friend) {
             boolean isOk = false;
             int count = A.length;
             do {
                 boolean curCheck = true;
                 for (int i = 0; i < count; i++) {
                     int left = (i - 1 + count) % count;
                     int right = (i + 1 + count) % count;
 
                     if (friend[A[i]] != A[left] && friend[A[i]] != A[right]) {
                         curCheck = false;
                     }
                 }
 
                 isOk |= curCheck;
             } while (ArrayUtils.nextPermutation(A));
 
             return isOk;
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
                 if (i != 0)
                     writer.print(' ');
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
 
     static interface IntIterator {
         public int value() throws NoSuchElementException;
 
         public void advance() throws NoSuchElementException;
 
         public boolean isValid();
 
     }
 
     static class BitUtils {
         public static boolean isContain(long mask, int bit) {
             return (mask & ((long) 1 << bit)) != 0;
         }
 
     }
 
     static class IOUtils {
         public static int[] readIntArray(InputReader in, int size) {
             int[] array = new int[size];
             for (int i = 0; i < size; i++)
                 array[i] = in.readInt();
             return array;
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
 
     static class IntArray extends IntList {
         private final int[] array;
 
         public IntArray(int[] array) {
             this.array = array;
         }
 
         public IntArray(IntCollection collection) {
             array = new int[collection.size()];
             int i = 0;
             for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
                 array[i++] = iterator.value();
         }
 
 
         public int get(int index) {
             return array[index];
         }
 
 
         public void set(int index, int value) {
             array[index] = value;
         }
 
 
         public int size() {
             return array.length;
         }
 
     }
 
     static abstract class IntList extends IntCollection implements Comparable<IntList> {
         public abstract int get(int index);
 
         public abstract void set(int index, int value);
 
 
         public IntIterator iterator() {
             return new IntIterator() {
                 private int size = size();
                 private int index = 0;
 
                 public int value() throws NoSuchElementException {
                     if (!isValid())
                         throw new NoSuchElementException();
                     return get(index);
                 }
 
                 public void advance() throws NoSuchElementException {
                     if (!isValid())
                         throw new NoSuchElementException();
                     index++;
                 }
 
                 public boolean isValid() {
                     return index < size;
                 }
             };
         }
 
         public boolean nextPermutation() {
             return nextPermutation(IntComparator.DEFAULT);
         }
 
         private boolean nextPermutation(IntComparator comparator) {
             int size = size();
             int last = get(size - 1);
             for (int i = size - 2; i >= 0; i--) {
                 int current = get(i);
                 if (comparator.compare(last, current) > 0) {
                     for (int j = size - 1; j > i; j--) {
                         if (comparator.compare(get(j), current) > 0) {
                             swap(i, j);
                             subList(i + 1, size).inPlaceReverse();
                             return true;
                         }
                     }
                 }
                 last = current;
             }
             return false;
         }
 
         public void inPlaceReverse() {
             for (int i = 0, j = size() - 1; i < j; i++, j--)
                 swap(i, j);
         }
 
         public IntList subList(final int from, final int to) {
             return new SubList(from, to);
         }
 
         private void swap(int first, int second) {
             if (first == second)
                 return;
             int temp = get(first);
             set(first, get(second));
             set(second, temp);
         }
 
         public int hashCode() {
             int hashCode = 1;
             for (IntIterator i = iterator(); i.isValid(); i.advance())
                 hashCode = 31 * hashCode + i.value();
             return hashCode;
         }
 
 
         public boolean equals(Object obj) {
             if (!(obj instanceof IntList))
                 return false;
             IntList list = (IntList) obj;
             if (list.size() != size())
                 return false;
             IntIterator i = iterator();
             IntIterator j = list.iterator();
             while (i.isValid()) {
                 if (i.value() != j.value())
                     return false;
                 i.advance();
                 j.advance();
             }
             return true;
         }
 
         public int compareTo(IntList o) {
             IntIterator i = iterator();
             IntIterator j = o.iterator();
             while (true) {
                 if (i.isValid()) {
                     if (j.isValid()) {
                         if (i.value() != j.value()) {
                             if (i.value() < j.value())
                                 return -1;
                             else
                                 return 1;
                         }
                     } else
                         return 1;
                 } else {
                     if (j.isValid())
                         return -1;
                     else
                         return 0;
                 }
                 i.advance();
                 j.advance();
             }
         }
 
         private class SubList extends IntList {
             private final int to;
             private final int from;
             private int size;
 
             public SubList(int from, int to) {
                 this.to = to;
                 this.from = from;
                 size = to - from;
             }
 
 
             public int get(int index) {
                 if (index < 0 || index >= size)
                     throw new IndexOutOfBoundsException();
                 return IntList.this.get(index + from);
             }
 
 
             public void set(int index, int value) {
                 if (index < 0 || index >= size)
                     throw new IndexOutOfBoundsException();
                 IntList.this.set(index + from, value);
             }
 
 
             public int size() {
                 return size;
             }
 
         }
 
     }
 
     static class ArrayUtils {
         public static boolean nextPermutation(int[] array) {
             return new IntArray(array).nextPermutation();
         }
 
     }
 
     static abstract class IntCollection {
         public abstract IntIterator iterator();
 
         public abstract int size();
 
     }
 
     static interface IntComparator {
         public static final IntComparator DEFAULT = new IntComparator() {
             public int compare(int first, int second) {
                 if (first < second)
                     return -1;
                 if (first > second)
                     return 1;
                 return 0;
             }
         };
 
         public int compare(int first, int second);
 
     }
 
     static class MiscUtils {
         public static void decreaseByOne(int[]... arrays) {
             for (int[] array : arrays) {
                 for (int i = 0; i < array.length; i++)
                     array[i]--;
             }
         }
 
     }
 }
 
