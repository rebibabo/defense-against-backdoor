package exo2;
 
 public class Exo extends Base {
 
 	public static void main(String[] argv) {
 		
 		
 		
 		
 		
 		
 		configSmall(4, true);
 		
 		
 		
 		
 		
 		try {
 			int testCaseCount = input().nextInt();
 			for(int i=1; i<=testCaseCount; ++i) {
 				
 				
 				
 				
 				
 				output().println("Case #" + i + ": " + implode(solveTestCase()));
 				
 				
 				
 				
 			}
 		}
 		finally {
 			done();
 		}
 	}
 	
 	
 	
 	
 	
 	private static String[] solveTestCase() {
 		
 		String team1 = input().next();
 		String team2 = input().next();
 		
 		int N = team1.length();
 		
 		StringBuilder sb1 = new StringBuilder();
 		StringBuilder sb2 = new StringBuilder();
 		
 		int diff = 0;
 		
 		int firstDiff = -1;
 		
 		for(int i=0; i<N; ++i) {
 			
 			
 			char c1 = team1.charAt(i);
 			char c2 = team2.charAt(i);
 			
 			if(c1 == '?' && c2 == '?') {
 				sb1.append(diff < 0 ? '9' : '0');
 				sb2.append(diff > 0 ? '9' : '0');
 			}
 			
 			else if(c1 == '?') {
 				sb1.append(diff == 0 ? c2 : diff < 0 ? '9' : '0');
 				sb2.append(c2);
 				
 			}
 			
 			else if(c2 == '?') {
 				sb1.append(c1);
 				sb2.append(diff == 0 ? c1 : diff > 0 ? '9' : '0');
 			}
 			
 			else {
 				sb1.append(c1);
 				sb2.append(c2);
 				if(diff==0) {
 					diff = Character.compare(c1, c2);
 					if(diff != 0) {
 						firstDiff = i;
 					}
 				}
 			}
 		}
 		
 		String res1 = sb1.toString();
 		String res2 = sb2.toString();
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		if(diff < 0) {
 			String altB = tryReduce(team2, team1, res2, res1, firstDiff);
 			
 			if(altB != null && Math.abs(Integer.parseInt(altB) - Integer.parseInt(res1)) <= Math.abs(Integer.parseInt(res1) - Integer.parseInt(res2))) {
 				res2 = altB;
 			}
 			else {
 				
 				String alt = tryIncrease(team1, team2, res1, res2, firstDiff);
 				
 				if(alt != null && Math.abs(Integer.parseInt(alt) - Integer.parseInt(res2)) < Math.abs(Integer.parseInt(res1) - Integer.parseInt(res2))) {
 					res1 = alt;
 				}
 			}
 		}
 		
 		else if(diff > 0 ) {
 			String altB = tryReduce(team1, team2, res1, res2, firstDiff);
 			
 			if(altB != null && Math.abs(Integer.parseInt(altB) - Integer.parseInt(res2)) <= Math.abs(Integer.parseInt(res1) - Integer.parseInt(res2))) {
 				res1 = altB;
 			}
 			
 			else {
 				String alt = tryIncrease(team2, team1, res2, res1, firstDiff);
 				if(alt != null && Math.abs(Integer.parseInt(alt) - Integer.parseInt(res1)) < Math.abs(Integer.parseInt(res1) - Integer.parseInt(res2))) {
 					res2 = alt;
 				}
 			}
 		}
 		
 		debug("IN[" + team1 + " " + team2 + "]  OUT[" + res1 + " " + res2 + "]");
 		
 		return new String[] { res1, res2 };
 	}
 	
 	
 	
 	private static String tryIncrease(String teamToChange, String otherTeam, String resToChange, String otherRes, int firstDiff) {
 		String alt1 = resToChange;
 		boolean succeed = false;
 		
 		for(int i=firstDiff-1; i>=0; --i) {
 			
 			if(teamToChange.charAt(i) == '?' && otherTeam.charAt(i)=='?') {
 				alt1 = subst(alt1, i, "1");
 				succeed = true;
 				break;
 			}
 			else if(teamToChange.charAt(i) == '?' && otherTeam.charAt(i)=='9') {
 				alt1 = subst(alt1, i, "0");
 			}
 			else if(teamToChange.charAt(i) == '?') {
 				alt1 = subst(alt1, i, next(otherTeam.charAt(i)));
 				succeed = true;
 				break;
 			}
 		}
 		
 		return succeed ? alt1 : null;
 	}
 	
 	private static String tryReduce(String teamToChange, String otherTeam, String resToChange, String otherRes, int firstDiff) {
 		String alt1 = resToChange;
 		boolean succeed = false;
 		
 		for(int i=firstDiff-1; i>=0; --i) {
 			
 			if(teamToChange.charAt(i) != '?') {
 				continue;
 			}
 			
 			if(otherTeam.charAt(i)=='?' || otherTeam.charAt(i)=='0') {
 				alt1 = subst(alt1, i, "9");
 			}
 			else {
 				alt1 = subst(alt1, i, prev(otherTeam.charAt(i)));
 				succeed = true;
 				break;
 			}
 		}
 		
 		return succeed ? alt1 : null;
 	}
 	
 	private static String next(char c) {
 		switch(c) {
 			case '0': return "1";
 			case '1': return "2";
 			case '2': return "3";
 			case '3': return "4";
 			case '4': return "5";
 			case '5': return "6";
 			case '6': return "7";
 			case '7': return "8";
 			case '8': return "9";
 			default:
 				return "ERROR";
 		}
 	}
 	
 	private static String prev(char c) {
 		switch(c) {
 			case '1': return "0";
 			case '2': return "1";
 			case '3': return "2";
 			case '4': return "3";
 			case '5': return "4";
 			case '6': return "5";
 			case '7': return "6";
 			case '8': return "7";
 			case '9': return "8";
 			default:
 				return "ERROR";
 		}
 	}
 	
 	private static String subst(String template, int index, String rep) {
 		return template.substring(0, index) + rep + template.substring(index+1);
 	}
 	
 	
 	
 }
