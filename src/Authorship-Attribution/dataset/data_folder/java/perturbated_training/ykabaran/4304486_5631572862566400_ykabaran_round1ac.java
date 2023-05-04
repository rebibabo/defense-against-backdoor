package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.util.Stack;
 
 
 public class Round1AC implements Problem {
 
   private int N;
   private int[] bffs;
   private int largestChain;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     N = Integer.parseInt(input.readLine());
     String line = input.readLine();
     String[] lineArgs = line.split(" ");
     bffs = new int[N];
     for (int i = 0; i < N; ++i) {
       bffs[i] = Integer.parseInt(lineArgs[i]);
     }
   }
   
   private int getBffOf(int n){
     return bffs[n] - 1;
   }
 
   private static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
     Set<Set<Integer>> sets = new HashSet<>();
     if (originalSet.isEmpty()) {
       sets.add(new HashSet<Integer>());
       return sets;
     }
     List<Integer> list = new ArrayList<>(originalSet);
     Integer head = list.get(0);
     Set<Integer> rest = new HashSet<>(list.subList(1, list.size()));
     for (Set<Integer> set : powerSet(rest)) {
       Set<Integer> newSet = new HashSet<>();
       newSet.add(head);
       newSet.addAll(set);
       sets.add(newSet);
       sets.add(set);
     }
     return sets;
   }
 
   private void permute(Set<Integer> items, Stack<Integer> permutation, int size) {
     if (permutation.size() == size) {
       onNewPermutationEncountered(permutation);
     }
 
     Integer[] availableItems = items.toArray(new Integer[0]);
     for (Integer i : availableItems) {
       permutation.push(i);
       items.remove(i);
       permute(items, permutation, size);
       items.add(permutation.pop());
     }
   }
   
   private boolean isValidOrdering(Integer[] order){
     int l = order.length;
     for(int i=0; i<l; ++i){
       int curr = order[i];
       int left = (i-1) % l;
       if(left < 0){
         left += l;
       }
       left = order[left];
       int right = (i+1) % l;
       right = order[right];
       int bff = getBffOf(curr);
       if(bff != left && bff != right){
         return false;
       }
     }
     return true;
   }
   
   private void onNewPermutationEncountered(Stack<Integer> permutation){
     Integer[] arr = permutation.toArray(new Integer[0]);
     if(isValidOrdering(arr)){
       int curr = arr.length;
       if(curr > largestChain){
         largestChain = curr;
       }
     }
   }
 
   @Override
   public void solve() {
     largestChain = 0;
     Set<Integer> originalSet = new HashSet<>();
     for(int i=0; i<N; ++i){
       originalSet.add(i);
     }
     Set<Set<Integer>> powerSets = powerSet(originalSet);
     for(Set<Integer> subSet : powerSets){
       permute(subSet, new Stack<Integer>(), subSet.size());
     }
   }
 
   @Override
   public String getSolution() {
     return Integer.toString(largestChain);
   }
 
 }
