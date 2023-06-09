package a;
 
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
        Solver<Object> solver = Solver.getInstance("A-small-attempt1.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            int[] g = new int[n];
            for(int i=0;i<n;i++) {
                g[i] = sc.nextInt();
            }
            Task task = new Task(n,p,g);
            solver.addTask(task);
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Object> {
    int n;
    int p;
    int[] g;
    
    
    public Task(int n, int p, int[] g) {
        super();
        this.n = n;
        this.p = p;
        this.g = g;
    }
 
 
    @Override
    public Object call() throws Exception {
        Arrays.fill(dp, (byte) -1);
        pow[0] = 1;
        for(int i=1;i<p;i++) {
            pow[i] = pow[i-1] * BASE;
        }
        int sum = 0;
        int[] count = new int[p];
        for(int i=0;i<n;i++) {
            count[g[i] % p]++;
            sum += g[i];
        }
        summod = sum % p;
        int x0 = 0;
        for(int i=0;i<p;i++) {
            x0 += pow[i] * count[i];
        }
        return f(x0);
    }
    
    int summod;
    private static final int BASE = 101;
    byte[] dp = new byte[110000000];
    int[] pow = new int[4];
    public byte f(int x) {
        if (x == 0) {
            return 0;
        }
        if (dp[x] >= 0) {
            return dp[x];
        }
        int y = x;
        int[] temp = new int[p];
        for(int i=0;i<p;i++) {
            temp[i] = y % BASE;
            y /= BASE;
        }
        int sum = 0;
        for(int i=0;i<p;i++) {
            sum += temp[i] * i;
        }
 
        int max = -1;
        for(int i=0;i<p;i++) {
            if (temp[i] <= 0) continue;
            max = Math.max(max, f(x - pow[i]) + (sum % p == summod ? 1 : 0));
        }
        return dp[x] = (byte) max;
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
                System.out.println("Case #" + (i+1) + ": " + tasks.get(i).call());
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
                pw.println("Case #" + (i+1) + ": " + tasks.get(i).get());
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
