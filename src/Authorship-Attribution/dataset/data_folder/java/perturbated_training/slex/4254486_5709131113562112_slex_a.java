import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 import static java.util.Arrays.*;
 import static java.lang.Math.*;
 
 
 public class A {
    
    Scanner scan;
    public A(Scanner s) {
        this.scan = s;
    }
 
    class N {
        ArrayList<N>ch = new ArrayList<A.N>();
        int s;
        int par;
        N(int a, int b){s=a;par=b;}
    }
    
    int count(N p, int a, int b) {
        if(p.s<a||p.s>b)return 0;
        int res = 1;
        for(N q:p.ch)res+=count(q, a, b);
        return res;
    }
    public String solve() {
        int n = scan.nextInt();int D = scan.nextInt();
        int s0 =scan.nextInt();int as=scan.nextInt();int cs = scan.nextInt();int rs=scan.nextInt();
        int m0=scan.nextInt();int am=scan.nextInt();int cm=scan.nextInt();int rm=scan.nextInt();
        int[]S = new int[n];int[] P = new int[n];
        S[0]=s0;P[0]=m0;
        N[]nodes = new N[n];
        nodes[0]=new N(s0, -1);
        for(int i=1;i<n;i++){
            S[i]= (int)((1L*S[i-1]*as+cs)%rs);
            P[i]= (int)((1L*P[i-1]*am+cm)%rm);
 
 
        }
        for(int i=1;i<n;i++){
            P[i]%=i;
            nodes[i]=new N(S[i], P[i]);
            nodes[P[i]].ch.add(nodes[i]);
        }
        int res = 0;
        for(int a=s0-D;a<=s0+D;a++){
            int r = count(nodes[0], a, a+D);
            res = max(res, r);
        }
        return ""+res;
    }
    
    int[] intArr() {
        int n = scan.nextInt();
        int[]res = new int[n];
        for(int i=0;i<n;i++)res[i]=scan.nextInt();
        return res;
    }
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = A.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt4.in";
        String largeName = cn+"-large.in";
        String name = sampleName;
        name = "src/" + name;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        PrintStream out = null;
        if(args.length>0) {
            name = args[0];
            outName = name.substring(0, name.indexOf('.'))+".out";
            out = new PrintStream(new File(outName));
        }
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        
        if (out == null) out = System.out;
        Scanner scan = new Scanner(in);
        
        int N = Integer.parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out != System.out)System.err.println(c + " done");
        }
        if(out != System.out)System.err.println("All done");
    }
 
 }
 
 
