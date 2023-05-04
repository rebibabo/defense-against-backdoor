import java.io.File;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class C {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter("C.out");
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int N = sc.nextInt();
            int ans = 0;
            String[] LEFT = new String[N];
            String[] RIGHT = new String[N];
            
            for(int a=0;a<N;a++){
                LEFT[a] = sc.next();
                RIGHT[a] = sc.next();
            }
            
            for(int mask = 0; mask<1<<N;mask++){
                LinkedList<String> L = new LinkedList<String>();
                LinkedList<String> R = new LinkedList<String>();
                HashSet<String> BAD = new HashSet<String>();
                for(int a=0;a<N;a++){
                    if(((mask>>a)&1)==1){
                        L.add(LEFT[a]);
                        R.add(RIGHT[a]);
                    }
                    else{
                        BAD.add(LEFT[a]+" "+RIGHT[a]);
                    }
                } 
                HashSet<String> ALL = new HashSet<String>();
                for(String l : L){
                    for(String r : R){
                        ALL.add(l+" "+r);
                    }
                }
 
 
 
 
 
                int found = 0;
                for(String a : ALL){
                    if(BAD.contains(a)){
                        found++;
                    }
                }
                ans = Math.max(ans,found);
            }
            
            
            System.out.printf("Case #%d: %s\n",t,ans);
            out.printf("Case #%d: %s\n",t,ans);
        }
        
        out.close();
    }
 }
