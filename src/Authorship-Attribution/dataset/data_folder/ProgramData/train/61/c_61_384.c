
int a[20]={1,1};					//???????????2?????????????2???
								//??????????a[2]?a[3]?????????
								//??a[2]?????a???????4??????????????????????????????
								//???????????????????????????????????????wrong answer?
								//???????????????????????????????????????????????
								//???????????????a(1 <= a <= 20)???????int a[20]={1,1};
int n,i,k,j;
int main()
{
	cin>>n;
	for(i=1;i<=n;i++)//???
	{
		cin>>k;

		if(k>2)
		{

		for(j=2;j<=k-1;j++)
		{
			a[j]=a[j-1]+a[j-2];//????
		}

		}
	cout<<a[k-1]<<endl;
	}
return 0;
}