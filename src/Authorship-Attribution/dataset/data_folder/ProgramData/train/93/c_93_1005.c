int op(int n,int i)//????,???????????
{
	if(i==1) cout<<" ";
	cout<<n;
	return 0;
}

int main()
{
	int n;//n???????
	int i=0;//i?????????????
	cin>>n;
	for (int k=3;k<=7;k+=2)//???????????k??
	{
		if (n%k==0) 
		{
			op(k,i);
			i=1;
		}
	}
	if (i==0) cout<<'n';//??????,??n
	return 0;
}