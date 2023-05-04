package Round1A;
 
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class BRankAndFile {
     static boolean firstLess(int[] a, int[] b) {
         for (int x = 0; x < a.length; x++) if (a[x] >= b[x]) return false;
         return true;
     }
     
     static void copy(int[] from, int[] to) {
         for (int x = 0; x < from.length; x++) to[x] = from[x];
     }
     
     static void insertAtIndex(int[][] list, int listSize, int[] paper, int index) {
         for (int x = listSize; x > index; x--) copy(list[x - 1], list[x]);
         copy(paper, list[index]);
     }
     
     static int insert(int[][] list, int listSize, int[] paper) {
         if (listSize == list.length) return -1;
         if (listSize == 0 || firstLess(paper, list[0])) {
             insertAtIndex(list, listSize, paper, 0);
             return 0;
         }
         for (int x = 1; x < listSize; x++) {
             if (firstLess(paper, list[x])) {
                 if (firstLess(list[x - 1], paper)) {
                     insertAtIndex(list, listSize, paper, x);
                     return x;
                 } else return -1;
             }
         }
         if (firstLess(list[listSize - 1], paper)) {
             insertAtIndex(list, listSize, paper, listSize);
             return listSize;
         }
         return -1;
     }
     
     static void remove(int[][] list, int listSize, int index) {
         for (int x = index; x <listSize - 1; x++) copy(list[x + 1], list[x]);
     }
     
     static int[] findMissing(int[][] completeList, int[][] incompleteList) {
         for (int x = 0; x < completeList.length; x++) {
             if (x == completeList.length - 1 || incompleteList[x][0] != completeList[0][x]) {
                 
                 int[] missingSlip = new int[completeList[0].length];
                 for (int y = 0; y < missingSlip.length; y++) missingSlip[y] = completeList[y][x];
                 return missingSlip;
             }
         }
         return new int[0]; 
     }
     
     static int find(int[] row, int[] column) {
         for (int x = 0; x < row.length; x++) {
             for (int y = 0; y < column.length; y++) {
                 if (row[x] == column[y]) return x;
             }
         }
         return -1;
     }
     
     static boolean matches(int[] row, int[] column, int rowIndex) {
         for (int x = 0; x < column.length; x++) if (row[rowIndex] == column[x]) return true;
         return false;
     }
     
     static boolean verify(int[][] rows, int rowSize, int[][] columns, int columnSize) {
         if (rowSize == 0 || columnSize == 0) return true;
         int[] referenceColumn = columns[0];
         int referenceIndex = find(rows[0], referenceColumn);
         if (referenceIndex == -1) return false;
         for (int rowNum = 1; rowNum < rows.length; rowNum++) {
             if (!matches(rows[rowNum], referenceColumn, referenceIndex)) return false;
         }
         return true;
     }
     
     static int[] solve(int[][] papers, int startingPaper, int[][] rowSlips, int rowSize, int[][] columnSlips, int columnSize) {
         
         if (startingPaper == papers.length) {
             if (rowSize == rowSlips.length) {
                 return findMissing(rowSlips, columnSlips);
             } else {
                 return findMissing(columnSlips, rowSlips);
             }
         }
         int insertedIndex = insert(rowSlips, rowSize, papers[startingPaper]);
         if (insertedIndex != -1) {
             if (true ) {
                 int[] answer = solve(papers, startingPaper + 1, rowSlips, rowSize + 1, columnSlips, columnSize);
                 if (answer.length != 0) return answer;
             }
             remove(rowSlips, rowSize + 1, insertedIndex);
         }
         insertedIndex = insert(columnSlips, columnSize, papers[startingPaper]);
         if (insertedIndex != -1) {
             if (true ) {
                 int[] answer = solve(papers, startingPaper + 1, rowSlips, rowSize, columnSlips, columnSize + 1);
                 if (answer.length != 0) return answer;
             }
             remove(columnSlips, columnSize + 1, insertedIndex);
         }
         return new int[0];
     }
     
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int n = sc.nextInt();
             int[][] papers = new int[(2 * n) - 1][n];
             for (int x = 0; x < (2 * n) - 1; x++) {
                 for (int y = 0; y < n; y++) {
                     papers[x][y] = sc.nextInt();
                 }
             }
             int[] answer = solve(papers, 0, new int[n][n], 0, new int[n][n], 0);
             System.out.printf("Case #%d:", t);
             for (int x = 0; x < answer.length; x++) {
                 System.out.printf(" %d", answer[x]);
             }
             System.out.println();
         }
         sc.close();
     }
 }
