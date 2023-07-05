package qualification.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.SortedMap;
 import java.util.TreeMap;
 
 public class P3
 {
    private static void calculate( Long N, Long K, BufferedWriter bw, int n ) throws Exception
    {
        SortedMap<Long, Long> holes = new TreeMap<>();
        holes.put( N, 1l );
        Long[] minMax = putIntoHoles( holes, K );
        
        bw.append( "Case #"+n+": " + minMax[1] + " " + minMax[0] + "\n" );
        bw.flush();
    }
    
    private static Long[] putIntoHoles( SortedMap<Long, Long> holes, Long K ) {
        Long largestHole = holes.lastKey();
        
        Long[] largestHoleSplit = largestHole % 2 == 0 ?
                new Long[] { largestHole / 2 - 1, largestHole / 2} :
                new Long[] { ( largestHole - 1 ) / 2, ( largestHole - 1 ) / 2 };
        
        Long largestHoleNum = holes.get( largestHole );
        
        if ( K <= largestHoleNum )
            return largestHoleSplit;
        else
        {
            holes.remove( largestHole );
            
            long minHoles = holes.containsKey( largestHoleSplit[0] ) ?
                    holes.get( largestHoleSplit[0] ) : 0l;
            holes.put( largestHoleSplit[0], minHoles + largestHoleNum );
            
            long maxHoles = holes.containsKey( largestHoleSplit[1] ) ?
                    holes.get( largestHoleSplit[1] ) : 0l;
            holes.put( largestHoleSplit[1], maxHoles + largestHoleNum );
            
            return putIntoHoles( holes, K - largestHoleNum );
        }
        
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/qualification/p3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/qualification/p3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            Long N = Long.parseLong( data[0] );
            Long K = Long.parseLong( data[1] );
            
            calculate( N, K, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
