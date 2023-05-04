package abtric.round1c;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class A {
 
    private static int done = 0;
 
    public static void main(String[] args) {
        
        String stringPath = "A-small-attempt0.in";
        
        try {
            final List<String> inputFile = Files
                    .readAllLines(FileSystems.getDefault().getPath("in\\2016\\1C\\A", stringPath));
            
            final int T = Integer.parseInt(inputFile.get(0));
            done = 0;
            Object lock = new Object();
            String[] results = new String[T];
            for (int i = 0; i < T; i++) {
                final int I = i;
                Runnable runner = new Runnable() {
 
                    @Override
                    public void run() {
                        final int N = Integer.parseInt(inputFile.get(I * 2 + 1));
                        final String[] P = inputFile.get(I * 2 + 2).split(" ");
 
                        String solution = "";
 
                        int[] left = new int[N];
                        int total = 0;
                        for (int j = 0; j < N; j++) {
                            left[j] = Integer.parseInt(P[j]);
                            total += left[j];
                        }
 
                        while (total > 0) {
 
                            int biggest = 0;
                            for (int j = 0; j < N; j++) {
                                if (left[j] > left[biggest]) {
                                    biggest = j;
                                }
                            }
                            int first = biggest;
                            left[biggest]--;
                            total--;
 
                            biggest = 0;
                            for (int j = 0; j < N; j++) {
                                if (left[j] > left[biggest]) {
                                    biggest = j;
                                }
                            }
                            int second = -1;
                            if (left[biggest] > 0) {
                                second = biggest;
                                left[biggest]--;
                                total--;
                            }
 
                            boolean valid = true;
                            for (int j = 0; j < N; j++) {
                                if (left[j] * 1.0 / total > 0.5) {
                                    valid = false;
                                }
                            }
                            if (!valid) {
                                left[second]++;
                                total++;
                                second = -1;
                            }
 
                            solution += (char) (65 + first);
                            if (second >= 0) {
                                solution += (char) (65 + second);
                            }
                            if (total > 0) {
                                solution += ' ';
                            }
                        }
 
                        results[I] = solution;
                        synchronized (lock) {
                            done++;
                        }
                    }
                };
                runner.run();
            }
            while (done < T) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            Writer.write("out\\A-small.out", results);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
