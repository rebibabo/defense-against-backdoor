int main()
{
	int a,b,x,n,i,j = 0,k,h,m,num1 = 0;
	char str[100],num[100];
	cin >> a >> str >> b;
	n = strlen(str);
	for(i = 0; i < n; i++)
	{
		int y = 1;
		for(m = 0; m < n - 1 - i;m++)
		{	
			y = y * a;
		}
		if(str[i] <= 57)
			num1 = num1 + (str[i] - 48) * y;
		if((str[i] >= 65)&&(str[i] < 97))
			num1 = num1 + (str[i] - 55) * y;
		if(str[i] >= 97)
			num1 = num1 + (str[i] - 87) * y;
	}
	x = num1;
	do
	{
		num1 = num1 / b;
		j++;                        
	}while(num1 > 0);
    for(k = 0 ; k < j; k++)
    {   
	    if(x % b < 10)
			num[j - 1 - k] = x % b + 48;
		if(x % b >= 10)
			num[j - 1 - k] = x % b + 55;
		x = x / b;
	}
	for(h = 0; h < j ; h++)
		cout << num[h];
	return 0;
}