import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.math.BigInteger;
 import java.util.*;
 
 public class C {
    Scanner scan;
 
    public C(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    
    boolean test(String s, int rad, long div) {
        BigInteger bi = new BigInteger(s,rad);
        return bi.mod(BigInteger.valueOf(div)).equals(BigInteger.ZERO);
    }
    
    public String solve() {
        int n = scan.nextInt();
        int J = scan.nextInt();
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        int patLen = n/2-2;
        for(int m =0;m<J;m++){
            String pat = Integer.toBinaryString(m);
            while(pat.length() <patLen)pat="0"+pat;
            pat = "1"+pat+"1";
            String whole = pat+pat;
            sb.append(whole);sb.append(" ");
            for(int rad=2;rad<11;rad++) {
                Long div = Long.parseLong(pat,rad);
                sb.append(div);
                sb.append(rad<10?" ":"\n");
                if(!test(whole,rad,div)){
                    System.out.println("ERROR");
                }
            }
        }
 
        return sb.toString();
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = C.class.getName();
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
            String res = new C(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
