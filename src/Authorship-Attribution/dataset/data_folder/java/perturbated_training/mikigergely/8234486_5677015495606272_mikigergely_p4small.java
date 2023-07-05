package round2.p4;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Set;
 
 public class P4Small
 {
    private static int conf;
    
    private static void calculate( int R, int C, BufferedWriter bw, int n ) throws IOException
    {
        conf = 0;
        solve( R, C ); 
        
        String s = "Case #" + n + ": " + conf + "" + "\n";
        bw.append( s );
        bw.flush();
    }
    
    private static void solve( int R, int C )
    {
        solve3( R, C, 1 );
        solve2( R, C, 1 );
        if ( C == 6 )
        {
            if ( R == 6 )
                conf += ( 6 + 6 );   
            else
                solve21( R, C, 1 );
        }
    }
    
    private static void solve3( int R, int C, int mul )
    {
        if ( R < 2 )
            return;
        if ( R == 2 )
            conf += mul;
        else
        {
            solve2( R-2, C, mul );
            if ( C == 6 )
                solve21( R-2, C, mul );
        }
    }
    
    private static void solve2( int R, int C, int mul )
    {
        if ( R < 1 )
            return;
        if ( R == 1 )
            conf += mul;
        else
            solve3( R-1, C, mul );
    }
    
    private static void solve21( int R, int C, int mul )
    {
        if ( R < 2 )
            return;
        if ( R == 2 )
            conf += 2*mul;
        if ( R > 2 )
            solve3( R-1, C, 2*mul );
            
    }
 
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round2/P4/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round2/P4/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] rc = br.readLine().split( " " );
            int R = Integer.parseInt( rc[0] );
            int C = Integer.parseInt( rc[1] );
            
            calculate( R, C, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
