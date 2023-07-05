import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class djikstra {
 	public static void main(String[] args) throws IOException{
 		BufferedReader buf = new BufferedReader(new FileReader("djikstra.in"));
 		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("djikstra.out")));
 		int n = Integer.parseInt(buf.readLine());
 		for(int i = 1; i <= n; i++){
 			int a = -1;
 			int b = -1;
 			int c = -1;
 			String[] s = buf.readLine().split(" ");
 			int l = Integer.parseInt(s[0]);
 			int x = Integer.parseInt(s[1]);
 			String s2 = buf.readLine();
 			boolean negative = false;
 			String s3 = "1";
 			int loops = 0;
 			int co = 4;
 			String p = "";
 			boolean pcheck = true;
 			while(co > 0 && c == -1){
 				co--;
 				loops++;
 				
 				for(int j = 0; j < l; j++){
 					System.out.println(s3);
 					if(s3.equals("1")){
 						if(String.valueOf(s2.charAt(j)).equals("1")) {
 							continue;
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("i")) {
 							s3 = "i";
 							if(a == -1 && !negative) {
 								a = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("j")) {
 							s3 = "j";
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("k")) {
 							s3 = "k";
 							if(a > -1 && b == -1 && !negative){
 								b = j;
 								co = 4;
 							}
 						}
 					}
 					else if(s3.equals("i")){
 						if(String.valueOf(s2.charAt(j)).equals("1")) {
 							s3 = "i";
 							if(a == -1 && !negative) {
 								a = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("i")) {
 							s3 = "1";
 							if(negative) negative = false;
 							else negative = true;
 							if(b > -1 && c == -1 && negative) {
 								c = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("j")) {
 							s3 = "k";
 							if(a > -1 && b == -1 && !negative){
 								b = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("k")) {
 							s3 = "j";
 							if(negative) negative = false;
 							else negative = true;
 						}
 					}
 					else if(s3.equals("j")){
 						if(String.valueOf(s2.charAt(j)).equals("1")) {
 							continue;
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("i")) {
 							s3 = "k";
 							if(negative) negative = false;
 							else negative = true;
 							if(a > -1 && b == -1 && !negative){
 								b = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("j")) {
 							s3 = "1";
 							if(negative) negative = false;
 							else negative = true;
 							if(b > -1 && c == -1 && negative) {
 								c = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("k")) {
 							s3 = "i";
 							if(a == -1 && !negative) {
 								a = j;
 								co = 4;
 							}
 						}
 					}
 					else if(s3.equals("k")){
 						if(String.valueOf(s2.charAt(j)).equals("1")) {
 							continue;
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("i")) {
 							s3 = "j";
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("j")) {
 							s3 = "i";
 							if(negative) negative = false;
 							else negative = true;
 							if(a == -1 && !negative) {
 								a = j;
 								co = 4;
 							}
 						}
 						else if(String.valueOf(s2.charAt(j)).equals("k")) {
 							s3 = "1";
 							if(negative) negative = false;
 							else negative = true;
 							if(b > -1 && c == -1 && negative) {
 								c = j;
 								co = 4;
 							}
 						}
 					}
 				}	
 				if(pcheck) {
 					pcheck = false;
 					p = s3;
 				}
 			}
 			System.out.println(a + " " + b + " " + c);
 			boolean good = false;
 			if(co > 0){
 				if((!p.equals("1") && (x % 4 == 2)) || (p.equals("1") && negative && (x % 2 == 1) )){
 					if(x >= loops){
 						good = true;
 					}
 				}
 			}
 			if(good){
 				out.println("Case #" + i + ": YES");
 			} else out.println("Case #" + i + ": NO");
 		}
 		buf.close();
 		out.close();
 	}
 }
