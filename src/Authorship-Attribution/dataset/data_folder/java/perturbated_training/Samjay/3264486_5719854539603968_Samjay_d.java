package con2017.con2017Q;
 
 
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.io.Writer;
 import java.util.InputMismatchException;
 import java.util.LinkedList;
 
 public class D {
 
   static final String islarge = "-large";
   private static final String fileLoc = "src/con2017/con2017Q/files/";
   private static final String fileName = fileLoc + D.class.getSimpleName().toLowerCase();
   private static final String inputFileName = fileName + ".in";
   private static final String outputFileName = fileName + ".out";
   private static InputReader in;
   private static OutputWriter out;
 
   static class Holder {
     private Cell[] cells;
 
     public Holder(int N) {
       cells = new Cell[N];
     }
 
     private Cell blocker = null;
 
     boolean isUsed() {
       return blocker != null;
     }
 
     void use(Cell cell) {
       if (isUsed()) {
         throw new RuntimeException("already in use by " + this.blocker.r + " " + this.blocker.c
             + " blocked for " + cell.r + " " + cell.c);
       }
       this.blocker = cell;
     }
 
     void unuse() {
       if (!isUsed()) {
         throw new RuntimeException("not in use ");
       }
       this.blocker = null;
     }
 
   }
 
   static class Cell {
     final int r, c;
     int type, orig;
     Holder row, col, diag1, diag2;
 
     public Cell(int r, int c) {
       this.r = r;
       this.c = c;
       this.orig = EMPTY;
     }
 
     boolean blocked() {
       return row.isUsed() || col.isUsed() || diag1.isUsed() || diag2.isUsed();
     }
 
     void setType(int tp) {
       
       int add = tp - (this.type & tp);
       if (add > 0) {
         points++;
         if (add == ALL) {
           points++;
         }
         if ((add & DIAG) == DIAG) {
           row.use(this);
           col.use(this);
         }
         if ((add & ROWCOL) == ROWCOL) {
           diag1.use(this);
           diag2.use(this);
         }
       }
       
       int rem = this.type - (this.type & tp);
       if (rem > 0) {
         points--;
         if (rem == ALL) {
           points--;
         }
         if ((rem & DIAG) == DIAG) {
           row.unuse();
           col.unuse();
         }
         if ((rem & ROWCOL) == ROWCOL) {
           diag1.unuse();
           diag2.unuse();
         }
       }
       this.type = tp;
     }
   }
 
   static final int EMPTY = 0, ROWCOL = 1, DIAG = 2, ALL = 3;
   static final char[] mdl = new char[] {'.', '+', 'x', 'o'};
   static int points;
 
   private void solve() {
     int N = in.readInt(), M = in.readInt();
     
     points = 0;
     
     Cell[][] grid = new Cell[N + 1][N + 1];
     for (int i = 1; i <= N; i++) {
       for (int j = 1; j <= N; j++) {
         grid[i][j] = new Cell(i, j);
       }
     }
     
     setHolders(grid);
     for (int i = 0; i < M; i++) {
       char model = in.readString().charAt(0);
       int r = in.readInt(), c = in.readInt();
       grid[r][c].setType(getType(model));
       grid[r][c].orig = grid[r][c].type;
     }
     
     for (int c = 1; c <= N; c++) {
       grid[1][c].setType(grid[1][c].type | ROWCOL);
     }
     
     for (int c = 2; c < N; c++) {
       grid[N][c].setType(grid[N][c].type | ROWCOL);
     }
     
     for (int r = 1, c = 1; r <= N && c <= N;) {
       if (grid[r][c].row.isUsed()) {
         r++;
       } else if (grid[r][c].col.isUsed()) {
         c++;
       } else {
         grid[r][c].setType(grid[r][c].type | DIAG);
         r++;
         c++;
       }
     }
     
     
     
     LinkedList<Cell> sol = new LinkedList<>();
     for (int i = 1; i <= N; i++) {
       for (int j = 1; j <= N; j++) {
         if (grid[i][j].type != grid[i][j].orig) {
           sol.add(grid[i][j]);
         }
       }
     }
     out.printLine(points + " " + sol.size());
     for (Cell cell : sol) {
       out.printLine(mdl[cell.type] + " " + cell.r + " " + cell.c);
     }
   }
 
   static int getType(char c) {
     switch (c) {
       case '+':
         return ROWCOL;
       case 'x':
         return DIAG;
       case 'o':
         return ALL;
       case '.':
         return EMPTY;
     }
     throw new RuntimeException("invalid char " + c);
   }
 
   static void setHolders(Cell[][] grid) {
     int N = grid.length - 1;
     
     for (int r = 1; r <= N; r++) {
       Holder rowholder = new Holder(N);
       for (int c = 1; c <= N; c++) {
         rowholder.cells[c - 1] = grid[r][c];
         grid[r][c].row = rowholder;
       }
     }
     
     for (int c = 1; c <= N; c++) {
       Holder colholder = new Holder(N);
       for (int r = 1; r <= N; r++) {
         colholder.cells[r - 1] = grid[r][c];
         grid[r][c].col = colholder;
       }
     }
     
     
     
     
     for (int rc = 1; rc <= N; rc++) {
       Holder d1holder = new Holder(1 + N - rc);
       for (int d = 0; rc + d <= N; d++) {
         grid[1 + d][rc + d].diag1 = d1holder;
         d1holder.cells[d] = grid[1 + d][rc + d];
       }
       if (rc == 1)
         continue;
       d1holder = new Holder(1 + N - rc);
       for (int d = 0; rc + d <= N; d++) {
         grid[rc + d][1 + d].diag1 = d1holder;
         d1holder.cells[d] = grid[rc + d][1 + d];
       }
     }
     
     
     
     for (int rc = 1; rc <= N; rc++) {
       Holder d2holder = new Holder(rc);
       for (int d = 0; rc - d > 0; d++) {
         grid[1 + d][rc - d].diag2 = d2holder;
         d2holder.cells[d] = grid[1 + d][rc - d];
       }
       d2holder = new Holder(1 + N - rc);
       for (int d = 0; rc + d <= N; d++) {
         grid[N - d][rc + d].diag2 = d2holder;
         d2holder.cells[d] = grid[N - d][rc + d];
       }
     }
     
     for (int r = 1; r <= N; r++) {
       for (int c = 1; c <= N; c++) {
         if (grid[r][c].row == null) {
           throw new RuntimeException(r + " " + c + " no row holder");
         }
         if (grid[r][c].col == null) {
           throw new RuntimeException(r + " " + c + " no col holder");
         }
         if (grid[r][c].diag1 == null) {
           throw new RuntimeException(r + " " + c + " no diag1 holder");
         }
         if (grid[r][c].diag2 == null) {
           throw new RuntimeException(r + " " + c + " no diag2 holder");
         }
       }
     }
   }
 
   static void printgrid(Cell[][] grid) {
     for (int r = 1; r < grid.length; r++) {
       for (int c = 1; c < grid.length; c++) {
         System.err.print(mdl[grid[r][c].type]);
       }
       System.err.println();
     }
   }
 
   public static void main(String[] args) throws IOException {
     long start = System.currentTimeMillis();
     in = new InputReader(new FileInputStream(inputFileName));
     out = new OutputWriter(new FileOutputStream(outputFileName));
     int tests = in.readInt();
     for (int t = 1; t <= tests; t++) {
       out.print("Case #" + t + ": ");
       new D().solve();
       System.out.println("Case #" + t + ": solved");
     }
     out.close();
     long stop = System.currentTimeMillis();
     System.out.println(stop - start + " ms");
   }
 
 
   static class InputReader {
     private InputStream stream;
     private byte[] buf = new byte[1024];
     private int curChar;
     private int numChars;
 
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
 
     public String readLine() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       StringBuilder res = new StringBuilder();
       do {
         res.appendCodePoint(c);
         c = read();
       } while (!isEndOfLine(c));
       return res.toString();
     }
 
     public String readString() {
       int c = read();
       while (isSpaceChar(c))
         c = read();
       StringBuilder res = new StringBuilder();
       do {
         res.appendCodePoint(c);
         c = read();
       } while (!isSpaceChar(c));
       return res.toString();
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
       return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
     }
 
     public boolean isEndOfLine(int c) {
       return c == '\n' || c == '\r' || c == -1;
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
 
     public void printLine(Object... objects) {
       print(objects);
       writer.println();
     }
 
     public void close() {
       writer.close();
     }
   }
 }
