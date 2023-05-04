package round1a.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P3
 {
    public static class Point {
        long x;
        long y;
        
        Point( long x, long y ) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static void calculate( int N, Point[] points, BufferedWriter bw, int n ) throws Exception
    {
        bw.append( "Case #"+n+":\n" );
        if ( N == 1 )
        {
            bw.append( "0\n" );
            return;
        }
        
        for ( Point p1 : points )
        {
            int min = Integer.MAX_VALUE;
            for ( Point p2 : points )
            {
                if ( p1 == p2 )
                    continue;
                min = Math.min( min, getMin( points, p1, p2 ) );
            }
            bw.append( min + "\n" );
        }
        
        bw.flush();
    }
    
    private static int getMin( Point[] points, Point p1, Point p2 )
    {
        long a = p2.y - p1.y;
        long b = p1.x - p2.x;
        long c = ( p1.y - p2.y ) * p1.x + ( p2.x - p1.x ) * p1.y;
        
        int cm = 0;
        int cp = 0;
        
        for ( Point p : points )
        {
            long v = a*p.x + b*p.y + c;
            if ( v < 0 ) cm++;
            if ( v > 0 ) cp++;
        }
        
        return Math.min( cm, cp );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round1a/P3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round1a/P3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            int N = Integer.parseInt( br.readLine() );
            
            Point[] points = new Point[N];
            for ( int j = 0; j < N; j++ )
            {
                String[] data = br.readLine().split( " " );
                
                long x = Long.parseLong( data[0] );
                long y = Long.parseLong( data[1] );
                points[j] = new Point( x, y );
            }
            
            calculate( N, points, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
