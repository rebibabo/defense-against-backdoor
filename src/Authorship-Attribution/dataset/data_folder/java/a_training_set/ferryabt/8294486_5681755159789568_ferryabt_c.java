package contest_2017.round_1b;
 
 import java.io.File;
 import java.io.IOException;
 import java.math.BigDecimal;
 import java.math.RoundingMode;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 
 import javax.print.attribute.standard.PDLOverrideSupported;
 
 import abtric.utility.Solution;
 
 
 public class C extends Solution {
 
     
 
     private static final String stringPath = "C-small-attempt1";
     
 
     public static void main(String[] args) {
         try {
 
             
             
 
             
             List<String> rawInputFile = Files.readAllLines(FileSystems.getDefault().getPath("in", stringPath + ".in"));
             List<String> inputFile = new ArrayList<>();
             inputFile.add(rawInputFile.remove(0));
             int T = Integer.parseInt(inputFile.get(0));
             for (int i = 0; i < T; i++) {
                 String[] NQ = rawInputFile.remove(0).split(" ");
                 int N = Integer.parseInt(NQ[0]);
                 int Q = Integer.parseInt(NQ[1]);
                 String horses = rawInputFile.remove(0);
                 for (int j = 1; j < N; j++) {
                     horses += " " + rawInputFile.remove(0);
                 }
                 String distances = rawInputFile.remove(0);
                 for (int j = 1; j < N; j++) {
                     distances += " " + rawInputFile.remove(0);
                 }
                 String routes = rawInputFile.remove(0);
                 for (int j = 1; j < Q; j++) {
                     routes += " " + rawInputFile.remove(0);
                 }
                 inputFile.add(Integer.toString(N));
                 inputFile.add(Integer.toString(Q));
                 inputFile.add(horses);
                 inputFile.add(distances);
                 inputFile.add(routes);
             }
             C solver = new C(inputFile);
 
             String[] result = solver.solve();
 
             write("out" + File.separatorChar + stringPath + ".out", result);
 
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     protected C(String stringPath) throws IOException {
         super(stringPath);
     }
 
     protected C(final List<String> inputFile) throws IOException {
         super(inputFile);
     }
 
     protected Runnable getNewRunnable(final List<Integer> i) {
         return new SolveRunnable(i);
     }
 
     private class SolveRunnable implements Runnable {
         private final List<Integer> I;
 
         private SolveRunnable(final List<Integer> i) {
             I = i;
         }
 
         public void run() {
             for (Integer i : I) {
                 long startTime = System.currentTimeMillis();
                 final int N = Integer.parseInt(m_inputFile.get(i * 5 + 1));
                 final int Q = Integer.parseInt(m_inputFile.get(i * 5 + 2));
                 final String[] horses = m_inputFile.get(i * 5 + 3).split(" ");
                 final int[] E = new int[N];
                 final int[] S = new int[N];
                 for (int j = 0; j < N; j++) {
                     E[j] = Integer.parseInt(horses[j * 2]);
                     S[j] = Integer.parseInt(horses[j * 2 + 1]);
                 }
                 final String[] distances = m_inputFile.get(i * 5 + 4).split(" ");
                 final int[][] D = new int[N][N];
                 for (int j = 0; j < N; j++) {
                     for (int k = 0; k < N; k++) {
                         D[j][k] = Integer.parseInt(distances[j * N + k]);
                     }
                 }
                 final String[] routes = m_inputFile.get(i * 5 + 5).split(" ");
                 final int[] U = new int[Q];
                 final int[] V = new int[Q];
                 for (int j = 0; j < Q; j++) {
                     U[j] = Integer.parseInt(routes[j * 2]);
                     V[j] = Integer.parseInt(routes[j * 2 + 1]);
                 }
 
                 BigDecimal minTime = new BigDecimal(Integer.toString(D[0][1]))
                         .divide(new BigDecimal(Integer.toString(S[0])), 10, RoundingMode.HALF_UP)
                         .add(nextTrip(0, D[0][1], 1, E, S, D, new BigDecimal[N][N]));
 
                 String solution = minTime.setScale(9, RoundingMode.HALF_UP).toString();
 
                 m_results[i] = solution;
                 long duration = System.currentTimeMillis() - startTime;
                 synchronized (m_lock) {
                     m_done++;
                     System.out.println(String.format("%03d/%03d (%dms)", m_done, m_numOfProblems, duration));
                 }
             }
         }
 
         private BigDecimal nextTrip(int currentHorse, int distanceTravelled, int currentCity, int[] E, int[] S,
                 int[][] D, BigDecimal[][] horseCityTime) {
             if (currentCity == D.length - 1) {
                 return new BigDecimal("0");
             }
             if (D[currentCity][currentCity + 1] + distanceTravelled > E[currentHorse]) {
                 return new BigDecimal(Integer.toString(D[currentCity][currentCity + 1]))
                         .divide(new BigDecimal(Integer.toString(S[currentCity])), 10, RoundingMode.HALF_UP)
                         .add(nextTrip(currentCity, D[currentCity][currentCity + 1], currentCity + 1, E, S, D,
                                 horseCityTime));
             } else {
                 if (horseCityTime[currentCity + 1][currentHorse] == null) {
                     horseCityTime[currentCity + 1][currentHorse] = nextTrip(currentHorse,
                             distanceTravelled + D[currentCity][currentCity + 1], currentCity + 1, E, S, D,
                             horseCityTime);
                 }
                 BigDecimal keepHorse = new BigDecimal(Integer.toString(D[currentCity][currentCity + 1]))
                         .divide(new BigDecimal(Integer.toString(S[currentHorse])), 10, RoundingMode.HALF_UP)
                         .add(horseCityTime[currentCity + 1][currentHorse]);
                 if (horseCityTime[currentCity + 1][currentCity] == null) {
                     horseCityTime[currentCity + 1][currentCity] = nextTrip(currentCity, D[currentCity][currentCity + 1],
                             currentCity + 1, E, S, D, horseCityTime);
                 }
                 BigDecimal changeHorse = new BigDecimal(Integer.toString(D[currentCity][currentCity + 1]))
                         .divide(new BigDecimal(Integer.toString(S[currentCity])), 10, RoundingMode.HALF_UP)
                         .add(horseCityTime[currentCity + 1][currentCity]);
                 return keepHorse.min(changeHorse);
             }
         }
     }
 }
