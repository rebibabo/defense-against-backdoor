package exo3;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.PriorityQueue;
 import java.util.Queue;
 import java.util.Scanner;
 import java.util.stream.Collectors;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
 
        
        
        
 
        
        configSmall(1, false);
        
 
        
        
 
        try {
            int testCaseCount = input().nextInt();
            for (int i = 1; i <= testCaseCount; ++i) {
                info("CASE #" + i + " ###################################################");
 
                
                
                
 
                output().println("Case #" + i + ": " + solveTestCase());
 
                
                
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        finally {
            done();
        }
    }
 
    
    
    
 
    private static String solveTestCase() {
 
        int N = input().nextInt();
        int Q = input().nextInt();
 
        double[] E = new double[N];
        double[] S = new double[N];
        for (int i = 0; i < N; ++i) {
            E[i] = input().nextInt();
            S[i] = input().nextInt();
        }
 
        double[][] dist = new double[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                dist[i][j] = input().nextInt();
            }
        }
 
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
 
        for (int k = 0; k < Q; ++k) {
            int initialTown = input().nextInt();
            int targetTown = input().nextInt();
            double res = timeWithOneHorse2(N, dist, initialTown - 1, E, S, targetTown - 1);
 
            if (isFirst) {
                isFirst = false;
            }
            else {
                sb.append(" ");
            }
            sb.append(res);
        }
 
        return sb.toString();
    }
 
    
    
    
    
    
    
    
 
    private static double timeWithOneHorse2(int N, double[][] dist, int initialTown, double[] E, double[] S,
            int targetTown) {
 
        double[] result = new double[N];
        Arrays.fill(result, Double.POSITIVE_INFINITY);
 
        
        Queue<PairTownTimeHorse> queue = new PriorityQueue<>((p1, p2) -> Double.compare(p1.time, p2.time));
        queue.add(new PairTownTimeHorse(initialTown, 0, -1, 0));
 
        while (!queue.isEmpty()) {
 
            PairTownTimeHorse current = queue.poll();
            
            
            
            result[current.town] = Math.min(current.time, result[current.town]);
            if (current.town == targetTown) {
                return result[current.town];
            }
 
            for (int j = 0; j < N; ++j) {
                if (Double.isFinite(result[j]) || dist[current.town][j] < 0) {
                    continue;
                }
 
                
                if (E[current.town] >= dist[current.town][j]) {
                    double nextTime = current.time + dist[current.town][j] / S[current.town];
                    queue.add(new PairTownTimeHorse(j, nextTime, current.town, E[current.town] - dist[current.town][j]));
                }
 
                
                if (current.horse >= 0 && current.remainingE >= dist[current.town][j]) {
                    double nextTime = current.time + dist[current.town][j] / S[current.horse];
                    queue.add(new PairTownTimeHorse(j, nextTime, current.horse, current.remainingE - dist[current.town][j]));
                }
            }
        }
 
        return Double.POSITIVE_INFINITY;
    }
 
    private static class PairTownTimeHorse {
        public final int town;
        public final double time;
        public final int horse;
        public final double remainingE;
 
        public PairTownTimeHorse(int town, double time, int horse, double remainingE) {
            this.town = town;
            this.time = time;
            this.horse = horse;
            this.remainingE = remainingE;
        }
    }
 
    private static Map<Integer, double[]> computeTimesWithOneHorse(int N, double[][] dist, double[] E, double[] S) {
        Map<Integer, double[]> result = new HashMap<Integer, double[]>();
 
        for (int i = 0; i < N; ++i) {
            result.put(i, timeWithOneHorse(N, dist, i, E[i], S[i]));
        }
 
        return result;
    }
 
    private static double[] timeWithOneHorse(int N, double[][] dist, int initialTown, double initialE, double initialS) {
 
        double[] result = new double[N];
        Arrays.fill(result, Double.POSITIVE_INFINITY);
 
        double totalTime = initialE / initialS;
        Queue<PairTownTime> queue = new PriorityQueue<>((p1, p2) -> Double.compare(p1.time, p2.time));
        queue.add(new PairTownTime(initialTown, 0));
 
        while (!queue.isEmpty()) {
 
            PairTownTime current = queue.poll();
            if (Double.isFinite(result[current.town])) {
                continue;
            }
            result[current.town] = current.time;
 
            for (int j = 0; j < N; ++j) {
                if (Double.isFinite(result[j]) || dist[current.town][j] < 0) {
                    continue;
                }
 
                double nextTime = current.time + dist[current.town][j] / initialS;
                if (nextTime <= totalTime) {
                    queue.add(new PairTownTime(j, nextTime));
                }
            }
        }
 
        return result;
    }
 
    private static class PairTownTime {
        public final int town;
        public final double time;
 
        public PairTownTime(int town, double time) {
            this.town = town;
            this.time = time;
        }
    }
 
 }
 
 
 
 
 
 
 
 
 
 class Base {
 
    
    
    
 
    public static String join(int[] values) {
        return join(" ", values);
    }
 
    public static String join(long[] values) {
        return join(" ", values);
    }
 
    public static String join(Object[] values) {
        return join(" ", values);
    }
 
    public static String join(Collection<?> values) {
        return join(" ", values);
    }
 
    public static String join(String delimiter, int[] values) {
        return Arrays.stream(values).mapToObj(value -> Integer.toString(value)).collect(Collectors.joining(delimiter));
    }
 
    public static String join(String delimiter, long[] values) {
        return Arrays.stream(values).mapToObj(value -> Long.toString(value)).collect(Collectors.joining(delimiter));
    }
 
    public static String join(String delimiter, Object[] values) {
        return Arrays.stream(values).map(value -> value.toString()).collect(Collectors.joining(delimiter));
    }
 
    public static String join(String delimiter, Collection<?> values) {
        return values.stream().map(value -> value.toString()).collect(Collectors.joining(delimiter));
    }
 
    
    
    
 
    private interface Config {
 
        void info(String text);
 
        void debug(String text);
 
        Scanner input();
 
        PrintStream output();
 
        void done();
    }
 
    private static Config CURRENT_CONFIG;
 
    protected static void info(String text) {
        CURRENT_CONFIG.info(text);
    }
 
    protected static void debug(String text) {
        CURRENT_CONFIG.debug(text);
    }
 
    protected static Scanner input() {
        return CURRENT_CONFIG.input();
    }
 
    protected static PrintStream output() {
        return CURRENT_CONFIG.output();
    }
 
    protected static void done() {
        CURRENT_CONFIG.done();
    }
 
    private static final String ECLIPSE_PREFIX = "src";
    private static final String BASE_PATH = ECLIPSE_PREFIX + File.separator + Base.class.getPackage().getName()
            + File.separator;
 
    private static abstract class AbstractConfig implements Config {
 
        private Scanner input;
        protected final boolean debugEnabled;
 
        public AbstractConfig(boolean debugEnabled) {
            this.debugEnabled = debugEnabled;
        }
 
        protected abstract String getInputFile();
 
        @Override
        public Scanner input() {
            if (input == null) {
                String source = BASE_PATH + getInputFile();
                try {
                    input = new Scanner(new FileInputStream(source));
                }
                catch (FileNotFoundException e) {
                    throw new IllegalArgumentException("File " + source + " cannot be read.");
                }
            }
            return input;
        }
 
        @Override
        public void done() {
            if (input != null) {
                input.close();
                input = null;
            }
        }
 
        @Override
        public void info(String text) {
            printWithTag("[INFO]", text);
        }
 
        @Override
        public void debug(String text) {
            if (debugEnabled) {
                printWithTag("[DEBUG]", text);
            }
        }
 
        private void printWithTag(String tag, String text) {
            if (text.indexOf("\n") < 0) {
                System.err.println(tag + " " + text);
            }
            else {
                System.err.println(tag + "[BEGIN]");
                System.err.print(text);
                if (!"\n".equals(text.charAt(text.length() - 1))) {
                    System.err.println();
                }
                System.err.println(tag + "[END]");
            }
        }
    }
 
    private static abstract class DevConfig extends AbstractConfig {
 
        public DevConfig(boolean debugEnabled) {
            super(debugEnabled);
        }
 
        @Override
        public PrintStream output() {
            return System.out;
        }
 
        @Override
        public void info(String text) {
            forceFlushOnOutput();
            super.info(text);
        }
 
        @Override
        public void debug(String text) {
            if (debugEnabled) {
                forceFlushOnOutput();
            }
            super.debug(text);
        }
 
        private void forceFlushOnOutput() {
            System.out.flush();
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {}
        }
    }
 
    private static abstract class ProdConfig extends AbstractConfig {
 
        private PrintStream output;
 
        public ProdConfig(boolean debugEnabled) {
            super(debugEnabled);
        }
 
        protected abstract String getOutputFile();
 
        @Override
        public PrintStream output() {
            if (output == null) {
                String target = BASE_PATH + getOutputFile() + ".txt";
                try {
                    output = new PrintStream(target);
                }
                catch (FileNotFoundException e) {
                    throw new IllegalArgumentException("File " + target + " cannot be written.");
                }
            }
            return output;
        }
 
        @Override
        public void done() {
            if (output == null) {
                System.err.println("[ERROR] No output");
            }
            else {
                output.close();
                output = null;
                System.out.println("Done.");
            }
            super.done();
        }
    }
 
    protected static void configTest(final int index, boolean debugEnabled) {
        installConfig(new DevConfig(debugEnabled) {
 
            @Override
            protected String getInputFile() {
                return "test" + index;
            }
        });
    }
 
    protected static void configSmall(final int index, boolean debugEnabled) {
        System.out.println("Processing small problem (attempt " + index + ")...");
        installConfig(new ProdConfig(debugEnabled) {
 
            @Override
            protected String getInputFile() {
                return "small" + index;
            }
 
            @Override
            protected String getOutputFile() {
                return "output-small" + index;
            }
        });
    }
 
    protected static void configLarge(boolean debugEnabled) {
        System.out.println("Processing large problem...");
        installConfig(new ProdConfig(debugEnabled) {
 
            @Override
            protected String getInputFile() {
                return "large";
            }
 
            @Override
            protected String getOutputFile() {
                return "output-large";
            }
        });
    }
 
    private static void installConfig(Config config) {
        if (CURRENT_CONFIG != null) {
            throw new IllegalStateException("I/O config already defined.");
        }
        CURRENT_CONFIG = config;
    }
 }
