package exo1;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class Exo extends Base {
 
 	public static void main(String[] argv) {
 		
 		
 		
 		
 		
 		
 		configSmall(1, false);
 		
 		
 		
 		
 		
 		try {
 			int testCaseCount = input().nextInt();
 			for(int i=1; i<=testCaseCount; ++i) {
 				
 				
 				
 				
 				
 				output().println("Case #" + i + ": " + solveTestCase());
 				
 				
 				
 				
 			}
 			
 		}
 		finally {
 			done();
 		}
 	}
 	
 	
 	
 	
 	
 	private static String solveTestCase() {
 		
 		String word = input().next();
 		Map<Character, Integer> letters = new HashMap<>();
 		for(int i=0; i<word.length(); ++i) {
 			char c = word.charAt(i);
 			if(letters.get(c) == null) {
 				letters.put(c, 1);
 			}
 			else {
 				letters.put(c, letters.get(c) + 1);
 			}
 		}
 		
 		int[] digitCount = new int[10];
 		
 		digitCount[0] = find0(letters);
 		digitCount[6] = find6(letters);
 		digitCount[8] = find8(letters);
 		digitCount[2] = find2(letters);
 		digitCount[3] = find3(letters);
 		digitCount[4] = find4(letters);
 		digitCount[1] = find1(letters);
 		digitCount[5] = find5(letters);
 		digitCount[9] = find9(letters);
 		digitCount[7] = find7(letters);
 		
 		debug(implode(digitCount));
 		
 		StringBuilder sb = new StringBuilder();
 		
 		for(int d=0; d<10; ++d) {
 			for(int count=0; count<digitCount[d]; ++count) {
 				sb.append(d);
 			}
 		}
 		
 		return sb.toString();
 	}
 	
 	
 	private static final String[] DIGITS = new String[] {
 		"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"
 	};
 	
 	
 	private static int find0(Map<Character, Integer> letters) {
 		return doFind(letters, 0, 'Z');
 	}
 	
 	private static int find6(Map<Character, Integer> letters) {
 		return doFind(letters, 6, 'X');
 	}
 	
 	private static int find8(Map<Character, Integer> letters) {
 		return doFind(letters, 8, 'G');
 	}
 	
 	private static int find2(Map<Character, Integer> letters) {
 		return doFind(letters, 2, 'W');
 	}
 	
 	private static int find3(Map<Character, Integer> letters) {
 		return doFind(letters, 3, 'H');
 	}
 	
 	private static int find4(Map<Character, Integer> letters) {
 		return doFind(letters, 4, 'U');
 	}
 	
 	private static int find1(Map<Character, Integer> letters) {
 		return doFind(letters, 1, 'O');
 	}
 	
 	private static int find9(Map<Character, Integer> letters) {
 		return doFind(letters, 9, 'I');
 	}
 	
 	private static int find5(Map<Character, Integer> letters) {
 		return doFind(letters, 5, 'F');
 	}
 	
 	private static int find7(Map<Character, Integer> letters) {
 		return doFind(letters, 7, 'S');
 	}
 	
 	private static int doFind(Map<Character, Integer> letters, int target, char targetLetter) { 
 		Integer result = letters.get(targetLetter);
 		if(result == null || result==0) {
 			return 0;
 		}
 		
 		for(int k=0; k<DIGITS[target].length(); ++k) {
 			
 			char currentChar = DIGITS[target].charAt(k);
 			letters.put(currentChar, letters.get(currentChar) - result);
 			
 		}
 		
 		return result;
 	}
 	
 	
 	
 	
 }
