int main()
{
	int num,i,odd,even;//????num,????,?????????
	cin>>num;
	for(i=0;;i++)
	{
		if(num%2==0)//?????????2
		{ 
			even=num;
			num=num/2;
			cout<<even<<"/2="<<num<<endl;
		}
		if(num%2==1&&num!=1)//????????1???3+1
		{
			odd=num;
			num=num*3+1;
			cout<<odd<<"*3+1="<<num<<endl;
		}
		if(num==1)//???1  ??
		{	
			cout<<"End";
			break;
		}
	}
	return 0;
}
