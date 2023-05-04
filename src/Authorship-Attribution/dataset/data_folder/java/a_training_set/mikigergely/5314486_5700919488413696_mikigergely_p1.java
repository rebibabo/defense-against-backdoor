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
    private static void calculate( int N, int P, List<Integer> g, BufferedWriter bw, int n ) throws Exception
    {
        int newPackage = 1;
        if ( P == 2 )
        {
            int numEven = 0;
            for ( int i = 0; i < N; i++ )
                if ( g.get( i ) % 2 == 0 )
                    numEven++;
            int numOdd = N - numEven;
            newPackage += numEven;
            if ( numOdd == 0 )
                newPackage--;
            else
                newPackage += ( numOdd / 2 - 1 + numOdd % 2 ); 
        }
        if ( P == 3 )
        {
            int[] mod = new int[3];
            for ( int i = 0; i < N; i++ )
                mod[g.get( i ) % 3]++;
            
            newPackage += mod[0];
            if ( mod[1] == 0 && mod[2] == 0 )
                newPackage--;
            else
            {
                int common = Math.min( mod[1], mod[2] );
                newPackage += common;
                mod[1] -= common;
                mod[2] -= common;
                if ( mod[1] == 0 && mod[2] == 0 )
                    newPackage--;
                else
                {
                    int still = Math.max( mod[1], mod[2] );
                    newPackage += still / 3 - 1 + ( still % 3 == 0 ? 0 : 1 );
                }
            }
        }
        
        bw.append( "Case #"+n+": " + newPackage + "\n" );
        bw.flush();
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
        BufferedWriter bw = new BufferedWriter( fw );
        
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
            
            calculate( N, P, g, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
