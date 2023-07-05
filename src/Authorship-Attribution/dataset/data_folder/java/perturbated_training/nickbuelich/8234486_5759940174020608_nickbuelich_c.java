import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Scanner;
 
 
 
 public class C{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter(new File("C.out"));
        int TT = sc.nextInt();
        for(int t=1;t<=TT;t++){
            int N = sc.nextInt();
            sc.nextLine();
            String[] Q = new String[N];
            ArrayList<Integer>[] stuff = new ArrayList[N];
            for(int a=0;a<N;a++){
                Q[a]=sc.nextLine();
                
            }
            HashMap<String,Integer> HM = new HashMap<String,Integer>();
            String ALL = "";
            for(int a=2;a<N;a++)ALL+=" "+Q[a];
            Scanner two = new Scanner(ALL);
            while(two.hasNext()){
                String temp = two.next();
                if(!HM.containsKey(temp)){
                    HM.put(temp, HM.size());
                }
            }
            
            int S = HM.size();
            ALL = Q[0]+" "+Q[1];
            two = new Scanner(ALL);
            while(two.hasNext()){
                String temp = two.next();
                if(!HM.containsKey(temp)){
                    HM.put(temp, HM.size());
                }
            }
            int OS = HM.size();
            int[] E = new int[OS];
            two = new Scanner(Q[0]);
            while(two.hasNext()){
                String temp = two.next();
                E[HM.get(temp)]|=1;
            }
            two = new Scanner(Q[1]);
            while(two.hasNext()){
                String temp = two.next();
                E[HM.get(temp)]|=2;
            }
            int base = 0;
            for(int a=S;a<OS;a++)
                if(E[a]==3)base++;
            int best = Integer.MAX_VALUE;
            
            for(int a=0;a<N;a++){
                stuff[a]=new ArrayList<Integer>();
                Scanner three = new Scanner(Q[a]);
                while(three.hasNext()){
                    stuff[a].add(HM.get(three.next()));
                }
            }
            
            
            for(int mask=0;mask<1<<(N-2);mask++){
                int[] G = E.clone();
                for(int a=0;a<N-2;a++){
                    int type = 1+((mask>>a)&1);
                    for(Integer x : stuff[a+2]){
                        G[x]|=type;
                    }
 
 
 
 
                }
                int temp = base;
                for(int x=0;x<S;x++){
                    if(G[x]==3)temp++;
                }
                best = Math.min(temp,best);
            }
            out.printf("Case #%d: %d%n",t,best);
            System.out.printf("Case #%d: %d%n",t,best);
        }
        out.close();
    }
 
 
 }
