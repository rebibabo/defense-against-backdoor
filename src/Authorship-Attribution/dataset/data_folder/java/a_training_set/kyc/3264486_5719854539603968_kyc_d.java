package codejam2017q;
 
 import org.junit.Test;
 import lib.CodeJamCommons;
 
 public class D extends CodeJamCommons {
 
     int N;
     boolean[][] gridPlus;
     boolean[][] gridTimes;
     boolean[][] hasPlus;
     boolean[][] hasTimes;
     boolean[][] hasPlusAll;
     boolean[][] hasTimesAll;
 
     @Test
     public void run() throws Exception {
         file("D-small-attempt1");
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             N = in.nextInt();
             int K = in.nextInt();
             gridPlus = new boolean[N][N];
             gridTimes = new boolean[N][N];
             hasPlus = new boolean[N][N];
             hasTimes = new boolean[N][N];
             hasPlusAll = new boolean[N][N];
             hasTimesAll = new boolean[N][N];
             int score = 0;
             for (int i = 0; i < K; i++) {
                 char c = in.next().charAt(0);
                 int row = in.nextInt();
                 int col = in.nextInt();
                 if (c == '+' || c == 'o') {
                     addPlus(row - 1, col - 1);
                     score++;
                 }
                 if (c == 'x' || c == 'o') {
                     addTimes(row - 1, col - 1);
                     score++;
                 }
             }
             for (int i = 0; i < N; i++)
                 for (int j = 0; j < N; j++)
                     if (!gridTimes[i][j]) {
                         addTimes(i, j);
                         score++;
                         hasTimes[i][j] = true;
                     }
             for (int i = 0; i < N; i++) {
                 if (!gridPlus[0][i]) {
                     addPlus(0, i);
                     score++;
                     hasPlus[0][i] = true;
                 }
                 if (!gridPlus[N - 1][i]) {
                     addPlus(N - 1, i);
                     score++;
                     hasPlus[N - 1][i] = true;
                 }
                 if (!gridPlus[i][0]) {
                     addPlus(i, 0);
                     score++;
                     hasPlus[i][0] = true;
                 }
                 if (!gridPlus[i][N - 1]) {
                     addPlus(i, N - 1);
                     score++;
                     hasPlus[i][N - 1] = true;
                 }
             }
             int z = 0;
             for (int i = 0; i < N; i++)
                 for (int j = 0; j < N; j++)
                     if (hasPlus[i][j] || hasTimes[i][j])
                         z++;
             out.printf("Case #%d: ", n + 1);
             out.println(score + " " + z);
             for (int i = 0; i < N; i++)
                 for (int j = 0; j < N; j++)
                     if (hasPlus[i][j] || hasTimes[i][j]) {
                         if (hasPlusAll[i][j] && hasTimesAll[i][j])
                             out.println("o " + (i + 1) + " " + (j + 1));
                         else if (hasPlusAll[i][j])
                             out.println("+ " + (i + 1) + " " + (j + 1));
                         else if (hasTimesAll[i][j])
                             out.println("x " + (i + 1) + " " + (j + 1));
                     }
         }
     }
 
     void addPlus(int row, int col) {
         for (int r = row, c = col; inBounds(r, c); r++, c++)
             gridPlus[r][c] = true;
         for (int r = row, c = col; inBounds(r, c); r++, c--)
             gridPlus[r][c] = true;
         for (int r = row, c = col; inBounds(r, c); r--, c++)
             gridPlus[r][c] = true;
         for (int r = row, c = col; inBounds(r, c); r--, c--)
             gridPlus[r][c] = true;
         hasPlusAll[row][col] = true;
     }
 
     void addTimes(int row, int col) {
         for (int i = 0; i < N; i++)
             gridTimes[row][i] = gridTimes[i][col] = true;
         hasTimesAll[row][col] = true;
     }
 
     boolean inBounds(int row, int col) {
         return row >= 0 && row < N && col >= 0 && col < N;
     }
 }
