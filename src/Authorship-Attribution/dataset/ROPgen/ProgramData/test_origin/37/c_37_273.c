int main()
{
	char sentence[100000];			//sentence ?????
	int n, i, len, character[26];	//n ?????i ?????len ??????character ????????
	cin >> n;
	while (n--)
	{
		for (i = 0; i <= 25; i++)
			character[i] = 0;		//???????????
		cin >> sentence;
		len = strlen(sentence);
		for (i = 0; i <= len - 1; i++)				//????????????????????
			character[(int)(sentence[i] - 'a')]++;
		for (i = 0; i <= len - 1; i++)				//???????????????????
		{
			if (character[(int)(sentence[i] - 'a')] == 1)
			{
				cout << sentence[i] << endl;
				break;								//?????????????????????
			}
		}
		if (i == len)					//??????????? i ????????? len???no
			cout << "no" << endl;
	}
	return 0;
}