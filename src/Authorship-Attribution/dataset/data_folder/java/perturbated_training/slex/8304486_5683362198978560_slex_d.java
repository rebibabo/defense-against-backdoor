import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class D {
    Scanner scan;
 
    public D(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    
    public String solve() {
        int R = scan.nextInt();
        int C =scan.nextInt();
        int N = scan.nextInt();
        long D = scan.nextInt();
        long[][]M = new long[R][C];
        boolean[][]st =new boolean[R][C];
        long mx=0;
        for(int i=0;i<N;i++){
            int r =scan.nextInt()-1;
            int c = scan.nextInt()-1;
            st[r][c]=true;
            int b = scan.nextInt();
            M[r][c]=b;
            mx = max(mx,b+D*1000);
        }
        for(int r=0;r<R;r++)for(int c=0;c<C;c++)if(!st[r][c])M[r][c]=mx;
        int[][]dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(true){
            boolean ok=true;
            boolean ch=false;
            for(int r=0;r<R;r++)for(int c=0;c<C;c++){
                for(int[]d:dir){
                    int rr = r+d[0];int cc=c+d[1];
                    if(rr<0||rr>=R||cc<0||cc>=C)continue;
                    if(M[r][c]>M[rr][cc] && M[r][c]-M[rr][cc]>D){
                        ok = false;
                        if(!st[r][c]){
                            M[r][c]=M[rr][cc]+D;
                            ch=true;
                        }
                    }
                }
            }
            if(ok){
                long res=0;
                for(int r=0;r<R;r++)for(int c=0;c<C;c++){
                    res = (res+M[r][c])%1000000007;
                }
                return res+"";
            }
            if(!ch)return "IMPOSSIBLE";
        }
        
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+D.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt2.in";
        String largeName = cn+"-large.in";
        
        String name = smallName;
        
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out =null;
        out = new PrintStream(new File(outName));
        if(out==null) out = System.out;
        Scanner scan = new Scanner(in);
        
        int N = Integer.parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new D(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
