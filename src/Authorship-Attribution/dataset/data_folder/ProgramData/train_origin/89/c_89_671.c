/*???1000012904_5.cpp
  ????????????
  ?????
  ???2010?12?10?
 */
int main()
{
	//???????????????count,?????????beasked,?????????answer?????i,??n,????sum
	int count[100000], beasked[100000], answer[100000], i, n,sum = 0;
	memset(count, 0, sizeof(count));
	cin >> n;
	for(i = 0; i < n * (n - 1); i++)
	{
		cin >> beasked[i] >> answer[i];
		//??0 0 ?????
		if( beasked[i] == 0 && answer[i] == 0)
			break;
		else
			count[answer[i]]++;//answer[i]???????????
		sum++;//?????1
	}
	int flag = 0;
	for(i = 0; i < n; i++)
	{
		int know = 0;//?????????????
		if(count[i] == n - 1 )//???????????
		{
			for(int j = 0; j < sum; j++)
			{
				if(beasked[j] == i)//????????????
					know++;//????????1
			}
			if(know == 0)//??????????
			{
				cout << i << endl;//??????????
				flag =1;//??????
			}
		}
	}	
	if(flag == 0)
		cout <<"NOT FOUND" << endl;//???????????NOT FOUND
	return 0;
}
	

