/**
 * ???8.cpp
 * ????????
 * ??????
 * ???2010?11?14?
 */
int main()
{
    int a[12]={12,43,71,102,132,163,193,224,255,285,316,346};//????a????13???????????
    int b[12],i,w;//????b?????13?????,w????????????
    cin>>w;
    for(i=0;i<12;i++)
	{         
         b[i]=a[i]%7+w;    //????13?????
         if(b[i]>7)
		 {b[i]=b[i]-7;}
         if(b[i]==5)       //??13????????
		 {cout<<i+1<<endl;}
	}
return 0;
}