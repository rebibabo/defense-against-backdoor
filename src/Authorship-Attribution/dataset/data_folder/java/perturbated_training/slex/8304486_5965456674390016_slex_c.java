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
    int C;
    int[]E;
    int[]L;
    int[]D;
    public C(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    
    HashMap<Long,Integer>H=new HashMap<>();
    int doit(int p, int h, int v){
        long K = p +(1L<<10)*h +(1L<<20)*v;
        if(H.containsKey(K))return H.get(K);
        if(p==0) {
            if(v==((1<<2*C)-1)){
                return 0;
            }
        }
        int res = Integer.MAX_VALUE;
        int e1 = E[2*p];
        int l1=L[2*p];
        int d1=D[2*p];
        int t1=2*p;
        if((v&(1<<(t1)))==0) {
            int r =0;
            if(l1>=h)r=l1-h;
            else r = l1+24-h;
            r+=d1;
            int rr = doit(e1,(l1+d1)%24,v|(1<<t1) );
            if(rr!=Integer.MAX_VALUE){
                r+=rr;
                res = min(res,r);
            }
        }
        e1 = E[1+2*p];
        l1=L[1+2*p];
        d1=D[1+2*p];
        t1 = 2*p+1;
        if((v&(1<<t1))==0) {
            int r =0;
            if(l1>=h)r=l1-h;
            else r = l1+24-h;
            r+=d1;
            int rr = doit(e1,(l1+d1)%24,v|(1<<t1) );
            if(rr!=Integer.MAX_VALUE){
                r+=rr;
                res = min(res,r);
            }
        }
        H.put(K, res);
        return res;
    }
    
    public String solve() {
        C=scan.nextInt();
        E = new int[2*C];
        L = new int[2*C];
        D = new int[2*C];
        for(int i=0;i<2*C;i++){
            E[i]=scan.nextInt()-1;
            L[i]=scan.nextInt();
            D[i]=scan.nextInt();
        }
        int res = doit(0,0,0);
        return ""+res;
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
