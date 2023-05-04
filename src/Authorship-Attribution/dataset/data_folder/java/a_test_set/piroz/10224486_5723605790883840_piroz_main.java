package c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.FutureTask;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class Main {
 
    public static void main(String[] args) throws Exception {
        Solver<Object> solver = Solver.getInstance("C-small-attempt1.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            int n = (h + w) * 2;
            int[] a = new int[n];
            for(int i=0;i<n;i++) {
                a[i] = sc.nextInt();
            }
            Task task = new Task(h,w,a);
            solver.addTask(task);
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Object> {
    int h,w;
    int[] a;
    
    public Task(int h, int w, int[] a) {
        super();
        this.h = h;
        this.w = w;
        this.a = a;
    }
 
    @Override
    public Object call() throws Exception {
        int n = (h + w) * 2;
        int m = h + w;
        boolean[][] edge = new boolean[n][n];
        for(int i=0;i<m;i++) {
            int u = a[i*2] - 1;
            int v = a[i*2+1] - 1;
            edge[u][v] = edge[v][u] = true;
        }
        ArrayList<Pair> al = new ArrayList<>();
        int count = 0;
        for(int i=0;i<n;i++) {
            int u = i;
            int v = (i + 1) % n;
            int size = 0;
            if (edge[u][v]) {
                int u_ = u;
                while(edge[u][v]) {
                    count++;
                    edge[u][v] = edge[v][u] = false;
                    size++;
                    u = (u + n - 1) % n;
                    v = (v + 1) % n;
                }
                al.add(new Pair(u_,size));
            }
        }
        if (count != m) {
            return "IMPOSSIBLE";
        }
        char[][] map = new char[h][w];
        for(int i=0;i<h;i++) {
            Arrays.fill(map[i], '/');
        }
        for(Pair p: al) {
 
            int oi,oj;
            if (p.a < w) {
                oi = 0;
                oj = p.a + 1;
            }else if(p.a < w + h) {
                oi = 1 + (p.a - w);
                oj = w;
            }else if(p.a < w + h + w) {
                oi = h;
                oj = w - 1 - (p.a - w - h);
            }else{
                oi = h - 1 - (p.a - w - h - w);
                oj = 0;
            }
            for(int i=oi-p.b;i<=oi+p.b;i++) {
                for(int j=oj-p.b;j<=oj+p.b;j++) {
                    if (i < 0 || i >= h || j < 0 || j >= w) {
                        continue;
                    }
                    double i2 = i + 0.5;
                    double j2 = j + 0.5;
                    if (Math.abs(oi - i2) + Math.abs(oj - j2) <= p.b + 0.1) {
                        if ((oi - i2) * (oj - j2) < 0) {
                            map[i][j] = '\\';
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<h;i++) {
            if (i > 0) {
                sb.append('\n');
            }
            for(int j=0;j<w;j++) {
                sb.append(map[i][j]);
            }
        }
        return sb.toString();
    }
    
    
 }
 class Pair {
    int a,b;
 
    public Pair(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }
    
    public String toString() {
        return "(" + a + "," + b + ")";
    }
    
 }
 interface Solver<R> {
    public Scanner getScanner();
    public void addTask(Callable<R> task);
    public void finish();
    public static <T> Solver<T> getInstance(String filename) throws IOException {
        if (filename == null) {
            return new DebugSolver<>();
        }else{
            return new ConcurrentSolver<>(filename);
        }
    }
 }
 class DebugSolver<R> implements Solver<R> {
    private ArrayList<Callable<R>> tasks = new ArrayList<>();
    public DebugSolver() {
        
    }
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
    public void addTask(Callable<R> task) {
        tasks.add(task);
    }
    public void finish() {
        for(int i=0;i<tasks.size();i++) {
            try {
                long stime = System.nanoTime();
                System.out.println("Case #" + (i+1) + ":\n" + tasks.get(i).call());
                System.out.println((System.nanoTime() - stime) / 1000000 + " ms");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
 }
 class ConcurrentSolver<R> implements Solver<R>{
    private ExecutorService es = Executors.newFixedThreadPool(4,(runnable)->new Thread(null, runnable, "", 16L * 1024L * 1024L)); 
    private ArrayList<FutureTask<R>> tasks = new ArrayList<>();
    private Scanner sc;
    private PrintWriter pw;
    public ConcurrentSolver(String filename) throws IOException {
        if (filename == null) {
            sc = new Scanner(System.in);
            pw = new PrintWriter(System.out);
        }else{
            sc = new Scanner(new File(filename));
            Matcher matcher = Pattern.compile("(.+)\\.in").matcher(filename);
            String filenameOut;
            if (matcher.matches()) {
                filenameOut = matcher.group(1) + ".out";
            }else{
                filenameOut = "out.txt";
            }
            pw = new PrintWriter(new File(filenameOut));
        }
    }
    public Scanner getScanner() {
        return sc;
    }
    public void addTask(Callable<R> task) {
        FutureTask<R> futureTask = new FutureTask<>(new TaskWrapper(tasks.size() + 1, task));
        tasks.add(futureTask);
        es.submit(futureTask);
    }
    public void finish() {
        for(int i=0;i<tasks.size();i++) {
            try {
                pw.print("Case #" + (i+1) + ":\n" + tasks.get(i).get() + "\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pw.flush();
        es.shutdown();
    }
    class TaskWrapper implements Callable<R> {
        int id;
        Callable<R> c;
        public TaskWrapper(int caseId,Callable<R> c) {
            this.id = caseId;
            this.c = c;
        }
        public R call() throws Exception {
            System.out.println("Start #" + id);
            long stime = System.nanoTime();
            R res = c.call();
            System.out.println("End #" + id + " (" + (System.nanoTime() - stime) / 1000000 + "ms)");
            return res;
        }
    }
 }
