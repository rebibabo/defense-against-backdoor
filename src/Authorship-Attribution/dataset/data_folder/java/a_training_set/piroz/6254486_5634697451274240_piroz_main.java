package bsmall;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Queue;
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
        Solver<Integer> solver = Solver.getInstance("B-small-attempt0.in");
        Scanner sc = solver.getScanner();
        int casenum = sc.nextInt();
        for(int caseid=0;caseid<casenum;caseid++) {
            String s = sc.next();
            solver.addTask(new Task(s));
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Integer> {
    String s;
    public Task(String s) {
        this.s = s;
    }
    
    @Override
    public Integer call() throws Exception {
        int n = s.length();
        HashMap<String, Integer> dist = new HashMap<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        dist.put(s, 0);
        while(!q.isEmpty()) {
            String s0 = q.poll();
            int cd = dist.get(s0);
            boolean flag = true;
            for(int i=0;i<n;i++) {
                if (s0.charAt(i) == '-') {
                    flag = false;
                }
            }
            if (flag) {
                return cd;
            }
            for(int i=1;i<=n;i++) {
                char[] s1a = new char[n];
                for(int j=0;j<i;j++) {
                    s1a[j] = s0.charAt(i-1-j) == '+' ? '-' : '+';
                }
                for(int j=i;j<n;j++) {
                    s1a[j] = s0.charAt(j);
                }
                String s1 = String.valueOf(s1a);
                if (!dist.containsKey(s1)) {
                    dist.put(s1,cd+1);
                    q.offer(s1);
                }
            }
        }
        return -1;
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
