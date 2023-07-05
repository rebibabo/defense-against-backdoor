package exo3;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.stream.Collectors;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
 
        
        
        
 
        
        configSmall(2, false);
        
 
        
        
 
        try {
            int testCaseCount = input().nextInt();
            for (int i = 1; i <= testCaseCount; ++i) {
                info("CASE #" + i + " ###################################################");
 
                
                
                
 
                String tmp = solveTestCase();
                output().println("Case #" + i + ": " + (tmp == null ? "IMPOSSIBLE" : "POSSIBLE\n" + tmp));
 
                
                
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
 
        int R = input().nextInt();
        int C = input().nextInt();
 
        input().nextLine();
 
        char[][] grid = new char[R][C];
        char[][] finalGrid = new char[R][C];
        for (int r = 0; r < R; ++r) {
            String line = input().nextLine();
            for (int c = 0; c < C; ++c) {
                grid[r][c] = line.charAt(c);
                finalGrid[r][c] = grid[r][c];
            }
        }
 
        Set<Integer> emptyCells = computeEmptyCell(R, C, grid);
 
        List<OrientableLaser> orientableLasers = new ArrayList<>();
 
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
 
                if (grid[r][c] != '|' && grid[r][c] != '-') {
                    continue;
                }
 
                Map<Character, Set<Integer>> coveredCells = computeCoveredCells(R, C, grid, r, c);
                switch (coveredCells.size()) {
                    case 0: 
                        return null;
 
                    case 1: 
                        for (int sq : coveredCells.values().iterator().next()) {
                            emptyCells.remove(sq);
                        }
                        finalGrid[r][c] = coveredCells.keySet().iterator().next();
                        break;
 
                    case 2: 
                        orientableLasers.add(new OrientableLaser(r, c, coveredCells));
                        break;
 
                    default:
                        throw new IllegalStateException();
                }
            }
        }
 
        Set<Integer> toRemove = new HashSet<>();
 
        for (int sq : emptyCells) {
 
            int nbFound = 0;
            OrientableLaser foundLaser = null;
            char foundOrientation = 'x';
 
            for (OrientableLaser laser : orientableLasers) {
                if (laser.coveredCells.get('-').contains(sq)) {
                    ++nbFound;
                    foundLaser = laser;
                    foundOrientation = '-';
                }
                if (laser.coveredCells.get('|').contains(sq)) {
                    ++nbFound;
                    foundLaser = laser;
                    foundOrientation = '|';
                }
            }
 
            if (nbFound == 0) {
                return null;
            }
            
            
            
            
            
        }
 
        emptyCells.removeAll(toRemove);
 
        return computeFinalGrid(R, C, finalGrid, orientableLasers, emptyCells, 0);
    }
 
    private static String computeFinalGrid(int R, int C, char[][] grid, List<OrientableLaser> orientableLasers,
            Set<Integer> emptyCells, int k) {
        if (k >= orientableLasers.size()) {
            return emptyCells.isEmpty() ? gridToString(R, C, grid) : null;
        }
 
        OrientableLaser currentLaser = orientableLasers.get(k);
 
        grid[currentLaser.r][currentLaser.c] = '-';
        Set<Integer> newEmptyCells = new HashSet<>(emptyCells);
        newEmptyCells.removeAll(currentLaser.coveredCells.get('-'));
 
        String result = computeFinalGrid(R, C, grid, orientableLasers, newEmptyCells, k + 1);
        if (result != null) {
            return result;
        }
 
        grid[currentLaser.r][currentLaser.c] = '|';
        newEmptyCells = new HashSet<>(emptyCells);
        newEmptyCells.removeAll(currentLaser.coveredCells.get('|'));
        return computeFinalGrid(R, C, grid, orientableLasers, newEmptyCells, k + 1);
    }
 
    private static class OrientableLaser {
        public final int r;
        public final int c;
        public final Map<Character, Set<Integer>> coveredCells;
 
        public OrientableLaser(int r, int c, Map<Character, Set<Integer>> coveredCells) {
            this.r = r;
            this.c = c;
            this.coveredCells = coveredCells;
        }
    }
 
    private static String gridToString(int R, int C, char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; ++r) {
            if (r > 0) {
                sb.append("\n");
            }
            for (int c = 0; c < C; ++c) {
                sb.append(grid[r][c]);
            }
        }
        return sb.toString();
    }
 
    private static Set<Integer> computeEmptyCell(int R, int C, char[][] grid) {
        Set<Integer> result = new HashSet<>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == '.') {
                    result.add(r * C + c);
                }
            }
        }
        return result;
    }
 
    private static Map<Character, Set<Integer>> computeCoveredCells(int R, int C, char[][] grid, int r0, int c0) {
        Map<Character, Set<Integer>> result = new HashMap<>();
 
        Set<Integer> horizontal = computeCoveredCells(R, C, grid, r0, c0, 0, 1);
        Set<Integer> horizontal2 = computeCoveredCells(R, C, grid, r0, c0, 0, -1);
        if (horizontal != null && horizontal2 != null) {
            horizontal.addAll(horizontal2);
            result.put('-', horizontal);
        }
 
        Set<Integer> vertical = computeCoveredCells(R, C, grid, r0, c0, 1, 0);
        Set<Integer> vertical2 = computeCoveredCells(R, C, grid, r0, c0, -1, 0);
        if (vertical != null && vertical2 != null) {
            vertical.addAll(vertical2);
            result.put('|', vertical);
        }
 
        return result;
    }
 
    private static Set<Integer> computeCoveredCells(int R, int C, char[][] grid, int r0, int c0, int dr, int dc) {
        Set<Integer> result = new HashSet<>();
        int r = r0 + dr;
        int c = c0 + dc;
        while (r >= 0 && r < R && c >= 0 && c < C) {
 
            switch (grid[r][c]) {
                case '.':
                    result.add(r * C + c);
                    break;
 
                case '-':
                case '|':
                    return null;
 
                case '#':
                    return result;
 
                case '/':
                    int tmp = dc;
                    dc = -dr;
                    dr = -tmp;
                    break;
 
                case '\\':
                    int tmp2 = dc;
                    dc = dr;
                    dr = tmp2;
                    break;
 
                default:
                    throw new IllegalStateException();
            }
 
            r += dr;
            c += dc;
        }
 
        return result;
    }
 
 }
 
 
 
 
 
 
 
 
 
 class Base {
 
    
    
    
 
    public static String join(int[] values) {
        return join(" ", values);
    }
 
    public static String join(long[] values) {
        return join(" ", values);
    }
 
    public static String join(double[] values) {
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
 
    public static String join(String delimiter, double[] values) {
        return Arrays.stream(values).mapToObj(value -> Double.toString(value)).collect(Collectors.joining(delimiter));
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
                    input.useLocale(Locale.ROOT);
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
