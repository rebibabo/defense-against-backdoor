/************************************************
**authour:	???				*
**number:	1000012905			*
**descripition:	??2?N??			*
************************************************/
int	result[101] = {0};	//????????????0  

void	yiwei(int);		//???????????  

int main()
{
	int	n;
	result[100] = 1;	//?????????????1  
	cin >> n;

	//? n ???  
	for (int i = 0; i < n; i ++)
	{
		//??????????2  
		for (int j = 0; j < 101; j ++)
		{result[j] = result[j] * 2;}
		//??10???????????????????  
		if(i % 10 == 0)
		{yiwei(100);}
	}
	yiwei(100);	//????????????  

	//?????? 
	int	*p = result;
	while(*p == 0)
	{p ++;}

	//??????  
	for (;p < result + 101; p ++)
	{cout << *p;}
	cout << endl;

	return 0;
}
void	yiwei(int n)
{
	//?????????????????  
	if(n == 0)
	{return;}

	//??? n ????????10?????????????  
	if(result[n] >= 10)
	{
		int	k = result[n] % 10;
		result[n - 1] += (int)(result[n] / 10);
		result[n] = k;
	}

	//????????????????? 
	yiwei(n - 1);

	return;
}
