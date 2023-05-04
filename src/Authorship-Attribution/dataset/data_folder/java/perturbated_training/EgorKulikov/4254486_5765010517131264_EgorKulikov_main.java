import java.io.BufferedWriter;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.util.Arrays;
 import java.io.InputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStreamWriter;
 import java.io.PrintStream;
 import java.util.Comparator;
 import java.io.FileOutputStream;
 import java.io.File;
 
 
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
        solver.solve(1, in, out);
        out.close();
    }
 }
 
 class TaskC {
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
             int mySpeed;
             int count;
             int[] position;
             int[] speed;
 
             public void read(InputReader in) {
                 mySpeed = in.readInt();
                 count = in.readInt();
                 position = IOUtils.readIntArray(in, count);
                 speed = IOUtils.readIntArray(in, count);
             }
 
             int start;
             double[][] answerLeft;
             double[][] positionLeft;
             double[][] answerRight;
             double[][] positionRight;
             double answer;
 
             public void solve() {
                 for (int i = 0; i < count; i++) {
                     if (position[i] < 0) {
                         speed[i] *= -1;
                     }
                 }
                 position = Arrays.copyOf(position, count + 1);
                 speed = Arrays.copyOf(speed, count + 1);
                 int[] order = ArrayUtils.createOrder(count + 1);
                 ArrayUtils.sort(order, (f, s) -> {
                     int key = Integer.signum(speed[f]) - Integer.signum(speed[s]);
                     if (key != 0) {
                         return key;
                     }
                     return speed[s] - speed[f];
                 });
                 ArrayUtils.orderBy(ArrayUtils.reversePermutation(order), position, speed);
                 start = ArrayUtils.find(position, 0);
                 answerLeft = new double[count + 1][count + 1];
                 ArrayUtils.fill(answerLeft, -1);
                 positionLeft = new double[count + 1][count + 1];
                 answerRight = new double[count + 1][count + 1];
                 ArrayUtils.fill(answerRight, -1);
                 positionRight = new double[count + 1][count + 1];
                 answerLeft[start][start] = 0;
                 answerRight[start][start] = 0;
                 answer = Math.min(goLeft(0, count), goRight(0, count));
                 answerLeft = null;
                 positionLeft = null;
                 answerRight = null;
                 positionRight = null;
             }
 
             private double goLeft(int left, int right) {
                 if (answerLeft[left][right] != -1) {
                     return answerLeft[left][right];
                 }
                 if (left >= start || right < start) {
                     return answerLeft[left][right] = Double.POSITIVE_INFINITY;
                 }
                 double ifLeft = goLeft(left + 1, right);
                 double ifLeftPosition = 0;
                 if (!Double.isInfinite(ifLeft)) {
                     double curPosition = positionLeft[left + 1][right];
                     double quailPosition = position[left] + speed[left] * ifLeft;
                     if (quailPosition > curPosition) {
                         ifLeftPosition = curPosition;
                     } else {
                         double addTime = (curPosition - quailPosition) / (mySpeed + speed[left]);
                         ifLeft += addTime;
                         ifLeftPosition = curPosition - addTime * mySpeed;
                     }
                 }
                 double ifRight = goRight(left + 1, right);
                 double ifRightPosition = 0;
                 if (!Double.isInfinite(ifRight)) {
                     double curPosition = positionRight[left + 1][right];
                     double quailPosition = position[left] + speed[left] * ifRight;
                     double addTime = (curPosition - quailPosition) / (mySpeed + speed[left]);
                     ifRight += addTime;
                     ifRightPosition = curPosition - addTime * mySpeed;
                 }
                 if (ifLeft < ifRight) {
                     answerLeft[left][right] = ifLeft;
                     positionLeft[left][right] = ifLeftPosition;
                 } else {
                     answerLeft[left][right] = ifRight;
                     positionLeft[left][right] = ifRightPosition;
                 }
                 return answerLeft[left][right];
             }
 
             private double goRight(int left, int right) {
                 if (answerRight[left][right] != -1) {
                     return answerRight[left][right];
                 }
                 if (left > start || right <= start) {
                     return answerLeft[left][right] = Double.POSITIVE_INFINITY;
                 }
                 double ifLeft = goLeft(left, right - 1);
                 double ifLeftPosition = 0;
                 if (!Double.isInfinite(ifLeft)) {
                     double curPosition = positionLeft[left][right - 1];
                     double quailPosition = position[right] + speed[right] * ifLeft;
                     double addTime = (quailPosition - curPosition) / (mySpeed - speed[right]);
                     ifLeft += addTime;
                     ifLeftPosition = curPosition + addTime * mySpeed;
                 }
                 double ifRight = goRight(left, right - 1);
                 double ifRightPosition = 0;
                 if (!Double.isInfinite(ifRight)) {
                     double curPosition = positionRight[left][right - 1];
                     double quailPosition = position[right] + speed[right] * ifRight;
                     if (quailPosition < curPosition) {
                         ifRightPosition = curPosition;
                     } else {
                         double addTime = (quailPosition - curPosition) / (mySpeed - speed[right]);
                         ifRight += addTime;
                         ifRightPosition = curPosition + addTime * mySpeed;
                     }
                 }
                 if (ifLeft < ifRight) {
                     answerRight[left][right] = ifLeft;
                     positionRight[left][right] = ifLeftPosition;
                 } else {
                     answerRight[left][right] = ifRight;
                     positionRight[left][right] = ifRightPosition;
                 }
                 return answerRight[left][right];
             }
 
             public void write(OutputWriter out, int testNumber) {
                 out.printLine("Case #" + testNumber + ":", answer);
             }
         }, 4);
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
 
    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }
 
    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
 
 class Scheduler {
    private final AtomicInteger testsRemaining;
    private final AtomicInteger threadsRemaining;
 
    public Scheduler(InputReader in, OutputWriter out, TaskFactory factory, int numParallel) {
        try {
            testsRemaining = new AtomicInteger(in.readInt());
            threadsRemaining = new AtomicInteger(numParallel);
            Task[] tasks = new Task[testsRemaining.get()];
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = factory.newTask();
            }
            for (Task task : tasks) {
                task.read(in);
                new Thread(() -> {
                    boolean freeThread = false;
                    synchronized (this) {
                        do {
                            try {
                                wait(10);
                            } catch (InterruptedException ignored) {
                            }
                            if (threadsRemaining.get() != 0) {
                                synchronized (threadsRemaining) {
                                    if (threadsRemaining.get() != 0) {
                                        threadsRemaining.decrementAndGet();
                                        freeThread = true;
                                    }
                                }
                            }
                        } while (!freeThread);
                    }
                    task.solve();
                    System.err.println(testsRemaining.decrementAndGet());
                    threadsRemaining.incrementAndGet();
                }).start();
            }
            synchronized (this) {
                while (testsRemaining.get() > 0) {
                    wait(10);
                }
            }
            for (int i = 0; i < tasks.length; i++) {
                tasks[i].write(out, i + 1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
 }
 
 interface Task {
    public void read(InputReader in);
    public void solve();
    public void write(OutputWriter out, int testNumber);
 }
 
 class IOUtils {
 
    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }
 
 }
 
 class ArrayUtils {
 
    public static void fill(double[][] array, double value) {
        for (double[] row : array)
            Arrays.fill(row, value);
    }
 
    public static int[] createOrder(int size) {
        int[] order = new int[size];
        for (int i = 0; i < size; i++)
            order[i] = i;
        return order;
    }
 
    public static int[] sort(int[] array, IntComparator comparator) {
        return sort(array, 0, array.length, comparator);
    }
 
    public static int[] sort(int[] array, int from, int to, IntComparator comparator) {
        if (from == 0 && to == array.length)
            new IntArray(array).inPlaceSort(comparator);
        else
            new IntArray(array).subList(from, to).inPlaceSort(comparator);
        return array;
    }
 
    public static int[] order(final int[] array) {
        return sort(createOrder(array.length), new IntComparator() {
            public int compare(int first, int second) {
                if (array[first] < array[second])
                    return -1;
                if (array[first] > array[second])
                    return 1;
                return 0;
            }
        });
    }
 
    public static int[] reversePermutation(int[] permutation) {
        int[] result = new int[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }
 
    public static void orderBy(int[] base, int[]... arrays) {
        int[] order = ArrayUtils.order(base);
        order(order, base);
        for (int[] array : arrays)
            order(order, array);
    }
 
    public static void order(int[] order, int[] array) {
 int[] tempInt = new int[order.length];
        for (int i = 0; i < order.length; i++)
            tempInt[i] = array[order[i]];
        System.arraycopy(tempInt, 0, array, 0, array.length);
    }
 
    public static int find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
 }
 
 interface IntComparator {
 
    public int compare(int first, int second);
 }
 
 abstract class IntCollection {
    public abstract IntIterator iterator();
    public abstract int size();
 
 }
 
 interface IntIterator {
    public int value() throws NoSuchElementException;
    public void advance() throws NoSuchElementException;
    public boolean isValid();
 }
 
 interface TaskFactory {
    public Task newTask();
 }
 
 abstract class IntList extends IntCollection implements Comparable<IntList> {
    private static final int INSERTION_THRESHOLD = 16;
 
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
 
    public IntSortedList inPlaceSort(IntComparator comparator) {
        quickSort(0, size() - 1, (Integer.bitCount(Integer.highestOneBit(size()) - 1) * 5) >> 1, comparator);
        return new IntSortedArray(this, comparator);
    }
 
    private void quickSort(int from, int to, int remaining, IntComparator comparator) {
        if (to - from < INSERTION_THRESHOLD) {
            insertionSort(from, to, comparator);
            return;
        }
        if (remaining == 0) {
            heapSort(from, to, comparator);
            return;
        }
        remaining--;
        int pivotIndex = (from + to) >> 1;
        int pivot = get(pivotIndex);
        swap(pivotIndex, to);
        int storeIndex = from;
        int equalIndex = to;
        for (int i = from; i < equalIndex; i++) {
            int value = comparator.compare(get(i), pivot);
            if (value < 0)
                swap(storeIndex++, i);
            else if (value == 0)
                swap(--equalIndex, i--);
        }
        quickSort(from, storeIndex - 1, remaining, comparator);
        for (int i = equalIndex; i <= to; i++)
            swap(storeIndex++, i);
        quickSort(storeIndex, to, remaining, comparator);
    }
 
    private void heapSort(int from, int to, IntComparator comparator) {
        for (int i = (to + from - 1) >> 1; i >= from; i--)
            siftDown(i, to, comparator, from);
        for (int i = to; i > from; i--) {
            swap(from, i);
            siftDown(from, i - 1, comparator, from);
        }
    }
 
    private void siftDown(int start, int end, IntComparator comparator, int delta) {
        int value = get(start);
        while (true) {
            int child = ((start - delta) << 1) + 1 + delta;
            if (child > end)
                return;
            int childValue = get(child);
            if (child + 1 <= end) {
                int otherValue = get(child + 1);
                if (comparator.compare(otherValue, childValue) > 0) {
                    child++;
                    childValue = otherValue;
                }
            }
            if (comparator.compare(value, childValue) >= 0)
                return;
            swap(start, child);
            start = child;
        }
    }
 
    private void insertionSort(int from, int to, IntComparator comparator) {
        for (int i = from + 1; i <= to; i++) {
            int value = get(i);
            for (int j = i - 1; j >= from; j--) {
                if (comparator.compare(get(j), value) <= 0)
                    break;
                swap(j, j + 1);
            }
        }
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
        IntList list = (IntList)obj;
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
 
 abstract class IntSortedList extends IntList {
    protected final IntComparator comparator;
 
    protected IntSortedList(IntComparator comparator) {
        this.comparator = comparator;
    }
 
    public void set(int index, int value) {
        throw new UnsupportedOperationException();
    }
 
    public IntSortedList inPlaceSort(IntComparator comparator) {
        if (comparator == this.comparator)
            return this;
        throw new UnsupportedOperationException();
    }
 
    protected void ensureSorted() {
        int size = size();
        if (size == 0)
            return;
        int last = get(0);
        for (int i = 1; i < size; i++) {
            int current = get(i);
            if (comparator.compare(last, current) > 0)
                throw new IllegalArgumentException();
            last = current;
        }
    }
 
    public IntSortedList subList(final int from, final int to) {
        return new IntSortedList(comparator) {
            private int size = to - from;
 
            public int get(int index) {
                if (index < 0 || index >= size)
                    throw new IndexOutOfBoundsException();
                return IntSortedList.this.get(index + from);
            }
 
            public int size() {
                return size;
            }
        };
    }
 }
 
 class IntArray extends IntList {
    private final int[] array;
 
    public IntArray(int[] array) {
        this.array = array;
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
 
 class IntSortedArray extends IntSortedList {
    private final int[] array;
 
    public IntSortedArray(IntCollection collection, IntComparator comparator) {
        super(comparator);
        array = new int[collection.size()];
        int i = 0;
        for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance())
            array[i++] = iterator.value();
        ensureSorted();
    }
 
    public int get(int index) {
        return array[index];
    }
 
    public int size() {
        return array.length;
    }
 }
 
