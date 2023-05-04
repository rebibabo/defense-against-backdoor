import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.StringTokenizer;
 
 
 
 class GCJ_InfiniteHousePancakes_v2 {
 
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
 
 
    
    StringTokenizer st;
    BufferedReader in;
    BufferedWriter out;
 
    int smax;
    String S;
 
    class State {
        int[] freq;
        int max;
    }
 
 
    int solveBourrin(State state){
 
        if (state.max<=3)
            return state.max;
        int min=state.max;
        State tmp;
        
        tmp=new State();
        tmp.freq=new int[state.max];
        tmp.max=state.max-1;
        System.arraycopy(state.freq,1,tmp.freq,0,state.max);
        int res1=1+solveBourrin(tmp);
        if (min<res1)
            min=res1;
        tmp=new State();
        if (state.freq[state.max]>1){
            tmp.freq=new int[state.max+1];
            for (int A=1;A<state.max;A++){
                System.arraycopy(state.freq,0,tmp.freq,0,state.max+1);
                tmp.freq[state.max]--;
                tmp.freq[A]++;
                tmp.freq[state.max-A]++;
                tmp.max=state.max;
                int res2=1+solveBourrin(tmp);
                if (res2<min)
                    min=res2;
            }
 
        } else {
            tmp.freq=new int[state.max];
            for (int A=1;A<state.max;A++){
                System.arraycopy(state.freq,0,tmp.freq,0,state.max);
                tmp.freq[A]++;
                tmp.freq[state.max-A]++;
                int x=state.max-1;
                while (tmp.freq[x]==0)
                    x--;
                tmp.max=x;
                
                int res2=1+solveBourrin(tmp);
                if (res2<min)
                    min=res2;
            }
        }
        return min;
 
    }
 
    int solve2(State state){
 
        int max=state.max;
 
        
 
        int min=max;
 
        boolean ok=true;
        int time=0;
        while (max>=2){
            int A=max/2;
            int B=max-A;
            state.freq[A]++;
            state.freq[B]++;
            state.freq[max]--;
            while (state.freq[max]==0)
                max--;
            time++;
            if (time+max<min)
                min=time+max;
        }
        return min;
    }
 
    void solveRandom(ArrayList<Integer> state){
        int NTEST=10000;
        int min=Integer.MAX_VALUE;
        Random r=new Random();
        for (int t=0;t<NTEST;t++){
            ArrayList<Integer> tmp=new ArrayList<Integer>();
 
        }
    }
 
    int solveSlow(ArrayList<Integer> state,String s){
 
        
        int L=state.size();
 
        int fmax=0;
 
        ArrayList<Integer> tmp=new ArrayList<Integer>();
        for (int i=0;i<L;i++){
            int x=state.get(i);
            if ((x=state.get(i))>1){
                tmp.add(x-1);
            }
            if (x>fmax)
                fmax=x;
        }
 
        int min=fmax;
 
        if (fmax>3){
            int res1=1+solveSlow(tmp,s+"-");
            if (res1<min)
                min=res1;
            
            for (int u=0;u<L;u++){
                tmp=new ArrayList<Integer>();
                boolean added=false;
                for (int i=0;i<L;i++){
                    int x=state.get(i);
                    if (i==u && x>1){
                        tmp.add(x/2);
                        tmp.add(x-x/2);
                        added=true;
                    } else {
                        tmp.add(x);
                    }
                }
                if (added){
                    
                    int res2=1+solveSlow(tmp,s+"-");
                    if (res2<min)
                        min=res2;
                }
            }
        }
 
        return min;
    }
 
    
 
 
 
    int solve(State state){
        
        if (state.max<=3)
            return state.max;
 
        State tmp;
        
        tmp=new State();
        tmp.freq=new int[state.max];
        tmp.max=state.max-1;
        System.arraycopy(state.freq,1,tmp.freq,0,state.max);
        int res1=1+solve(tmp);
 
        
        tmp=new State();
        if (state.freq[state.max]>1){
            tmp.freq=new int[state.max+1];
            System.arraycopy(state.freq,0,tmp.freq,0,state.max+1);
            tmp.freq[state.max]--;
            tmp.freq[state.max/2]++;
            tmp.freq[state.max-state.max/2]++;
            tmp.max=state.max;
        } else {
            tmp.freq=new int[state.max];
            System.arraycopy(state.freq,0,tmp.freq,0,state.max);
            tmp.freq[state.max/2]++;
            tmp.freq[state.max-state.max/2]++;
            int x=state.max-1;
            while (tmp.freq[x]==0)
                x--;
            tmp.max=x;
        }
        int res2=1+solve(tmp);
        
        return Math.min(res1, res2);
    }
 
    int solve3(State state){
        
        int min=Integer.MAX_VALUE;
        if (state.max<=3)
            return state.max;
 
        State tmp;
        
        tmp=new State();
        tmp.freq=new int[state.max];
        tmp.max=state.max-1;
        System.arraycopy(state.freq,1,tmp.freq,0,state.max);
        min=Math.min(min,1+solve3(tmp));
 
        
        for (int i=4;i<=state.max;i++){
            tmp=new State();
            if (i<state.max && state.freq[i]>0){
            
                tmp.freq=new int[state.max+1];
                System.arraycopy(state.freq,0,tmp.freq,0,state.max+1);
                tmp.freq[i]--;
                tmp.freq[i/2]++;
                tmp.freq[i-i/2]++;
                tmp.max=state.max;
                min=Math.min(min, 1+solve3(tmp));
            } else {
 
                if (state.freq[state.max]>1){
                    tmp.freq=new int[state.max+1];
                    System.arraycopy(state.freq,0,tmp.freq,0,state.max+1);
                    tmp.freq[state.max]--;
                    tmp.freq[state.max/2]++;
                    tmp.freq[state.max-state.max/2]++;
                    tmp.max=state.max;
                    min=Math.min(min,1+solve3(tmp));
                } else {
                    tmp.freq=new int[state.max];
                    System.arraycopy(state.freq,0,tmp.freq,0,state.max);
                    tmp.freq[state.max/2]++;
                    tmp.freq[state.max-state.max/2]++;
                    int x=state.max-1;
                    while (tmp.freq[x]==0)
                        x--;
                    tmp.max=x;
                    min=Math.min(min, 1+solve3(tmp));
                }
            }
            
        }
        
        return min;
    }
 
 
 
    void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
 
 
        
        File inputFile=new File("B-small-attempt5.in");
 
 
 
        
        PrintWriter outputFile= new PrintWriter("B-small-attempt5.out");
 
 
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
            log("t:"+t);
            int D=sc.nextInt();
            int[] x=new int[D];
            int pmax=0;
            ArrayList<Integer> list=new ArrayList<Integer>();
            for (int i=0;i<D;i++){
                x[i]=sc.nextInt();
                if (x[i]>pmax)
                    pmax=x[i];
                list.add(x[i]);
            }
            State state=new State();
            state.max=pmax;
            state.freq=new int[pmax+1];
            for (int i=0;i<D;i++){
                state.freq[x[i]]++;
            }
            log(state.freq);
            log("Solving");
            log("list:"+list);
            int ss=solve2(state);
        
            System.out.println("Case #"+t+": "+ss);
            outputFile.println("Case #"+t+": "+ss);
 
 
        }
        outputFile.println("");
 
 
        sc.close();
        outputFile.close();
 
 
    }
 
 
 
    public static void main(String[] args) throws Exception {
        GCJ_InfiniteHousePancakes_v2 J=new GCJ_InfiniteHousePancakes_v2();
 
        J.process();
 
 
    }
 
 
 
 
 }