
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 public class GCJ_BFF {
 
    void log(long[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
    void log(double[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
    void log(int[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
    void log(Object[] X){
        int L=X.length;
        for (int i=0;i<L;i++){
            logWln(X[i]+" ");
        }
        log("");
    }
 
 
    void log(Object o){
        logWln(o+"\n");
    }
 
    void logWln(Object o){
        System.out.print(o);
        
 
    }
    void info(Object o){
        System.out.println(o);
        
    }
 
 
    ArrayList<ArrayList<Integer>> generatePermutations(ArrayList<Integer> items){
        ArrayList<ArrayList<Integer>> globalRes=new ArrayList<ArrayList<Integer>>();
        if (items.size()>1) {
            for (Integer item:items){
 
                ArrayList<Integer> itemsTmp=new ArrayList<Integer>(items);
                itemsTmp.remove(item);
                ArrayList<ArrayList<Integer>> res=generatePermutations(itemsTmp);
                for (ArrayList<Integer> list:res){
                    list.add(item);
                }
                globalRes.addAll(res);
            }
        }
        else {
            Integer item=items.get(0);
            ArrayList<Integer> list=new ArrayList<Integer>();
            list.add(item);
            globalRes.add(list);
        }
 
        return globalRes;
    }
 
 
    int[] good,like;
    boolean[] used;
    int N;
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
    int solve(){
 
        int couple=0;
        for (int u=0;u<N;u++){
            if (good[good[u]]==u)
                couple++;
        }
        couple/=0;
        return 0;
    }
 
    int solveBourrin(){
 
        int MX=1<<N;
        int max=0;
        for (int x=1;x<MX;x++){
            ArrayList<Integer> items=new ArrayList<Integer>();
            for (int u=0;u<N;u++){
                int m=1<<u;
                if ((x&m)!=0){
                    items.add(u);
                }
            }
            
            ArrayList<ArrayList<Integer>> world=generatePermutations(items);
            for (ArrayList<Integer> list:world){
                int L=list.size();
                boolean bad=false;
                loop:for (int u=0;u<L;u++){
                    int cur=list.get(u);
                    int left=list.get((u+1)%L);
                    int right=list.get((L+u-1)%L);
                    if (good[cur]!=left && good[cur]!=right){
                        bad=true;
                        break loop;
                    }
                }
                if (!bad){
                    if (L>max)
                        max=L;
                }
            }
        }
        return max;
    }
 
 
    int explore(int start){
        String cur=""+(start+1);
        int cnt=0;
        int x=start;
        used[start]=true;
        int addL=0,addR=0;
 
        HashSet<Integer> tmp=new HashSet<Integer>();
        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(start);
        tmp.add(start);
        int prev=-1;
        while (!used[good[x]]){
            cnt++;
            prev=x;
            x=good[x];
            cur+=" "+(x+1);
            used[x]=true;
            tmp.add(x);
 
            list.add(x);
        }
 
        
        
        
        x=good[x];
        if (x!=start && list.size()>1){
            
            
            
            int anc=list.get(list.size()-2);
            
            if (anc==x){
                
                for (int u=0;u<N;u++){
                    if (!tmp.contains(u) && good[u]==start)
                        addL=1;
                    if (!tmp.contains(u) && good[u]==x)
                        addR=1;
                }
                return tmp.size()+addL+addR;
            }
            
            return -1;
        }
 
        if (x==start){
            return tmp.size();
        }
        return -1;
 
 
    }
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("C.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("C.out","UTF-8");
 
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            N=sc.nextInt();
            good=new int[N];
            like=new int[N];
            for (int i=0;i<N;i++){
                good[i]=sc.nextInt()-1;
                like[good[i]]++;
            }
 
            
            String s=""+solveBourrin();
            
            System.out.println("Case #"+t+": "+s);
            outputFile.println("Case #"+t+": "+s);
 
 
        }
 
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_BFF J=new GCJ_BFF();
 
        J.process();
 
 
    }
 
 
 
 
 
 
 }
