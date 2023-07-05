package abtric.utility;
 
 import java.io.FileWriter;
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.List;
 
 public abstract class Solution {
 
     protected final int m_numOfProblems;
     protected final List<String> m_inputFile;
     protected int m_done = 0;
     protected String[] m_results;
     protected final Object m_lock = new Object();
 
     protected Solution(String stringPath) throws IOException {
         m_inputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
         m_numOfProblems = Integer.parseInt(m_inputFile.get(0));
         m_results = new String[m_numOfProblems];
     }
 
     protected Solution(final List<String> inputFile) {
         m_inputFile = inputFile;
         m_numOfProblems = Integer.parseInt(m_inputFile.get(0));
         m_results = new String[m_numOfProblems];
     }
 
     protected String[] solve() {
         long start = System.currentTimeMillis();
         final int CORES = 8;
         for (int i = 0; i < CORES; i++) {
             List<Integer> tasks = new ArrayList<>();
             int j = i;
             while (j < m_numOfProblems) {
                 tasks.add(j);
                 j += CORES;
             }
             Thread worker = new Thread(getNewRunnable(tasks));
             try {
                 worker.start();
             } catch (Exception e) {
                 e.printStackTrace();
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(m_done + "/" + m_numOfProblems);
                 }
             }
         }
         while (m_done < m_numOfProblems) {
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println(System.currentTimeMillis() - start + "ms");
         return m_results;
     }
 
     protected static void write(String file, String[] content) {
         try {
             System.out.println("writing to " + FileSystems.getDefault().getPath(file));
             FileWriter writer = new FileWriter(file);
             for (int i = 0; i < content.length; i++) {
                 writer.write("Case #" + (i + 1) + ": " + content[i] + "\n");
             }
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     abstract protected Runnable getNewRunnable(List<Integer> i);
 
 }
