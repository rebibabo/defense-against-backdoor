/*????
x1 = (-b + sqrt(b*b-4*a*c))/(2*a)
x2 = (-b - sqrt(b*b-4*a*c))/(2*a)
??????? ax2 + bx + c = 0 ?????a???0?

????
???????????n?
??n?????????a, b, c??????????????????ax2 + bx + c =0???

????
????n????????????
???????????x1=...;x2 = ...
????????????x1=x2=...
???????????x1=??+??i; x2=??-??i

???????????????5?????????????*/
int main()
{
	double q,p;
	int n,i;
	cin>>n;
	double a[n],b[n],c[n];
	for( i=1;i<=n;i++)
		cin>>a[i]>>b[i]>>c[i];
	for(i=1;i<=n;i++)
	{
		if(b[i]*b[i]-4*a[i]*c[i]>0)
		{
			p=-b[i]/(2*a[i]);
		    q=sqrt(b[i]*b[i]-4*a[i]*c[i])/(2*a[i]);
		    if(p==-0) p=0;
		    if(q==-0) q=0;
		    cout<<"x1="<<fixed<<setprecision(5)<<p+q<<";";
		    cout<<"x2="<<fixed<<setprecision(5)<<p-q<<endl;
		}
		if(b[i]*b[i]-4*a[i]*c[i]<0)
		{
			p=-b[i]/(2*a[i]);
			q=sqrt(-b[i]*b[i]+4*a[i]*c[i])/(2*a[i]);
			 if(p==-0) p=0;
					    if(q==-0) q=0;
			cout<<"x1="<<fixed<<setprecision(5)<<p<<"+"<<q<<"i"<<";";
		    cout<<"x2="<<fixed<<setprecision(5)<<p<<"-"<<q<<"i"<<endl;
		}
		if(b[i]*b[i]-4*a[i]*c[i]==0)
		{
			p=-b[i]/(2*a[i]);
			 if(p==-0) p=0;
			cout<<"x1=x2="<<fixed<<setprecision(5)<<p<<endl;;
		}

	}
	return 0;

}