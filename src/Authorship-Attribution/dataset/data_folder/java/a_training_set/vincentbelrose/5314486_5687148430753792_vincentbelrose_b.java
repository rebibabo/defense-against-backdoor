import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Locale;
 import java.util.PriorityQueue;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class B {
 
    static boolean verb=true;
    static void log(Object X){if (verb) System.err.println(X);}
    static void log(Object[] X){if (verb) {for (Object U:X) System.err.print(U+" ");System.err.println("");}}
    static void log(int[] X){if (verb) {for (int U:X) System.err.print(U+" ");System.err.println("");}}
    static void logWln(Object X){if (verb) System.err.print(X);}
    static void info(Object o){ System.out.println(o);}
    static void output(Object o){outputWln(""+o+"\n");  }
    static void outputWln(Object o){try {out.write(""+ o);} catch (Exception e) {}}
 
 
 
 
    
    static BufferedWriter out;
 
 
 
    static int MX=Integer.MAX_VALUE;
 
    static ArrayList<Integer>[] tick;
 
    static int n,M,C;
    static int[] left,right;
 
    static class Composite implements Comparable<Composite>{
 
        int left;
        int right;
        int min;
        int idx;
 
        public int compareTo(Composite X){
            if (min!=X.min)
                return X.min-min;
            return idx-X.idx;
 
        }
 
        public Composite(int left, int right, int idx) {
            this.left = left;
            this.right = right;
            this.idx = idx;
            min=Math.min(left,right);
        }
 
 
 
 
    }
 
    static int[] solveSmall(){
 
        Composite[] ar=new Composite[n];
        for (int i=0;i<n;i++)
            ar[i]=new Composite(left[i],right[i],i);
        PriorityQueue<Composite> pq=new PriorityQueue<Composite>();
        for (Composite X:ar)
            pq.add(X);
        int turns=0;
        int promo=0;
        int emptyLet=0;
        int emptyRight=0;
        while (pq.size()>0){
            Composite X=pq.peek();
            if (X.min==0){
                
                int lft=0;
                int rgt=0;
 
                while (pq.size()>0){
                    Composite Y=pq.poll();
                    lft+=Y.left;
                    rgt+=Y.right;
                }
                turns+=Math.max(lft, rgt);
            } else {
                pq.poll();
                if (pq.size()>0 && pq.peek().min>0) {
                    Composite Y=pq.poll();
                    int mn=Math.min(Y.min,X.min);
                    X.min-=mn;
                    X.left-=mn;
                    X.right-=mn;
                    Y.min-=mn;
                    Y.left-=mn;
                    Y.right-=mn;
                    turns+=mn;
                    if (X.left>0 || X.right>0)
                        pq.add(X);
                    if (Y.left>0 || Y.right>0)
                        pq.add(Y);
                    
                } else {
                    
                    
                    
                    
                    int lft=0;
                    int rgt=0;
                    while (pq.size()>0){
                        Composite Y=pq.poll();
                        lft+=Y.left;
                        rgt+=Y.right;
                    }
                    int mn1=Math.min(X.right, lft);
                    int mn2=Math.min(X.left, rgt);
                    X.right-=mn1;
                    lft-=mn1;
                    
                    X.left-=mn2;
                    rgt-=mn2;
                    
                    turns+=mn1;
                    turns+=mn2;
                    
                    X.min=Math.min(X.left,X.right);
                    
                    
                    
                    if (X.min>0){
                        if (X.idx>0){
                            
                            promo+=X.min;
                            turns+=X.min;
                            X.left-=X.min;
                            X.right-=X.min;
                            X.min=0;
                        } else {
                            turns+=2*X.min;
                            X.left-=X.min;
                            X.right-=X.min;
                            X.min=0;
                    
                        }
                    }
                    lft+=X.left;
                    rgt+=X.right;
                    
                    turns+=Math.max(lft, rgt);
                                    
                    
                    
                
                
                }
            }
        }
        return new int[]{turns,promo};
    }
 
    static void process() throws Exception {
        Locale.setDefault(Locale.US);
 
 
        File inputFile=new File("B.in");
        PrintWriter outputFile= new PrintWriter("B.out","UTF-8");
        Scanner sc=new Scanner(inputFile);
        sc.useLocale(Locale.US);
 
 
 
        int T=sc.nextInt();
        for (int t=1;t<=T;t++){
 
            n=sc.nextInt();
            C=sc.nextInt();
            M=sc.nextInt();
            tick=new ArrayList[C];
 
            for (int i=0;i<C;i++){
                tick[i]=new ArrayList<Integer>();
            }
 
            left=new int[n];
            right=new int[n];
 
            for (int i=0;i<M;i++){
                int pos=sc.nextInt()-1;
                int c=sc.nextInt()-1;
                tick[c].add(pos);
                if (c==0)
                    left[pos]++;
                if (c==1)
                    right[pos]++;
            }
            String ss="";
            if (C==2){
            int[] tmp=solveSmall();
            ss=tmp[0]+" "+tmp[1];
            } else {
                ss="BUG";
            }
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
