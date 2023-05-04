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
 import java.io.InputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStreamWriter;
 import java.io.PrintStream;
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
     static class Quaternion {
         int type;
         int sign;
 
         public Quaternion(int type, int sign) {
             this.type = type;
             this.sign = sign;
         }
 
         Quaternion multiply(Quaternion arg) {
             if (type == 0) {
                 return new Quaternion(arg.type, sign * arg.sign);
             }
             if (arg.type == 0) {
                 return new Quaternion(type, sign * arg.sign);
             }
             if (type == arg.type) {
                 return new Quaternion(0, -sign * arg.sign);
             }
             if (arg.type == type + 1 || type == 3 && arg.type == 1) {
                 return new Quaternion(6 - type - arg.type, sign * arg.sign);
             }
             return new Quaternion(6 - type - arg.type, -sign * arg.sign);
         }
     }
 
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
             int length;
             char[] string;
             long times;
             boolean answer;
 
             public void read(InputReader in) {
                 length = in.readInt();
                 times = in.readLong();
                 string = IOUtils.readCharArray(in, length);
             }
 
             public void solve() {
                 Quaternion value = new Quaternion(0, 1);
                 for (char c : string) {
                     value = value.multiply(new Quaternion(c - 'i' + 1, 1));
                 }
                 Quaternion total = new Quaternion(0, 1);
                 for (int i = 0; i < (times & 3); i++) {
                     total = total.multiply(value);
                 }
                 if (total.type != 0 || total.sign != -1) {
                     answer = false;
                     return;
                 }
                 long firstI = Long.MAX_VALUE / 2;
                 Quaternion current = new Quaternion(0, 1);
                 for (int i = 0; i < length; i++) {
                     current = current.multiply(new Quaternion(string[i] - 'i' + 1, 1));
                     Quaternion shifted = current;
                     for (int j = 0; j < 4; j++) {
                         if (shifted.type == 1 && shifted.sign == 1) {
                             firstI = Math.min(firstI, i + j * length + 1);
                         }
                         shifted = value.multiply(shifted);
                     }
                 }
                 long lastK = Long.MAX_VALUE / 2;
                 current = new Quaternion(0, 1);
                 for (int i = length - 1; i >= 0; i--) {
                     current = new Quaternion(string[i] - 'i' + 1, 1).multiply(current);
                     Quaternion shifted = current;
                     for (int j = 0; j < 4; j++) {
                         if (shifted.type == 3 && shifted.sign == 1) {
                             lastK = Math.min(lastK, length - i + length * j);
                         }
                         shifted = shifted.multiply(value);
                     }
                 }
                 answer = firstI + lastK < times * length;
             }
 
             public void write(OutputWriter out, int testNumber) {
                 out.printLine("Case #" + testNumber + ":", answer ? "YES" : "NO");
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
 
    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
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
 
    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
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
 
    public static char[] readCharArray(InputReader in, int size) {
        char[] array = new char[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readCharacter();
        return array;
    }
 
 }
 
 interface TaskFactory {
    public Task newTask();
 }
 
