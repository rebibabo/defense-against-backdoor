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
 import java.util.List;
 import java.util.Scanner;
 import java.util.concurrent.CompletionService;
 import java.util.concurrent.ExecutorCompletionService;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class BS implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 7; 
    static String BASEPATH = "c:\\temp\\gcj2017\\r3\\";
 
    
    static String PREFIX = BASEPATH + BS.class.getSimpleName().charAt(0);
    static String INPATH = PREFIX + (LARGE ? "-large.in" : "-small-attempt2.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    int n, m;
    int[][] g;
    
    public void read() 
    {
        n = ni();
        m = ni();
        g = new int[n][n];
        for(int i = 0;i < n;i++){
            Arrays.fill(g[i], -1);
        }
        for(int i = 0;i < m;i++){
            g[ni()-1][ni()-1] = i;
        }
    }
    
    public void process() 
    {
        int u = n*(n-1)/2;
        int[] w = new int[u];
        int[][] bal = new int[n][n];
        int[] lim = new int[u];
        Arrays.fill(lim, 2*n*n+1);
        {
            int p = 0;
            for(int i = 0;i < n;i++){
                for(int j = i+1;j < n;j++){
                    if(g[i][j] == -1 && g[j][i] == -1){
                        lim[p] = 1;
                    }
                    p++;
                }
            }
        }
        outer:
        do{
            int p = 0;
            for(int i = 0;i < n;i++){
                for(int j = i+1;j < n;j++){
                    if(g[i][j] == -1 && g[j][i] == -1)continue;
 
                    bal[i][j] = w[p] - n*n;
                    bal[j][i] = -bal[i][j];
                    if(g[i][j] != -1 && g[j][i] != -1){
                    }else if(w[p] == n*n){
                        continue outer;
                    }
                    p++;
                    
                }
            }
            for(int i = 0;i < n;i++){
                int ba = 0;
                for(int j = 0;j < n;j++){
                    ba += bal[i][j];
                    ba -= bal[j][i];
                }
                if(ba != 0)continue outer;
            }
            int[] ans = new int[m];
            Arrays.fill(ans, Integer.MIN_VALUE);
            for(int i = 0;i < n;i++){
                for(int j = i;j < n;j++){
                    if(g[i][j] == -1 && g[j][i] == -1)continue;
                    if(g[i][j] != -1 && g[j][i] == -1){
                        ans[g[i][j]] = bal[i][j];
                    }else if(g[i][j] == -1 && g[j][i] != -1){
                        ans[g[j][i]] = bal[j][i];
                    }else{
                        if(bal[i][j] + 1 != 0 && bal[i][j] + 1 <= n*n){
                            ans[g[i][j]] = bal[i][j] + 1;
                            ans[g[j][i]] = 1;
                        }else if(bal[i][j] - 1 != 0 && bal[i][j] - 1 >= -n*n){
                            ans[g[i][j]] = bal[i][j] - 1;
                            ans[g[j][i]] = -1;
                        }else{
                            throw new RuntimeException();
                        }
                    }
                }
            }
            for(int i = 0;i < m;i++){
                assert ans[i] >= -n*n && ans[i] <= n*n;
 
                assert ans[i] != 0;
            }
            for(int i = 0;i < m;i++){
                if(i > 0)out.print(" ");
                out.print(ans[i]);
            }
            out.println();
            return;
        }while(inc(w, lim));
        out.println("IMPOSSIBLE");
    }
    
    public static boolean inc(int[] b, int[] a)
    {
        for(int i = b.length-1;i >= 0;i--){
            if(++b[i] < a[i])return true;
            b[i] = 0;
        }
        return false;
    }
 
    
    void dfs(int r, int c, int[] w, int[][] bal)
    {
        if(r == n){
            
        }
        if(c == n){
            
            dfs(r+1, 0, w, bal);
            return;
        }
        if(g[r][c] == -1 && g[c][r] == -1){
            bal[r][c] = bal[c][r] = 0;
            dfs(r, c+1, w, bal);
            return;
        }
        for(int i = -n*n;i <= n*n;i++){
            
        }
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
    
    public BS(int cas, Scanner in)
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
        CompletionService<BS> cs = new ExecutorCompletionService<BS>(es);
        
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
            BS runner = new BS(i+1, in);
            runner.read();
            cs.submit(runner, runner);
        }
        es.shutdown();
        String[] outs = new String[n];
        for(int i = 0;i < n;i++){
            BS runner = cs.take().get(); 
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
