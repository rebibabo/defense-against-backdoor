import java.util.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class Main {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static ArrayList<Beam> beams = new ArrayList<Beam>();
   public static char[][] board;
   public static Beam[][] udCover, lrCover;
   public static int rows = -1, cols = -1;
   public static interface Func { boolean call(int r, int c); }
   public static class Point {
     public int r;
     public int c;
     public int r() { return r; }
     public int c() { return c; }
     Point(int r_, int c_) { r=r_; c=c_; }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Point o = (Point)oo;
       return r==o.r&&c==o.c;
     }
     public int hashCode() { return Objects.hash(r,c); }
     public String toString() { return ""+r+","+c; }
   }
   public static boolean propogate(Beam b, Func callback) {
     return propogate(b.r, b.c, b.isUD, callback);
   }
   public static boolean propogate(int r, int c, boolean isUD, Func callback) {
     return propogate(r, c, isUD ? 1 : 0, isUD ? 0 : 1, callback) &&
            propogate(r, c, isUD ? -1 : 0, isUD ? 0 : -1, callback);
   }
   public static boolean propogate(int r, int c, int dr, int dc, Func callback) {
     while (true) {
       r += dr; c += dc;
       if (r < 0 || c < 0 || r >= rows || c >= cols || board[r][c] == '#') {
         break;
       } else if (board[r][c] == '/') {
         dr^=dc^(dc=dr);
         dr *= -1;
         dc *= -1;
       } else if (board[r][c] == '\\') {
         dr^=dc^(dc=dr);
       } else {
         if (callback.call(r, c)) {
           return false;
         }
       }
     }
     return true;
   }
   public static boolean check(Beam beam, boolean isUD) {
     Func callback = (r, c) -> {
       if (isUD) {
         udCover[r][c] = beam;
       } else {
         lrCover[r][c] = beam;
       }
       return board[r][c] == '-' || board[r][c] == '|';
     };
     return propogate(beam.r, beam.c, isUD, callback);
   }
   public static void printBoard() {
     for (Beam b : beams) {
       board[b.r][b.c] = b.isUD ? '|' : '-';
     }
     for (char[] row : board) {
       outWriter.println(new String(row));
     }
   }
   public static Object solve() {
     rows = nextInt();
     cols = nextInt();
     board = new char[rows][cols];
     beams.clear();
     udCover = new Beam[rows][cols];
     lrCover = new Beam[rows][cols];
     for (int r = 0; r < rows; r++) {
       String s = nextLine();
       for (int c = 0; c < cols; c++) {
         board[r][c] = s.charAt(c);
         if (board[r][c] == '|' || board[r][c] == '-') {
           board[r][c] = '-';
           beams.add(new Beam(r,c));
         }
       }
     }
     for (Beam b : beams) {
       boolean canUD = check(b, true);
       boolean canLR = check(b, false);
       if (!canUD && !canLR) {
         return "IMPOSSIBLE";
       } else if (canUD ^ canLR) {
         b.locked = true;
         b.isUD = canUD;
       }
     }
     LinkedList<Point> ambiguous = new LinkedList<Point>();
     for (int r = 0; r < rows; r++) {
       for (int c = 0; c < cols; c++) {
         if (board[r][c] == '.') {
           Beam ud = udCover[r][c], lr = lrCover[r][c];
           if (ud == null && lr == null) { return "IMPOSSIBLE"; }
           if (ud != null && ud.locked && ud.isUD || lr != null && lr.locked && !lr.isUD) {
             continue;
           }
           if (ud == null || ud.locked) {
             if (lr.isUD && lr.locked) { return "IMPOSSIBLE"; }
             lr.isUD = false; lr.locked = true;
           } else if (lr == null || lr.locked) {
             if (!ud.isUD && ud.locked) { return "IMPOSSIBLE"; }
             ud.isUD = true; ud.locked = true;
           } else {
             ambiguous.add(new Point(r,c));
           }
         }
       }
     }
     System.err.println("ambiguous.size():"+(ambiguous.size()));
     outWriter.println("POSSIBLE");
     printBoard();
     return null;
   }
   public static class Beam {
     public int r;
     public int c;
     public int r() { return r; }
     public int c() { return c; }
     Beam(int r_, int c_) { r=r_; c=c_; }
     public boolean equals(Object oo) {
       @SuppressWarnings("unchecked")
       Beam o = (Beam)oo;
       return r==o.r&&c==o.c;
     }
     public int hashCode() { return Objects.hash(r,c); }
     public String toString() { return ""+r+","+c; }
     boolean isUD = false;
     boolean locked = false;
   }
   public static void main(String[] args) {
     int T = nextInt();
     for (int i = 0; i < T; i++) {
       outWriter.print("Case #"+(i+1)+": ");
       Object tmp = solve();
       if (tmp != null) { outWriter.println(tmp); }
     }
     outWriter.flush();
   }
   public static StringTokenizer tokenizer = null;
   public static String nextLine() {
     try { return buffer.readLine(); } catch (IOException e) { throw new UncheckedIOException(e); }
   }
   public static String next() {
     while (tokenizer == null || !tokenizer.hasMoreElements()) { tokenizer = new StringTokenizer(nextLine()); }
     return tokenizer.nextToken();
   }
   public static int nextInt() { return Integer.parseInt(next()); }
   public static long nextLong() { return Long.parseLong(next()); }
   public static double nextDouble() { return Double.parseDouble(next()); }
 }
