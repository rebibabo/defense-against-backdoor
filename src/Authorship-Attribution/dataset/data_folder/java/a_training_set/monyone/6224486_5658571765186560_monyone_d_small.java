import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.StringTokenizer;
 
 
 public class D_small {
    
    public static final int[][][][] mino_3 = {
        {{
            {1, 1},
            {1, 0}
        },
        {
            {1, 1},
            {0, 1}
        },
        {
            {0, 1},
            {1, 1}
        },
        {
            {1, 0},
            {1, 1}
        }},
        
        {{
            {1},
            {1},
            {1}
        },
        {
            {1, 1, 1}
        }}
    };
    
    public static final int[][][][] mino_4 = {
        {{
            {1, 1},
            {1, 1},
        }},
        
        {{
            {1, 1},
            {1, 0},
            {1, 0},
        },
        {
            {1, 1, 1},
            {0, 0, 1},
        },
        {
            {0, 1},
            {0, 1},
            {1, 1},
        },
        {
            {1, 0, 0},
            {1, 1, 1},
        },
        {
            {1, 1},
            {0, 1},
            {0, 1},
        },
        {
            {0, 0, 1},
            {1, 1, 1},
        },
        {
            {1, 0},
            {1, 0},
            {1, 1},
        },
        {
            {1, 1, 1},
            {1, 0, 0},
        }},
        
        {{
            {1, 0},
            {1, 1},
            {1, 0},
        },
        {
            {1, 1, 1},
            {0, 1, 0},
        },
        {
            {0, 1},
            {1, 1},
            {0, 1},
        },
        {
            {0, 1, 0},
            {1, 1, 1},
        }},
        
        {{
            {1, 0},
            {1, 1},
            {0, 1},
        },
        {
            {0, 1, 1},
            {1, 1, 0},
        },
        {
            {0, 1},
            {1, 1},
            {1, 0},
        },
        {
            {1, 1, 0},
            {0, 1, 1},
        }},
        
        {{
            {1},
            {1},
            {1},
            {1}
        },
        {
            {1, 1, 1, 1}
        }}
    };
    
    public static final int[][] move_dir = new int[][]{
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    
    public static boolean ok(final int X, final int R, final int C, int[][] map, int[][][][] minoss, int sx, int sy){
        
        if(map[sy][sx] != 0){
            if(sx == C - 1 && sy == R - 1){
                return true;
            }else{
                return ok(X, R, C, map, minoss, sx + 1 >= C ? 0 : sx + 1, sx + 1 >= C ? sy + 1 : sy);
            }
        }else{
            
            for(int[][][] minos : minoss){
                for(int[][] mino : minos){
                    final int mino_height = mino.length;
                    final int mino_width = mino[0].length;
                
                    for(int ty = sy - mino_height + 1; ty < sy + mino_height; ty++){
                        
                        if(ty < 0){ continue; }
                        if(ty + mino_height > R){ continue; }
                        
                        LOOP:
                        for(int tx = sx - mino_width + 1; tx < sx + mino_width; tx++){
                            if(tx < 0){ continue; }
                            if(tx + mino_width> C){ continue; }
                    
                            
                            
                            for(int i = 0; i < mino.length; i++){
                                for(int j = 0; j < mino[i].length; j++){
                                    if(mino[i][j] != 0 && map[ty + i][tx + j] != 0){
                                        continue LOOP;
                                    }
                                }
                            }
                            
                            for(int i = 0; i < mino.length; i++){
                                for(int j = 0; j < mino[i].length; j++){
                                    map[ty + i][tx + j] = mino[i][j] == 1 ? 1 : map[ty + i][tx + j];
                                }
                            }
                            
                            if(ok(X, R, C, map, minoss, sx, sy)){
                                return true;
                            }
                            
                            for(int i = 0; i < mino.length; i++){
                                for(int j = 0; j < mino[i].length; j++){
                                    map[ty + i][tx + j] = mino[i][j] == 1 ? 0 : map[ty + i][tx + j];
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public static boolean check(final int X, final int R, final int C, int[][][][] minoss){
        int[][] map = new int[R][C];
        
        
        
        INNER_LOOP:
        for(int[][][] minos : minoss){
            for(int[][] mino : minos){
            
                final int height = mino.length;
                final int width = mino[0].length;
                
                for(int sy = 0; sy <= R - height; sy++){
                    for(int sx = 0; sx <= C - width; sx++){
                        for(int i = 0; i < R; i++){
                            for(int j = 0; j < C; j++){
                                map[i][j] = 0;
                            }
                        }
                        
                        for(int i = 0; i < height; i++){
                            for(int j = 0; j < width; j++){
                                map[sy + i][sx + j] = mino[i][j];
                            }
                        }
                    
                        boolean can_fill = ok(X, R, C, map, minoss, 0, 0);
                    
                        
                        if(can_fill){
                            continue INNER_LOOP;
                        }
                    }
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        final int T = sc.nextInt();
        
        for(int tt = 1; tt <= T; tt++){
            final int X = sc.nextInt();
            final int R = sc.nextInt();
            final int C = sc.nextInt();
            
            boolean win = false;
            if(X == 1){
                win = false;
            }else if(X == 2){
                win = (R * C) % 2 != 0;
            }else if(X == 3){
                win = check(X, R, C, mino_3);
            }else if(X == 4){
                win = check(X, R, C, mino_4);
            }
            
            System.out.printf("Case #%d: %s\n", tt, win ? "RICHARD" : "GABRIEL");
        }
        
        sc.close();
    }
    
    public static class Scanner {
        private BufferedReader br;
        private StringTokenizer tok;
 
        public Scanner(InputStream is) throws IOException {
            br = new BufferedReader(new InputStreamReader(is));
        }
 
        private void getLine() throws IOException {
            while (!hasNext()) { tok = new StringTokenizer(br.readLine()); }
        }
 
        private boolean hasNext() {
            return tok != null && tok.hasMoreTokens();
        }
 
        public String next() throws IOException {
            getLine(); return tok.nextToken();
        }
 
        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
        
        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
        
        public void close() throws IOException {
            br.close();
        }
    }
 }
