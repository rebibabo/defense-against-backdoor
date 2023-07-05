import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Integer.*;
 import static java.lang.Character.*;  
 
 
 public class B {
    
    Scanner scan;
    public B(Scanner s) {
        this.scan = s;
    }
 
    public String solve() {
        int r = scan.nextInt();int c = scan.nextInt();int n = scan.nextInt();
        int res = Integer.MAX_VALUE;
        for(int m =0;m<(1<<(r*c));m++) {
            if(Integer.bitCount(m)!=n)continue;
            boolean[][]U = new boolean[r][c];
            for(int i=0;i<r*c;i++){
                if((m&(1<<i))==0)continue;
                int a = i/c;int b = i%c;
                U[a][b]=true;
            }
            int un=0;
            for(int i=0;i<r;i++)for(int j=0;j<c;j++){
                if(!U[i][j])continue;
                for(int[] d: new int[][]{{-1,0},{1,0},{0,-1},{0,1}}){
                    int jj = j+d[1];int ii=i+d[0];
                    if(ii>=0 &&ii<r&&jj>=0&&jj<c &&U[ii][jj])un++;
                }
            }
            res = Math.min(res,un);
        }
        return ""+res/2;
    }
    int intLine(){
        return parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = B.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt0.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream("src/"+name));
        PrintStream out = new PrintStream(new File("src/"+outName));
        
        Scanner scan = new Scanner(in);
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new B(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out != System.out)System.err.println(c + " done");
        }
        
        if(out != System.out)System.err.println("All done");
        
    }
 
 }
 
 
