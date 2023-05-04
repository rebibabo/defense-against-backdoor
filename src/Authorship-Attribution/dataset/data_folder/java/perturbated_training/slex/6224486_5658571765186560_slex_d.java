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
 
 
 public class D {
    
    Scanner scan;
    public D(Scanner s) {
        this.scan = s;
    }
 
    public String solve() {
        int X = scan.nextInt();
        int R = scan.nextInt();
        int C = scan.nextInt();
        int md = Math.min(R, C);
        if ((R*C)%X !=0) return "RICHARD";
        switch(X) {
            case 1: break;
            case 2: break;
            case 3: 
            case 4: if(md==1)return "RICHARD"; break;
            case 5:
            case 6:
                if(md==2)return "RICHARD"; break;
            default: return "RICHARD";
                
            
        }
        return "GABRIEL";
    }
    int intLine(){
        return parseInt(scan.nextLine());
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
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new D(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            System.err.println(c + " done");
        }
        
        System.err.println("All done");
        
    }
 
 }
 
 
