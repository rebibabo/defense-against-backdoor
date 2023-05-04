import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
 	public static void main(String[] args) throws IOException
 	{
 		Main main = new Main();
 		main.read();
 	}
 
 	
 	private StringBuilder ans;
 	private BufferedReader in;
 	private BufferedWriter out;
 	private StringTokenizer tok;
 
 	
 	static HashMap<String, Integer> toIndex;
 	static
 	{
 		toIndex = new HashMap<>();
 		toIndex.put("1", 0);
 		toIndex.put("i", 1);
 		toIndex.put("j", 2);
 		toIndex.put("k", 3);
 	}
 	static String table[][] = new String[][]
 	{
 	{ "1", "i", "j", "k" },
 	{ "i", "1", "k", "j" },
 	{ "j", "k", "1", "i" },
 	{ "k", "j", "i", "1" } };
 	static boolean sign[][] = new boolean[][]
 	{
 	{ true, true, true, true },
 	{ true, false, true, false },
 	{ true, false, false, true },
 	{ true, true, false, false } };
 	private int l;
 	private int x;
 	private String strOriginal;
 
 	private void read() throws IOException
 	{
 		
 		String inputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Input.in";
 		String outputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Output.txt";
 		in = new BufferedReader(new FileReader(inputFileName));
 		out = new BufferedWriter(new FileWriter(outputFileName));
 		ans = new StringBuilder();
 
 		
 		int nCases = Integer.parseInt(in.readLine());
 		for (int cas = 1; cas <= nCases; cas++)
 		{
 			
 			tok = new StringTokenizer(in.readLine());
 			l = Integer.parseInt(tok.nextToken());
 			x = Integer.parseInt(tok.nextToken());
 			strOriginal = in.readLine().trim();
 
 			
 			ans.append("Case #" + cas + ": " + solve() + "\n");
 
 		}
 
 		
 		System.out.print(ans.toString());
 		out.write(ans.toString());
 		out.flush();
 		out.close();
 	}
 
 	private String solve()
 	{
 		
 		StringBuilder strb = new StringBuilder();
 		for (int i = 0; i < x; i++)
 			strb.append(strOriginal);
 		String str = strb.toString();
 
 		
 		String req = "i";
 		String curr = "1";
 		boolean sign = true;
 		int count = 0;
 		for (int i = 0; i < str.length(); i++)
 		{
 			
 			String next = str.charAt(i) + "";
 			sign = multuplySign(sign, curr, next);
 			curr = multiply(curr, next);
 
 			
 			if (curr.equals(req) && sign == true)
 			{
 				req = getNext(curr);
 				curr = "1";
 				count++;
 			}
 		}
 		return curr.equals("1") && sign && count == 3 ? "YES" : "NO";
 	}
 
 	private String getNext(String x)
 	{
 		if (x.equals("i"))
 			return "j";
 		else if (x.equals("j"))
 			return "k";
 		else
 			return "END";
 	}
 
 	private boolean multuplySign(boolean oldSign, String x, String y)
 	{
 		int idx1 = toIndex.get(x);
 		int idx2 = toIndex.get(y);
 		boolean newSign = sign[idx1][idx2];
 		return !(oldSign ^ newSign);
 	}
 
 	private String multiply(String x, String y)
 	{
 		int idx1 = toIndex.get(x);
 		int idx2 = toIndex.get(y);
 		return table[idx1][idx2];
 	}
 
 }
