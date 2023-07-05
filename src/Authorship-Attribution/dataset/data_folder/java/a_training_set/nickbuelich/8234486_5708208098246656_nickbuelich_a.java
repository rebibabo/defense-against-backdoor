import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 
 public class A {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int H = sc.nextInt();
            int W = sc.nextInt();
            char[][] grid = new char[H][W];
            for(int a=0;a<H;a++)grid[a]=sc.next().toCharArray();
            boolean impossible = false;
            for(int a=0;a<H;a++){
                stuff: for(int b=0;b<W;b++){
                    if(grid[a][b]=='.')continue;
                    boolean bad = true;
                    for(int delta = 0; delta<4;delta++){
                        for(int d=1;d<=(H+W);d++){
                            int nx = b+(dx[delta]*d);
                            int ny = a+(dy[delta]*d);
                            if(nx<0||ny<0||nx>=W||ny>=H)continue;
                            if(grid[ny][nx]!='.')bad=false;
                        }
                    }
                    if(bad)impossible=true;
                }
            }
            if(impossible){
                out.printf("Case #%d: IMPOSSIBLE%n",t);
            }
            else{
                int ans = 0;
                for(int a=0;a<H;a++){
                    stuff: for(int b=0;b<W;b++){
                        if(grid[a][b]=='.')continue;
                        boolean bad = true;
                        int delta = magic(grid[a][b]);
                        for(int d=1;d<=(H+W);d++){
                            int nx = b+(dx[delta]*d);
                            int ny = a+(dy[delta]*d);
                            if(nx<0||ny<0||nx>=W||ny>=H)continue;
                            if(grid[ny][nx]!='.')bad=false;
                        }
                        if(bad){
                            ans++;
                        }
                        
                    }
                }
                out.printf("Case #%d: %d%n",t,ans);
 
            }
        }
        
        
        out.close();
    }
    private static int magic(char c) {
        if(c=='v')return 0;
        if(c=='^')return 1;
        if(c=='>')return 2;
        if(c=='<')return 3;
        
        return -1;
    }
 
 }
