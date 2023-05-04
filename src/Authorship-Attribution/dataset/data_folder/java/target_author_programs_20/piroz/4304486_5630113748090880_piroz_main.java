package b2;
 
 import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.FutureTask;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import java.util.stream.Collectors;
 
 public class Main {
 
 	public static void main(String[] args) throws Exception {
 		Solver<Object> solver = Solver.getInstance("B-small-attempt1.in");
 		Scanner sc = solver.getScanner();
 		int casenum = sc.nextInt();
 		for(int caseid=0;caseid<casenum;caseid++) {
 			int n = sc.nextInt();
 			ArrayList<ArrayList<Integer>> a = new ArrayList<>();
 			for(int i=0;i<n*2-1;i++) {
 				ArrayList<Integer> al = new ArrayList<>();
 				for(int j=0;j<n;j++) {
 					al.add(sc.nextInt());
 				}
 				a.add(al);
 			}
 			Task task = new Task(n,a);
 			solver.addTask(task);
 		}
 		solver.finish();
 	}
 
 }
 class Task implements Callable<Object> {
 	int n;
 	ArrayList<ArrayList<Integer>> a;
 
 	public Task(int n, ArrayList<ArrayList<Integer>> a) {
 		super();
 		this.n = n;
 		this.a = a;
 	}
 
 	@Override
 	public Object call() throws Exception {
 		ArrayList<Integer> ans =
 				a.stream().flatMap(ArrayList::stream).collect(Collectors.groupingBy(x -> x, Collectors.counting()))
 				.entrySet().stream().filter(x -> x.getValue() % 2 == 1).map(Map.Entry::getKey).sorted().collect(Collectors.toCollection(ArrayList::new));
 		StringBuilder sb = new StringBuilder();
 		for(int i=0;i<ans.size();i++) {
 			if (i > 0) {
 				sb.append(' ');
 			}
 			sb.append(ans.get(i));
 		}
 		return sb.toString();
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
