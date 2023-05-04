package qualification.p3;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class P3
 {
    private enum Quaternion
    {
        I,
        J,
        K,
        ONE
    }
    
    private static class QuaternionWithSign
    {
        private final Quaternion quaternion;
        private final boolean    isMinus;
        
        public QuaternionWithSign( Quaternion quaternion, boolean isMinus )
        {
            this.quaternion = quaternion;
            this.isMinus    = isMinus;
        }
 
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((quaternion == null) ? 0 : quaternion.hashCode());
            result = prime * result + (isMinus ? 1231 : 1237);
            return result;
        }
 
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            QuaternionWithSign other = (QuaternionWithSign) obj;
            if (quaternion != other.quaternion)
                return false;
            if (isMinus != other.isMinus)
                return false;
            return true;
        }
    }
    
    private static void calculate( int L, long X, String ijk, BufferedWriter bw, int n ) throws Exception
    {
        int iLength = getILength( L, ijk );
        int kLength = getKLength( L, ijk );
        
        String answer = null;
        
        if ( iLength == -1 || kLength == -1 || iLength + kLength >= L*X )
            answer = "NO";
        else
        {
            long iBlockCount = (long)( Math.ceil( (double)iLength / (double)L ) );
            long kBlockCount = (long)( Math.ceil( (double)kLength / (double)L ) );
            
            QuaternionWithSign qMiddle = null;
            
            if ( iBlockCount + kBlockCount == X + 1 )
            {
                String middleStab = ijk.substring( iLength % L, ( L - kLength % L ) % L ); 
                qMiddle = getStringValue( middleStab );
            }
            else
            {
                String leftStab  = ( iLength % L == 0 ) ? "" : ijk.substring( iLength % L ); 
                String rightStab = ( kLength % L == 0 ) ? "" : ijk.substring( 0, ( L - kLength % L ) );
                
                long innerCount = X - ( iBlockCount + kBlockCount );
                
                QuaternionWithSign qLeft = getStringValue( leftStab );
                QuaternionWithSign qRight = getStringValue( rightStab );
                
                QuaternionWithSign qIjk = getStringValue( ijk );
                QuaternionWithSign qIjkP = qIjk;
                QuaternionWithSign qInner = new QuaternionWithSign( Quaternion.ONE, false );
                
                while ( innerCount > 0 )
                {
                    int lastDigit = (int)( innerCount % 10 );
                    
                    for ( int i = 0; i < lastDigit; i++ )
                        qInner = mul( qInner, qIjkP );
                    
                    innerCount = ( innerCount - lastDigit ) / 10;
                    qIjkP = power( qIjkP, 10 );
                }
            
                qMiddle = mul( mul( qLeft, qInner ), qRight );
            }
            
            if ( qMiddle.quaternion == Quaternion.J && qMiddle.isMinus == false )
                answer = "YES";
            else
                answer = "NO";
        }
        
        bw.append( "Case #"+n+": " + answer + "\n" );
        bw.flush();
    }
 
    private static int getILength( int L, String ijk )
    {
        QuaternionWithSign actual = new QuaternionWithSign( Quaternion.ONE, false );
        
        int iLength = 0;
        while ( true )
        {
            char c = ijk.charAt( iLength % L );
            Quaternion qc = Quaternion.valueOf( String.valueOf( c ).toUpperCase() );
            QuaternionWithSign qws = new QuaternionWithSign( qc, false );
            actual = mul( actual, qws );
            
            iLength++;
            
            if ( actual.quaternion == Quaternion.I && actual.isMinus == false )
                return iLength;
            
            if ( iLength >= 100 * L )
                return -1;
        }
    }
 
    private static int getKLength( int L, String ijk )
    {
        QuaternionWithSign actual = new QuaternionWithSign( Quaternion.ONE, false );
        
        int kLength = 0;
        while ( true )
        {
            char c = ijk.charAt( L - 1 - kLength % L );
            Quaternion qc = Quaternion.valueOf( String.valueOf( c ).toUpperCase() );
            QuaternionWithSign qws = new QuaternionWithSign( qc, false );
            actual = mul( qws, actual );
            
            kLength++;
            
            if ( actual.quaternion == Quaternion.K && actual.isMinus == false )
                return kLength;
            
            if ( kLength >= 100 * L )
                return -1;
        }
    }
    
    private static QuaternionWithSign getStringValue( String ijk )
    {
        QuaternionWithSign actual = new QuaternionWithSign( Quaternion.ONE, false );
        
        for ( char c : ijk.toCharArray() )
        {
            Quaternion qc = Quaternion.valueOf( String.valueOf( c ).toUpperCase() );
            QuaternionWithSign qws = new QuaternionWithSign( qc, false );
            actual = mul( actual, qws );
        }
        
        return actual;
    }
    
    private static QuaternionWithSign power( QuaternionWithSign q, int p )
    {
        QuaternionWithSign actual = new QuaternionWithSign( Quaternion.ONE, false );
        for ( int i = 0; i < p; i++ )
            actual = mul( actual, q );
        
        return actual;
    }
    
    private static QuaternionWithSign mul( QuaternionWithSign q1, QuaternionWithSign q2 )
    {
        Quaternion result = null;
        boolean mulBoolean = false;
        
        if ( q1.quaternion == Quaternion.ONE )
        {
            result = q2.quaternion;
            mulBoolean = false;
        }
        else if ( q2.quaternion == Quaternion.ONE )
        {
            result = q1.quaternion;
            mulBoolean = false;
        }
        else if ( q1.quaternion == q2.quaternion )
        {
            result = Quaternion.ONE;
            mulBoolean = true;
        }
        else if ( q1.quaternion == Quaternion.I && q2.quaternion == Quaternion.J )
        {
            result = Quaternion.K;
            mulBoolean = false;
        }
        else if ( q1.quaternion == Quaternion.I && q2.quaternion == Quaternion.K )
        {
            result = Quaternion.J;
            mulBoolean = true;
        }
        else if ( q1.quaternion == Quaternion.J && q2.quaternion == Quaternion.I )
        {
            result = Quaternion.K;
            mulBoolean = true;
        }
        else if ( q1.quaternion == Quaternion.J && q2.quaternion == Quaternion.K )
        {
            result = Quaternion.I;
            mulBoolean = false;
        }
        else if ( q1.quaternion == Quaternion.K && q2.quaternion == Quaternion.I )
        {
            result = Quaternion.J;
            mulBoolean = false;
        }
        else if ( q1.quaternion == Quaternion.K && q2.quaternion == Quaternion.J )
        {
            result = Quaternion.I;
            mulBoolean = true;
        }
        else
            throw new IllegalStateException();
        
        return new QuaternionWithSign( result, mulBoolean ^ q1.isMinus ^ q2.isMinus );
    }
    
    public static void main( String[] args ) throws Exception
    {
        File inputFile = new File( "inputfiles/qualification/P3/input.txt" );
        FileReader fr = new FileReader( inputFile );
        BufferedReader br = new BufferedReader( fr );
        
        int numOfTestCases = Integer.parseInt( br.readLine() );
        
        File outputFile = new File( "inputfiles/qualification/P3/output.txt" );
        outputFile.delete();
        outputFile.createNewFile();
        FileWriter fw = new FileWriter( outputFile );
        BufferedWriter bw = new BufferedWriter( fw );
        
        for ( int i = 0; i < numOfTestCases; i++ )
        {
            String[] data = br.readLine().split( " " );
            int L = Integer.parseInt( data[0] );
            long X = Long.parseLong( data[1] );
            
            String ijk = br.readLine();
            
            calculate( L, X, ijk, bw, i+1 );
        }
        
        bw.flush();
        fw.flush();
        
        bw.close();
        fw.close();
    }
 }
