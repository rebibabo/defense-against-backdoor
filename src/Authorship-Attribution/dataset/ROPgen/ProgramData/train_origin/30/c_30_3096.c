int main()
{
	int n;(0<n<100);//???????n(n<100)
	cin>>n;//??n
	int sum=0;//???7???????sum
         int k;
	int i;
	for(i=1;i<=n;i++)
	{
		if(i%7==0)//i??7??
			continue;
		if(i%10==7)//i?????7
			continue;
		k=i/10;
		if(k%10==7)//i?????7
			continue;
		sum=sum+i*i;
	}
	cout<<sum<<endl;//??sum
	return 0;
}

