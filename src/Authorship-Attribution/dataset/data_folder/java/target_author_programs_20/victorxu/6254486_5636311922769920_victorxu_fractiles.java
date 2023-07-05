import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 
 
 public class fractiles {
 	public static void main(String[] args) throws IOException{
 		BufferedReader buf = new BufferedReader(new FileReader("fractiles.in"));
 		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fractiles.out")));
 		int t = Integer.parseInt(buf.readLine());
 		for(int i = 0; i < t; i++){
 			String[] input = buf.readLine().split(" ");
 			int yay = Integer.parseInt(input[0]);
 			out.print("Case #"+(i+1)+": ");
 			for(int j = 1; j <= yay; j++){
 				out.print(j + " ");
 			}
 			out.println();
 		}
 		out.close();
 	}
 }
