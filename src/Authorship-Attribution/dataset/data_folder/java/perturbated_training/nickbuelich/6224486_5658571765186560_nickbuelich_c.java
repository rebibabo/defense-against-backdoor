import java.io.File;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class C {
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static LinkedList<boolean[][]>[] MOS;
    static HashMap<Integer,Boolean> MEMO;
    static int X;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("domino.txt"));
        PrintWriter out = new PrintWriter(new File("domino.out"));
        MOS = new LinkedList[5];
        for(int a=0;a<5;a++)MOS[a]= new LinkedList<boolean[][]>();
        stuff: for(int mask=1;mask<1<<16;mask++){
            boolean[][] grid = new boolean[4][4];
            int ctr =0;
            if(Integer.bitCount(mask)>4)continue;
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    grid[a][b] = ((mask>>ctr)&1)==1;
                    ctr++;
                }
            }
            DisjointSet D = new DisjointSet(16);
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    if(grid[a][b])
                    for(int d=0;d<4;d++){
                        int nx = b+dx[d];
                        int ny = a+dy[d];
                        if(nx<0||ny<0||nx>=3||ny>=3)continue;
                        if(grid[ny][nx])D.union(a*4+b,ny*4+nx);
                    }
                }
            }
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    if(grid[a][b])
                    for(int c=0;c<4;c++){
                        for(int d=0;d<4;d++){
                            if(grid[c][d]){
                                if(D.find(a*4+b)!=D.find(c*4+d))continue stuff;
                            }
                        }
                    }
                }
            }
            MOS[Integer.bitCount(mask)].add(grid);
        }
        for(LinkedList<boolean[][]> x : MOS) System.out.println(x.size());
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            X = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            MEMO = new HashMap<Integer,Boolean>();
            boolean ans = MAGIC(R,C,X)||MAGIC(C,R,X);
            System.out.println(ans);
            out.printf("Case #%d: %s%n",t,ans?"GABRIEL":"RICHARD");
 
            
        }
        
        
        out.close();
    }
 
    private static boolean MAGIC(int R, int C, int X) {
        if(X==1)return true;
        if(X==2){
            return ((R*C)&1)==0;
        }
        if(X==3){
            boolean ans = false;
            
            boolean cando = false;
            for(int a=0;a<=R-3;a++){
                for(int b=0;b<C;b++){
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<3;c++)
                        board[a+c][b]=true;
                    ans|=solve(board);
                }
            }
            if(!cando)return false;
            cando = false;
            for(int a=0;a<=R-2;a++){
                for(int b=0;b<=C-2;b++){
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<2;c++)
                        for(int d=0;d<2;d++)
                            board[a+c][b+d]=true;
                    board[a][b]=false;
                    ans|=solve(board);
                }
            }
            if(!cando )return false;
            
            return ans;
        }
        if(X==4){
            boolean ans = false;
            boolean cando = false;
            for(int a=0;a<=R-4;a++){
                for(int b=0;b<C;b++){
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<4;c++)
                        board[a+c][b]=true;
                    ans|=solve(board);
                }
            }
            
            if(!cando )return false;
            cando = false;
            for(int a=0;a<=R-2;a++){
                for(int b=0;b<=C-2;b++){
                    
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<2;c++)
                        for(int d=0;d<2;d++)
                            board[a+c][b+d]=true;
                    ans|=solve(board);
                }
            }
            
            if(!cando )return false;
            cando = false;
            for(int a=0;a<=R-3;a++){
                for(int b=0;b<=C-2;b++){
                    
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<3;c++)
                        for(int d=0;d<2;d++)
                            board[a+c][b+d]=true;
                    board[a][b+1]=false;
                    board[a+2][b+1]=false;
                    ans|=solve(board);
                }
            }
            
            if(!cando )return false;
            cando = false;
            for(int a=0;a<=R-3;a++){
                for(int b=0;b<=C-2;b++){
                    
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<3;c++)
                        for(int d=0;d<2;d++)
                            board[a+c][b+d]=true;
                    board[a][b+1]=false;
                    board[a+2][b]=false;
                    ans|=solve(board);
                }
            }
            
            if(!cando )return false;
            cando = false;
            for(int a=0;a<=R-3;a++){
                for(int b=0;b<=C-2;b++){
                    
                    cando = true;
                    boolean[][] board = new boolean[R][C];
                    for(int c=0;c<3;c++)
                        for(int d=0;d<2;d++)
                            board[a+c][b+d]=true;
                    board[a+1][b+1]=false;
                    board[a+2][b+1]=false;
                    ans|=solve(board);
                }
            }
            
            if(!cando )return false;
 
            return ans;
        }
        
        return false;
    }
 
    private static void print(boolean[][] board) {
        for(int a=0;a<board.length;a++){
            for(int b=0;b<board[0].length;b++){
                System.out.print(board[a][b]?"O":"_");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    private static boolean solve(boolean[][] board) {
 
        int thing = 0;
        boolean win = true;
        for(int a=0;a<board.length;a++){
            for(int b=0;b<board[0].length;b++){
                if(board[a][b])thing++;
                else win=false;
                thing<<=1;
            }
        }
        if(win)return true;
        if(MEMO.containsKey(thing))return MEMO.get(thing);
        boolean ans = false;
        loop: for(boolean[][] B : MOS[X]){
            boolean[][] board2 = new boolean[board.length][board[0].length];
            for(int a=0;a<4;a++){
                for(int b=0;b<4;b++){
                    
                    
                    if(B[a][b]){
                        if(a>=board.length||b>=board[0].length)continue loop;
                        board2[a][b]=true;
                    }
                    if(a>=board.length||b>=board[0].length)continue;
                    if(board[a][b]){
                        board2[a][b]=true;
                    }
                    if(board[a][b]&&B[a][b]){
                        continue loop;
                    }
                }
            }
            ans|=solve(board2);
        }
        
        
        
        MEMO.put(thing,ans);
        return ans;
    }
 
 static class DisjointSet {
    int[] p, r;
 
    DisjointSet(int s) {
        p = new int[s];
        r = new int[s];
        for (int i = 0; i < s; i++)
            p[i] = i;
 
    }
    
    void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b)
            return;
        if (r[a] == r[b])
            r[p[b] = a]++;
        else
            p[a] = p[b] = r[a] < r[b] ? b : a;
    }
 
    int find(int x) {
        return p[x] = p[x] == x ? x : find(p[x]);
    }
 
 }
 
 }
