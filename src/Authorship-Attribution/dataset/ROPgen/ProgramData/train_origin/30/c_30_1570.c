//********************************
//*?7???????    **
//*????? 1300012753 **
//*???2013.9.13  **
//********************************
int main()
{
	int n, y, t, sum = 0, a; // ??n??????, y???????????sum????t?????7??? 
	scanf("%d", &n);         // ??n              
	
	// ???????1???????????? 
	for (int i = 1; i <= n; i++)
	{
		t = i % 7;           // ????????7??? 
		
		// ??????0?????7?? 
		if (t != 0)      
		{
			y = i % 10;     // ??i?????y 
			a = i / 10;     // ?i?????a
            
            // ?????????7 
			if (y != 7 && a != 7)     
			{
				sum = sum + i * i;  // ?????????7????sum?? 
			}
		}
    }
	printf("%d", sum);      // ??sum 
	return 0;
}