/*
??2?N??
Author:??
Number:1000012913
Date: 2010-12-15
*/

//?g_ans[i]?char???int

//?int???char

//??????????????
char g_ans[1000];

int main()
{

	//??????????
	int n;
	cin >> n;

	//????????????1
	memset(g_ans, 0,sizeof(g_ans));
	g_ans[0] = '1';

	//n??????,i?????
	int n_len, i;

	//n??2
	while (n--)
	{

		//?????????????????2
		n_len = strlen(g_ans);
		for (i = 0; i != n_len; i++)
			g_ans[i] = itoc(ctoi(i) * 2);

		//??????
		for (i = 0; i != n_len; i++)

			//?????
			if (g_ans[i] > '9')
			{

				//?????10
				g_ans[i] -= 10;

				//?????????????????
				if (g_ans[i + 1])

					//????1
					g_ans[i + 1]++;

				//??????????????0????'\0'?
				//????????1
				else
					g_ans[i + 1] = '1';
			}
	}

	//???????????????????????
	char *pt = &g_ans[strlen(g_ans) - 1];
	while (pt != &g_ans[-1])
		cout.put(*pt--);
	cout << endl;
	return 0;
}

