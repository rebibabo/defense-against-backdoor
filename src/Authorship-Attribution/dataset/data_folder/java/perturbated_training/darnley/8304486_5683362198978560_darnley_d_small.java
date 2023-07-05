import java.io.*;
 import java.util.*;
 import java.util.concurrent.*;
 
 public class D_small {
    long M = 1000000007;
    String NO = "IMPOSSIBLE";
    
    String solve() {
        long ans = 0;
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                long v = Long.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    int dist = Math.abs(i - x[k]) + Math.abs(j - y[k]);
                    v = Math.min(v, b[k] + dist * 1L * d);
                }
                for (int k = 0; k < n; k++) {
                    int dist = Math.abs(i - x[k]) + Math.abs(j - y[k]);
                    if (dist == 0 && v < b[k]) {
                        return NO;
                    }
                }
                ans = (ans + v) % M;
            }
        }
        return "" + ans;
    }
    
    int hei, wid, n, d;
    int[] x, y, b;
    
    public D_small(Scanner in) {
        hei = in.nextInt();
        wid = in.nextInt();
        n = in.nextInt();
        d = in.nextInt();
        x = new int[n];
        y = new int[n];
        b = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt() - 1;
            y[i] = in.nextInt() - 1;
            b[i] = in.nextInt();
        }
    }
    
    public static void main(String[] args) throws Exception {
        int nThreads = 4;
        
        String fileNameSuffix = "";
        String formatOut = "Case #%2$d: %1$s";
        String formatSystemOut = formatOut;
 
 
 
 
        
        String fileName = D_small.class.getSimpleName().replaceFirst("_.*", "").toLowerCase() + fileNameSuffix;
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
            final D_small testCase = new D_small(in);
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
