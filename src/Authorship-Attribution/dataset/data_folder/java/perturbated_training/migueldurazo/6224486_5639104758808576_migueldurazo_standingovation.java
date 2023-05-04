
 package codejamnew;
 
 
 public class StandingOvation {
 
     Integer[] audience;
 
     public StandingOvation(Integer[] audience) {
 
         this.audience = audience;
 
     }
     
     public StandingOvation(String line) {
 
         String[] splitted = line.split(" ");
         
         String audiencePart = splitted[1];
         audience = new Integer[audiencePart.length()];
         
         for( int i = 0 ; i < audiencePart.length() ; i++ ){
             
             audience[i] = Integer.parseInt( audiencePart.charAt(i)+"" );
             
         }
          
     }
 
     public int achieveStandingOvation() {
 
         int peopleStanding = 0;
         int invitedFriends = 0;
 
         for (int i = 0; i < audience.length; i++) {
 
             int shynessLevel = i;
             int people = audience[i];
             
             if( people == 0 ){
                 
                 continue;
                 
             }
 
             if (shynessLevel <= peopleStanding) {
 
                 peopleStanding += people;
 
             } else {
 
                 
                 
                 
                 
                 int neededFriends = shynessLevel-peopleStanding;
                 invitedFriends += neededFriends;
                 peopleStanding += (people+neededFriends);
                 
             }
 
         }
         
         return invitedFriends;
 
     }
 
     public static void main(String[] args) {
 
     }
 
 }
