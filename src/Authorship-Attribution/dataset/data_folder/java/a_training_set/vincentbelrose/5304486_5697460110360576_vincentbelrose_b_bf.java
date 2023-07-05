import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Iterator;
 import java.util.Locale;
 import java.util.PriorityQueue;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class B_BF {
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
    static int N,P;
    static int[] R;
    static int[][] Q;
 
    static class Edge{
        int c;
        int f;
        int dest;
        int org;
 
        public String toString(){
            return ("<"+org+","+dest+"> "+f+"/"+c);
        }
    }
 
 
 
 
    static void addEdge(Node[] graph,int org,int dest,int c){
        
        Edge e=new Edge();
        e.dest=dest;
        e.org=org;
        e.c=c;
        e.f=0;
        graph[org].outEdges.add(e);
        graph[dest].inEdges.add(e);
    }
 
 
    static class Node implements Comparable<Node>{
        int c;
        ArrayList<Edge> outEdges;
        ArrayList<Edge> inEdges;
        int dist;
        int minToHere;
        int index;
        int prev;
        boolean rev;
        Edge usedEdge;
 
        
        int ing;
        int q;
        int quant;
 
 
        Node(int index){
            outEdges=new ArrayList<Edge>();
            inEdges=new ArrayList<Edge>();
            dist=Integer.MAX_VALUE;
            this.index=index;
        }
 
        public int compareTo(Node X){
            return this.c-X.c;
        }
 
        public String toString(){
            return ing+" "+q+" "+quant;
        }
    }
 
    static int computeMaxFlow(Node[] graph){
        int G=graph.length;
        int flow=0;
 
        int c=findAugmentingPath(graph);
        while (c!=Integer.MAX_VALUE-1){
 
            flow+=c;
            int x=G-1;
            
            
            while (x!=0) {
                
                int y=graph[x].prev;
                Edge e=graph[x].usedEdge;
 
                if (graph[x].rev){
                    e.f-=c;
                } else
                    e.f+=c;
                
                
                x=y;
            }
            
            c=findAugmentingPath(graph);
        }
 
 
        return flow;
    }
 
    static int findAugmentingPath(Node[] graph){
        PriorityQueue<Node> pq=new PriorityQueue<Node>();
        int G=graph.length;
        for (int i=1;i<G;i++) {
            graph[i].dist=Integer.MAX_VALUE-1;
            graph[i].minToHere=Integer.MAX_VALUE-1;
        }
        graph[0].dist=0;
        pq.add(graph[0]);
        graph[0].minToHere=Integer.MAX_VALUE-1;
        while (pq.size()>0){
            Node X=pq.poll();
            if (X.index==G-1) {
                
                return X.minToHere;
            }
            Iterator<Edge> it=X.outEdges.iterator();
            while (it.hasNext()){
                Edge e=it.next();
                int cf=e.c-e.f;
                if (cf>0) {
                    Node Y=graph[e.dest];
                    if (Y.dist>X.dist+1) {
                        Y.dist=X.dist+1;
                        Y.minToHere=Math.min(cf,X.minToHere);
                        
                        Y.prev=X.index;
                        Y.rev=false;
                        Y.usedEdge=e;
                        pq.add(Y);
                    }
                }
            }
            it=X.inEdges.iterator();
            while (it.hasNext()){
                Edge e=it.next();
                int cf=e.f;
                if (cf>0) {
                    Node Y=graph[e.org];
                    if (Y.dist>X.dist+1) {
                        Y.dist=X.dist+1;
                        Y.minToHere=Math.min(cf,X.minToHere);
                        
                        Y.prev=X.index;
                        Y.rev=true;
                        Y.usedEdge=e;
                        pq.add(Y);
                    }
                }
            }
        }
        return graph[G-1].minToHere;
    }
 
    static int solve(){
 
        ArrayList<Node> lst=new ArrayList<Node>();
 
        ArrayList<Node>[][] ing=new ArrayList[N][P];
 
        ArrayList<Integer>[][] others=new ArrayList[N][P];
        int[][] ref=new int[N][P];
 
 
        Node source=new Node(0);
        lst.add(source);
 
 
        int idx=1;
 
        
        for (int i=0;i<N;i++) 
            for (int j=0;j<P;j++){
                
                
                others[i][j]=new ArrayList<Integer>();
                ref[i][j]=idx;
                Node X=new Node(idx++);
                lst.add(X);
 
                ing[i][j]=new ArrayList<Node>();
 
                
                
                
                log(Q[i][j]+" "+R[i]);
                int vmin=(100*Q[i][j])/(R[i]*110);
                if (vmin*R[i]*110!=Q[i][j]*100)
                    vmin++;
 
                int vmax=(100*Q[i][j])/(R[i]*90);
                log("vmin:"+vmin+" vmax:"+vmax+" "+(vmax-vmin));
 
                
 
                
            }
 
 
        Node end=new Node(idx++);
        lst.add(end);
 
        Node[] graph=new Node[lst.size()];
        for (int i=0;i<lst.size();i++)
            graph[i]=lst.get(i);
 
 
        for (int i=0;i<N;i++)
            for (int j=0;j<P;j++){
                int src=ref[i][j];
                if (i==0){
                    addEdge(graph,0,src,1);
                } 
 
                for (int dst:others[i][j]) {
                    addEdge(graph,src,dst,1);
                    if (i==N-1){
                        addEdge(graph,dst,graph.length-1,1);
                    }
                }
            }
        for (int i=0;i+1<N;i++)
            for (int a=i+1;a<N;a++)
                for (int j=0;j<P;j++)
                    for (int b=0;b<P;b++){
                        for (Node X:ing[i][j])
                            for (Node Y:ing[a][b]){
                                if (X.quant==Y.quant){
                                    
                                    addEdge(graph,X.index,Y.index,1);
                                }
                            }
 
                    }
 
        int count=computeMaxFlow(graph);
        return count;
    }
 
 
    static int allocate(int u,ArrayList<Integer>[] compat,boolean[] visited,int[] target,int[] anc){
        for (int v:compat[u]){
            if (!visited[v]){
                visited[v]=true;
                if (anc[v]==-1){
                    anc[v]=u;
                    target[u]=v;
                    return v;
                }
                else {
                    int y=anc[v];
                    int w=allocate(y,compat,visited,target,anc);
                    if (w>=0){
                        anc[v]=u;
                        target[u]=v;
                        return v;
                    }
                }
            }
        }
        return -1;
    }
 
    static int solveBidon(){
 
        int cnt=0;
        for (int j=0;j<P;j++) {
 
            int vmin=(100*Q[0][j])/(R[0]*110);
            if (vmin*R[0]*110!=Q[0][j]*100)
                vmin++;
            int vmax=(100*Q[0][j])/(R[0]*90);
            if (vmin<=vmax)
                cnt++;
 
 
        }
        return cnt;
    }
 
    
            static int solveSmall(){
 
                if (N==1)
                    return solveBidon();
 
                int[] anc=new int[P];
                int[] target=new int[P];
                Arrays.fill(target,-1);
                Arrays.fill(anc,-1);
 
 
                ArrayList<Integer>[] compat=new ArrayList[P];
                for (int j=0;j<P;j++)
                    compat[j]=new ArrayList<Integer>();
                for (int j=0;j<P;j++)
                    for (int k=0;k<P;k++){
 
                        int vmin=(100*Q[0][j])/(R[0]*110);
                        if (vmin*R[0]*110!=Q[0][j]*100)
                            vmin++;
                        int vmax=(100*Q[0][j])/(R[0]*90);
 
 
                        int zmin=(100*Q[1][k])/(R[1]*110);
                        if (zmin*R[1]*110!=Q[1][k]*100)
                            zmin++;
                        int zmax=(100*Q[1][k])/(R[1]*90);
                        if (!(vmin>zmax || zmin>vmax)){
                            if (vmin<=vmax && zmin<=zmax)
                            compat[j].add(k);
                        }
                    }
 
                int cnt=0;
                for (int u=0;u<P;u++){
                    boolean[] visited=new boolean[P];
                    if (allocate(u,compat,visited,target,anc)!=-1) {
                        cnt++; 
                    }
                }
                return cnt;
            }
 
 
            
            static BufferedWriter out;
 
            static void process() throws Exception {
                Locale.setDefault(Locale.US);
 
 
                File inputFile=new File("B.in");
                PrintWriter outputFile= new PrintWriter("B.out","UTF-8");
                Scanner sc=new Scanner(inputFile);
                sc.useLocale(Locale.US);
 
                
 
                
 
                int T=sc.nextInt();
                for (int t=1;t<=T;t++){
 
                    N=sc.nextInt();
                    P=sc.nextInt();
                    R=new int[N];
                    Q=new int[N][P];
                    for (int i=0;i<N;i++)
                        R[i]=sc.nextInt();
                    for (int i=0;i<N;i++)
                        for (int j=0;j<P;j++)
                            Q[i][j]=sc.nextInt();
 
                    String ss=""+solveSmall();
 
                    System.out.println("Case #"+t+": "+ss);
                    outputFile.println("Case #"+t+": "+ss);
 
 
                }
 
 
 
                sc.close();
                outputFile.close();
 
 
            }
 
 
 
            public static void main(String[] args) throws Exception {
 
 
                process();
 
 
            }
 
 
 
 
 
 
 }
