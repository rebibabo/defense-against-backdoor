int fab(int num);                     //??fab??
int main()                            //?????
{
	int n,i;                          //??????n,i,n???????i?????
	int num;                          
	cin>>n;
	for(i=1;i<=n;i++)                 //??n?????????????
	{
	   cin>>num;
	   cout<<fab(num)<<endl;
	}
	
return 0;
}
int fab(int num)                     //??fab??
{
	if(num==1)                       //??????1
	{return 1;}
	if(num==2)                       //??????1
	{return 1;}
	if(num!=1&&num!=2)               //????
	{return fab(num-1)+fab(num-2);}


}