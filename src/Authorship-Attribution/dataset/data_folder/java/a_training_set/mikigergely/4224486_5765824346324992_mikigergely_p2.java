package round1a.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.Arrays;
 
 public class P2
 {
    private static class Status
    {
        long[] progress;
        long count;
        
        Status( int B ) {
            progress = new long[B];
            count = 0;
        }
        
        Status( long[] progress, long count ) {
            this.progress = progress;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return count + " - " + Arrays.toString( progress );
        }
    }
    
    private static void calculate( int B, long N, long[] m, BufferedWriter bw, int n ) throws Exception
    {
        Status status = new Status( B );
        
        boolean up = true;
        int step = 1;
        while ( true )
        {
            Status newStatus = cut( status, step, m, B );
            if ( newStatus.count < N )
            {
                if ( up )
                    step = step * 10;
                status = newStatus;
            }
            else
            {
                up = false;
                if ( step == 1 )
                    break;
                else step = step / 10;
            }
        }
        
        int b = 0;
        
        long pos = N - status.count;
        for ( int i = 0; i < B; i++ )
        {
            
            if ( status.progress[i] == 0 )
            {
                if ( pos > 1 )
                    pos--;
                else
                {
                    b = i+1;
                    break;
                }
            }
        }
        
        bw.append( "Case #"+n+": " + b + "\n" );
        bw.flush();
    }
    
    private static Status cut( Status status, int step, long[] m, int B )
    {
        long[] progress = status.progress;
        long count = status.count;
        
        long[] newProgress = new long[B];
        
        for ( int i = 0; i < B; i++ )
        {
            long t = progress[i] + step;
            newProgress[i] = t % m[i];
            
            count += t / m[i] - ( progress[i] != 0  ? 1 : 0 ) + ( newProgress[i] != 0 ? 1 : 0 );
        }
        
        return new Status( newProgress, count );
    }
 
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/round1a/P2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/round1a/P2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int B = Integer.parseInt( data[0] );
            long N = Integer.parseInt( data[1] );
            
            data = br.readLine().split( " " );
            
            long[] m = new long[B];
            for ( int j = 0; j < B; j++ )
                m[j] = Integer.parseInt( data[j] );
            
            calculate( B, N, m, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
