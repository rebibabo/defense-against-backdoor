import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class B {
    Scanner scan;
 
    public B(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    
    class ch{
        ch(char a, int b){c=a;x=b;}
        char c;int x;
        @Override
        public String toString() {
            
            return c+" "+x;
        }
    }
    
    String doit(ch a, ch b, ch c){
        StringBuffer sb = new StringBuffer();
        ch curr;
        ch start;
        if(a.x>0){
            sb.append(a.c);a.x--;start=a;
        }else if(b.x>0){
            sb.append(b.c);b.x--;start=b;
        }else{
            sb.append(c.c);c.x--;start=c;
        }
        curr=start;
        while(a.x+b.x+c.x>0){
            ch o1;ch o2;
            if(a==curr){
                o1 = b;o2=c;
            }else if(b==curr){
                o1=a;o2=c;
            }else{
                o1=a;o2=b;
            }
            if(o1.x+o2.x==0)return null;
            if(o1.x>o2.x){
                curr = o1;
            } else if(o2.x>o1.x){
                curr = o2;
            }else {
                if(o1==start)curr=o1;
                else curr=o2;
            }
            sb.append(curr.c);curr.x--;
        }
        if(curr==start)return null;
        return sb.toString();
    }
    
    public String solve() {
        int n = scan.nextInt();
        int r = scan.nextInt();
        int o = scan.nextInt();
        int y = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        int v = scan.nextInt();
        String res = doit(new ch('R',r), new ch('Y',y),new ch('B',b));
        if(res ==null)res = "IMPOSSIBLE";
        return res;
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+B.class.getName();
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
            String res = new B(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
