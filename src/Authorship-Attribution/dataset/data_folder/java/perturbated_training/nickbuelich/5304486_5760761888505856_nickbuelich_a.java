import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class A {
    static char[][] grid;
    static int R,C,used;
    static ArrayList<Integer>[] bad;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\nickbuelich\\Downloads\\A-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            R = sc.nextInt();
            C = sc.nextInt();
            grid = new char[R][C];
            for(int a=0;a<R;a++){
                grid[a]=sc.next().toCharArray();
            }
            
            
            boolean[] blocked = new boolean[C];
            Arrays.fill(blocked, true);
            char[] cur = grid[0].clone();
            
            for(int a=0;a<R;a++){
                int i = 0;
                while(i!=C&&grid[a][i]=='?'){
                    i++;
                }
                if(i==C)continue;
                char letter = grid[a][i];
                for(int b=0;b<C;b++){
                    if(grid[a][b]=='?'){
                        grid[a][b]=letter;
                    }
                    letter=grid[a][b];
                }
            }
            for(int a=0;a<R;a++){
                int i = 0;
                while(i!=C&&grid[a][i]=='?'){
                    i++;
                }
                if(i==C&&a!=0){
                    grid[a]=grid[a-1].clone();
                }
            }
            
            for(int a=R-1;a>=0;a--){
                int i = 0;
                while(i!=C&&grid[a][i]=='?'){
                    i++;
                }
                if(i==C&&a!=R-1){
                    grid[a]=grid[a+1].clone();
                }
            }
 
            System.out.printf("Case #%d:%n", t);
            out.printf("Case #%d:%n", t);
            
            for(int a=0;a<R;a++){
                for(int b=0;b<C;b++){
                    System.out.print(grid[a][b]);
                    out.print(grid[a][b]);
                }
                System.out.println();
                out.println();
            }
        }
        out.close();
    }
 }
