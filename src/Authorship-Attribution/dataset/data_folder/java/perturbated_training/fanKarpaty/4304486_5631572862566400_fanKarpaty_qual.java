package facebook;
 
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 
 public class Qual {
 
    private static BufferedReader br = null;
    private static int readInt() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return 0;
    }
    
    private static double readDouble() {
        try {
            return Double.parseDouble(br.readLine());
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return 0;
    }
    
    private static int[] readIntArr() {
        int[] ret = null;
        String[] tmp;
        try {
            String str = br.readLine();
            tmp = str.split(" ");
            ret = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++)
                ret[i] = Integer.parseInt(tmp[i]);
        } catch (NumberFormatException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return ret;
    }
    
    
    
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new FileReader(new File("input.txt")));
        System.setOut(new PrintStream(new File("output.txt")));
        int T = readInt();
        
        for (int ind = 1; ind<=T; ind++) {
            System.out.print("Case #" + ind + ": ");
            
            int N = readInt();
            int[] ar = readIntArr();
            for (int i = 0; i < N; i++)ar[i]--;
            boolean used[] = new boolean[N];
            int ans = 0;
            int pairMax = 0;
            int cycleMax = 0;
            int cyclePair = 0;
            int pair = 0;
            for (int i = 0; i < N; i++) {
                if (!used[i]) {
                    List<Integer> list = new LinkedList<Integer>();
                    list.add(i);
                    used[i] = true;
                    int j = ar[i];
                    while(!list.contains(j) && !used[j]) {
                        list.add(j);
                        used[j] = true;
                        j = ar[j];
                    }
                    if (!list.contains(j))continue;
                    if (list.size() == 2)pair++;
                    boolean removed = false;
                    while(list.get(0) != j){list.remove(0);removed = true;}
                    if (list.size() > 2 && list.size() > cycleMax) cycleMax = list.size();
                    if (list.size() == 2) {
                        int a = list.remove(0);
                        int b = list.remove(0);
                        int tmp = 2;
                        
                            boolean us[] = new boolean[N];
                            us[a] = true;
                            us[b] = true;
                            
                            Set<Integer> set = new HashSet<Integer>();
                            set.add(a);
                            while (true) {
                                boolean found = false;
                                Set<Integer> tSet = new HashSet<Integer>();
                                for (int ii = 0; ii < N; ii++) {
                                    
                                    if (!us[ii] && set.contains(ar[ii])) {
                                        us[ii] = true;
                                        found = true;
                                        tSet.add(ii);
                                    }
                                }
                                if (found)tmp++;
                                if (!found)break;
                                set.addAll(tSet);
                            }
                            
                            set = new HashSet<Integer>();
                            set.add(b);
                            while (true) {
                                boolean found = false;
                                Set<Integer> tSet = new HashSet<Integer>();
                                for (int ii = 0; ii < N; ii++) {
                                    
                                    if (!us[ii] && set.contains(ar[ii])) {
                                        us[ii] = true;
                                        found = true;
                                        tSet.add(ii);
                                    }
                                }
                                if(found)tmp++;
                                if (!found)break;
                                set.addAll(tSet);
                            }
                        
                        
                        
                          pairMax += tmp;
                        
                    } else {
                        
                    }
                }
            }
            
            
            
            ans = Math.max(cyclePair, pairMax);
            ans = Math.max(cycleMax, ans);
            System.out.println(ans);
            
        }
        
 
    }
 
 }
