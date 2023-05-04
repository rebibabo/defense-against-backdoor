
 import java.io.*;
 import java.util.*;
 
 public class A {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    boolean escape(char[][] board, int r, int c) {
        switch (board[r][c]) {
            case '^': while (--r >= 0)              if (board[r][c] != '.') return false; break;
            case '>': while (++c < board[0].length) if (board[r][c] != '.') return false; break;
            case 'v': while (++r < board.length)    if (board[r][c] != '.') return false; break;
            case '<': while (--c >= 0)              if (board[r][c] != '.') return false; break;
            case '.': return false;
            default: break;
        }
        return true;
    }
 
    char[] dirs = new char[]{'^', '>', 'v', '<'};
 
    int go(char[][] board) {
        int R = board.length;
        int C = board[0].length;
        int changes = 0;
        for (int r = 0 ; r < R ; r++) {
            for (int c = 0 ; c < C ; c++) {
                if (escape(board, r, c)) {
                    boolean found = false;
                    for (char dir : dirs) {
                        board[r][c] = dir;
                        if (!escape(board, r, c)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) return -1;
                    changes++;
                }
            }
        }
        return changes;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int CASES = getInt(in);
            for (int CASE = 1 ; CASE <= CASES ; CASE++) {
                int[] par = getInts(in);
                int R = par[0];
                char[][] board = new char[R][];
                for (int r = 0 ; r < R ; r++) {
                    board[r] = in.readLine().toCharArray();
                }
                int changes = go(board);
                if (changes == -1) out.printf("Case #%d: IMPOSSIBLE\n", CASE);
                else out.printf("Case #%d: %d\n", CASE, changes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new A().run(args);
    }
 }
