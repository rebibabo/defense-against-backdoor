package round1a.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Set;
 import java.util.SortedSet;
 import java.util.TreeSet;
 
 public class P2
 {
    private static void calculate( int N, int P, int[] ingredients, int[][] packages, BufferedWriter bw, int n ) throws Exception
    {
        Interval[][] kitSize = new Interval[P][N];
        for ( int i = 0; i < P; i++ )
            for ( int j = 0; j < N; j++ )
                kitSize[i][j] = enoughFor( packages[i][j], ingredients[j] );
        
        int max = 0;
        if ( N == 1 )
            for ( int i = 0; i < P; i++ )
                if ( kitSize[i][0] != null )
                    max++;
        if ( N == 2 )
            max = check2( N, P, kitSize, 0, new HashSet<Integer>() );
        
        bw.append( "Case #"+n+": " + max + "\n" );
        bw.flush();
    }
    
    private static int check2( int N, int P, Interval[][] kitSize, int p, Set<Integer> taken )
    {
        if ( p == P )
            return 0;
        int max = check2( N, P, kitSize, p+1, new HashSet<Integer>( taken ) );
        
        Interval nums = kitSize[p][0];
        if ( nums != null )
            for ( int j = 0; j < P; j++ )
            {
                if ( taken.contains( j ) || kitSize[j][1] == null )
                    continue;
                
                if ( nums.intersects( kitSize[j][1] ) )
                {
                    if ( p == P - 1 )
                        return 1;
                    Set<Integer> newTaken = new HashSet<Integer>( taken );
                    newTaken.add( j );
                    int restMax = check2( N, P, kitSize, p + 1, newTaken );
                    max = Math.max( max, restMax + 1 );
                }
            }
        
        return max;
    }
    
    private static class Interval
    {
        private final int start;
        private final int end;
        
        public Interval( int start, int end )
        {
            this.start = start;
            this.end = end;
        }
        
        public boolean intersects( Interval other )
        {
            return
                    other.start >= start && other.start <= end ||
                    other.end >= start && other.end <= end;
        }
    }
    
    private static Interval enoughFor( int amount, int neededAmount )
    {
        SortedSet<Integer> enoughFor = new TreeSet<>();
        int div = amount / neededAmount;
        if ( div * neededAmount * 90 <= amount * 100 && div * neededAmount * 110 >= amount * 100 )
            enoughFor.add( div );
        for ( int i = 1; ; i++ )
            if ( (div + i) * neededAmount * 90 <= amount * 100 && (div + i) * neededAmount * 110 >= amount * 100 )
                enoughFor.add( div + i );
            else
                break;
        for ( int i = 1; ; i++ )
            if ( (div - i) * neededAmount * 90 <= amount * 100 && (div - i) * neededAmount * 110 >= amount * 100 )
                enoughFor.add( div - i );
            else
                break;
        
        return enoughFor.isEmpty() ? null : new Interval( enoughFor.first(), enoughFor.last() );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "src/" + P2.class.getPackage().getName().replace( '.', '/' ) + "/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        File outputFile = new File( "src/" + P2.class.getPackage().getName().replace( '.', '/' ) + "/output.txt" );
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
            
            String[] is = br.readLine().split( " " );
            int[] ingredients = new int[N];
            for ( int j = 0; j < N; j++ )
                ingredients[j] = Integer.parseInt( is[j] );
            
            int[][] packages = new int[P][N];
            for ( int j = 0; j < N; j++ )
            {
                String[] packageData = br.readLine().split( " " );
                for ( int k = 0; k < P; k++ )
                    packages[k][j] = Integer.parseInt( packageData[k] );
            }
            
            calculate( N, P, ingredients, packages, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
