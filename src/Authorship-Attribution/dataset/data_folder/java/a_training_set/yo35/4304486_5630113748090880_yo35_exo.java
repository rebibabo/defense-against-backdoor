package exo2;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {
        
        
        
        
        
        
        configSmall(1, false);
        
        
        
        
        
        try {
            int testCaseCount = input().nextInt();
            for(int i=1; i<=testCaseCount; ++i) {
                
                
                
                
                
                output().println("Case #" + i + ": " + implode(solveTestCase()));
                
                
                
                
            }
        }
        finally {
            done();
        }
    }
    
    
    
    
    
    private static int[] solveTestCase() {
        
        int N = input().nextInt();
        int[][] data = new int[2*N - 1][N];
        
        for(int i=0; i<2*N-1; ++i) {
            for(int j=0; j<N; ++j) {
                data[i][j] = input().nextInt();
            }
        }
        
        boolean[] alreadyUsed = new boolean[2*N-1];
        int[][] grid = new int[N][N];
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        InfoRes info = tryFillGrid(N, data, alreadyUsed, grid, 0, -1, false);
        
        printGrid(N, grid);
        
        int[] result = new int[N];
        for(int k=0; k<N; ++k) {
            result[k] = info.missingIsCol ? grid[k][info.missingIndex] : grid[info.missingIndex][k]; 
        }
        return result;
    }
    
    private static InfoRes tryFillGrid(int N, int[][] data, boolean[] alreadyUsed, int[][] grid, int j, int missingIndex, boolean missingIsCol) {
        
        if(j >= N) {
            return new InfoRes(missingIndex, missingIsCol);
        }
        
        Pair p = findMin(N, data, alreadyUsed, j);
        
        
        if(tryFillColumn(N, data, grid, j, p.index1, missingIndex, missingIsCol)) {
            
            debug("Filled col[" + j + "] with " + p.index1);
            
            if(p.index2 >= 0) {
                if(tryFillRow(N, data, grid, j, p.index2, missingIndex, missingIsCol)) {
                    
                    debug("Filled row[" + j + "] with " + p.index2 + " (index2)");
                    
                    alreadyUsed[p.index1] = true;
                    alreadyUsed[p.index2] = true;
                    
                    InfoRes res = tryFillGrid(N, data, alreadyUsed, grid, j+1, missingIndex, missingIsCol);
                    
                    alreadyUsed[p.index1] = false;
                    alreadyUsed[p.index2] = false;
                    
                    if(res.itWorks) {
                        return res;
                    }
                }
            }
            else {
                
                debug("Missing is index " + j + " (row)");
                
                alreadyUsed[p.index1] = true;
                
                InfoRes res = tryFillGrid(N, data, alreadyUsed, grid, j+1, j, false);
                
                alreadyUsed[p.index1] = false;
                
                if(res.itWorks) {
                    return res;
                }
            }
        }
        
        
        if(tryFillRow(N, data, grid, j, p.index1, missingIndex, missingIsCol)) {
            
            debug("Filled row[" + j + "] with " + p.index1);
            
            if(p.index2 >= 0) {
                if(tryFillColumn(N, data, grid, j, p.index2, missingIndex, missingIsCol)) {
                    
                    debug("Filled col[" + j + "] with " + p.index2 + " (index2)");
                    
                    alreadyUsed[p.index1] = true;
                    alreadyUsed[p.index2] = true;
                    
                    InfoRes res = tryFillGrid(N, data, alreadyUsed, grid, j+1, missingIndex, missingIsCol);
                    
                    alreadyUsed[p.index1] = false;
                    alreadyUsed[p.index2] = false;
                    
                    if(res.itWorks) {
                        return res;
                    }
                }
            }
            else {
                
                
                debug("Missing is index " + j + " (col)");
                
                alreadyUsed[p.index1] = true;
                
                InfoRes res = tryFillGrid(N, data, alreadyUsed, grid, j+1, j, true);
                
                alreadyUsed[p.index1] = false;
                
                if(res.itWorks) {
                    return res;
                }
            }
        }
        
        debug("Failure at index " + j);
        
        return new InfoRes();
    }
    
    private static void printGrid(int N, int[][] grid) {
        debug("Grid:");
        for(int k=0; k<N; ++k) {
            debug(implode(grid[k]));
        }
    }
    
    private static boolean tryFillRow(int N, int[][] data, int[][] grid, int j, int index, int missingIndex, boolean missingIsCol) {
        for(int k=0; k<j; ++k) {
            if(missingIndex == k && missingIsCol) {
                grid[j][k] = data[index][k];
            }
            
            if(grid[j][k] != data[index][k]) {
                return false;
            }
        }
        for(int k=j; k<N; ++k) {
            grid[j][k] = data[index][k];
        }
        return true;
    }
    
    private static boolean tryFillColumn(int N, int[][] data, int[][] grid, int j, int index, int missingIndex, boolean missingIsCol) {
        for(int k=0; k<j; ++k) {
            if(missingIndex == k && !missingIsCol) {
                grid[k][j] = data[index][k];
            }
            
            if(grid[k][j] != data[index][k]) {
                return false;
            }
        }
        for(int k=j; k<N; ++k) {
            grid[k][j] = data[index][k];
        }
        return true;
    }
    
    private static Pair findMin(int N, int[][] data, boolean[] alreadyUsed, int j) {
        int value = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;
        
        for(int i=0; i<2*N-1; ++i) {
            if(alreadyUsed[i] || data[i][j] > value) {
                continue;
            }
            
            if(data[i][j] == value) {
                index2 = i;
            }
            else if(data[i][j] < value) {
                value = data[i][j];
                index1 = i;
                index2 = -1;
            }
        }
        
        return new Pair(index1, index2);
    }
    
    private static class Pair {
        final int index1;
        final int index2;
        
        public Pair(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }
    
    private static class InfoRes {
        final boolean itWorks;
        final int missingIndex;
        final boolean missingIsCol;
        
        public InfoRes() {
            this.itWorks = false;
            this.missingIndex = -1;
            this.missingIsCol = false;
        }
        
        public InfoRes(int missingIndex, boolean missingIsCol) {
            this.itWorks = true;
            this.missingIndex = missingIndex;
            this.missingIsCol = missingIsCol;
        }
    }
    
    
    
 }
