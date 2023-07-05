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
    
    char[][]C;
    
    void div(int x1,int x2, int y1, int y2){
        HashMap<Character, int[]>H = new HashMap<>();
        for(int i=x1;i<x2;i++)for(int j=y1;j<y2;j++){
            if(C[i][j]=='?')continue;
            H.put(C[i][j], new int[]{i,j});
        }
        if(H.size()==1){
            char c = H.keySet().iterator().next();
            for(int i=x1;i<x2;i++)for(int j=y1;j<y2;j++){
                C[i][j]=c;
            }
        }else {
            Iterator<Character>it =H.keySet().iterator(); 
            char c1=it.next();
            char c2 = it.next();
            int cx1 = H.get(c1)[0];
            int cy1 = H.get(c1)[1];
            int cx2 = H.get(c2)[0];
            int cy2 = H.get(c2)[1];
            if(cx1==cx2){
                int dy = min(cy1,cy2)+1;
                div(x1,x2,y1,dy);
                div(x1,x2,dy,y2);
            }
            else {
                int dx = min(cx1,cx2)+1;
                div(x1,dx,y1,y2);
                div(dx,x2,y1,y2);
            }
        }
    }
    
    public String solve() {
        int r = scan.nextInt();int c = scan.nextInt();
        C=new char[r][];
        for(int i=0;i<r;i++){
            C[i]=scan.next().toCharArray();
        }
        div(0,r,0,c);
        String res ="";
        for(int i=0;i<r;i++){
            res+="\n"+new String(C[i]);
        }
        return res;
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
