package qualy;
 
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class D {
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static final int MAXN = 6;
    
    private static class Nomino {
        private boolean[][] map;
        private int size, X, Y;
 
        public Nomino(boolean[][] map) {
            this.map = map;
            X = map.length;
            Y = map[0].length;
            size = 0;
            for (int i = 0; i < X; i++) for (int j = 0; j < Y; j++) if (map[i][j]) size++;
        }
        
        private boolean isOn(int posx, int posy) {
            return 0 <= posx && posx < X && 0 <= posy && posy < Y && map[posx][posy];
        }
 
        public Set<Nomino> getNeighbors() {   
            Set<Nomino> ret = new HashSet<Nomino>();
            for (int posx = -1; posx <= X; posx++) for (int posy = -1; posy <= Y; posy++) {
                if (!isOn(posx, posy)) {
                    boolean ok = false;
                    for (int k = 0; k < dx.length; k++) {
                        int nx = posx+dx[k], ny = posy+dy[k];
                        if (isOn(nx, ny)) ok = true;
                    }
                    if (ok) ret.add(addPosition(posx, posy));
                }
            }
            return ret;
        }
        
        public boolean canBeUsedToFillGrid(int R, int C) {
            if (canBeUsedToFillGridWithNoRotation(R, C)) return true;
            if (R != C && canBeUsedToFillGridWithNoRotation(C, R)) return true;
            return false;
        }
        
        private boolean canBeUsedToFillGridWithNoRotation(int R, int C) {
            if ((R * C) % size != 0) return false;
            if (R >= X + 2 && C >= Y + 2) return true;
            if (R < X || C < Y) return false;
            for (int i = 0; i <= R - X; i++) for (int j = 0; j <= C - Y; j++) {
                if (canFillIt(R, C, i, j)) return true;
            }
            return false;
        }
        
        private boolean canFillIt(int R, int C, int posi, int posj) {
            boolean[][] table = new boolean[R][C];
            for (int i = 0; i < X; i++) for (int j = 0; j < Y; j++) table[posi+i][posj+j] = map[i][j];
            for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) if (!table[i][j]) {
                if (fillAndGetCounter(table, i, j) % size != 0) return false;
            }
            return true;
        }
 
        private static int fillAndGetCounter(boolean[][] table, int i, int j) {
            int ret = 1;
            table[i][j] = true;
            for (int k = 0; k < dx.length; k++) {
                int ni = i + dx[k], nj = j + dy[k];
                if (0 <= ni && ni < table.length && 0 <= nj && nj < table[0].length && !table[ni][nj]) {
                    ret += fillAndGetCounter(table, ni, nj);
                }
            }
            return ret;
        }
 
        private Nomino addPosition(int posx, int posy) {
            int newX = 0 <= posx && posx < X ? X : X + 1;
            int addx = posx == -1 ? 1 : 0;
            int newY = 0 <= posy && posy < Y ? Y : Y + 1;
            int addy = posy == -1 ? 1 : 0;
            boolean[][] ret = new boolean[newX][newY];
            for (int x = 0; x < X; x++) for (int y = 0; y < Y; y++) {
                ret[x + addx][y + addy] = map[x][y];
            }
            ret[posx + addx][posy + addy] = true;
            return new Nomino(ret);
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.deepHashCode(map);
            return result;
        }
 
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Nomino other = (Nomino) obj;
            if (!Arrays.deepEquals(map, other.map))
                return false;
            return true;
        }
 
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    sb.append(map[i][j] ? 'X' : '.');
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        Set<Nomino>[] nominos = new Set[MAXN + 1];
        for (int i = 1; i <= MAXN; i++) {
            nominos[i] = new HashSet<Nomino>();
        }
        nominos[1].add(new Nomino(new boolean[][] {{true}}));
        for (int i = 2; i <= MAXN; i++) {
            for (Nomino elem : nominos[i-1]) {
                nominos[i].addAll(elem.getNeighbors());
            }
        }
        
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int X = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            boolean richardWins = X > MAXN;
            if (!richardWins) {
                for (Nomino xomino : nominos[X]) {
                    if (!xomino.canBeUsedToFillGrid(R, C)) {
                        richardWins = true;
                        break;
                    }
                }
            }
            System.out.println("Case #" + caze + ": " + (richardWins ? "RICHARD" : "GABRIEL"));
            
        }
    }
 }
