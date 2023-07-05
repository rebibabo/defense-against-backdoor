package round2.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.List;
 
 public class P2
 {
    private static double maxTieChance;
    
    private static void calculate( int N, int K, double[] chances, BufferedWriter bw, int n ) throws Exception
    {
        maxTieChance = 0;
        check( N, K, chances, 0, new ArrayList<Double>() );
        
        bw.append( "Case #"+n+": " + maxTieChance + "\n" );
        bw.flush();
    }
    
    private static void check( int N, int K, double[] chances, int pos, List<Double> members )
    {
        if ( members.size() == K )
        {
            double tieChance = getTieChance( members, 0, 0, 0, 1.0 );
            if ( tieChance > maxTieChance )
                maxTieChance = tieChance;
        }
        
        if ( pos == chances.length )
            return;
        
        {
            List<Double> nextMembers = new ArrayList<>( members );
            nextMembers.add( chances[pos] );
            check( N, K, chances, pos + 1, nextMembers );
        }
        
        {
            List<Double> nextMembers = new ArrayList<>( members );
            check( N, K, chances, pos + 1, nextMembers );
        }
    }
    
    private static double getTieChance( List<Double> members, int pos, int yes, int no, double chance )
    {
        if ( pos == members.size() )
            return ( yes == no ? chance : 0 );
        double memberChance = members.get( pos );
        return
            getTieChance( members, pos+1, yes+1, no, chance*memberChance ) +
            getTieChance( members, pos+1, yes, no+1, chance*(1 - memberChance) );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/round2/p2/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "src/round2/p2/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            
            int N = Integer.parseInt( data[0] );
            int K = Integer.parseInt( data[1] );
            
            String[] sChances = br.readLine().split( " " );
            double[] chances = new double[N];
            for ( int j = 0; j < N; j++ )
                chances[j] = Double.parseDouble( sChances[j] );
            
            calculate( N, K, chances, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
