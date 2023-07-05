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
 
 class hik{
    int start;long speed;
    hik(int a,long b){start = a;speed=b;}
    long fin(){
        return (360-start)*speed/360;
    }
    long full(){
        return speed;
    }
 }
 public class C {
    
    Scanner scan;
    public C(Scanner s) {
        this.scan = s;
    }
 
    public String solve() {
        ArrayList<hik>A = new ArrayList();
        int groups = scan.nextInt();
        for(int i=0;i<groups;i++){
            int d = scan.nextInt();
            int h = scan.nextInt();
            long m = scan.nextInt();
            for(int j=0;j<h;j++){
                A.add(new hik(d, (m+j)*360));
            }
        }
        if(A.size()<2)return "0";
        long f1 = A.get(0).fin();
        long f2 = A.get(1).fin();
        long ff1 = f1 + A.get(0).full();
        long ff2=f2+A.get(1).full();
        if(ff1<=f2)return "1";
        if(ff2<=f1)return "1";
        return "0";
    }
    int intLine(){
        return parseInt(scan.nextLine());
    }
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = C.class.getName();
        String sampleName = cn+"-sample.in";
        String smallName = cn+"-small-1-attempt2.in";
        String largeName = cn+"-large.in";
        String name = smallName;
        String outName = name.substring(0, name.indexOf('.'))+".out";
        InputStream in = new BufferedInputStream(new FileInputStream("src/"+name));
        PrintStream out = new PrintStream(new File("src/"+outName));
        
        Scanner scan = new Scanner(in);
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new C(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out != System.out)System.err.println(c + " done");
        }
        
        if(out != System.out)System.err.println("All done");
        
    }
 
 }
 
 
