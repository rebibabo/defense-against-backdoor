package round2.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P1
 {
    private static String goodLineUp;
    
    private static void calculate( int N, int R, int P, int S, BufferedWriter bw, int n ) throws Exception
    {
        goodLineUp = null;
        
        checkLineUps( R, P, S, "" );
        
        bw.append( "Case #"+n+": " + ( goodLineUp == null ? "IMPOSSIBLE" : goodLineUp ) + "\n" );
        bw.flush();
    }
    
    private static void checkLineUps( int R, int P, int S, String lineUp )
    {
        if ( goodLineUp != null )
            return;
        
        if ( R == 0 && P == 0 && S == 0 )
        {
            if ( check( lineUp ) )
                goodLineUp = lineUp;
            return;
        }
            
        if ( P != 0 )
            checkLineUps( R, P-1, S, lineUp + "P" );
        if ( R != 0 )
            checkLineUps( R-1, P, S, lineUp + "R" );
        if ( S != 0 )
            checkLineUps( R, P, S-1, lineUp + "S" );
    }
    
    private static boolean check( String lineUp )
    {
        if ( lineUp.length() == 1 )
            return true;
        
        String nextLineUp = "";
        for ( int i = 0; i < lineUp.length() / 2; i++ )
        {
            char p1 = lineUp.charAt( i*2 );
            char p2 = lineUp.charAt( i*2 + 1 );
            if ( p1 == p2 )
                return false;
            if ( p1 == 'R' && p2 == 'S' )
                nextLineUp += 'R';
            if ( p1 == 'R' && p2 == 'P' )
                nextLineUp += 'P';
            if ( p1 == 'P' && p2 == 'S' )
                nextLineUp += 'S';
            if ( p1 == 'P' && p2 == 'R' )
                nextLineUp += 'P';
            if ( p1 == 'S' && p2 == 'R' )
                nextLineUp += 'R';
            if ( p1 == 'S' && p2 == 'P' )
                nextLineUp += 'S';
        }
        
        return check( nextLineUp );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round2/p1/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round2/p1/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int N = Integer.parseInt( data[0] );
            int R = Integer.parseInt( data[1] );
            int P = Integer.parseInt( data[2] );
            int S = Integer.parseInt( data[3] );
            
            calculate( N, R, P, S, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
