//???????????? ???  12858
int main()
{
	char s[101];                 //s???????
	int l,flagex,flagmul,i,k,t,j;   
	// l?s????
	// flagex : ?????????
	// flagmul: ???????????
	// t:????
	// i,j,k:????
	cin >> t;
	for (i = 1;i <= t;i++)
	{
		cin >> s;
		l =	strlen (s);
		flagex = 0;
		for (j = 0;j <= l-1;j++)
		{
			flagmul = 0;
			for (k = 0;k <= l-1;k++)
				if ((j != k) && (s[j] == s[k]))                     //?????????
				{
					flagmul = 1;
					break;
				}
			if (flagmul == 0)										//???????????????????1
			{
				cout << s[j] << endl;
				flagex = 1;
				break;
			}
		}
		if (flagex == 0)
			cout << "no" << endl;                             //???????no
	}
    return 0;
}
