/*
 * hanshu_huisu_4.cpp
 * Name??????
 *  Created on: 2010-12-6
 *      Author: ??
 */

int M,N;//
int f(int m,int n){
	if(m==0) return 1;//??????0??????????1
	if(n==1) return 1;//??????1??????????????1
	if(n>m) return f(m,m);/*???????????????n-m????????????????*/
	if(n<=m) return f(m-n,n)+f(m,n-1);/*??????????????????????????????n????m??????????n????m-n???????????????????n?????m??????????n-1?????m????????*/
}
int main(){
	int t;
	cin>>t;//?????????t
	while(t>0){
	cin>>M>>N;//??????????????
	cout<<f(M,N)<<endl;//???????????
	t--;
	}
return 0;
}