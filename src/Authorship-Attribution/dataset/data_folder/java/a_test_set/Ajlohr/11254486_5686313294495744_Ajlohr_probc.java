package Round1B;
 
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class ProbC {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            int n =sc.nextInt();
            String[] sofara = new String[0];
            String[] sofarb= new String[0];
            for(int i=0;i<n;i++)
            {
                
                String first = sc.next();
                String last = sc.next();
                String[] newa = new String[i+1];
                String[] newb= new String[i+1];
                for(int j=0;j<i;j++)
                {
                    newa[j] = sofara[j];
                    newb[j] = sofarb[j];
                }
                newa[i] = first;
                newb[i] = last;
                int bestconflicts = numcheats(newa,newb);
                String[] bestnewa = newa.clone();
                String[] bestnewb = newb.clone();
                for(int j=i-1;j>=0;j--)
                {
                    String[] cura = newa.clone();
                    String[] curb = newb.clone();
                    for(int k=j;k<i;k++){
                    String swap = cura[k];
                    cura[k] = cura[k+1];
                    cura[k+1] = swap;
                    swap = curb[k];
                    curb[k] = curb[k+1];
                    curb[k+1] = swap;}
                    if(numcheats(cura,curb)>bestconflicts)
                    {
                        bestconflicts = numcheats(cura,curb);
                        bestnewa = cura.clone();
                        bestnewb = curb.clone();
                    }
                }
                sofara = bestnewa;
                sofarb = bestnewb;
            }
            
        System.out.println("Case #"+curcase+": "+numcheats(sofara,sofarb));     
        }
        
    }
    public static int numcheats(String[] a,String[] b)
    {
        HashSet<String> as = new HashSet<String>();
        HashSet<String> bs = new HashSet<String>();
        int ret = 0;
        for(int i=0;i<a.length;i++)
        {
            if((as.contains(a[i]))&&(bs.contains(b[i])))
            {
                ret++;
            }
            else
            {
                as.add(a[i]);bs.add(b[i]);
            }
        }
        return ret;
 
    }
 
 }
