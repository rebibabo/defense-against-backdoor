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
 
 
 public class A {
    
    Scanner scan;
    public A(Scanner s) {
        this.scan = s;
    }
 
    public String solve() {
        int smax = scan.nextInt();
        String s = scan.next();
        int extra = 0;
        int total = 0;
        for(int i=0;i<s.length();i++){
            int a = s.charAt(i)-'0';
            if(total+extra<i){
                extra += i - (total+extra);
            }
            total += a;
        }
        return ""+extra;
    }
    int intLine(){
        return parseInt(scan.nextLine());
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
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            System.err.println(c + " done");
        }
        
        System.err.println("All done");
        
    }
 
 }
 
 
