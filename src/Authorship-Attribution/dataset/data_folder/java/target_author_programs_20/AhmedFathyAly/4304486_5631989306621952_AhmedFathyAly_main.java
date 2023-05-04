import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
 	
 	InputReader in;
 	StringTokenizer tok;
 	StringBuilder ans;
 	private String str;
 
 	
 
 	public static void main(String[] args) throws Exception
 	{
 		Main sol = new Main();
 		sol.begin();
 
 	}
 
 	private void begin() throws IOException
 	{
 		
 		boolean file = true;
 		if (file)
 			in = new InputReader(new FileInputStream("A-small-attempt0.in"));
 		else
 			in = new InputReader(System.in);
 		ans = new StringBuilder();
 
 		
 		int nCases = in.nextInt();
 		for (int cas = 1; cas <= nCases; cas++)
 		{
 			str = in.next();
 
 			ans.append("Case #" + cas + ": ");
 			ans.append(solve());
 			ans.append("\n");
 		}
 
 		
 		System.out.println("\nOutput: ");
 		System.out.println(ans.toString());
 		BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
 		out.write(ans.toString());
 		out.close();
 
 		
 		System.out.println("\nTest: ");
 	}
 
 	private String solve()
 	{
 		String result = str.charAt(0) + "";
 		for (int i = 1; i < str.length(); i++)
 			if (result.charAt(0) <= str.charAt(i))
 				result = str.charAt(i) + result;
 			else
 				result = result + str.charAt(i);
 		return result;
 
 	}
 
 }
 
 class InputReader
 {
 	BufferedReader reader;
 	StringTokenizer tok;
 
 	public InputReader(InputStream stream)
 	{
 		reader = new BufferedReader(new InputStreamReader(stream), 32768);
 		tok = new StringTokenizer("");
 	}
 
 	public String next()
 	{
 		while (!tok.hasMoreTokens())
 			try
 			{
 				tok = new StringTokenizer(reader.readLine());
 			} catch (IOException e)
 			{
 				e.printStackTrace();
 			}
 		return tok.nextToken();
 	}
 
 	public int nextInt()
 	{
 		return Integer.parseInt(next());
 	}
 }