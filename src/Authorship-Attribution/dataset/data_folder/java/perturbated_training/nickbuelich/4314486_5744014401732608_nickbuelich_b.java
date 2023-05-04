import java.io.File;
 import java.io.PrintWriter;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class B {
    static int M;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        
        
        for(int t=1;t<=T;t++){
            int B = sc.nextInt();
            M = sc.nextInt();
            long[][] board = new long[B][B];
 
 
    
            
            if(MAGIC(0,0, board)){
                System.out.printf("Case #%d: %s%n",t,"POSSIBLE");
                out.printf("Case #%d: %s%n",t,"POSSIBLE");
                
                for(int a=0;a<board.length;a++){
                    for(int b=0;b<board[0].length;b++){
                        out.print(board[a][b]);
                        System.out.print(board[a][b]);
                    }
                    out.println();
                    System.out.println();
                }
            }
            else{
                System.out.printf("Case #%d: %s%n",t,"IMPOSSIBLE");
                out.printf("Case #%d: %s%n",t,"IMPOSSIBLE");
            }
            
        }
        
        out.close();
    }
 
    private static boolean MAGIC(int i, int j, long[][] board) {
        if(j>=board.length){
            return MAGIC(i+1,i+1,board);
        }
        long count = count(board);
        if(count>M)return false;
        if(count==M)return true;
        if(i==(board.length-1))return false;
 
 
        if(MAGIC(i,j+1, board)){
            return true;
        }
        if(i!=j){
            board[i][j]=1;
            if(MAGIC(i,j+1, board)){
                return true;
            }
        }
        board[i][j]=0;
        return false;
    }
    private static long count(long[][] test) {
        long sum = 0;
        long[][] cur = new long[test.length][test.length];
        for(int a=0;a<test.length;a++){
            cur[a]= test[a].clone();
        }
        for(int a=0;a<test.length;a++){
            sum+=cur[0][test.length-1];
            cur = mult(cur,test);
            if(sum>M)return sum;
        }
        return sum;
    }
    public static long[][] fastExpo(long[][] m, long pow) {
        if (pow == 0) {
            long[][] i = new long[m.length][m.length];
            for (int t = 0; t < i.length; t++)
                i[t][t] = 1;
            return i;
        }
        if (pow % 2 == 1)
            return mult(m, fastExpo(m, pow - 1));
        long[][] temp = fastExpo(m, pow / 2);
        return mult(temp, temp);
    }
 
    public static long[][] mult(long[][] a, long[][] b) {
        long[][] x = new long[a.length][b[0].length];
        for (int r = 0; r < x.length; r++)
            for (int c = 0; c < x[r].length; c++)
                for (int i = 0; i < a[0].length; i++) {
                    x[r][c] += a[r][i] * b[i][c];
            
                }
        return x;
    }
 
 
 }
