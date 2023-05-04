package gcj2017.r1a;
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
 
 public class A implements Runnable {
    static final boolean LARGE = false;
    static final boolean PROD = true;
    static final int NTHREAD = 1; 
    static String BASEPATH = "c:\\temp\\gcj2017\\r1a\\";
 
    
    static String INPATH = BASEPATH + A.class.getSimpleName().charAt(0) + (LARGE ? "-large.in" : "-small-attempt1.in");
 
    static String OUTPATH = INPATH.substring(0, INPATH.length()-3) + new SimpleDateFormat("-HHmmss").format(new Date()) + ".out";
    
    static String INPUT = "";
    
    char[][] map;
    int n, m;
    
    public void read() 
    {
        n = ni(); m = ni();
        map = new char[n][];
        for(int i = 0;i < n;i++){
            map[i] = in.next().toCharArray();
        }
    }
    
    public void process() 
    {
        int[] minr = new int[26];
        int[] minc = new int[26];
        int[] maxr = new int[26];
        int[] maxc = new int[26];
        Arrays.fill(minr, 999999);
        Arrays.fill(minc, 999999);
        Arrays.fill(maxc, -1);
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(map[i][j] != '?'){
                    int id = map[i][j]-'A';
                    minr[id] = Math.min(minr[id], i);
                    maxr[id] = Math.max(maxr[id], i);
                    minc[id] = Math.min(minc[id], j);
                    maxc[id] = Math.max(maxc[id], j);
                }
            }
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(map[i][j] == '?'){
                    for(int k = 0;k < 26;k++){
                        if(minr[k] <= i && i <= maxr[k] &&
                                minc[k] <= j && j <= maxc[k]){
                            map[i][j] =  (char)('A'+k);
                            break;
                        }
                    }
                }
            }
        }
        
        for(int rep = 0;rep < 25;rep++){
            for(int k = 0;k < 26;k++){
                if(minr[k]-1 >= 0){
                    minr[k]--;
                    if(test(map, k, minr, maxr, minc, maxc)){
                        paint(map, k, minr, maxr, minc, maxc);
                    }else{
                        minr[k]++;
                    }
                }
                if(maxr[k]+1 < n){
                    maxr[k]++;
                    if(test(map, k, minr, maxr, minc, maxc)){
                        paint(map, k, minr, maxr, minc, maxc);
                    }else{
                        maxr[k]--;
                    }
                }
                if(minc[k]-1 >= 0){
                    minc[k]--;
                    if(test(map, k, minr, maxr, minc, maxc)){
                        paint(map, k, minr, maxr, minc, maxc);
                    }else{
                        minc[k]++;
                    }
                }
                if(maxc[k]+1 < m){
                    maxc[k]++;
                    if(test(map, k, minr, maxr, minc, maxc)){
                        paint(map, k, minr, maxr, minc, maxc);
                    }else{
                        maxc[k]--;
                    }
                }
            }
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                assert map[i][j] != '?';
            }
        }
        out.println();
        for(int i = 0;i < n;i++){
            out.println(new String(map[i]));
        }
    }
    
    boolean test(char[][] map, int k, int[] minr, int[] maxr, int[] minc, int[] maxc)
    {
        for(int i = minr[k];i <= maxr[k];i++){
            for(int j = minc[k];j <= maxc[k];j++){
                if(map[i][j] == (char)('A'+k) || map[i][j] == '?'){
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    
    void paint(char[][] map, int k, int[] minr, int[] maxr, int[] minc, int[] maxc)
    {
        for(int i = minr[k];i <= maxr[k];i++){
            for(int j = minc[k];j <= maxc[k];j++){
                map[i][j] = (char)('A'+k);
            }
        }
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
    
    public A(int cas, Scanner in)
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
        CompletionService<A> cs = new ExecutorCompletionService<A>(es);
        
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
            A runner = new A(i+1, in);
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
        if(PROD){
            System.out.println("INPATH : " + INPATH);
            System.out.println("OUTPATH : ");
            System.out.println(OUTPATH);
        }
    }
 }
