int main()
{
	int n,t,m=0;                      //n?????????????,t??????????,m????????
	int a[500];                       //??a??????
	cin>>n;                           //??n
	while(n--)
	{
		cin>>t;                       //??????
		if(t%2==1)
		{
			a[m++]=t;                 //???????a
		}
	}
	for(int i=0;i<m-1;i++)            //???a??????
	{
		for(int j=i+1;j<m;j++)
		{
			if(a[i]>a[j])
			{
				int p=a[i];
				a[i]=a[j];
				a[j]=p;
			}
		}
	}
	cout<<a[0];                       //???????
	for(int k=1;k<m;k++)              //???????
	{
		cout<<','<<a[k];
	}
	return 0;
}