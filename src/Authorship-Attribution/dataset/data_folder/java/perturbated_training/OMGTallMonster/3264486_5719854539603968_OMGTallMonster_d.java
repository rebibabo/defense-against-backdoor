import java.util.*;
 import java.util.stream.*;
 import java.io.*;
 import java.math.*;
 import java.awt.geom.*;
 public class D {
   public static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
   public static final PrintWriter outWriter = new PrintWriter(System.out);
   public static char[][] board;
   public static int N;
   public static int tmp(int r, int c) {
     char ch = board[r][c];
     if (ch == 'o') { return 0; }
     if (ch == '.') {
       int axisC = checkAxis(r,c), diagC = checkDiag(r,c);
       board[r][c] = axisC == 0 && diagC == 0 ? 'o' :
                     axisC == 0 ? 'x' :
                     diagC == 0 ? '+' :
                                                '.';
     } else if (ch == 'x' && checkDiag(r,c) == 0 || ch == '+' && checkAxis(r,c) == 0) {
       board[r][c] = 'o';
     }
     return board[r][c] != ch ? 1 : 0;
   }
   public static char[][] copyBoard() {
     int len1 = board.length, len2 = board[0].length;
     char[][] out = new char[len1][len2];
     for (int i = 0; i < len1; i++) {
       System.arraycopy(board[i], 0, out[i], 0, len2);
     }
     return out;
   }
   public static Object solve() {
     N = nextInt();
     int M = nextInt();
     board = new char[N][N];
     int count = 0;
     for (char[] row : board) { Arrays.fill(row, '.'); }
     for (int i = 0; i < M; i++) {
       char ch = next().charAt(0);
       int r = nextInt(), c = nextInt();
       board[r-1][c-1] = ch;
     }
     char[][] initBoard = copyBoard();
     for (int i = 0; i < N; i++) {
       for (int j = 0; j <= i; j++) {
         int r1 = i-j, c1 = j;
         int r2 = N-1-r1, c2 = N-1-c1;
         count += tmp(r1, c1);
         count += tmp(r1, c2);
         count += tmp(r2, c1);
         count += tmp(r2, c2);
       }
     }
     int score = score();
     if (!checkBoard()) {
       System.err.println("Uh oh...");
     }
     outWriter.println(score()+" "+count);
     for (int r = 0; r < N; r++) {
       for (int c = 0; c < N; c++) {
         if (board[r][c] != initBoard[r][c]) {
           outWriter.println(board[r][c]+" "+r+" "+c);
           count--;
         }
       }
     }
     if (count != 0) {
       System.err.println("Wrong count?");
     }
     return null;
   }
   public static int score() {
     int out = 0;
     for (char[] row : board) {
       for (char c : row) {
         if (c == '+' || c == 'x') { out++; }
         else if (c == 'o') { out+=2; }
       }
     }
     return out;
   }
   public static boolean checkBoard() {
     for (int i = 0; i < board.length; i++) {
       if (checkRow(i) >= 2) { return false; }
       if (checkCol(i) >= 2) { return false; }
       if (checkDiagDR(i, 0) >= 2) { return false; }
       if (checkDiagDR(0, i) >= 2) { return false; }
       if (checkDiagUR(i, 0) >= 2) { return false; }
       if (checkDiagUR(i, i) >= 2) { return false; }
     }
     return true;
   }
   public static int checkAxis(int r, int c) { return Math.max(checkRow(r), checkCol(c)); }
   public static int checkDiag(int r, int c) { return Math.max(checkDiagDR(r, c), checkDiagUR(r, c)); }
   public static int checkRow(int r) { return check(r, 0, 0, 1, '+'); }
   public static int checkCol(int c) { return check(0, 1, c, 0, '+'); }
   public static int checkDiagDR(int r, int c) { return check(r, 1, c, 1, 'x'); }
   public static int checkDiagUR(int r, int c) { return check(r, -1, c, 1, 'x'); }
   public static int check(int r, int dr, int c, int dc, char allowed) {
     int out = 0, r_ = r, c_ = c;
     for ( ; r >= 0 && r < N && c >= 0 && c < N; r += dr, c += dc) {
       if (board[r][c] != '.' && board[r][c] != allowed) { out++; }
     }
     for (r = r_-dr, c = c_-dc; r >= 0 && r < N && c >= 0 && c < N; r -= dr, c -= dc) {
       if (board[r][c] != '.' && board[r][c] != allowed) { out++; }
     }
     return out;
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
