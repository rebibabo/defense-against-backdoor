package gcj2017.r3;
 import java.io.File;
 import java.io.PrintWriter;
 import java.io.StringWriter;
 import java.lang.reflect.Field;
 import java.lang.reflect.Modifier;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class AN implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1; 
    static String BASEPATH = "c:\\temp\\gcj2017\\r3\\";
 
    
    static String PREFIX = BASEPATH + AN.class.getSimpleName().charAt(0);
    static String INPATH = PREFIX + (LARGE ? "-large.in" : "-small-attempt0.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "1\r\n00001";
    
    static List<List<int[]>> ass;
    static List<List<Long>> numss;
    static List<Map<Long, Integer>> ahss;
    
    int[] a;
    
    public void read() 
    {
        char[] s = in.next().toCharArray();
        a = new int[s.length];
        for(int i = 0;i < s.length;i++){
            a[i] = s[i] - '0';
        }
    }
    
    static long enc(int[] a)
    {
        long h = 0;
        for(int v : a)h = h * 10 + v;
        return h;
    }
    
    public void process() 
    {
        int n = a.length;
        int[] b = new int[n];
        int ct = 0;
        do{
            Set<Long> set = new HashSet<>();
            int[] u = b;
            while(set.add(enc(u))){
                if(Arrays.equals(u, a)){
                    ct++;
                    break;
                }
                u = next(u);
            }
        }while(inc(b, n+1));
        out.println(ct);
    }
    
    public static boolean inc(int[] a, int base) {
        int n = a.length;
        int i;
        for (i = n - 1; i >= 0 && a[i] == base - 1; i--)
            ;
        if (i == -1)
            return false;
 
        a[i]++;
        Arrays.fill(a, i + 1, n, 0);
        return true;
    }
 
 
    
    public static void preprocess()
    {
    }
    
    public static class DJSet {
        public int[] upper;
 
        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }
 
        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }
 
        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }
 
        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }
 
        public int count() {
            int ct = 0;
            for (int u : upper)
                if (u < 0)
                    ct++;
            return ct;
        }
    }
 
    
    public static ResultCluster cluster(int[] f)
    {
        int n = f.length;
        DJSet ds = new DJSet(n);
        int[] red = new int[n+1];
        int p = 0;
        for(int i = 0;i < n;i++){
            if(ds.union(i, f[i]))red[p++] = f[i];
        }
        int[] clus = new int[n]; Arrays.fill(clus, -1);
        int cidgen = 0;
        for(int i = 0;i < n;i++)if(ds.upper[i] < 0)clus[i] = cidgen++;
        for(int i = 0;i < n;i++)clus[i] = clus[ds.root(i)];
        
        int[][] cycles = new int[p][];
        int[] temp = new int[n+1];
        for(int i = 0;i < p;i++){
            temp[0] = red[i];
            int j = 1;
            for(;;j++){
                temp[j] = f[temp[j-1]];
                if(temp[j] == temp[0])break;
            }
            cycles[clus[red[i]]] = Arrays.copyOf(temp, j);
        }
        ResultCluster rc = new ResultCluster();
        rc.clus = clus;
        rc.cycles = cycles;
        return rc;
    }
    
    public static class ResultCluster {
        int[] clus;
        int[][] cycles;
    }
 
    
    public static int[] sortTopologicallyWithoutCycles(int[] f)
    {
        int n = f.length;
        int[] indeg = new int[n];
        for(int i = 0;i < n;i++)if(f[i] != -1)indeg[f[i]]++;
        
        boolean[] ved = new boolean[n];
        int[] q = new int[n];
        int p = 0;
        for(int i = 0;i < n;i++){
            if(indeg[i] == 0){
                ved[i] = true;
                q[p++] = i;
            }
        }
        for(int r = 0;r < p;r++){
            int cur = q[r];
            if(f[cur] != -1 && --indeg[f[cur]] == 0)q[p++] = f[cur];
        }
        return Arrays.copyOf(q, p);
    }
    
    static int[] next(int[] a)
    {
        int[] b = new int[a.length];
        for(int v : a){
            if(v >= 1)b[v-1]++;
        }
        return b;
    }
    
    static int[] fs = new int[10];
    
    static void dfs(int pos, int use, int len, long have, int[] a, List<int[]> as, List<Long> nums)
    {
        if(pos == len){
            if(use <= len && use > 0){
                int s = 0;
                for(int i = 0;i < len;i++)s += a[i] * (i+1);
                have /= fs[len-use];
                as.add(Arrays.copyOf(a, len));
 
                nums.add(s >= len+1 ? have : 0);
            }
            return;
        }
        for(int i = 0;i <= len-use;i++){
            a[pos] = i;
            dfs(pos+1, use+i, len, have / fs[i], a, as, nums);
        }
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
    
    public AN(int cas, Scanner in)
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
        CompletionService<AN> cs = new ExecutorCompletionService<AN>(es);
        
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
            AN runner = new AN(i+1, in);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            AN runner = cs.take().get(); 
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
