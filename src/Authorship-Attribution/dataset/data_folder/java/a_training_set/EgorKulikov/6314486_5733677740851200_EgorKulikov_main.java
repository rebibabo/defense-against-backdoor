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
 import java.io.PrintStream;
 import java.io.BufferedWriter;
 import java.util.InputMismatchException;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.concurrent.atomic.AtomicInteger;
 import java.io.Writer;
 import java.io.OutputStreamWriter;
 import java.math.BigInteger;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "B-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified()) {
                     toRun = candidate;
                 }
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("b.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskB solver = new TaskB();
         solver.solve(1, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             Scheduler scheduler = new Scheduler(in, out, () -> new Task() {
                 int s;
                 int c;
                 TaskB.Operation[] operations;
 
 
                 public void read(InputReader in) {
                     s = in.readInt();
                     c = in.readInt();
                     operations = new TaskB.Operation[c];
                     for (int i = 0; i < c; i++) {
                         char type = in.readCharacter();
                         int value = in.readInt();
                         if (type == '+' && value < 0) {
                             type = '-';
                             value = -value;
                         }
                         if (type == '-' && value < 0) {
                             type = '+';
                             value = -value;
                         }
                         if (type == '/' && Math.abs(value) == 1) {
                             type = '*';
                         }
                         operations[i] = new TaskB.Operation(type, BigInteger.valueOf(value));
                     }
                 }
 
                 TaskB.BigRational answer = null;
 
 
                 public void solve() {
                     TaskB.Operation plus = new TaskB.Operation('+', BigInteger.ZERO);
                     TaskB.Operation minus = new TaskB.Operation('-', BigInteger.ZERO);
                     TaskB.Operation multiply = new TaskB.Operation('*', BigInteger.ONE);
                     TaskB.Operation minNegMult = null;
                     TaskB.Operation zeroMult = null;
                     TaskB.Operation divide = new TaskB.Operation('/', BigInteger.ONE);
                     TaskB.Operation minNegDiv = null;
                     for (TaskB.Operation operation : operations) {
                         if (operation.operation == '+') {
                             plus = new TaskB.Operation('+', plus.value.add(operation.value));
                         } else if (operation.operation == '-') {
                             minus = new TaskB.Operation('-', minus.value.add(operation.value));
                         } else if (operation.operation == '*') {
                             if (operation.value.signum() == 0) {
                                 zeroMult = operation;
                             } else {
                                 if (operation.value.signum() < 0 && (minNegMult == null || operation.value.compareTo
                                         (minNegMult.value) > 0)) {
                                     if (minNegMult != null) {
                                         multiply = new TaskB.Operation('*', multiply.value.multiply(minNegMult.value));
                                     }
                                     minNegMult = operation;
                                     continue;
                                 }
                                 multiply = new TaskB.Operation('*', multiply.value.multiply(operation.value));
                             }
                         } else {
                             if (operation.value.signum() < 0 && (minNegDiv == null || operation.value.compareTo
                                     (minNegDiv.value) > 0)) {
                                 if (minNegDiv != null) {
                                     divide = new TaskB.Operation('/', divide.value.multiply(minNegDiv.value));
                                 }
                                 minNegDiv = operation;
                                 continue;
                             }
                             divide = new TaskB.Operation('/', divide.value.multiply(operation.value));
                         }
                     }
                     List<TaskB.Operation> operations = new ArrayList<>();
                     operations.add(plus);
                     operations.add(minus);
                     operations.add(multiply);
                     operations.add(divide);
                     if (minNegMult != null) {
                         operations.add(minNegMult);
                     }
                     if (minNegDiv != null) {
                         operations.add(minNegDiv);
                     }
                     if (zeroMult != null) {
                         operations.add(zeroMult);
                     }
                     solve(operations, 0, 0, new TaskB.BigRational(BigInteger.valueOf(s), BigInteger.ONE));
                     answer.canonize();
                 }
 
                 private void solve(List<TaskB.Operation> operations, int step, int used, TaskB.BigRational current) {
                     if (step == operations.size()) {
                         if (answer == null) {
                             answer = current;
                         } else {
                             answer.max(current);
                         }
                         return;
                     }
                     for (int i = 0; i < operations.size(); i++) {
                         if ((used >> i & 1) == 1) {
                             continue;
                         }
                         TaskB.BigRational copy = new TaskB.BigRational(current.numerator, current.denominator);
                         copy.apply(operations.get(i));
                         solve(operations, step + 1, used + (1 << i), copy);
                     }
                 }
 
 
                 public void write(OutputWriter out, int testNumber) {
                     out.printLine("Case #" + testNumber + ":", answer
                             .numerator, answer.denominator);
                 }
             }, 23);
         }
 
         static class Operation {
             char operation;
             BigInteger value;
 
             public Operation(char operation, BigInteger value) {
                 this.operation = operation;
                 this.value = value;
             }
 
         }
 
         static class BigRational {
             BigInteger numerator;
             BigInteger denominator;
 
             public BigRational(BigInteger numerator, BigInteger denominator) {
                 this.numerator = numerator;
                 this.denominator = denominator;
             }
 
             public void add(BigInteger number) {
                 numerator = numerator.add(number.multiply(denominator));
             }
 
             public void subtract(BigInteger number) {
                 numerator = numerator.subtract(number.multiply(denominator));
             }
 
             public void multiply(BigInteger number) {
                 numerator = numerator.multiply(number);
             }
 
             public void divide(BigInteger number) {
                 denominator = denominator.multiply(number);
             }
 
             public void canonize() {
                 if (denominator.signum() < 0) {
                     denominator = denominator.negate();
                     numerator = numerator.negate();
                 }
                 BigInteger gcd = numerator.gcd(denominator);
                 numerator = numerator.divide(gcd);
                 denominator = denominator.divide(gcd);
             }
 
             public void max(TaskB.BigRational candidate) {
                 if (numerator.multiply(candidate.denominator).compareTo(denominator.multiply(candidate.numerator)) <
                         0) {
                     numerator = candidate.numerator;
                     denominator = candidate.denominator;
                 }
             }
 
             public void apply(TaskB.Operation operation) {
                 if (operation.operation == '+') {
                     add(operation.value);
                 }
                 if (operation.operation == '-') {
                     subtract(operation.value);
                 }
                 if (operation.operation == '*') {
                     multiply(operation.value);
                 }
                 if (operation.operation == '/') {
                     divide(operation.value);
                 }
             }
 
         }
 
     }
 
     static interface TaskFactory {
         public Task newTask();
 
     }
 
     static interface Task {
         public void read(InputReader in);
 
         public void solve();
 
         public void write(OutputWriter out, int testNumber);
 
     }
 
     static class Scheduler {
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
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public char readCharacter() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             return (char) c;
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
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
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void close() {
             writer.close();
         }
 
     }
 }
 
