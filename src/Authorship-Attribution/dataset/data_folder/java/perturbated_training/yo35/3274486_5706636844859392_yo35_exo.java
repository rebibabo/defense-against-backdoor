package exo2;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.List;
 import java.util.Scanner;
 import java.util.stream.Collectors;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
 
        
        
        
 
        
        configSmall(0, false);
        
 
        
        
 
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
 
    
    
    
 
    private static int solveTestCase() {
 
        int Ac = input().nextInt();
        int Aj = input().nextInt();
 
        int timeJ = 0;
        int timeC = 0;
        int countJ = 0;
        int countC = 0;
        List<Interval> intervals = new ArrayList<>();
 
        for (int i = 0; i < Ac; ++i) {
            int c = input().nextInt();
            int d = input().nextInt();
 
            timeJ += d - c;
            countJ++;
            intervals.add(new Interval(c, d, 'J'));
        }
 
        for (int i = 0; i < Aj; ++i) {
            int c = input().nextInt();
            int d = input().nextInt();
 
            timeC += d - c;
            countC++;
            intervals.add(new Interval(c, d, 'C'));
        }
 
        Collections.sort(intervals, (i1, i2) -> i1.begin - i2.begin);
 
        int remainingJ = 720 - timeJ;
        int remainingC = 720 - timeC;
 
        while (true) {
            int newRemaining = collapse(intervals, remainingJ, 'J');
            if (newRemaining < 0) {
                break;
            }
            remainingJ = newRemaining;
        }
 
        while (true) {
            int newRemaining = collapse(intervals, remainingC, 'C');
            if (newRemaining < 0) {
                break;
            }
            remainingC = newRemaining;
        }
 
        
        
        
        
 
        return countSwitch(intervals);
    }
 
    private static int countSwitch(List<Interval> intervals) {
        int result = 0;
 
        for (int i = 0; i < intervals.size(); ++i) {
            Interval prev = intervals.get(i);
            Interval next = intervals.get((i + 1) % intervals.size());
            if (prev.inCharge == next.inCharge) {
                result += 2;
            }
            else {
                result += 1;
            }
        }
 
        return result;
    }
 
    private static int collapse(List<Interval> intervals, int remainingTime, char target) {
 
        int candidate = -1;
        int bestAdditionalTime = Integer.MAX_VALUE;
 
        for (int i = 0; i < intervals.size(); ++i) {
            Interval prev = intervals.get(i);
            Interval next = intervals.get((i + 1) % intervals.size());
            if (prev.inCharge == target && next.inCharge == target) {
                int currentAdditionalTime = (1440 + next.begin - prev.end) % 1440;
                if (currentAdditionalTime <= remainingTime && currentAdditionalTime < bestAdditionalTime) {
                    candidate = i;
                    bestAdditionalTime = currentAdditionalTime;
                }
            }
        }
 
        if (candidate < 0) {
            return -1;
        }
 
        int afterCandidate = (candidate + 1) % intervals.size();
 
        intervals.get(candidate).end = intervals.get(afterCandidate).end;
        intervals.remove(afterCandidate);
 
        return remainingTime - bestAdditionalTime;
    }
 
    private static class Interval {
        final int begin;
        int end;
        final char inCharge;
 
        public Interval(int begin, int end, char inCharge) {
            this.begin = begin;
            this.end = end;
            this.inCharge = inCharge;
        }
 
        @Override
        public String toString() {
            return inCharge + "[" + begin + " -> " + end + "]";
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
