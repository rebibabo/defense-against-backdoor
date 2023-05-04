import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class cake {
 	public static void main(String[] args) throws IOException{
 		BufferedReader buf = new BufferedReader(new FileReader("cake.in"));
 		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cake.out")));
 		int t = Integer.parseInt(buf.readLine());
 		for(int i = 0; i < t; i++) {
 			out.println("Case #" + (i + 1) + ":");
 			String[] s = buf.readLine().split(" ");
 			int r = Integer.parseInt(s[0]);
 			int c = Integer.parseInt(s[1]);
 			char[][] ans = new char[r][c];
 			char[][] input = new char[r][c];
 			boolean flb = false;
 			int fl = -1;
 			int flc = -1;
 			char[] aline = new char[c];
 			for(int j = 0; j < r; j++){
 				String line = buf.readLine();
 				for(int k = 0; k < c; k++){
 					input[j][k] = line.charAt(k);
 					if(!flb && input[j][k] != '?'){
 						flb = true;
 						fl = j;
 						flc = k;
 					}
 				}
 			}
 			char start = input[fl][flc];
 			for(int k = 0; k < c; k++){
 				if(input[fl][k] != '?') start = input[fl][k];
 				aline[k] = start;
 			}
 			for(int j = 0; j < r; j++){
 				boolean empty = true;
 				char first = '0';
 				for(int k = 0; k < c; k++){
 					if(input[j][k] != '?') {
 						empty = false;
 						first = input[j][k];
 						break;
 					}
 				}
 				if(!empty){
 					for(int k = 0; k < c; k++){
 						if(input[j][k] != '?') {
 							first = input[j][k];
 						}
 						aline[k] = first;
 					}
 				} 
 				for(int k = 0; k < c; k++){
 					ans[j][k] = aline[k];
 				}
 			}
 			for(int j = 0; j < r; j++){
 				for(int k = 0; k < c; k++){
 					out.print(ans[j][k]);
 				}
 				out.println();
 			}
 		}
 		out.close();
 	}
 }
