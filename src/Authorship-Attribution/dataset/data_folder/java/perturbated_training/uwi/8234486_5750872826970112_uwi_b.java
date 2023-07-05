package gcj2015.r2;
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
 
 public class B implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1;
    static String BASEPATH = "c:\\temp\\gcj2015\\r2\\";
 
    
    static String INPATH = BASEPATH + B.class.getSimpleName().charAt(0) + (LARGE ? "-large.in" : "-small-attempt0.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    int n;
    long v;
    long x;
    long[] r;
    long[] c;
    
    public void read() 
    {
        n = ni();
        v = Math.round(nd() * 10000);
        x = Math.round(nd() * 10000);
        r = new long[n];
        c = new long[n];
        for(int i = 0;i < n;i++){
            r[i] = Math.round(nd() * 10000);
            c[i] = Math.round(nd() * 10000);
        }
    }
    
    public void process() 
    {
        if(n == 1){
            if(x == c[0]){
                out.printf("%.12f\n", (double)v/r[0]);
            }else{
                out.println("IMPOSSIBLE");
            }
        }else{
            double min = Double.POSITIVE_INFINITY;
            for(int i = 0;i < n;i++){
                if(x == c[i]){
                    min = Math.min(min, (double)v/r[i]);
                }
            }
            if(x == c[0] && x == c[1]){
                min = Math.min(min, (double)v/(r[0]+r[1]));
            }
            
            
            
            
            
            for(int i = 0;i < n;i++){
                for(int j = 0;j < n;j++){
                    if(i == j)continue;
                    if(c[i] < x && x < c[j]){
                        
                        double vr = (double)(x-c[i])/(c[j]-x);
                        double ni = (double)v/(1+vr)*1;
                        double nj = (double)v/(1+vr)*vr;
                        min = Math.min(min, Math.max(ni/r[i], nj/r[j]));
                    }
                }
            }
            if(Double.isInfinite(min)){
                out.println("IMPOSSIBLE");
            }else{
                out.printf("%.12f\n", min);
            }
        }
    }
    
    public static void preprocess()
    {
    }
    
    Scanner in;
    PrintWriter out;
    StringWriter sw;
    int cas;
    static List<Status> running = new ArrayList<Status>();
    
    @Override
    public void run()
    {
        long S = System.nanoTime();
        
        synchronized(running){
            Status st = new Status();
            st.id = cas;
            st.S = S;
            running.add(st);
        }
        process();
        
        synchronized(running){
            for(Status st : running){
                if(st.id == cas){
                    running.remove(st);
                    break;
                }
            }
        }
        long G = System.nanoTime();
        
        if(PROD){
            System.err.println("case " + cas + " solved. [" + (G-S)/1000000 + "ms]");
            synchronized(running){
                StringBuilder sb = new StringBuilder("running : ");
                for(Status st : running){
                    sb.append(st.id + ":" + (G-st.S)/1000000 + "ms, ");
                }
                System.err.println(sb);
            }
        }
    }
    
    private static class Status {
        public int id;
        public long S;
    }
    
    public B(int cas, Scanner in)
    {
        this.cas = cas;
        this.in = in;
        this.sw = new StringWriter();
        this.out = new PrintWriter(this.sw);
    }
    
    private int ni() { return Integer.parseInt(in.next()); }
    private long nl() { return Long.parseLong(in.next()); }
    private int[] na(int n) { int[] a = new int[n]; for(int i = 0;i < n;i++)a[i] = ni(); return a; }
    private double nd() { return Double.parseDouble(in.next()); }
    private void tr(Object... o) { if(!PROD)System.out.println(Arrays.deepToString(o)); }
    
    public static void main(String[] args) throws Exception
    {
        long start = System.nanoTime();
        
        ExecutorService es = Executors.newFixedThreadPool(NTHREAD);
        CompletionService<B> cs = new ExecutorCompletionService<B>(es);
        
        if(PROD){
            System.out.println("INPATH : " + INPATH);
            System.out.println("OUTPATH : " + OUTPATH);
        }
        Scanner in = PROD ? new Scanner(new File(INPATH)) : new Scanner(INPUT);
        PrintWriter out = PROD ? new PrintWriter(new File(OUTPATH)) : new PrintWriter(System.out);
        int n = in.nextInt();
        in.nextLine();
        
        preprocess();
        for(int i = 0;i < n;i++){
            B runner = new B(i+1, in);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            B runner = cs.take().get(); 
            runner.out.flush();
            runner.out.close();
            outs[runner.cas-1] = runner.sw.toString();
        }
        for(int i = 0;i < n;i++){
            out.printf("Case #%d: ", i+1);
            out.append(outs[i]);
            out.flush();
        }
        
        long end = System.nanoTime();
        System.out.println((end - start)/1000000 + "ms");
        if(PROD){
            System.out.println("INPATH : " + INPATH);
            System.out.println("OUTPATH : " + OUTPATH);
        }
    }
 }
