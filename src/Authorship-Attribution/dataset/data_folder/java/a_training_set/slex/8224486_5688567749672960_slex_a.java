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
 
    int rev(int x){
        int r =0;
        while(x>0){
            r = r*10+x%10;
            x/=10;
        }
        return r;
    }
    public String solve() {
        int n = scan.nextInt();
        boolean[] U = new boolean[2000001];
        LinkedList<int[]>Q = new LinkedList<int[]>();
        Q.add(new int[]{1,1});
        while(true) {
            int[] x = Q.removeFirst();
            int p = x[0];int t = x[1];
            U[p]=true;
            if(p==n)return ""+t;
            if(!U[p+1]){
                U[p+1]=true;
                Q.add(new int[]{p+1,t+1});
            }
            int y = rev(p);
            if(!U[y]){
                U[y]=true;
                Q.add(new int[]{y,t+1});
            }
        }
        
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
        InputStream in = new BufferedInputStream(new FileInputStream("src/"+name));
        PrintStream out = new PrintStream(new File("src/"+outName));
        
        Scanner scan = new Scanner(in);
        
        int N = parseInt(scan.nextLine());
        for(int c=1;c<=N;c++) {
            String res = new A(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out != System.out)System.err.println(c + " done");
        }
        
        if(out != System.out)System.err.println("All done");
        
    }
 
 }
 
 
