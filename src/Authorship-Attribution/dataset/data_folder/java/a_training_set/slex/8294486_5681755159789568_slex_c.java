import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class C {
    Scanner scan;
 
    public C(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    int[]E;int[][]D;int[]S;
    int[][]DD;
    double[][]M;
    int n;
    double doit(int c, int hc){
        if(M[c][hc]!=-1)return M[c][hc];
        double res =1e60;
        if(c==n-1)return 0;
        int left = E[hc] - DD[hc][c];
        if(left>=D[c][c+1]){
            double r = 1.0*D[c][c+1]/S[hc] + doit(c+1,hc);
            res = min(res,r);
        }if(E[c]>=D[c][c+1]){
            double r = 1.0*D[c][c+1]/S[c]+doit(c+1,c);
            res = min(res,r);
        }
        return M[c][hc]=res;
    }
    
    double solveBrut(int from, int to){
        DD = new int[D.length][D.length];
        for(int i=0;i<D.length;i++)for(int j=i+1;j<D.length;j++){
            DD[i][j]=DD[i][j-1]+D[j-1][j];
        }
        M = new double[n][n];
        for(double[]m:M)Arrays.fill(m, -1);
        return doit(0,0);
    }
    public String solve() {
        n = scan.nextInt();int q = scan.nextInt();
        E = new int[n];S=new int[n];
        for(int i=0;i<n;i++){
            E[i]=scan.nextInt();
            S[i]=scan.nextInt();
        }
        
        D = new int[n][n];
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)D[i][j]=scan.nextInt();
        String res ="";
        for(int qq=0;qq<q;qq++){
            int from = scan.nextInt();
            int to = scan.nextInt();
            res+=solveBrut(from,to)+" ";
        }
        return res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+C.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt0.in";
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
            String res = new C(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
