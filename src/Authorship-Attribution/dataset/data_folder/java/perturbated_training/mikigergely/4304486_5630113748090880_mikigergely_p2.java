package round1a.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 
 public class P2
 {
    private static void calculate( int N, Integer[][] lists, BufferedWriter bw, int n ) throws Exception
    {
        List<Integer[]> ordered = new ArrayList<>();
        for ( Integer[] list : lists )
            ordered.add( list );
        Collections.sort( ordered, new Comparator<Integer[]>()
        {
            @Override
            public int compare( Integer[] o1, Integer[] o2 )
            {
                for ( int i = 0; i < o1.length; i++ )
                    if ( o1[i] < o2[i] )
                        return -1;
                    else if ( o1[i] > o2[i] )
                        return 1;
                return 0;
            }
        } );
        
        Integer[][] board = new Integer[N][N];
        for ( int i = 0; i < N; i++ )
            for ( int j = 0; j < N; j++ )
                board[i][j] = -1;
        
        finalBoard = null;
        missingPos = -1;
        tryToCreateBoard( board, ordered, new boolean[2*N - 1], 0, true );
        
        bw.append( "Case #" + n + ":" );
        for ( int i = 0; i < N; i++ )
            bw.append( " " + ( rowMissing ? finalBoard[missingPos][i] : finalBoard[i][missingPos] ) );
        bw.append( "\n" );
        bw.flush();
    }
    
    private static Integer[][] finalBoard;
    private static boolean rowMissing;
    private static int missingPos;
    
    private static void tryToCreateBoard( Integer[][] board, List<Integer[]> lists, boolean[] used, int n, boolean row )
    {
        if ( n == board.length )
        {
            finalBoard = board;
            return;
        }
        
        mainCycle: for ( int i = 0; i < lists.size(); i++ )
        {
            if ( used[i] )
                continue;
            Integer[] list = lists.get( i );
            
            for ( int j = 0; j < ( row ? n : Math.min( n + 1, board.length ) ); j++ )
            {
                int value = row ? board[n][j] : board[j][n];
                if ( value != -1 && value != list[j] )
                        continue mainCycle;
            }
            
            boolean[] nextUsed = new boolean[ used.length ];
            System.arraycopy( used, 0, nextUsed, 0, used.length );
            nextUsed[i] = true;
            
            Integer[][] nextBoard = new Integer[ board.length ][ board.length ];
            for ( int k = 0; k < board.length; k++ )
                for ( int l = 0; l < board.length; l++ )
                    nextBoard[k][l] = board[k][l];
            for ( int k = 0; k < board.length; k++ )
                if ( row )
                    nextBoard[n][k] = list[k];
                else
                    nextBoard[k][n] = list[k];
            
            
            
            tryToCreateBoard( nextBoard, lists, nextUsed, row ? n : n+1, !row );
            if ( finalBoard != null )
                return;
        }
        
        if ( missingPos == -1 )
        {
            missingPos = n;
            rowMissing = row;
            tryToCreateBoard( board, lists, used, row ? n : n+1, !row );
            if ( finalBoard == null )
                missingPos = -1;
        }
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1a/p2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1a/p2//output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int N = Integer.parseInt( br.readLine() );
            
            Integer[][] lists = new Integer[2*N - 1][N];
            for ( int j = 0; j < 2*N - 1; j++ )
            {
                String[] data = br.readLine().split( " " );
                for ( int k = 0; k < N; k++ )
                    lists[j][k] = Integer.parseInt( data[k] );
            }
            
            calculate( N, lists, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
