package round1a.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Set;
 
 public class P3
 {
    private static Set<String> states;
    
    private static void calculate( int hd, int ad, int hk, int ak, int b, int d, BufferedWriter bw, int n ) throws Exception
    {
        states = new HashSet<>();
        int minSteps = minSteps( hd, hd, ad, hk, ak, b, d );
        
        bw.append( "Case #"+n+": " + ( minSteps == -1 ? "IMPOSSIBLE" : minSteps ) + "\n" );
        bw.flush();
    }
    
    private static int minSteps( int hdOrigin, int hd, int ad, int hk, int ak, int b, int d )
    {
        if ( hk <= 0 )
            return 0;
        if ( hd <= 0 )
            return -1;
        
        String stateCode = hd + " " + ad + " " + hk + " " + ak;
        if ( states.contains( stateCode ) )
            return -1;
        
        int minSteps = Integer.MAX_VALUE;
        states.add( stateCode );
        int minStepsA = minSteps( hdOrigin, hd - ak, ad, hk - ad, ak, b, d );
        if ( minStepsA != -1 )
            minSteps = Math.min( minSteps, minStepsA + 1 );
        
        if ( ad < hk && b != 0 )
        {
            int minStepsB = minSteps( hdOrigin, hd - ak, ad + b, hk, ak, b, d );
            if ( minStepsB != -1 )
                minSteps = Math.min( minSteps, minStepsB + 1 );
        }
        
        int minStepsC = minSteps( hdOrigin, hdOrigin - ak, ad, hk, ak, b, d );
        if ( minStepsC != -1 )
            minSteps = Math.min( minSteps, minStepsC + 1 );
        
        if ( ak > 0 && d != 0 )
        {
            int minStepsD = minSteps( hdOrigin, hd - (ak - d), ad, hk, ak - d, b, d );
            if ( minStepsD != -1 )
                minSteps = Math.min( minSteps, minStepsD + 1 );
        }
        
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/" + P3.class.getPackage().getName().replace( '.', '/' ) + "/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        File outputFile = new File( "src/" + P3.class.getPackage().getName().replace( '.', '/' ) + "/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int Hd = Integer.parseInt( data[0] );
            int Ad = Integer.parseInt( data[1] );
            int Hk = Integer.parseInt( data[2] );
            int Ak = Integer.parseInt( data[3] );
            int B = Integer.parseInt( data[4] );
            int D = Integer.parseInt( data[5] );
            
            calculate( Hd, Ad, Hk, Ak, B, D, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
