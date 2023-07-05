package round1c.p2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 public class P2
 {
    private static void calculate( int[][] cA, int[][] jA, int Ac, int Aj, BufferedWriter bw, int n ) throws Exception
    {
        int[] day = new int[1440];
        
        for ( int i = 0; i < Ac; i++ )
            for ( int j = cA[i][0]; j < cA[i][1]; j++ )
                day[j] = 1;
        
        for ( int i = 0; i < Aj; i++ )
            for ( int j = jA[i][0]; j < jA[i][1]; j++ )
                day[j] = 2;
        
        int lastState = -1;
        int lastStatePos = -1;
        for ( int i = 1439; i >= 0; i-- )
            if ( day[i] != 0 )
            {
                lastState = day[i];
                lastStatePos = i;
                break;
            }
        
        int firstState = -1;
        int firstStatePos = -1;
        for ( int i = 0; i < 1440; i++ )
            if ( day[i] != 0 )
            {
                firstState = day[i];
                firstStatePos = i;
                break;
            }
        
        for ( int i = 1439; i >= 0; i-- )
            if ( day[i] == 0 )
                day[i] = ( lastState == firstState ? ( lastState == 1 ? 11 : 12 ) : 3 );
            else
                break;
        
        for ( int i = 0; i < 1440; i++ )
            if ( day[i] == 0 )
                day[i] = ( lastState == firstState ? ( lastState == 1 ? 11 : 12 ) : 3 );
            else
                break;
        
        int state = firstState;
        for ( int i = firstStatePos + 1; i <= lastStatePos; i++ )
        {
            if ( day[i] == 0 )
            {
                int s = i + 1;
                while ( day[s] == 0 )
                    s++;
                for ( int j = i; j < s; j++ )
                    day[j] = ( state == day[s] ? ( state == 1 ? 11 : 12 ) : 3 );
            }
            else if ( day[i] == 1 || day[i] == 2 )
                state = day[i];
        }
        
        int zone3Num = 0;
        int zone3Size = 0;
        for ( int i = 0; i < 1440; i++ )
            if ( day[i] == 3 )
            {
                zone3Size++;
                if ( ( i >= 1 && day[i - 1] != 3 ) || ( i == 0 && day[1439] != 3 ) )
                    zone3Num++;
            } else {
                int dayState = day[i];
                int prevDayState = ( i == 0 ? day[1439] : day[i-1] );
                
                if ( dayState == 1 && prevDayState == 2 || dayState == 2 && prevDayState == 1 )
                    zone3Num++;
            }
        
        
        int time1 = 0;
        for ( int i = 0; i < 1440; i++ )
            if ( day[i] == 1 || day[i] == 11 )
                time1++;
        
        int time2 = 0;
        for ( int i = 0; i < 1440; i++ )
            if ( day[i] == 2 || day[i] == 12 )
                time2++;
        
        
        int start1Bridge = 0;
        List<Integer> bridges1 = new ArrayList<>();
        int bridgeStart = -1;
        for ( int i = 0; i < 1440; i++ )
        {
            if ( day[i] == 11 && bridgeStart == -1 )
                bridgeStart = i;
            if ( day[i] != 11 && bridgeStart != -1 )
            {
                if ( bridgeStart == 0 )
                    start1Bridge = i;
                else
                    bridges1.add( i - bridgeStart );
                
                bridgeStart = -1;
            }
        }
        int end1Bridge = ( bridgeStart == -1 ? 0 : 1439 - bridgeStart + 1);
        if ( start1Bridge != 0 || end1Bridge != 0 )
            bridges1.add( start1Bridge + end1Bridge );
        
        int start2Bridge = 0;
        List<Integer> bridges2 = new ArrayList<>();
        bridgeStart = -1;
        for ( int i = 0; i < 1440; i++ )
        {
            if ( day[i] == 12 && bridgeStart == -1 )
                bridgeStart = i;
            if ( day[i] != 12 && bridgeStart != -1 )
            {
                if ( bridgeStart == 0 )
                    start2Bridge = i;
                else
                    bridges2.add( i - bridgeStart );
                
                bridgeStart = -1;
            }
        }
        int end2Bridge = ( bridgeStart == -1 ? 0 : 1439 - bridgeStart + 1);
        if ( start2Bridge != 0 || end2Bridge != 0 )
            bridges2.add( start2Bridge + end2Bridge );
        
        Collections.sort( bridges1 );
        Collections.sort( bridges2 );
        Collections.reverse( bridges1 );
        Collections.reverse( bridges2 );
        
        int c = zone3Num;
        
        if ( time1 > 720 )
        {
            for ( int i = 0; time1 > 720; i++ )
            {
                c += 2;
                time1 -= bridges1.get( i );
            }
        }
        
        if ( time2 > 720 )
        {
            for ( int i = 0; time2 > 720; i++ )
            {
                c += 2;
                time2 -= bridges2.get( i );
            }
        }
        
        bw.append( "Case #"+n+": " + c + "\n" );
        bw.flush();
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
            int Ac = Integer.parseInt( data[0] );
            int Aj = Integer.parseInt( data[1] );
            
            int[][] cA = new int[Ac][2];
            for ( int j = 0; j < Ac; j++ )
            {
                String[] a = br.readLine().split( " " );
                cA[j][0] = Integer.parseInt( a[0] );
                cA[j][1] = Integer.parseInt( a[1] );
            }
            
            int[][] jA = new int[Aj][2];
            for ( int j = 0; j < Aj; j++ )
            {
                String[] a = br.readLine().split( " " );
                jA[j][0] = Integer.parseInt( a[0] );
                jA[j][1] = Integer.parseInt( a[1] );
            }
            
            calculate( cA, jA, Ac, Aj, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        br.close();
        bw.close();
        fw.close();
    }
 }
