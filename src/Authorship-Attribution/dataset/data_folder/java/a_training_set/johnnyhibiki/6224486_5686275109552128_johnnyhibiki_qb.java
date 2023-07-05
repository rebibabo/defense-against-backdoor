import java.util.*;
 
 public class QB {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            int n = sc.nextInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<n;i++) list.add(sc.nextInt());
            Collections.sort(list);
            
            ArrayList<Integer> orgList = new ArrayList<Integer>();
            orgList.addAll(list);
            
            int cnt = 0;
            int amax = list.get(n-1);
            int bmax = -1;
            while(true){
                amax--;
                if(amax<0) break;
                
                int max = list.get(list.size()-1);
                list.set(list.size()-1, max/2+max%2);
                list.add(max/2);
                Collections.sort(list);
                bmax = list.get(list.size()-1);
                if(bmax<amax) amax = bmax;
                cnt++;
            }
            int ans1 = cnt;
            
            list.clear();
            list.addAll(orgList);
            cnt = 0;
            amax = list.get(n-1);
            bmax = -1;
            while(true){
                amax--;
                if(amax<0) break;
                
                int max = list.get(list.size()-1);
                if(max==9){
                    list.set(list.size()-1, 6);
                    list.add(3);
                }else{
                    list.set(list.size()-1, max/2+max%2);
                    list.add(max/2);
                }
                Collections.sort(list);
                bmax = list.get(list.size()-1);
                if(bmax<amax) amax = bmax;
                cnt++;
            }
            int ans2 = cnt;
            
            int ans = -1;
            if(ans1>ans2){
                ans = ans2;
            }else{
                ans = ans1;
            }
            
            System.out.println("case #" + test + ": " + ans);
        }
        sc.close();
    }
 
 }
