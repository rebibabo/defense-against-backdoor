int main()
{
	int m, i, j, k, t, p, q;
	cin >> m;
	for(i=3;i<=m/2;i=i+2)                //???3?????????
	{
		for(t=1,k=2;k*k<=i;k++)          //?????????????t????????????????
		{
			if(i%k==0)
			{
				t=0;
			    break;                   //??????t=0,????????????      
			}
		}
		if(t==1)                         //t!=0,???????
		{
			j=m-i;                       
			for(p=1,q=2;q*q<=j;q++)      //????????????????????????
			{
				if(j%q==0)
				{
					p=0;
				    break;
				}
			}
			if(p==1)
				cout << i <<" "<< j << endl;   //?????????????i??????????i??j,i??
		}
	}
	return 0;
}
