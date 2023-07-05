import java.io.*;
 import java.util.*;
 import java.util.concurrent.*;
 
 public class A {
    String solve() {
        n = s.length();
        dfs(Integer.parseInt(s));
        return "" + seen.size();
    }
    
    void dfs(int x) {
        if (x == 0 || !seen.add(x)) {
            return;
        }
        int[] count = new int[n + 1];
        count[0] = n;
        for (int i = n; i >= 1; i--) {
            count[i] = x % 10;
            x /= 10;
            count[0] -= count[i];
        }
        if (count[0] < 0) {
            return;
        }
        search(0, count);
    }
 
    void search(int x, int[] count) {
        boolean last = true;
        for (int i = 0; i <= n; i++) {
            if (count[i] == 0) {
                continue;
            }
            last = false;
            count[i]--;
            search(x * 10 + i, count);
            count[i]++;
        }
        if (last) {
            dfs(x);
        }
    }
 
    Set<Integer> seen = new TreeSet<>();
    int n;
    String s;
    
    public A(Scanner in) {
        s = in.next();
    }
    
    public static void main(String[] args) throws Exception {
        int nThreads = 1;
        
        String fileNameSuffix = "";
        String formatOut = "Case #%2$d: %1$s";
        String formatSystemOut = formatOut;
 
 
 
 
        
        String fileName = A.class.getSimpleName().replaceFirst("_.*", "").toLowerCase() + fileNameSuffix;
        String inputFileName = fileName + ".in";
        String outputFileName = fileName + ".out";
        
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(new File(inputFileName));
        PrintWriter out = new PrintWriter(outputFileName);
        int tests = in.nextInt(); in.nextLine();
        nThreads = Math.min(nThreads, tests);
        @SuppressWarnings("unchecked")
        Callable<String>[] callables = new Callable[tests];
        for (int t = 0; t < tests; t++) {
            final A testCase = new A(in);
            final int testCaseNumber = t + 1;
            callables[t] = new Callable<String>() {
                @Override
                public String call() {
                    String answer = testCase.solve();
                    System.out.println(String.format(formatSystemOut, answer, testCaseNumber));
                    return String.format(formatOut, answer, testCaseNumber);
                }
            };
        }
        try {
            if (nThreads > 1) {
                ExecutorService executor = Executors.newFixedThreadPool(4);
                @SuppressWarnings("unchecked")
                Future<String>[] outputs = new Future[tests];
                for (int t = 0; t < tests; t++) {
                    outputs[t] = executor.submit(callables[t]);
                }
                for (int t = 0; t < tests; t++) {
                    out.println(outputs[t].get());
                }
                executor.shutdown();
            } else {
                for (int t = 0; t < tests; t++) {
                    out.println(callables[t].call());
                }
            }
        } catch (Exception e) {
            System.out.flush();
            System.err.flush();
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("COMPLETE");
        in.close();
        out.close();
    }
 }
