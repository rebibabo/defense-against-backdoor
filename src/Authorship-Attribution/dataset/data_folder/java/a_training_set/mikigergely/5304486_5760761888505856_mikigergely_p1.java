package round1a.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Set;
 
 public class P1
 {
    private static void calculate( int R, int C, char[][] cake, BufferedWriter bw, int n ) throws Exception
    {
        Set<Character> handled = new HashSet<>();
        
        for ( int i = 0; i < R; i++ )
            for ( int j = 0; j < C; j++ )
            {
                char c = cake[i][j];
                if ( c == '?' || handled.contains( c ) )
                    continue;
                
                handled.add( c );
                setSlice( R, C, cake, i, j );
            }
        
        bw.append( "Case #"+n+":\n" );
        for ( int i = 0; i < R; i++ )
        {
            for ( int j = 0; j < C; j++ )
                bw.append( cake[i][j] );
            
            bw.append( '\n' );
        }
        
        bw.flush();
    }
    
    private static void setSlice( int R, int C, char[][] cake, int i, int j )
    {
        char c = cake[i][j];
        int sliceXBottom = i;
        int sliceXTop = i;
        int sliceYLeft = j;
        int sliceYRight = j;
        
        while ( sliceYLeft > 0 && isFree( sliceXBottom, sliceXTop, sliceYLeft - 1, sliceYRight, c, cake ) )
            sliceYLeft--;
        while ( sliceYRight < C-1 && isFree( sliceXBottom, sliceXTop, sliceYLeft, sliceYRight + 1, c, cake ) )
            sliceYRight++;
        while ( sliceXBottom > 0 && isFree( sliceXBottom - 1, sliceXTop, sliceYLeft, sliceYRight, c, cake ) )
            sliceXBottom--;
        while ( sliceXTop < R-1 && isFree( sliceXBottom, sliceXTop + 1, sliceYLeft, sliceYRight, c, cake ) )
            sliceXTop++;
        
        for ( int i1 = sliceXBottom; i1 <= sliceXTop; i1++ )
            for ( int j1 = sliceYLeft; j1 <= sliceYRight; j1++ )
                cake[i1][j1] = c;
    }
    
    private static boolean isFree( int sliceXBottom, int sliceXTop, int sliceYLeft, int sliceYRight, char c, char[][] cake )
    {
        for ( int i = sliceXBottom; i <= sliceXTop; i++ )
            for ( int j = sliceYLeft; j <= sliceYRight; j++ )
                if ( cake[i][j] != c && cake[i][j] != '?' )
                    return false;
        
        return true;
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/" + P1.class.getPackage().getName().replace( '.', '/' ) + "/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        File outputFile = new File( "src/" + P1.class.getPackage().getName().replace( '.', '/' ) + "/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int R = Integer.parseInt( data[0] );
            int C = Integer.parseInt( data[1] );
            
            char[][] cake = new char[R][C];
            
            for ( int j = 0; j < R; j++ )
                cake[j] = br.readLine().toCharArray();
            
            calculate( R, C, cake, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
