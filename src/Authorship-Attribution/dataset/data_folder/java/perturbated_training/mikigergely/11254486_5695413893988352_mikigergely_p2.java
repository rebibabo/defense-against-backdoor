package round1b.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P2
 {
    private static int bestC = -1;
    private static int bestJ = -1;
    private static String bestCString = null;
    private static String bestJString = null;
    
    private static void calculate( String C, String J, BufferedWriter bw, int n ) throws Exception
    {
        bestC = -1;
        bestJ = -1;
        bestCString = null;
        bestJString = null;
        
        check( C, J, 0 );
        
        bw.append( "Case #" + n + ": " + bestCString + " " + bestJString + "\n" );
        bw.flush();
    }
    
    private static void check( String c, String j, int pos )
    {
        if ( pos == c.length() )
        {
            checkNumbers( c, j );
            return;
        }
        
        if ( c.charAt( pos ) != '?' && j.charAt( pos ) != '?' )
            check( c, j, pos + 1 );
        else if ( c.charAt( pos ) != '?' && j.charAt( pos ) == '?' )
        {
            for ( int i = 0; i < 10; i++ )
                check( c, j.substring( 0, pos ) + i + j.substring( pos + 1 ), pos + 1 );
        }
        else if ( c.charAt( pos ) == '?' && j.charAt( pos ) != '?' )
        {
            for ( int i = 0; i < 10; i++ )
                check( c.substring( 0, pos ) + i + c.substring( pos + 1 ), j, pos + 1 );
        }
        else if ( c.charAt( pos ) == '?' && j.charAt( pos ) == '?' )
        {
            for ( int i = 0; i < 10; i++ )
                for ( int k = 0; k < 10; k++ )
                    check( c.substring( 0, pos ) + i + c.substring( pos + 1 ), j.substring( 0, pos ) + k + j.substring( pos + 1 ), pos + 1 );
        }
    }
    
    private static void checkNumbers( String c, String j )
    {
        int cNum = Integer.parseInt( c );
        int jNum = Integer.parseInt( j );
        
        boolean better = false;
        if ( bestC == -1 )
            better = true;
        else
        {
            int bestDiff = Math.abs( bestC - bestJ );
            int thisDiff = Math.abs( cNum - jNum );
            if ( bestDiff > thisDiff )
                better = true;
            if ( bestDiff == thisDiff && bestC > cNum )
                better = true;
            if ( bestDiff == thisDiff && bestC == cNum && bestJ > jNum )
                better = true;
        }
        
        if ( better )
        {
            bestC = cNum;
            bestJ = jNum;
            bestCString = c;
            bestJString = j;
        }
    }
 
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round1b/p2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round1b/p2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            String C = data[0];
            String J = data[1];
            
            calculate( C, J, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
