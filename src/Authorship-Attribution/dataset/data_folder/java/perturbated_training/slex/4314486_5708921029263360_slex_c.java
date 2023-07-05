import static java.lang.Math.*;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 public class C {
    Scanner scan;
 
    public C(Scanner s) {
        scan = s;
    }
    
    int intLine(){
        return Integer.parseInt(scan.nextLine());
    }
    int[][]A;
    String print(int bm, int all){
        String res = ""+Integer.bitCount(bm);
        for(int i=0;i<all;i++){
            if((bm&(1<<i))==0)continue;
            res+="\n"+(A[i][0]+1)+" "+(A[i][1]+1)+" "+(A[i][2]+1);
        }
        return res;
    }
    
    
    
    public String solve() {
        int J = scan.nextInt();
        int P = scan.nextInt();
        int S = scan.nextInt();
        int K = scan.nextInt();
        int all = J*P*S;
        A = new int[all][3];
        int pp=0;
        for(int j=0;j<J;j++)for(int p=0;p<P;p++)for(int s=0;s<S;s++){
            A[pp][0]=j;A[pp][1]=p;A[pp][2]=s;
            pp++;
        }
        int bm=0;
        if(J==3){
            if(K==1)return print(22102794,all);
            if(K==2)return print(62580637,all);
            return print((1<<all)-1,all);
        }
        for(int m=0;m<(1<<all);m++) {
            int[][]JP = new int[J][P];
            int[][]JS = new int[J][S];
            int[][]PS = new int[P][S];
            boolean ok = true;
            for(int i=0;i<all;i++){
                if((m&(1<<i))==0)continue;
                int j = A[i][0];int p =A[i][1];int s=A[i][2];
                JP[j][p]++;JS[j][s]++;PS[p][s]++;
                if(JP[j][p]>K||JS[j][s]>K||PS[p][s]>K){ok=false;break;}
            }
            if(ok &&Integer.bitCount(m) >Integer.bitCount(bm))bm=m;
        }
        return print(bm,all);
    }
    
    
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.US);
        String cn = "src/"+C.class.getName();
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
            String res = new C(scan).solve();
            out.printf("Case #%d: %s\n",c, res);
            if(out!=System.out)System.err.println(c + " done");
        }
        if(out!=System.out)System.err.println("All done");
        
    }
 }
