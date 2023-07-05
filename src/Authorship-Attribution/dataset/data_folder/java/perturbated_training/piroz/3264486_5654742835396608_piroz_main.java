package c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.PriorityQueue;
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
        Solver<Object> solver = Solver.getInstance("C-small-1-attempt0.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            Task task = new Task(k,n);
            solver.addTask(task);
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Object> {
    long k,n;
    
    public Task(long k, long n) {
        super();
        this.k = k;
        this.n = n;
    }
 
    @Override
    public Object call() throws Exception {
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        q.offer(new Tuple(0, n-1));
        for(long i=0;i<k;i++) {
            Tuple t = q.poll();
            if (i == k - 1) {
                return t.max + " " + t.min;
            }
            if (t.l <= t.c-1) {
                q.offer(new Tuple(t.l, t.c-1));
            }
            if (t.c+1 <= t.r) {
                q.offer(new Tuple(t.c+1, t.r));
            }
        }
        throw new RuntimeException();
    }
    
 }
 
 class Tuple implements Comparable<Tuple> {
    long l,r,c,max,min;
 
    public Tuple(long l, long r) {
        super();
        this.l = l;
        this.r = r;
        c = (l + r) / 2;
        long ls = c - l;
        long rs = r - c;
        min = Math.min(ls, rs);
        max = Math.max(ls, rs);
    }
 
    @Override
    public int compareTo(Tuple o) {
        if (min != o.min) {
            return -Long.compare(min, o.min);
        }
        if (max != o.max) {
            return -Long.compare(max, o.max);
        }
        return Long.compare(c, o.c);
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
