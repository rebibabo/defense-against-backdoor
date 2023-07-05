
 import java.io.*;
 import java.util.*;
 import java.util.concurrent.*;
 import java.math.*;
 
 public class C {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] param = getInts(in);
                int N = param[0];
                int J = param[1];
                out.printf("Case #%d:\n", t);
                for (int c = (1 << (N - 1)) + 1 ; J > 0 ; c += 2) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    String s = Integer.toBinaryString(c);
                    Task task = new Task(s);
                    Future<String> future = executor.submit(task);
                    try {
                        System.out.print("Started " + s);
                        String res = future.get(1, TimeUnit.SECONDS);
                        if (res != null) {
                            out.println(res);
                            J--;
                        }
                        System.out.printf(" Finished, %d to go\n", J);
                    } catch (TimeoutException | ExecutionException | InterruptedException e) {
                        future.cancel(true);
                        System.out.println(" Terminated");
                    }
                    executor.shutdownNow();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
 
 class Task implements Callable<String> {
    String s;
 
    Task(String _s) {
        s = _s;
    }
 
    public String call() {
        for (int radix = 2 ; radix <= 10 ; radix++) {
            if (new BigInteger(s, radix).isProbablePrime(10)) return null;
        }
        StringBuilder res = new StringBuilder(s);
 radix: for (int radix = 2 ; radix <= 10 ; radix++) {
            BigInteger i = new BigInteger(s, radix);
            for (BigInteger p = new BigInteger("2") ;; p = p.nextProbablePrime()) {
                if (i.mod(p).signum() == 0) {
                    res.append(' ').append(p);
                    continue radix;
                }
            }
        }
        return res.toString();
    }
 }
