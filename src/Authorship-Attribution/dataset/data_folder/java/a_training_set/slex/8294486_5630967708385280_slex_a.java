import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class A {
    Scanner scan;
 
    
    class H implements Comparable<H>{
        int pos;int sp;
        @Override
        public int compareTo(H o) {
            if(pos!=o.pos) return pos-o.pos;
            return sp-o.sp;
        }
    }
    public A(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    
    public String solve() {
        int D = scan.nextInt();
        int n = scan.nextInt();
        H[]A = new H[n];
        for(int i=0;i<n;i++){
            A[i]=new H();
            A[i].pos = scan.nextInt();
            A[i].sp = scan.nextInt();
        }
        Arrays.sort(A);
        H cur = A[n-1];
        for(int i=n-2;i>=0;i--){
            if(A[i].sp <= cur.sp)cur = A[i];
            else {
                long p1 = A[i].pos;long p2 = cur.pos;
                long v1 = A[i].sp;long v2 = cur.sp;
                if(p1*v2-p2*v1<(v2-v1)*D) {
                    cur = A[i];
                }
            }
        }
        double t = 1.0*(D-cur.pos)/cur.sp;
        double res = D/t;
        return ""+res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+A.class.getName();
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
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
