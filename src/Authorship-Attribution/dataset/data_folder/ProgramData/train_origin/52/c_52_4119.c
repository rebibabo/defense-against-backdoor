/*
 * move.cpp
 * Author: ???
 * Created on: 2011-1-8
 * ?????????
 */
int main(){
	int *p = NULL,*q = NULL;//??????????
	int n,m,i;
	cin>>n>>m;//??n?m
	int a[n];
	for(i=0;i<n;i++)  cin>>a[i];//?????????
	p=a;
	q=a+n;//p?????????q??????????
	for(i=0;i<n-m;i++){
		*(q++)=*(p++);
	}//??n-m?????????
	p=a+n-m;
	for(i=0;i<n-1;i++){
		cout<<*(p++)<<" ";
	}//???n-m+1????????????
	cout<<*p<<endl;
	return 0;
}

