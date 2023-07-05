package c;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
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
        Solver<Object> solver = Solver.getInstance("C-small-attempt0.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            int n = sc.nextInt();
            String[] s = new String[n];
            String[] t = new String[n];
            for(int i=0;i<n;i++) {
                s[i] = sc.next();
                t[i] = sc.next();
            }
            Task task = new Task(n,s,t);
            solver.addTask(task);
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Object> {
    int n;
    String[] s,t;
    
    
    public Task(int n, String[] s, String[] t) {
        super();
        this.n = n;
        this.s = s;
        this.t = t;
    }
 
 
    @Override
    public Object call() throws Exception {
        int max = 0;
        for(int i=0;i<1<<n;i++) {
            int score = 0;
            HashSet<String> a = new HashSet<>();
            HashSet<String> b = new HashSet<>();
            for(int j=0;j<n;j++) {
                if ((i >> j & 1) == 0) {
                    a.add(s[j]);
                    b.add(t[j]);
                }
            }
            for(int j=0;j<n;j++) {
                if ((i >> j & 1) == 1) {
                    if (a.contains(s[j]) && b.contains(t[j])) {
                        score++;
                    }
                    a.add(s[j]);
                    b.add(t[j]);
                }
            }
            max = Math.max(max, score);
        }
        return max;
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
