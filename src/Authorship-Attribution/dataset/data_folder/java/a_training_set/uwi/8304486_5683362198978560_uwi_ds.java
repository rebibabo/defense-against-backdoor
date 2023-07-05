package gcj2017.r3;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.lang.reflect.Field;
 import java.lang.reflect.Modifier;
 import java.text.SimpleDateFormat;
 import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.List;
 import java.util.Queue;
 import java.util.Scanner;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class DS implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1; 
    static String BASEPATH = "c:\\temp\\gcj2017\\r3\\";
 
    
    static String PREFIX = BASEPATH + DS.class.getSimpleName().charAt(0);
    static String INPATH = PREFIX + (LARGE ? "-large.in" : "-small-attempt0.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    int n, m;
    long[][] sup;
    long[][] inf;
    int[][] coms;
    int D;
    int mod = 1000000007;
    
    public void read() 
    {
        n = ni(); m = ni();
        sup = new long[n][m];
        inf = new long[n][m];
        for(int i = 0;i < n;i++){
            Arrays.fill(inf[i], Long.MIN_VALUE);
            Arrays.fill(sup[i], Long.MAX_VALUE);
        }
        int K = ni();
        D = ni();
        coms = new int[K][];
        for(int i = 0;i < K;i++){
            coms[i] = new int[]{ni()-1, ni()-1, ni()};
        }
    }
    
    public void process() 
    {
        Queue<int[]> q = new ArrayDeque<>();
        for(int[] com : coms){
            int r = com[0], c = com[1];
            int v = com[2];
            inf[r][c] = sup[r][c] = v;
            q.add(new int[]{r, c});
        }
        
        int[] dr = { 1, 0, -1, 0 };
        int[] dc = { 0, 1, 0, -1 };
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            if(inf[r][c] > sup[r][c]){
                out.println("IMPOSSIBLE");
                return;
            }
            for(int k = 0;k < 4;k++){
                int nr = r + dr[k], nc = c + dc[k];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    long ninf = inf[r][c] - D;
                    long nsup = sup[r][c] + D;
                    if(ninf > inf[nr][nc] || nsup < sup[nr][nc]){
                        inf[nr][nc] = Math.max(inf[nr][nc], ninf);
                        sup[nr][nc] = Math.min(sup[nr][nc], nsup);
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        long ret = 0;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                ret += sup[i][j];
                ret %= mod;
            }
        }
        out.println(ret);
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
    
    public DS(int cas, Scanner in)
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
        CompletionService<DS> cs = new ExecutorCompletionService<DS>(es);
        
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
            DS runner = new DS(i+1, in);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            DS runner = cs.take().get(); 
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
