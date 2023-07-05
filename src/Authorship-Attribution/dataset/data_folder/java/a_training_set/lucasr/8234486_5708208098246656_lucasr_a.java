package round2;
 
 import java.util.Scanner;
 
 public class A {
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    
    static int position(char c) {
        switch (c) {
        case '^':
            return 0;
 
        case '>':
            return 1;
 
        case 'v':
            return 2;
 
        case '<':
            return 3;
 
        default:
            return -1;
        }
    }
    
    static boolean isSafe(int[][] pos, int ii, int jj, int dir) {
        while (true) {
            ii+=di[dir];
            jj+=dj[dir];
            if (!(0<= ii && ii < pos.length && 0<=jj && jj < pos[0].length)) return false;
            if (pos[ii][jj] != -1) return true;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            int[][] pos = new int[R][C];
            for (int i = 0; i < pos.length; i++) {
                String line = sc.next();
                for (int j = 0; j < line.length(); j++) {
                    pos[i][j] = position(line.charAt(j));
                }
            }
            boolean can = true;
            int ans = 0;
            for (int i = 0; i < pos.length && can; i++) {
                for (int j = 0; j < pos[i].length && can; j++) {
                    if (pos[i][j] != -1) {
                        if (!isSafe(pos, i, j, pos[i][j])) {
                            boolean hasSafeDir = false;
                            for (int k = 0; k < 4; k++) if (isSafe(pos, i, j, k)) hasSafeDir = true;
                            if (!hasSafeDir) can = false;
                            else ans++;
                        }
                    }
                }
            }
            System.out.println("Case #" + caze + ": " + (can ? ans + "" : "IMPOSSIBLE"));
        }
    }
 
 }
