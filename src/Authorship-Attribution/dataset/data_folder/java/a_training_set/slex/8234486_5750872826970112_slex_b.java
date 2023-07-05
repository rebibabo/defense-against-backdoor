import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Character.*;  
 
 
 public class B {
    
    Scanner scan;
    public B(Scanner s) {
        this.scan = s;
    }
 
    
    double time(double v, double r) {
        return v/r;
    }
    String solve2(double V, double X, double x1, double x2, double r1, double r2) {
        if(X < min(x1, x2))return "IMPOSSIBLE";
        if(X>max(x1, x2))return "IMPOSSIBLE";
        if(x1==x2) {
            return ""+time(V, r1+r2);
        }
        double v1 = V*(X-x2)/(x1-x2);
        double v2 = V-v1;
        double res =max(time(v1, r1), time(v2, r2));
        return res+"";
    }
    
    
    String solve1(double V, double X, double x, double r) {
        if(X!=x)return "IMPOSSIBLE";
        double res = time(V, r);
        return ""+res;
    }
    public String solve() {
        int n = scan.nextInt();
        double v = scan.nextDouble();double x = scan.nextDouble();
        double[]R = new double[n];double[]C = new double[n];
        for(int i=0;i<n;i++){
            R[i]=scan.nextDouble();C[i]=scan.nextDouble();
        }
        if(n>2)return "DUNNO";
        if(n==2)return solve2(v, x, C[0], C[1], R[0], R[1]);
        if(n==1)return solve1(v,x,C[0], R[0]);
        return "foo";
    }
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = B.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt1.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out = new PrintStream(new File(outName));
        
        Scanner scan = new Scanner(in);
        
        int N = Integer.parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new B(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 
 }
 
 
