import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.*;
 
 
 
 import static java.lang.Math.*; 
 import static java.util.Arrays.*;
 import static java.lang.Character.*;  
 
 
 public class C {
    
    Scanner scan;
    public C(Scanner s) {
        this.scan = s;
    }
 
    HashSet<Integer>split(String s){
        HashSet<Integer>r = new HashSet<>();
        for(String x:s.split(" "))r.add(ids(x));
        return r;
    }
    
    HashMap<String, Integer>Aw = new HashMap<String, Integer>();
    
    int ids(String s){
        if(Aw.containsKey(s))return Aw.get(s);
        int v = Aw.size();
        Aw.put(s, v);
        return v;
    }
    
    public String solve() {
        int n = intLine()-2;
        String eng = scan.nextLine();
        String fre = scan.nextLine();
        HashSet<Integer>ew = split(eng);
        HashSet<Integer>fw = split(fre);
        
        Integer[][]W = new Integer[n][];
        for(int i=0;i<n;i++){
            String s[] = scan.nextLine().split(" ");
            W[i]=new Integer[s.length];
            for(int j=0;j<s.length;j++)
                W[i][j] =ids(s[j]); 
        }
        boolean[]E = new boolean[Aw.size()];
        for(int x:ew)E[x]=true;
        boolean[]F = new boolean[Aw.size()];
        for(int x:fw)F[x]=true;
        int res = Integer.MAX_VALUE;
        for(int m =0;m<(1<<n);m++) {
            int r =0;
            boolean[] AllE = E.clone();
            boolean[] AllF = F.clone();
            for(int i=0;i<n;i++) {
                for(Integer w:W[i]){
                    if(((m&(1<<i))==0))AllE[w]=true;
                    else AllF[w]=true;;
                }
                
            }
            for(int i=0;i<Aw.size();i++)if(AllF[i]&&AllE[i])r++;
            res = min(res, r);
        }
        return res+"";
    }
    int intLine(){
        return Integer.parseInt(scan.nextLine());
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
 
 
