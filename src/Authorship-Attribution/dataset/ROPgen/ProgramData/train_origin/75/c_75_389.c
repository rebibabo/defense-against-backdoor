/**
* @file		?????.cpp
* @author	??	1000012792
* @date		2010.11.24
*/

int main()
{
	int x[1000], y[1000],num[1000]={0},start,end, max=0, count, i=1, j;
	//num?????i???????startend???????????max????????
	
	cin >> x[0];
	start = x[0];
	
	while(cin.get()==',')		//??x??????????????start
	{
		cin >> x[i];			
		if(x[i]<start)
			start = x[i];
		i++;
	}

	count = i;					//count?????????
	
	cin >> y[0];
	end = y[0];

	i = 1;
	while(cin.get()==',')		//??y??????????????end
	{
		cin >> y[i];
		if(y[i]>end)
			end = y[i];
		i++;
	}

	for(i=start;i<end;i++)		//?start??????end???????????????num[i]
	{
		for(j=0;j<count;j++)
		{
			if(x[j]<=i&&i<y[j])
				num[i]++;
		}
		if(num[i]>max)			//?num???????max
			max = num[i];
	}
	cout << count << " " << max;	//??count?max
	return 0;
}


