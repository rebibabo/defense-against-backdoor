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
 
    public A(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    
    public String solve() {
        String s = scan.next();
        int k = scan.nextInt();
        char[]S = s.toCharArray();
        int res =0;
        for(int i=0;i+k<=s.length();i++){
            if(S[i]=='-'){
                res++;
                for(int j=0;j<k;j++){
                    int ii = i+j;
                    if(S[ii]=='-')S[ii]='+';
                    else if(S[ii]=='+')S[ii]='-';
                }
            }
        }
        for(int i=0;i<S.length;i++)if(S[i]=='-')return "IMPOSSIBLE";
        return ""+res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = A.class.getName();
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
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
 
    }
 }
