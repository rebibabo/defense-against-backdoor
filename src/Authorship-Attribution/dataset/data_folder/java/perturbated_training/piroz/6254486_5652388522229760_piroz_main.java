package a;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Scanner;
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.FutureTask;
 
 public class Main {
 
    public static void main(String[] args) throws Exception {
        Solver<String> solver = Solver.newInstance("A-small-attempt0.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            int n = sc.nextInt();
            solver.addTask(new Task(n));
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<String> {
    int n;
    public Task(int n) {
        this.n = n;
    }
    
    @Override
    public String call() throws Exception {
        if (n == 0) {
            return "INSOMNIA";
        }
        boolean[] seen = new boolean[10];
        long x = n;
        while(true) {
            String s = String.valueOf(x);
            for(int i=0;i<s.length();i++) {
                seen[s.charAt(i) - '0'] = true;
            }
            boolean flag = true;
            for(boolean f: seen) {
                if (!f) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }
            x += n;
        }
        return String.valueOf(x);
    }
    
 }
 
 interface Solver<R> {
    public Scanner getScanner();
    public void addTask(Callable<R> task);
    public void finish();
    public static <T> Solver<T> newInstance(String filename) throws IOException {
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
            pw = new PrintWriter(new File("out.txt"));
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
