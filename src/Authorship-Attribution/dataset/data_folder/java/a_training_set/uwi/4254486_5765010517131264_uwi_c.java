package gcj2015.r3;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Date;
 import java.util.List;
 import java.util.Scanner;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class C implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 7;
    static String BASEPATH = "c:\\temp\\gcj2015\\r3\\";
 
    
    static String INPATH = BASEPATH + C.class.getSimpleName().charAt(0) + (LARGE ? "-large.in" : "-small-attempt4.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "2\r\n" + 
            "4 3\r\n" + 
            "-3 -6 -9\r\n" + 
            "3 2 1\r\n" + 
            "2 2\r\n" + 
            "1 -1\r\n" + 
            "1 1\r\n" + 
            "";
    
    int y, n;
    long[][] qs;
    
    public void read() 
    {
        y = ni();
        n = ni();
        qs = new long[n][];
        for(int i = 0;i < n;i++){
            qs[i] = new long[]{ni(), 0};
        }
        for(int i = 0;i < n;i++){
            qs[i][1] = ni();
        }
        Arrays.sort(qs, new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
                return Long.compare(Math.abs(a[0]) * (b[1] + y), Math.abs(b[0]) * (a[1] + y));
            }
        });
    }
    
    public void process() 
    {
        min = Double.POSITIVE_INFINITY;
        double time = 0, cur = 0;
        for(int i = 0;i < n;i++){
            double dist = qs[i][0] < 0 ? 
                    Math.abs(qs[i][0] - time * qs[i][1] - cur) :
                    Math.abs(qs[i][0] + time * qs[i][1] - cur);
            double tcap = dist/(y-qs[i][1]);
            time += tcap;
            cur = qs[i][0] < 0 ? 
                    qs[i][0] - time * qs[i][1]:
                    qs[i][0] + time * qs[i][1];
        }
        
 
        out.printf("%.13f\n", time);
    }
    
    double min;
    
    void dfs(double time, int ptn, double cur)
    {
 
        if(ptn+1 == 1<<n){
            min = Math.min(min, time);
            tr(min);
            return;
        }
        if(time >= min)return;
        
        double[][] hi = new double[n][];
        int p = 0;
        for(int i = 0;i < n;i++){
            if(ptn<<~i>=0){
                double dist = qs[i][0] < 0 ? 
                        Math.abs(qs[i][0] - time * qs[i][1] - cur) :
                        Math.abs(qs[i][0] + time * qs[i][1] - cur);
                double tcap = dist/(y-qs[i][1]);
                hi[p++] = new double[]{tcap, i};
            }
        }
        Arrays.sort(hi, 0, p, new Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
        
        outer:
        for(int z = 0;z < p;z++){
            int i = (int)hi[z][1];
                double dist = qs[i][0] < 0 ? 
                        Math.abs(qs[i][0] - time * qs[i][1] - cur) :
                        Math.abs(qs[i][0] + time * qs[i][1] - cur);
                double tcap = dist/(y-qs[i][1]);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
                double npos = qs[i][0] < 0 ? 
                        qs[i][0] - (time+tcap) * qs[i][1] :
                            qs[i][0] + (time+tcap) * qs[i][1];
                dfs(time + tcap, ptn|1<<i, npos);
 
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
    
    public C(int cas, Scanner in)
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
        CompletionService<C> cs = new ExecutorCompletionService<C>(es);
        
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
            C runner = new C(i+1, in);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            C runner = cs.take().get(); 
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
