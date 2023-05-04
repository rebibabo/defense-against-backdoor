package exo4;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Base {
    
    protected static void debug(String text) {
        System.out.println("[DEBUG] " + text);
    }
 
    protected interface Config {
        Scanner input();
        PrintStream output();
        void done();
    }
    
    private static String getBasePath() {
        return ECLIPSE_PREFIX + File.separator + Base.class.getPackage().getName() + File.separator;
    }
    
    private static final String ECLIPSE_PREFIX = "src";
    
    private static abstract class AbstractConfig implements Config {
        
        private Scanner _input;
        
        protected abstract String getInputFile();
        
        @Override
        public Scanner input() {
            if(_input == null) {
                String source = getBasePath() + getInputFile() + ".txt";
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
    }
    
    private static abstract class DevConfig extends AbstractConfig {
        
        @Override
        public PrintStream output() {
            return System.out;
        }
    }
    
    private static abstract class ProdConfig extends AbstractConfig {
        
        private PrintStream _output;
        
        protected abstract String getOutputFile();
        
        @Override
        public PrintStream output() {
            if(_output == null) {
                String target = getBasePath() + getOutputFile() + ".txt";
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
    
    protected static Config createTestConfig(final int index) {
        return new DevConfig() {
    
            @Override
            protected String getInputFile() {
                return "test-" + index;
            }
        };
    }
    
    protected static Config createSmallConfig(final int index) {
        System.out.println("Processing small problem (attempt " + index + ")...");
        return new ProdConfig() {
    
            @Override
            protected String getInputFile() {
                return "input-small-" + index;
            }
    
            @Override
            protected String getOutputFile() {
                return "output-small-" + index;
            }
        };
    }
    
    protected static Config createLargeConfig() {
        System.out.println("Processing large problem...");
        return new ProdConfig() {
    
            @Override
            protected String getInputFile() {
                return "input-large";
            }
    
            @Override
            protected String getOutputFile() {
                return "output-large";
            }
        };
    }
    
 }
