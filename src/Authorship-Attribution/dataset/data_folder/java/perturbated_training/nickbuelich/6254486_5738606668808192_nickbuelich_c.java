import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 
 public class C {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter(new File("C.out"));
            int found = 0;
            StringBuilder ans = new StringBuilder();
            stuff: for(int mask=0;mask<1<<14;mask++){
                String fat = Long.toBinaryString(mask);
                while(fat.length()!=14)fat = "0"+fat;
                String cur = "1"+fat+"1";
                StringBuilder temp = new StringBuilder();
                for(int a=2;a<=10;a++){
                    long value = Long.parseLong(cur, a);
                    if(new BigInteger(""+value).isProbablePrime(100))continue stuff;
                }
                stuff2: for(int a=2;a<=10;a++){
                    long value = Long.parseLong(cur, a);
                    for(long b=2;b<=Math.sqrt(value);b++){
                        if(value%b==0){
                            temp.append(b+" ");
                            continue stuff2;
                        }
                    }
                }
                ans.append(cur+" "+temp+"\n");
                found++;
                if(found==50)break;
            }
            int t =1;
            System.out.printf("Case #%d:%n%s%n",t,ans);
            out.printf("Case #%d:%n%s%n",t,ans);
            System.out.println(found);
        out.close();
    }
 }
