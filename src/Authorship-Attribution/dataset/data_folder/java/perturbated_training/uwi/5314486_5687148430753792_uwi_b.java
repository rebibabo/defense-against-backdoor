package gcj2017.r2;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.lang.reflect.Field;
 import java.lang.reflect.Modifier;
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
    static String BASEPATH = "c:\\temp\\gcj2017\\r2\\";
 
    
    static String PREFIX = BASEPATH + B.class.getSimpleName().charAt(0);
    static String INPATH = PREFIX + (LARGE ? "-large.in" : "-small-attempt0.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    int n, m;
    int[][] pb;
    
    public void read() 
    {
        n = ni();
        int C = ni(); m = ni();
        pb = new int[m][];
        for(int i = 0;i < m;i++){
            pb[i] = new int[]{ni(), ni()};
        }
    }
    
    public void process() 
    {
        int[] f = new int[n+1];
        int[] deg = new int[1001];
        for(int[] p : pb){
            f[p[0]]++;
            deg[p[1]]++;
        }
        int max = Arrays.stream(deg).max().getAsInt();
        int s = 0;
        for(int i = 1;i <= n;i++){
            s += f[i];
            max = Math.max(max, (s+i-1)/i);
        }
        int fix = 0;
        for(int i = 1;i <= n;i++){
            fix += Math.min(f[i], max);
        }
        out.println(max + " " + (m-fix));
    }
    
    public static void preprocess()
    {
    }
    
    protected Scanner in;
    protected PrintWriter out;
    protected StringWriter sw;
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
        
        
        StringBuilder dump = new StringBuilder();
        for(Field f : this.getClass().getDeclaredFields()){
            int mo = f.getModifiers();
            if(!Modifier.isStatic(mo) && !Modifier.isProtected(mo)){
                try {
                    dump.append(String.format("%s: %s\n", f.getName(), Arrays.deepToString(new Object[]{f.get(this)})));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        try{
            process();
        }catch(Throwable t){
            
            System.err.println(dump);
            throw t;
        }
        
        
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
            System.out.println("OUTPATH : ");
            System.out.println(OUTPATH);
        }
    }
 }
