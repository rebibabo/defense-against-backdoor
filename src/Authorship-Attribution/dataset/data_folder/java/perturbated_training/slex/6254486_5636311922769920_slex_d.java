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
        int K = scan.nextInt();
        int C = scan.nextInt();
        int S = scan.nextInt();
        int canCheck = S*C;
        if(canCheck <K)return "IMPOSSIBLE";
        long[]levels = new long[C];
        levels[0]=1;
        for(int i=1;i<C;i++)levels[i]=K*levels[i-1];
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<K;i+=C) {
            long x=1;
            int lvl=0;
            for(long j=i;j<K&&j<i+C;j++) {
                x+=j*levels[lvl++];
            }
            sb.append(x);sb.append(" ");
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = D.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-attempt0.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream(name));
        PrintStream out = new PrintStream(new File(outName));
        
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
