int main()
{
	int num,n=0,a,b,c;
	cin>>num;
	a=num%3;//a??3??????
	b=num%5;//b??5??????
	c=num%7;//C??7??????
	if (a==0&&b==0&&c==0)//???????????
		cout<<"3 5 7"<<endl;
	if (a==0&&b==0&&c!=0)//??7??
		cout<<"3 5"<<endl;
	if (a==0&&c==0&&b!=0)//??5??
		cout<<"3 7"<<endl;
	if (b==0&&c==0&&a!=0)//??3??
		cout<<"5 7"<<endl;
	if (a==0&&b!=0&&c!=0)//??5?7??
		cout<<"3"<<endl;
	if (a!=0&&b==0&&c!=0)//??3?7??
		cout<<"5"<<endl;
	if (a!=0&&b!=0&&c==0)//??3?5??
		cout<<"7"<<endl;
	if (a!=0&&b!=0&&c!=0)//??????
		cout<<"n"<<endl;
	return 0;
}

