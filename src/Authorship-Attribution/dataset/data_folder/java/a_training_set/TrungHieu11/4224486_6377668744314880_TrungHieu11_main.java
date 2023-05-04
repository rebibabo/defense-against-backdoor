import java.io.OutputStreamWriter;
 import java.io.BufferedWriter;
 import java.util.Locale;
 import java.util.Comparator;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.File;
 import java.io.Writer;
 import java.io.FilenameFilter;
 import java.util.List;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.ArrayList;
 import java.io.FileOutputStream;
 import java.io.FileInputStream;
 import java.util.NoSuchElementException;
 import java.math.BigInteger;
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
     public void solve(int testNumber, InputReader in, OutputWriter out) {
         out.printLine("Case #" + testNumber + ":");
         int count = in.readInt();
         Point[] points = new Point[count];
         for (int i = 0; i < count; i++) {
             points[i] = new Point(in.readInt(), in.readInt());
         }
 
         int[] answer = new int[count];
         for (int i = 0; i < count; i++) {
             answer[i] = Integer.MAX_VALUE;
             for (int mask = 0; mask < (1 << count); mask++) {
                 if (!BitUtils.isContain(mask, i)) {
                     continue;
                 }
                 List<Point> nextPoints = new ArrayList<Point>();
                 for (int bit = 0; bit < count; bit++) {
                     if (BitUtils.isContain(mask, bit)) {
                         nextPoints.add(points[bit]);
                     }
                 }
                 if (nextPoints.size() == 0) {
                     continue;
                 }
                 Point[] curPoints = nextPoints.toArray(new Point[nextPoints.size()]);
                 Point[] curPolygon = Polygon.convexHull(curPoints).vertices;
                 if (!isContains(curPolygon, points[i])) {
                     continue;
                 }
                 answer[i] = Math.min(answer[i], count - curPoints.length);
             }
         }
 
         for (int i = 0; i < count; i++) {
             out.printLine(answer[i]);
         }
     }
 
     private boolean isContains(Point[] all, Point cur) {
         for (int i = 0; i < all.length; i++) {
             if (all[i].equals(cur)) {
                 return true;
             }
         }
         return false;
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
 
     public void printLine(int i) {
        writer.println(i);
    }
 }
 
 class Point {
     public final double x;
    public final double y;
 
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
 
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
     public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
 
        Point point = (Point) o;
 
        return Math.abs(x - point.x) <= GeometryUtils.epsilon && Math.abs(y - point.y) <= GeometryUtils.epsilon;
    }
 
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
 
 }
 
 class BitUtils {
 
     public static boolean isContain(long mask, int bit) {
         return (mask & ((long)1 << bit)) != 0;
     }
 }
 
 class Polygon {
    public final Point[] vertices;
 
     public Polygon(Point...vertices) {
        this.vertices = vertices.clone();
    }
 
     public static boolean over(Point a, Point b, Point c) {
         return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) < -GeometryUtils.epsilon;
     }
 
     private static boolean under(Point a, Point b, Point c) {
         return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) > GeometryUtils.epsilon;
     }
 
     public static Polygon convexHull(Point[] points) {
         if (points.length == 1)
             return new Polygon(points);
         Arrays.sort(points, new Comparator<Point>() {
             public int compare(Point o1, Point o2) {
                 int value = Double.compare(o1.x, o2.x);
                 if (value != 0)
                     return value;
                 return Double.compare(o1.y, o2.y);
             }
         });
         Point left = points[0];
         Point right = points[points.length - 1];
         List<Point> up = new ArrayList<Point>();
         List<Point> down = new ArrayList<Point>();
         for (Point point : points) {
             if (point == left || point == right || !under(left, point, right)) {
                 while (up.size() >= 2 && under(up.get(up.size() - 2), up.get(up.size() - 1), point))
                     up.remove(up.size() - 1);
                 up.add(point);
             }
             if (point == left || point == right || !over(left, point, right)) {
                 while (down.size() >= 2 && over(down.get(down.size() - 2), down.get(down.size() - 1), point))
                     down.remove(down.size() - 1);
                 down.add(point);
             }
         }
         Point[] result = new Point[up.size() + down.size() - 2];
         int index = 0;
         for (Point point : up)
             result[index++] = point;
         for (int i = down.size() - 2; i > 0; i--)
             result[index++] = down.get(i);
         return new Polygon(result);
     }
 
 }
 
 class GeometryUtils {
    public static double epsilon = 1e-8;
 
 }
 
