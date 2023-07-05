import java.io.File;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.Scanner;
 
 
 public class A {
    static Node[] Tree;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("A.in"));
        PrintWriter out = new PrintWriter(new File("A.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int N = sc.nextInt();
            int D = sc.nextInt();
            
            int S0 = sc.nextInt();
            int AS = sc.nextInt();
            int CS = sc.nextInt();
            int RS = sc.nextInt();
            
            int M0 = sc.nextInt();
            int AM = sc.nextInt();
            int CM = sc.nextInt();
            int RM = sc.nextInt();
            
            int[] ID = new int[N];
            int[] SALARY = new int[N];
            
            
            ID[0] = M0;
            SALARY[0] = S0;
            for(int a=1;a<N;a++){
                SALARY[a] = (SALARY[a-1]*AS+CS)%RS;
                ID[a] = (ID[a-1]*AM + CM)%RM;
            }
            long ans  =0;
            for(int a=1;a<N;a++){
                ID[a] = ID[a]%a;
            }
            
        
        
            
            Tree = new Node[N];
            for(int a=0;a<N;a++){
                Tree[a] = new Node(ID[a],SALARY[a]);
            }
            for(int a=1;a<N;a++){
                Tree[ID[a]].K.add(a);
            }
            Tree[0].getKids();
            for(int min = 0; min < 10000; min++){
                ans = Math.max(ans,N-magic(0,min,min+D));
            }
            System.out.printf("Case #%d: %d%n",t,ans);
            out.printf("Case #%d: %d%n",t,ans);
        }
        
        out.close();
    }
    private static int magic(int i, int min, int max) {
        if(Tree[i].SAL<min||Tree[i].SAL>max){
            return 1+Tree[i].KIDS;
        }
        int ans = 0;
        for(int k : Tree[i].K){
            ans+=magic(k,min,max);
        }
        return ans;
    }
    static class Node{
        int SAL,PAR,KIDS;
        LinkedList<Integer> K;
        Node(int a, int b){
            PAR = a;
            SAL = b;
            KIDS = 0;
            K = new LinkedList<Integer>();
        }
        int getKids(){
            KIDS = 0;
            for(int k : K){
                KIDS += Tree[k].getKids()+1;
            }
            return KIDS;
        }
    }
 }
