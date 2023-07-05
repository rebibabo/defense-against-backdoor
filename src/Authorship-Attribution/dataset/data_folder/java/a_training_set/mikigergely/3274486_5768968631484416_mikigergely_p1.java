package round1c.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.math.BigDecimal;
 import java.util.Arrays;
 
 public class P1
 {
    private static class Pencake implements Comparable<Pencake>
    {
        private final BigDecimal r;
        private final BigDecimal h;
        
        public Pencake( int r, int h )
        {
            this.r = BigDecimal.valueOf( r );
            this.h = BigDecimal.valueOf( h );
        }
        
        @Override
        public int compareTo( Pencake p ) {
            return p.r.subtract( r ).toBigInteger().intValue();
        }
    }
    
    private static void calculate( int N, int K, Pencake[] pencakes, BufferedWriter bw, int n ) throws Exception
    {
        Arrays.sort( pencakes );
        
        BigDecimal bd2 = BigDecimal.valueOf( 2 ); 
        BigDecimal bdPi = BigDecimal.valueOf( Math.PI ); 
        
        BigDecimal[] side = new BigDecimal[N];
        for ( int i = 0; i < N; i++ )
            side[i] = bd2.multiply( pencakes[i].r ).multiply( bdPi ).multiply( pencakes[i].h );
        
        BigDecimal[] surface = new BigDecimal[N];
        for ( int i = 0; i < N; i++ )
            surface[i] = pencakes[i].r.multiply( pencakes[i].r ).multiply( bdPi );
        
        BigDecimal max = BigDecimal.ZERO;
        for ( int i = 0; i < N - K + 1; i++ )
        {
            BigDecimal s = surface[i].add( side[i] ).add( max( side, i, N, K-1 ) );
            max = max.max( s );
        }
        
        bw.append( "Case #"+n+": " + max.toPlainString() + "\n" );
        bw.flush();
    }
    
    private static BigDecimal max( BigDecimal[] side, int i, int N, int l )
    {
        BigDecimal[] sides = new BigDecimal[N - (i + 1) ];
        for ( int j = i+1; j <N; j++ )
            sides[j-(i+1)] = side[j];
        
        Arrays.sort( sides );
        BigDecimal maxSides = BigDecimal.ZERO;
        for ( int j = sides.length - 1; j >= sides.length - l; j-- )
            maxSides = maxSides.add( sides[j] );
        
        return maxSides;
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
            String[] data = br.readLine().split(" ");
            int N = Integer.parseInt( data[0] );
            int K = Integer.parseInt( data[1] );
            
            Pencake[] pencakes = new Pencake[N];
            for ( int j = 0; j < N; j++ )
            {
                String[] p = br.readLine().split(" ");
                Pencake pencake = new P1.Pencake( Integer.parseInt( p[0] ), Integer.parseInt( p[1] ) );
                pencakes[j] = pencake;
            }
            
            calculate( N, K, pencakes, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
